/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletCajero;

import Modelo.CuentaModel;
import Modelo.TransaccionModel;
import Objeto.Cajero;
import Objeto.Cuenta;
import Objeto.Transaccion;
import ServletCliente.RealizarTransaccion;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author potz
 */
@WebServlet(name = "RealizarRetiro", urlPatterns = {"/RealizarRetiro"})
public class RealizarRetiro extends HttpServlet {

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
            out.println("<title>Servlet RealizarRetiro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RealizarRetiro at " + request.getContextPath() + "</h1>");
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
        CuentaModel cuenmodel = new CuentaModel();
        Cajero cajero =(Cajero) request.getSession().getAttribute("Cajero");
        TransaccionModel transaccion = new TransaccionModel();
        String monto = request.getParameter("monto").trim();
        Double dinero = Double.parseDouble(monto);
        Cuenta cuenta1 = (Cuenta) request.getSession().getAttribute("cuenta");
        
        
        if (cuenta1.getMonto() < dinero) {
                request.setAttribute("error", 1);
                request.getRequestDispatcher("/Cajero/IngresarRetiro.jsp").forward(request, response);
            } else {
                try {
                    dinero = cuenta1.getMonto() - dinero;
                    cuenta1.setMonto(dinero);
                    Transaccion transaccionDeposito = new Transaccion(Long.valueOf(0), Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), "DEBITO", Double.parseDouble(monto), cajero.getCodigo(), cuenta1.getCodigo());
                    transaccion.agregartransaccion2(transaccionDeposito);
                    cuenmodel.modificarMonto(cuenta1);                 
                    request.getRequestDispatcher("/Cajero/Mensaje.jsp?mensaje=Retiro realizado con exito").forward(request, response);
                } catch (SQLException ex) {
                    
                    Logger.getLogger(RealizarTransaccion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(RealizarTransaccion.class.getName()).log(Level.SEVERE, null, ex);
                }

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
