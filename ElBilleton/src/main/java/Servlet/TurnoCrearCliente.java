      /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.GerenteModel;
import Objeto.Gerente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author potz
 */
@WebServlet(name = "TurnoCrearCliente", urlPatterns = {"/TurnoCrearCliente"})
public class TurnoCrearCliente extends HttpServlet {

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
            out.println("<title>Servlet TurnoCrearCliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TurnoCrearCliente at " + request.getContextPath() + "</h1>");
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
        Gerente gerente = (Gerente) request.getSession().getAttribute("Gerente");
        GerenteModel model = new GerenteModel();
        if (model.estaEnTurno(gerente)) {
            request.getSession().setAttribute("Turno", "Turno Activo");
            request.setAttribute("enturno", 1);
            if (request.getParameter("pagina").equals("0")) {

                request.getRequestDispatcher("/Gerente/CrearCliente.jsp").forward(request, response);
            } else if (request.getParameter("pagina").equals("1")) {
                request.getRequestDispatcher("/Gerente/CrearCajero.jsp").forward(request, response);
            } else if (request.getParameter("pagina").equals("2")) {
                request.getRequestDispatcher("MostrarClientes").forward(request, response);
            }else if (request.getParameter("pagina").equals("3")) {
                request.getRequestDispatcher("/Gerente/CrearGerente.jsp").forward(request, response);
            }else if (request.getParameter("pagina").equals("4")) {
                request.getRequestDispatcher("MostrarClientes").forward(request, response);
            }else if (request.getParameter("pagina").equals("5")) {
                request.getRequestDispatcher("MostrarGerentes").forward(request, response);
            }else if (request.getParameter("pagina").equals("6")) {
                request.getRequestDispatcher("/Gerente/ModificarGerente.jsp").forward(request, response);
            }else if (request.getParameter("pagina").equals("7")) {
                request.getRequestDispatcher("ObtenerLImites").forward(request, response);
            }else if (request.getParameter("pagina").equals("8")) {
                request.getRequestDispatcher("ObtenerHistorial").forward(request, response);
            }else if (request.getParameter("pagina").equals("9")) {
                request.getRequestDispatcher("ObtenerReporte2").forward(request, response);
            }else if (request.getParameter("pagina").equals("10")) {
                request.getRequestDispatcher("ObtenerReporte3").forward(request, response);
            }else if (request.getParameter("pagina").equals("11")) {
                request.getRequestDispatcher("ObtenerReporte4").forward(request, response);
            }else if (request.getParameter("pagina").equals("12")) {
                request.getRequestDispatcher("ClientesReporte6").forward(request, response);
            }

        } else {
            request.getSession().setAttribute("Turno", "Turno Inactivo");
            request.setAttribute("enturno", 0);
            request.getRequestDispatcher("/Gerente/MenuGerente.jsp").forward(request, response);
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
