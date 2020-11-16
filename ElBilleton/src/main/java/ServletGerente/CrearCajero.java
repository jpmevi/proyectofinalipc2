/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletGerente;

import Modelo.CajeroModel;
import Modelo.ClienteModel;
import Modelo.Historial_CajeroModel;
import Modelo.Historial_ClienteModel;
import Objeto.Cajero;
import Objeto.Cliente;
import Objeto.ConstructorArchivo;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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
@WebServlet(name = "CrearCajero", urlPatterns = {"/CrearCajero"})
public class CrearCajero extends HttpServlet {

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
            out.println("<title>Servlet CrearCajero</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CrearCajero at " + request.getContextPath() + "</h1>");
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
            CajeroModel clienteModel = new CajeroModel();
            String nombre = request.getParameter("nombre").trim();
            String DPI = request.getParameter("dpi");
            String direccion = request.getParameter("direccion").trim();
            String sexo = request.getParameter("sexo");
            String turno = request.getParameter("turno");
            String password = request.getParameter("password");

            if (!nombre.trim().equals("") && !direccion.trim().equals("") ){
                Cajero c = new Cajero(Long.valueOf(0), nombre, sexo, DPI, direccion, sexo, password);
                Long codigo = clienteModel.agregarCajero(c);
                Historial_CajeroModel hist = new Historial_CajeroModel();
                hist.agregarCajeroSinCodigo(c, codigo);
                response.sendRedirect("Gerente/Mensaje.jsp?mensaje=Cajero creado con exito el codigo es: " + codigo);
            }else{
                response.sendRedirect("Gerente/Mensaje.jsp?mensaje=Ingreso un dato con espacio vacio, no se pudo crear el cajero ");
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
