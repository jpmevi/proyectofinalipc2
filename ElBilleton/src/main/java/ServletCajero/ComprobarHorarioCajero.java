/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletCajero;

import Objeto.Cajero;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author potz
 */
@WebServlet(name = "ComprobarHorarioCajero", urlPatterns = {"/ComprobarHorarioCajero"})
public class ComprobarHorarioCajero extends HttpServlet {

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
            out.println("<title>Servlet ComprobarHorarioCajero</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComprobarHorarioCajero at " + request.getContextPath() + "</h1>");
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
         Cajero gerente = (Cajero) request.getSession().getAttribute("Cajero");
        String turno=gerente.getTurno();
        LocalTime hora = LocalTime.now();
        LocalTime horamatutina1= LocalTime.of(6, 0);
        LocalTime horamatutina2= LocalTime.of(14, 30);
        LocalTime vespertino1= LocalTime.of(13, 0);
        LocalTime vespertino2= LocalTime.of(22, 0);
       
        if(hora.isAfter(horamatutina1) && hora.isBefore(horamatutina2) && turno.equalsIgnoreCase("Matutino")){
            request.getSession().setAttribute("Turno", "Turno Activo");
            request.getRequestDispatcher("/Cajero/MenuCajero.jsp").forward(request, response);
            
        }else if(hora.isAfter(vespertino1) && hora.isBefore(vespertino2) && turno.equalsIgnoreCase("Vespertino")){
            request.getSession().setAttribute("Turno", "Turno Activo");
             request.getRequestDispatcher("/Cajero/MenuCajero.jsp").forward(request, response);
        }else{
            request.getSession().setAttribute("Turno", "Turno Inactivo");
            request.getRequestDispatcher("/Cajero/MenuCajero.jsp").forward(request, response);
            
        }
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
        processRequest(request, response);
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
