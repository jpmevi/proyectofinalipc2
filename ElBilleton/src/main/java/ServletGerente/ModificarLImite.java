/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletGerente;

import Modelo.LimiteModel;
import Objeto.Limite;
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
@WebServlet(name = "ModificarLImite", urlPatterns = {"/ModificarLImite"})
public class ModificarLImite extends HttpServlet {

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
            out.println("<title>Servlet ModificarLImite</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModificarLImite at " + request.getContextPath() + "</h1>");
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
            LimiteModel clienteModel = new LimiteModel();
            Double limite1 = Double.parseDouble(request.getParameter("limite1"));
            Double limite2 = Double.parseDouble(request.getParameter("limite2"));
            if(limite1<limite2){
                Limite c = new Limite(limite1, limite2);
                Long codigo = clienteModel.modificarLimite(c);
                request.getSession().setAttribute("Limites", c);
                response.sendRedirect("Gerente/Mensaje.jsp?mensaje=Limites de reportes modificados con exito ");
            }else{
                response.sendRedirect("Gerente/Mensaje.jsp?mensaje=Error, el limite 1 tiene que ser menor que el limite 2 ");
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
