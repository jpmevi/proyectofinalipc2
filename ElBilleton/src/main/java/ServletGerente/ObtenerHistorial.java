/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletGerente;

import Modelo.Historial_CajeroModel;
import Modelo.Historial_ClienteModel;
import Modelo.Historial_GerenteModel;
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
@WebServlet(name = "ObtenerHistorial", urlPatterns = {"/ObtenerHistorial"})
public class ObtenerHistorial extends HttpServlet {

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
            out.println("<title>Servlet ObtenerHistorial</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ObtenerHistorial at " + request.getContextPath() + "</h1>");
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
        try {

            String entidad = request.getParameter("entidad");
            if (entidad == null) {
                entidad = "cliente";
            }
            if (entidad.equals("cliente")) {
                request.setAttribute("si", 1);
                Historial_ClienteModel cli = new Historial_ClienteModel();
                request.getSession().setAttribute("Historial", cli.obtenerHistorialClientes(""));
                request.getRequestDispatcher("/Gerente/HistorialDeCambiosEntidad.jsp").forward(request, response);
            } else if (entidad.equals("gerente")) {
                request.setAttribute("si", 0);
                Historial_GerenteModel cli = new Historial_GerenteModel();
                request.getSession().setAttribute("Historial", cli.obtenerCajeros(""));
                request.getRequestDispatcher("/Gerente/HistorialDeCambiosEntidad.jsp").forward(request, response);
            } else if (entidad.equals("cajero")) {
                request.setAttribute("si", 0);
                Historial_CajeroModel cli = new Historial_CajeroModel();
                
                request.getSession().setAttribute("Historial", cli.obtenerCajeros(""));
                request.getRequestDispatcher("/Gerente/HistorialDeCambiosEntidad.jsp").forward(request, response);
            }

        } catch (SQLException e) {

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
      try {
            String filtro = request.getParameter("filtro");
            if (filtro == null) {
                filtro = "";
            }
            String entidad = request.getParameter("entidad");
            if (entidad == null) {
                entidad = "cliente";
            }
            if (entidad.equals("cliente")) {
                 request.setAttribute("si", 1);
                Historial_ClienteModel cli = new Historial_ClienteModel();
                request.getSession().setAttribute("Historial", cli.obtenerHistorialClientes(filtro));
                request.getRequestDispatcher("/Gerente/HistorialDeCambiosEntidad.jsp").forward(request, response);
            } else if (entidad.equals("gerente")) {
                 request.setAttribute("si", 0);
                Historial_GerenteModel cli = new Historial_GerenteModel();
                request.getSession().setAttribute("Historial", cli.obtenerCajeros(filtro));
                request.getRequestDispatcher("/Gerente/HistorialDeCambiosEntidad.jsp").forward(request, response);
            } else if (entidad.equals("cajero")) {
                 request.setAttribute("si", 0);
                Historial_CajeroModel cli = new Historial_CajeroModel();
                request.getSession().setAttribute("Historial", cli.obtenerCajeros(filtro));
                request.getRequestDispatcher("/Gerente/HistorialDeCambiosEntidad.jsp").forward(request, response);
            }

        } catch (SQLException e) {

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
