<%-- 
    Document   : Ultimas15Transacciones
    Created on : Nov 17, 2020, 1:55:00 AM
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
        <title>Ver transacciones</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/fondo.png') center ; background-attachment: fixed;">
        <%@include file="header.jsp" %>
        <form action="BuscarCuenta" method="post" >
            <div class="cajatabla">
                <h2 >Ultimas transacciones de la cuenta ${Cuenta.getCodigo()}</h2>
                <table class="darkTable">
                    <tr>
                        <th class="text-center">Codigo de Transaccion</th>
                        <th class="text-center">Fecha</th>
                        <th class="text-center">Hora</th>
                        <th class="text-center">Tipo</th>
                        <th class="text-center">Monto</th>
                    </tr>
                    <c:forEach items="${Transaccion}" var="transaccion">
                        <tr>
                            <td class="text-center">${transaccion.getCodigo()}</td>
                            <td class="text-center">${transaccion.getFecha()}</td>
                            <td class="text-center">${transaccion.getHora()}</td>
                            <td class="text-center">${transaccion.getTipo()}</td>
                            <td class="text-center">${transaccion.getMonto()}</td>
                        </tr>
                    </c:forEach>
                </table>
                <a class="btn solid" href="${pageContext.request.contextPath}/Ultimas15transaccioens?cuenta=${Cuenta.getCodigo()}">Exportar reporte</a>
            </div>
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>

