<%-- 
    Document   : VerCuentasCliente
    Created on : Nov 15, 2020, 8:49:49 PM
    Author     : potz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css?3.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/textstyle.css?3.1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/select.css?3.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button.css?3.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tabla.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/searchbar.css?3.0">
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:400,500,700'>
        <title>Ver cuentas</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/fondo.png') center ; background-attachment: fixed;">
        <%@include file="header.jsp" %>
        <form action="ObtenerCuentas" method="post" >
            <div class="cajatabla">
                <c:if test="${pagina == 1}">
                    <h2 >Seleccione la cuenta para asociar ${Cliente.getNombre()}:</h2>
                </c:if>
                <c:if test="${pagina == 2}">
                    <h2 >Seleccione la cuenta para realizar transaccion: ${Cliente.getNombre()}</h2>
                </c:if>
                <section class="webdesigntuts-workshop" >
                    <div>
                        <input type="search" placeholder="Filtrar por codigo?" name="filtro">		    	
                        <button>Filtrar</button>
                    </div>
                </section>
                <table class="darkTable">
                    <tr>
                        <th class="text-center">Codigo</th>
                        <th class="text-center">Fecha Creacion</th>
                        <th class="text-center">Monto</th>
                        <th class="text-center">Codigo Cliente</th>
                    </tr>
                    <c:forEach items="${Cuentas}" var="cliente">
                        <tr>
                            <td class="text-center">${cliente.getCodigo()}</td>
                            <td class="text-center">${cliente.getFechaCreacion()}</td>
                            <td class="text-center">${cliente.getMonto()}</td>
                            <td class="text-center">${cliente.getCliente_codigo()}</td>
                            <c:if test="${pagina == 1}">
                                <td>
                                    <a class="btn solid" href="${pageContext.request.contextPath}/Cliente/IngresarCuenta.jsp?cuenta=${cliente.getCodigo()}&&cliente=${cliente.getCliente_codigo()}">Asociar</a>
                                </td>
                            </c:if>
                            <c:if test="${pagina == 2}">
                                <td>
                                    <a class="btn solid" href="${pageContext.request.contextPath}/BuscarCuentaAsociada?cuenta=${cliente.getCodigo()}&&cliente=${cliente.getCliente_codigo()}">Seleccionar</a>
                                </td>
                            </c:if>
                            <c:if test="${pagina == 3}">
                                <td>
                                    <a class="btn solid" href="${pageContext.request.contextPath}/VerSolicitudesPendientes?cuenta=${cliente.getCodigo()}">Ver solicitudes</a>
                                </td>
                            </c:if>
                            <c:if test="${pagina == 4}">
                                <td>
                                    <a class="btn solid" href="${pageContext.request.contextPath}/VerEstadoCuenta?cuenta=${cliente.getCodigo()}">Ver estado de cuenta</a>
                                </td>
                            </c:if>
                            <c:if test="${pagina == 5}">
                                <td>
                                    <a class="btn solid" href="${pageContext.request.contextPath}/Ultimas15transacciones?cuenta=${cliente.getCodigo()}">Ver ultimas transacciones</a>
                                </td>
                            </c:if>
                            <c:if test="${pagina == 6}">
                                <td>
                                    <a class="btn solid" href="${pageContext.request.contextPath}/SolicitudesRecibidas?cuenta=${cliente.getCodigo()}">Solicitudes Recibidas</a>
                                </td>
                            </c:if>
                            <c:if test="${pagina == 7}">
                                <td>
                                    <a class="btn solid" href="${pageContext.request.contextPath}/SolicitudesEnviadas?cuenta=${cliente.getCodigo()}">Solicitudes Enviadas</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
                <input type="hidden" name="pagina" value=<%=request.getParameter("pagina")%> > 
            </div>
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>