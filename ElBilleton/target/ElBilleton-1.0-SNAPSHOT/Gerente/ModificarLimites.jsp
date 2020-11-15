<%-- 
    Document   : ModificarLimites
    Created on : Nov 14, 2020, 7:26:30 PM
    Author     : potz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Modificar Limites</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/bgformulario.png') left ; background-attachment: fixed;">
        <%@include file="header.jsp" %>
        <form action="ModificarLImite" method="post" >
            <div class="caja">
                <h2>Modificar Limites:</h2>
                <h4 style="text-align: left;">Limite de reporte 2:</h4>
                <div class="inputAnimate">
                    <input type="number" min="0" value="${Limites.getLimiteReporte2()}" name="limite1" required="">
                </div>
                <h4 style="text-align: left;">Limite de reporte 3:</h4>
                <div class="inputAnimate">
                    <input type="number" min="0" value="${Limites.getLimiteReporte3()}" name="limite2" required>
                </div>

                <button class="draw" type="submit" name="gen">Modificar</button>
            </div>
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>
