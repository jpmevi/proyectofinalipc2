/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Conexion.Conexion;
import Encriptar.Encriptar;
import Modelo.CajeroModel;
import Modelo.ClienteModel;
import Modelo.GerenteModel;
import Objeto.Cajero;
import Objeto.Cliente;
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
@WebServlet(name = "Ingresar", urlPatterns = {"/Ingresar"})
public class Ingresar extends HttpServlet {

    //Creamos una instancia de nuestros modelos
    ClienteModel clientemodel = new ClienteModel();
    GerenteModel gerentemodel = new GerenteModel();
    CajeroModel cajeromodel = new CajeroModel();

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
            out.println("<title>Servlet Ingresar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Ingresar at " + request.getContextPath() + "</h1>");
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
            //Realizamos la conexion con la base de datos
            Conexion conexion = new Conexion();
            conexion.conexionDB();
            
            //Guardamos en variables nuestros datos
            int user = Integer.parseInt(request.getParameter("user"));
            String password = request.getParameter("password");
            String atributo = request.getParameter("atributo").toUpperCase();
            //Comparamos el atributo seleccionado
            switch (atributo) {
                case "CLIENTE": {
                    //Comprobamos si el usuario existe mandandole el usuario y la password
                    Cliente cliente = clientemodel.loginValidation(user, password);
                    if (cliente != null) {
                        request.getSession().setAttribute("Cliente", request.getParameter("user"));
                        response.sendRedirect("Cliente/MenuCliente.jsp");
                    } else {
                        //Mandamos un mensaje de error si no encontro los datos
                        request.setAttribute("success", 0);
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                    break;
                }
                case "CAJERO": {
                    //Comprobamos si el usuario existe mandandole el usuario y la password
                    Cajero cajero = cajeromodel.loginValidation(user, password);
                    if (cajero != null) {
                        request.getSession().setAttribute("Cajero", request.getParameter("user"));
                        response.sendRedirect("Cliente/MenuCliente.jsp");
                    }else{
                        //Mandamos un mensaje de error si no encontro los datos
                        request.setAttribute("success", 0);
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                    break;
                }
                case "GERENTE": {
                    //Comprobamos si el usuario existe mandandole el usuario y la password
                    Gerente gerente = gerentemodel.loginValidation(user, password);
                    if (gerente != null) {
                        request.getSession().setAttribute("Gerente", gerente);
                        response.sendRedirect(request.getContextPath()+"/ComprobarHorario");
                    }else{
                        //Mandamos un mensaje de error si no encontro los datos
                        request.setAttribute("success", 0);
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                    break;
                }
            }

        } catch (Exception e) {
            request.setAttribute("success", 0);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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
