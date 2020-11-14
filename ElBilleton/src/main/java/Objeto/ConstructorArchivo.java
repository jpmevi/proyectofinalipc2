/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objeto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author potz
 */
public class ConstructorArchivo {

    private String pathGeneral;

  
    public InputStream extraerArchivo(String nombreParametro, HttpServletRequest request) {
        try {
            Part filePart = request.getPart(nombreParametro);
            return filePart.getInputStream();
        } catch (IOException | ServletException e) {
            System.out.println("Error al intentar construir el archivo");
            return null;
        }
    }
    
    public void escribirArchivo(Part filePart){
        try {
            InputStream inputStream = filePart.getInputStream();
            OutputStream outputStream = new FileOutputStream(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
            inputStream.transferTo(outputStream);
            
            File file = Paths.get(filePart.getSubmittedFileName()).toFile();
            setPathGeneral(file.getAbsolutePath().replace("/" + file.getName(), "/"));
        } catch (IOException e) {
            System.out.println("Error al intentar construir el archivo");
        }
    }

    public String getPathGeneral() {
        return pathGeneral;
    }

    public void setPathGeneral(String pathGeneral) {
        this.pathGeneral = pathGeneral;
    }

}
