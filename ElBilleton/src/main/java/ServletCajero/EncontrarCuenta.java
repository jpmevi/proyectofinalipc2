/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletCajero;

import Modelo.ClienteModel;
import Modelo.CuentaModel;
import ServletCliente.BuscarCuenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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
@WebServlet(name = "EncontrarCuenta", urlPatterns = {"/EncontrarCuenta"})
public class EncontrarCuenta extends HttpServlet {

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
            out.println("<title>Servlet EncontrarCuenta</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EncontrarCuenta at " + request.getContextPath() + "</h1>");
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
         CuentaModel cuenta = new CuentaModel();
         ClienteModel clientemodel = new ClienteModel();
        Long cuentanum = Long.parseLong(request.getParameter("cuenta"));

        try {
            if (cuenta.buscarCuentaCodigo(cuentanum) != null) {
                request.getSession().setAttribute("cliente",clientemodel.obtenerCliente(cuenta.buscarCuentaCodigo(cuentanum).getCliente_codigo()));
                request.getSession().setAttribute("cuenta", cuenta.buscarCuentaCodigo(cuentanum));
                request.getRequestDispatcher("/Cajero/IngresarDeposito.jsp").forward(request, response);
            } else {
                request.setAttribute("no", 0);
                request.getRequestDispatcher("/Cajero/IngresarCuenta.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuscarCuenta.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(BuscarCuenta.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
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
