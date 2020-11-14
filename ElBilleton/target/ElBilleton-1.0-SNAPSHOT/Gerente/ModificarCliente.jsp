<%-- 
    Document   : ModificarCliente
    Created on : Nov 14, 2020, 4:54:29 AM
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
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:400,500,700'>
        <title>Crear Cliente</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/bg.jpg') no-repeat top center / cover;">
        <%@include file="header.jsp" %>
        <form action="ModificarCliente" method="post" enctype="multipart/form-data">
            <div class="caja">
                <h2 >Modificar Cliente:</h2>
                <h4 style="text-align: left;" >Codigo:</h4>
                <div class="inputAnimate">
                    <input type="number" value="${cliente.getCodigo()}" name="codigo" readonly>
                </div>
                <h4 style="text-align: left;" >Nombre:</h4>
                <div class="inputAnimate">
                    <input type="text" value="${cliente.getNombre()}" name="nombre" required>
                </div>
                <h4 style="text-align: left;" >Dpi:</h4>
                <div class="inputAnimate">
                    <input type="text"  value="${cliente.getDpi()}" name="dpi" required>
                </div>
                <h4 style="text-align: left;" >Direccion:</h4>
                <div class="inputAnimate">
                    <input type="text"  value="${cliente.getDireccion()}" name="direccion" required>
                </div>
                <h4 style="text-align: left;" >Password:</h4>
                <div class="inputAnimate">
                    <input type="password"  value="${cliente.getPassword()}" name="password" required>
                </div>
               <h4 style="text-align: left;">Fecha de Nacimiento:</h4>
                <div class="inputAnimate">
                    <input type="date" id="start" name="fecha"
                           min="1900-01-01" max="2022-12-31"  value="${cliente.getFechaNacimiento()}"required >
                </div>
                    <div class="custom-select" style="width:200px; margin: 5px;left: 50%; transform: translate(-50%,0);">
                        <select name="sexo">
                            <option  value="${cliente.getSexo()}"> ${cliente.getSexo()}</option>
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                            <option value="Otro">Otro</option>
                        </select>
                    </div>
               <a class="btn solid" href="${pageContext.request.contextPath}/MostrarDpi?codigo=${cliente.getCodigo()}" target="_blank">Ver DPI</a>
                 <h4>PDF DPI:</h4>
                    <input type="file"  name="file" >
                    <br></br>
                <button class="draw" type="submit" name="gen">Modificar</button>
            </div>
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>
