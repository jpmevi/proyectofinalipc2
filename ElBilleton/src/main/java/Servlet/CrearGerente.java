/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.GerenteModel;
import Modelo.Historial_GerenteModel;
import Objeto.Gerente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author potz
 */
@WebServlet(name = "CrearGerente", urlPatterns = {"/CrearGerente"})
public class CrearGerente extends HttpServlet {

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
            out.println("<title>Servlet CrearGerente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CrearGerente at " + request.getContextPath() + "</h1>");
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
            GerenteModel clienteModel = new GerenteModel();
            String nombre = request.getParameter("nombre").trim();
            String DPI = request.getParameter("dpi");
            String direccion = request.getParameter("direccion").trim();
            String sexo = request.getParameter("sexo");
            String turno = request.getParameter("turno");
            String password = request.getParameter("password");

            if (!nombre.trim().equals("") && !direccion.trim().equals("")) {
                Gerente c = new Gerente(Long.valueOf(0), nombre, sexo, DPI, direccion, sexo, password);
                Long codigo = clienteModel.agregarGerenteCodigo(c);
                Historial_GerenteModel hist = new Historial_GerenteModel();
                hist.agregarHistorialGerenteSinCodigo(c, codigo);
                response.sendRedirect("Gerente/Mensaje.jsp?mensaje=Gerente creado con exito el codigo es: " + codigo);
            } else {
                response.sendRedirect("Gerente/Mensaje.jsp?mensaje=Ingreso un dato con espacio vacio, no se pudo crear el gerente ");
            }
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
