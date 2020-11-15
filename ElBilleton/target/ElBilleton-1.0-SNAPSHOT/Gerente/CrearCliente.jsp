<%-- 
    Document   : CrearCliente
    Created on : Nov 13, 2020, 5:43:40 PM
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
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:400,500,700'>
        <title>Crear Cliente</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/bgformulario.png') left ; background-attachment: fixed;">
        <%@include file="header.jsp" %>
        <form action="CrearCliente" method="post" enctype="multipart/form-data">
            <div class="caja">
                <h2 >Ingresar Cliente:</h2>
                <div class="inputAnimate">
                    <input type="text" placeholder="Nombre" name="nombre" required>
                </div>
                <div class="inputAnimate">
                    <input type="number" min="0" placeholder="Dpi" name="dpi" required>
                </div>
                <div class="inputAnimate">
                    <input type="text" placeholder="Direccion" name="direccion" required>
                </div>
                <div class="inputAnimate">
                    <input type="number" placeholder="Monto" name="monto" min="1" step=".01"required>
                </div>
                <div class="inputAnimate">
                    <input type="password" placeholder="Password" name="password" required>
                </div>
               <h4 style="text-align: left;">Fecha de Nacimiento:</h4>
                <div class="inputAnimate">
                    <input type="date" id="start" name="fecha"
                           min="1900-01-01" max="2022-12-31" required >
                </div>
                    <div class="custom-select" style="width:200px; margin: 5px;left: 50%; transform: translate(-50%,0);">
                        <select name="sexo">
                            <option value="Masculino">Sexo:</option>
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                            <option value="Otro">Otro</option>
                        </select>
                    </div>
               
                 <h4>PDF DPI:</h4>
                    <input type="file"  name="file" required>
                    <br></br>
                <button class="draw" type="submit" name="gen">Generar</button>
            </div>
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>
