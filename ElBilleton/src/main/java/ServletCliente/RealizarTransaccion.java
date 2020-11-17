/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletCliente;

import Modelo.CuentaModel;
import Modelo.TransaccionModel;
import Objeto.Cuenta;
import Objeto.Transaccion;
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
import javax.swing.JOptionPane;

/**
 *
 * @author potz
 */
@WebServlet(name = "RealizarTransaccion", urlPatterns = {"/RealizarTransaccion"})
public class RealizarTransaccion extends HttpServlet {

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
            out.println("<title>Servlet RealizarTransaccion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RealizarTransaccion at " + request.getContextPath() + "</h1>");
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
        TransaccionModel transaccion = new TransaccionModel();
        String monto = request.getParameter("monto").trim();
        if (monto != null && monto != "0.0" && monto != "") {
            Double dinero = Double.parseDouble(monto);
            Cuenta cuenta1 = (Cuenta) request.getSession().getAttribute("Cuenta1");
            Cuenta cuenta2 = (Cuenta) request.getSession().getAttribute("Cuenta2");
            if (cuenta1.getMonto() < dinero) {
                request.setAttribute("error", 1);
                request.getRequestDispatcher("/Cliente/RealizarTransferencia.jsp").forward(request, response);
            } else {
                try {
                    cuenta2.setMonto(dinero + cuenta2.getMonto());
                    dinero = cuenta1.getMonto() - dinero;
                    cuenta1.setMonto(dinero);
                    Transaccion transaccionRetiro=new Transaccion(Long.valueOf(0),Date.valueOf(LocalDate.now()),Time.valueOf(LocalTime.now()),"DEBITO",dinero,Long.valueOf(101),cuenta1.getCodigo());
                    Transaccion transaccionDeposito=new Transaccion(Long.valueOf(0),Date.valueOf(LocalDate.now()),Time.valueOf(LocalTime.now()),"CREDITO",dinero,Long.valueOf(101),cuenta2.getCodigo());
                    transaccion.agregartransaccion2(transaccionRetiro);
                    transaccion.agregartransaccion2(transaccionDeposito);
                    cuenmodel.modificarMonto(cuenta1);
                    cuenmodel.modificarMonto(cuenta2);
                    
                    request.getRequestDispatcher("/Cliente/Mensaje.jsp?mensaje=Transferencia realizada con exito").forward(request, response);
                } catch (SQLException ex) {
                    
                    Logger.getLogger(RealizarTransaccion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(RealizarTransaccion.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        else{
            request.setAttribute("error", 2);
            request.getRequestDispatcher("/Cliente/RealizarTransferencia.jsp").forward(request, response);
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
