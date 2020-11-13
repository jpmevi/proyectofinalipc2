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
                            <a href="MenuGerente.jsp">Home</a>
                        </li>
                        <li class="nav-link" style="--i: .6s">
                            <a href="#">Crear<i class="fas fa-caret-down"></i></a>
                            <div class="dropdown">
                                <ul>
                                    <li class="dropdown-link">
                                        <a href="">Registrar Cliente</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="ModificarExamen.jsp">Registrar Cajero</a>
                                    </li>
                                     <li class="dropdown-link">
                                        <a href="ModificarExamen.jsp">Registrar Gerente</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="ModificarExamen.jsp">Crear Cuenta</a>
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
                                        <a href="ModificarAdministrador.jsp">Modificar Administrador</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="ModificarLaboratorista.jsp">Modificar Laboratorista</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="ModificarMedico.jsp">Modificar Medico</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="ModificarPaciente.jsp">Modificar Paciente</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="ModificarConsulta.jsp">Modificar Consulta</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="AgregarEspecialidad.jsp">Agregar Especialidad</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-link" style="--i: .6s">
                            <a href="#">Reportes<i class="fas fa-caret-down"></i></a>
                            <div class="dropdown">
                                <ul>
                                    <li class="dropdown-link">
                                        <a href="MedicosMasInformes.jsp">Medicos con mas informes</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="IngresosMedico.jsp">Medicos mas Ingresos</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="MedicosMenosCitas.jsp">Medicos menos Citas</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="ExamenesMasDemandados.jsp">Examenes mas demandados</a>
                                    </li>
                                    <li class="dropdown-link">
                                        <a href="MedicosMasExamenes.jsp">Medicos con mas examenes</a>
                                    </li>
                                     <li class="dropdown-link">
                                         <a href="IngresosPaciente.jsp">Ingresos de un paciente</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>

                <div class="log-sign" style="--i: 1.8s">
                    <a href="../index.jsp" class="btn solid">Cerrar Sesion</a>
                </div>
            </div>

            <div class="hamburger-menu-container">
                <div class="hamburger-menu">
                    <div></div>
                </div>
            </div>
        </div>
    </header>
    </body>
</html>

