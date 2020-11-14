/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.ClienteModel;
import Modelo.CuentaModel;
import Modelo.Historial_ClienteModel;
import Objeto.Cliente;
import Objeto.ConstructorArchivo;
import Objeto.Cuenta;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
@WebServlet(name = "ModificarCliente", urlPatterns = {"/ModificarCliente"})
@MultipartConfig
public class ModificarCliente extends HttpServlet {

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
            out.println("<title>Servlet ModificarCliente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModificarCliente at " + request.getContextPath() + "</h1>");
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
       try {
           
            Long cliente =Long.valueOf( request.getParameter("codigo"));
            
            ClienteModel clienteModel = new ClienteModel();
            ConstructorArchivo generadorArchivo = new ConstructorArchivo();
            String nombre = request.getParameter("nombre");
            Date fecha_nacimiento = Date.valueOf((String) request.getParameter("fecha"));
            String DPI = request.getParameter("dpi");
            String direccion = request.getParameter("direccion");
            String sexo = request.getParameter("sexo");
            String password = request.getParameter("password");
            InputStream archivo = InputStream.nullInputStream();
            try {
                archivo = generadorArchivo.extraerArchivo("file", request);
            } catch (Exception e) {

            }
            
            
            
            Part archivos = request.getPart("file");

            if(archivos!=null && archivos.getSize()>0){
               archivo = generadorArchivo.extraerArchivo("file", request);
                Cliente c = new Cliente(cliente, nombre, DPI, sexo, password, direccion, fecha_nacimiento, archivo);
                Long codigo = clienteModel.modificarCliente(c);
                 Historial_ClienteModel hist = new Historial_ClienteModel();
            hist.agregarHistorialClienteSinCodigo(c, cliente);     
            }else{
                Cliente c = new Cliente(cliente, nombre, DPI, sexo, password, direccion, fecha_nacimiento, clienteModel.obtenerDPI(cliente));
                Long codigo = clienteModel.modificarCliente(c);
                 Historial_ClienteModel hist = new Historial_ClienteModel();
            hist.agregarHistorialClienteSinCodigo(c, cliente);     
            }
            

                  

           request.getRequestDispatcher("FiltroCliente").forward(request, response);
        } catch (SQLException E) {

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
