<%-- 
    Document   : header
    Created on : Oct 2, 2020, 11:38:25 PM
    Author     : potz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
        <div class="container">
            <input type="checkbox" name="" id="check">
            <div class="logo-container">
                <h3 class="logo"><span>${Turno}</span></h3>
            </div>
            <div class="nav-btn">
                <div class="nav-links">
                    <ul>
                        <li class="nav-link" style="--i: .6s">
                            <a href="${pageContext.request.contextPath}/Cajero/MenuCajero.jsp">Home</a>
                        </li>
                        <li class="nav-link" style="--i: .6s">
                            <a href="#">Transaccion<i class="fas fa-caret-down"></i></a>
                            <div class="dropdown">
                                <ul>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoAccionCajero?pagina=0">Realizar Deposito</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoAccionCajero?pagina=1">Realizar Retiro</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        
              
                      
                        <li class="nav-link" style="--i: .6s">
                            <a href="#">Reportes<i class="fas fa-caret-down"></i></a>
                            <div class="dropdown">
                                <ul>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/ObtenerCuentas?codigo=${Cliente.getCodigo()}&&pagina=5">Ultimas 15 transacciones</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/Cliente/Fecha.jsp">Ver cuenta con mas dinero</a>
                                    </li>
                                    
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>

                <div class="log-sign" style="--i: 1.8s">
                    <a href="${pageContext.request.contextPath}/Logout" class="btn solid">Cerrar Sesion</a>
                </div>
            </div>

            <div class="hamburger-menu-container">
                <div class="hamburger-menu">
                    <div></div>
                </div>
            </div>
        </div>
    </header>
                                    <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

            if (session.getAttribute("Gerente") == null && session.getAttribute("Cajero") == null&&session.getAttribute("Cliente") == null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        %>
    </body>
</html>

