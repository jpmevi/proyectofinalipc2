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
                            <a href="${pageContext.request.contextPath}/Gerente/MenuGerente.jsp">Home</a>
                        </li>
                        <li class="nav-link" style="--i: .6s">
                            <a href="#">Crear<i class="fas fa-caret-down"></i></a>
                            <div class="dropdown">
                                <ul>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=0">Registrar Cliente</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=1">Registrar Cajero</a>
                                    </li>
                                     <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=3">Registrar Gerente</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=2">Crear Cuenta</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-link" style="--i: .6s">

                            <a href="#">Cargar<i class="fas fa-caret-down"></i></a>
                            <div class="dropdown">
                                <ul>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCargarArchivo">Archivos</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="CrearMedico.jsp">Crear Medico</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="CrearLaboratorista.jsp">Crear Laboratorista<i class="fas fa-caret-down"></i></a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="CrearAdministrador.jsp">Crear Administrador</a>
                                    </li>
                                    <div class="arrow"></div>
                                </ul>
                            </div>
                        </li>
              
                        <li class="nav-link" style="--i: .6s">
                            <a href="#">Modificar<i class="fas fa-caret-down"></i></a>
                            <div class="dropdown">
                                <ul>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=4">Modificar Cliente</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=5">Modificar Cajero</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=6">Modificar Gerente</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=7">Modificar Limite</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-link" style="--i: .6s">
                            <a href="#">Reportes<i class="fas fa-caret-down"></i></a>
                            <div class="dropdown">
                                <ul>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=8">Historial de entidad</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=9">Ver clientes con transacciones mayores</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=10">Ver clientes con transacciones sumadas</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=11">10 clientes con mas dinero en cuentas</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=12">Clientes que no han realizado transacciones</a>
                                    </li>
                                     <li class="dropdown-link">
                                         <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=13">Historial de transacciones por cliente</a>
                                    </li>
                                    <li class="dropdown-link">
                                         <a href="${pageContext.request.contextPath}/TurnoCrearCliente?pagina=14">Cajero con mas transacciones</a>
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

