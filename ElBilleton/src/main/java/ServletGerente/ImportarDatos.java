/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletGerente;

import CargaArchivo.DividirEtiquetas;
import CargaArchivo.GerenteArchivo;
import Conexion.Conexion;
import Modelo.GerenteModel;
import Objeto.ConstructorArchivo;
import Objeto.Gerente;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

/**
 *
 * @author potz
 */
@WebServlet(name = "ImportarDatos", urlPatterns = {"/ImportarDatos"})
@MultipartConfig
public class ImportarDatos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ImportarDatos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImportarDatos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConstructorArchivo constructor = new ConstructorArchivo();
        try {
            String archivoXML="";

            ArrayList<Part> parts = new ArrayList<>();
            ArrayList<Part> partsMessy = new ArrayList<>(request.getParts());
            for (int i = 0; i < partsMessy.size(); i++) {
                if (partsMessy.get(i).getSubmittedFileName() != null) {
                    if (partsMessy.get(i).getSubmittedFileName().endsWith(".xml")) {
                        archivoXML = partsMessy.get(i).getSubmittedFileName();
                    }
                    parts.add(partsMessy.get(i));
                }
            }

            for (int i = 0; i < parts.size(); i++) {
                constructor.escribirArchivo(parts.get(i));
            }

            File file = new File(constructor.getPathGeneral()+archivoXML);
            Conexion conexion = new Conexion();
             
            DividirEtiquetas ar = new DividirEtiquetas();
            ar.dividirEtiquetas(file,constructor.getPathGeneral());


        } catch (Exception e) {
        }finally{
            request.getRequestDispatcher("/Gerente/MenuGerente.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
