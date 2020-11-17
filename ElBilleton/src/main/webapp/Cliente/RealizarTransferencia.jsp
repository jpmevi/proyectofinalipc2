<%-- 
    Document   : RealizarTransferencia
    Created on : Nov 16, 2020, 4:32:06 PM
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
        <form action="RealizarTransaccion" method="post" >
            <div class="caja">
                <h2 >Cuenta que envia la transferencia:</h2>
                <div class="inputAnimate">
                    <input type="number" value="${Cuenta1.getCodigo()}" min="1" name="cuenta" readonly>
                </div> 
                <h4 >Propietario:</h4>
                <div class="inputAnimate">
                    <input type="text"  value="${Cliente.getNombre()}" name="cliente" readonly>
                </div>
                <h4>Monto disponible:</h4>
                <div class="inputAnimate">
                    <input type="number"  value="${Cuenta1.getMonto()}"  name="cliente" readonly>
                </div>
                <h2 >Cuenta que recibe la transferencia:</h2>
                <div class="inputAnimate">
                    <input type="number" value="${Cuenta2.getCodigo()}"  name="cuenta" readonly>
                </div> 
                <h4 >Propietario:</h4>
                <div class="inputAnimate">
                    <input type="text"  value="${Cliente2.getNombre()}" name="cliente" readonly>
                </div>
                <h2 >Ingrese el monto a transferir:</h2>
                <div class="inputAnimate">
                    <input type="number" min="1" step="0.1" name="monto" required="">
                </div> 
                <button class="draw" type="submit" name="gen">Transferir</button>
                <c:if test="${error == 1}">
                    <p id="error" style="color: red;">No posee el monto para realizar la transaccion</p>
                </c:if>
                <c:if test="${error == 2}">
                    <p id="error" style="color: red;">El monto no puede ser nulo o 0</p>
                </c:if>
            </div>           
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>
