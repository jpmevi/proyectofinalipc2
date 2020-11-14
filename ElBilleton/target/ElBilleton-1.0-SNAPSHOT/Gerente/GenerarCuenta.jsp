<%-- 
    Document   : GenerarCuenta
    Created on : Nov 14, 2020, 2:57:43 AM
    Author     : potz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css?3.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/textstyle.css?3.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/select.css?3.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button.css?3.0">
         <link rel="stylesheet" href="${pageContext.request.contextPath}/css/searchbar.css">
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:400,500,700'>
        <title>Crear Cuenta</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/40.svg') no-repeat top center / cover;">
        <%@include file="header.jsp" %>
        <form action="CrearCuenta" method="post" >
            <div class="caja">
                <h2 >Cuenta para cliente: ${cliente.getNombre()}</h2>
                <div class="inputAnimate">
                    <input type="number" placeholder="Monto" name="monto" required>
                </div>
                <h4>Codigo Cliente:</h4>
                <div class="inputAnimate">
                    <input type="text" placeholder="${cliente.getCodigo()}" value="${cliente.getCodigo()}" name="codigo" readonly>
                </div>
                <button class="draw" type="submit" name="gen">Generar</button>
                 </div>           
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>
