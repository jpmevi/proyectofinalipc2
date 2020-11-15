<%-- 
    Document   : TransaccionesReporte2
    Created on : Nov 15, 2020, 2:43:40 AM
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
        <title>Ver transacciones reporte 2</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/fondo.png') center ; background-attachment: fixed;">
        <%@include file="header.jsp" %>
        <form >
            <div class="cajatabla">
                <h2 >Transacciones del cliente con codigo: ${codigo}</h2>
                <table class="darkTable">
                    <tr>
                        <th class="text-center">Codigo</th>
                        <th class="text-center">Fecha</th>
                        <th class="text-center">Hora</th>
                        <th class="text-center">Tipo</th>
                        <th class="text-center">Monto</th>
                        <th class="text-center">Cuenta</th>
                        <th class="text-center">Codigo cajero</th>
                    </tr>
                    <c:forEach items="${Transaccion}" var="cliente">
                        <tr>
                            <td class="text-center">${cliente.getCodigo()}</td>
                            <td class="text-center">${cliente.getFecha()}</td>
                            <td class="text-center">${cliente.getHora()}</td>
                            <td class="text-center">${cliente.getTipo()}</td>
                            <td class="text-center">${cliente.getMonto()}</td>
                            <td class="text-center">${cliente.getCuenta_codigo()}</td>
                            <td class="text-center">${cliente.getCajero_codigo()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>
