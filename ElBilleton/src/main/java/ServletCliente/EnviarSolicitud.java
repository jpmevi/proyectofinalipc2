/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletCliente;

import Modelo.SolicitudModel;
import Objeto.Solicitud;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author potz
 */
@WebServlet(name = "EnviarSolicitud", urlPatterns = {"/EnviarSolicitud"})
public class EnviarSolicitud extends HttpServlet {

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
            out.println("<title>Servlet EnviarSolicitud</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EnviarSolicitud at " + request.getContextPath() + "</h1>");
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
            Long cuentaenvia = Long.parseLong(request.getParameter("cuenta"));
            Long cuentarecibe = Long.parseLong(request.getParameter("cuenta2"));
            SolicitudModel solicitudmodel = new SolicitudModel();

            if (solicitudmodel.comprobarSolicitud(cuentaenvia, cuentarecibe) <= 3) {
                Solicitud solicitud = new Solicitud(0, Date.valueOf(LocalDate.now()), "Pendiente", cuentaenvia, cuentarecibe);
                solicitudmodel.crearSolicitud(solicitud);
                response.sendRedirect("Gerente/Mensaje.jsp?mensaje=Solicitud enviada con exito a la cuenta: " + cuentarecibe);
            }else{
                 response.sendRedirect("Gerente/Mensaje.jsp?mensaje=Ya supero la cantidad maxima de solicitudes con esa cuenta");
            }

        } catch (SQLException ex) {
            Logger.getLogger(EnviarSolicitud.class.getName()).log(Level.SEVERE, null, ex);
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
