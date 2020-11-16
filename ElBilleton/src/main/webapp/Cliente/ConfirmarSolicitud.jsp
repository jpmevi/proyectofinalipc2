<%-- 
    Document   : AceptarAsociar
    Created on : Nov 15, 2020, 9:45:50 PM
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
         <link rel="stylesheet" href="${pageContext.request.contextPath}/css/searchbar.css">
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:400,500,700'>
        <title>Buscar Cuenta</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/bgformulario.png') left ; background-attachment: fixed;">
        <%@include file="header.jsp" %>
        <form action="EnviarSolicitud" method="post" >
            <div class="caja">
                 <h2 >Cuenta que envia la solicitud:</h2>
                <div class="inputAnimate">
                    <input type="number" placeholder="${cuenta}"value="${cuenta}" min="1" name="cuenta" readonly>
                </div> 
                 <h2 >Propietario:</h2>
                <div class="inputAnimate">
                    <input type="number" placeholder="${cliente}" value="${cliente}" min="1" name="cliente" readonly>
                </div>
                <h2 >Cuenta que recibira la solicitud:</h2>
                <div class="inputAnimate">
                    <input type="number" placeholder="Cuenta" value="${Cuentaa.getCodigo()}" min="1" name="cuenta2" required>
                </div>
                 <h2 >Propietario:</h2>
                <div class="inputAnimate">
                    <input type="number" placeholder="Cuenta" value="${Cuentaa.getCliente_codigo()}" min="1" name="cliente2" required>
                </div>
                <button class="draw" type="submit" name="gen">Enviar solicitud</button>
                 </div>           
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>
