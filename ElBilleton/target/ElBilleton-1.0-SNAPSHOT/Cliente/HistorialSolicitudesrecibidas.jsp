<%-- 
    Document   : HistorialSolicitudesrecibidas
    Created on : Nov 17, 2020, 4:34:56 AM
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
        <title>Ver solicitudes</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/fondo.png') center ; background-attachment: fixed;">
        <%@include file="header.jsp" %>
        <form action="ObtenerCuentas" method="post" >
            <div class="cajatabla">
                <table class="darkTable">
                     <tr>
                        <th >Codigo  </th>
                        <th >Fecha  </th>
                        <th >Estado  </th>
                        <th >Codigo de la cuenta emisora  </th>
                        <th >Codigo de la cuenta receptora  </th>
                    </tr>
                    <c:forEach items="${Solicitud}" var="solicitud">
                        <tr>
                            <td>${solicitud.getCodigo()}</td>
                            <td>${solicitud.getFecha()}</td>
                            <td>${solicitud.getEstado()}</td>
                            <td>${solicitud.getCuentaEnvio()}</td>
                             <td>${solicitud.getCuentaRecibe()}</td>
                        </tr>
                    </c:forEach>
                </table>
                <a class="btn solid" href="${pageContext.request.contextPath}/Historialrecibidas?cliente=${Cliente.getCodigo()}">Exportar</a>
            </div>
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>
