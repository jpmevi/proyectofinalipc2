<%-- 
    Document   : Reporte7
    Created on : Nov 15, 2020, 9:19:04 AM
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
        <title>Ver cajero</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/fondo.png') center ; background-attachment: fixed;">
        <%@include file="header.jsp" %>
        <form action="MostrarGerentes" method="post" >
            <div class="cajatabla">
                <h2 >Ver Cajero con mas transacciones</h2>
                <table class="darkTable">
                    <tr>
                        <th class="text-center">Codigo</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">Turno</th>
                        <th class="text-center">Dpi</th>
                        <th class="text-center">Direccion</th>
                        <th class="text-center">Sexo</th>
                        <th class="text-center">Transacciones</th>
                    </tr>
                    <c:forEach items="${Cajeros}" var="gerente">
                        <tr>
                            <td class="text-center">${gerente.getCodigo()}</td>
                            <td class="text-center">${gerente.getNombre()}</td>
                            <td class="text-center">${gerente.getTurno()}</td>
                            <td class="text-center">${gerente.getDPI()}</td>
                            <td class="text-center">${gerente.getDireccion()}</td>
                            <td class="text-center">${gerente.getSexo()}</td>
                            <td class="text-center">${gerente.getPassword()}</td>
                        </tr>
                    </c:forEach>
                </table>
                <a class="btn solid" href="${pageContext.request.contextPath}/Cajeromastransacciones?fecha1=${fecha1}&&fecha2=${fecha2}">Exportar reporte</a>

            </div>

        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>
