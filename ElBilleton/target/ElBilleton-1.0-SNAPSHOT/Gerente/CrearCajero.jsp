<%-- 
    Document   : CrearCajero
    Created on : Nov 13, 2020, 10:57:09 PM
    Author     : potz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Crear Cajero</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/40.svg') no-repeat top center / cover;">
        <%@include file="header.jsp" %>
        <form action="CrearCajero" method="post" >
            <div class="caja">
                <h2 >Ingresar Cajero:</h2>
                <div class="inputAnimate">
                    <input type="text" placeholder="Nombre" name="nombre" required>
                </div>
                <div class="inputAnimate">
                    <input type="text" placeholder="Dpi" name="dpi" required>
                </div>
                <div class="inputAnimate">
                    <input type="text" placeholder="Direccion" name="direccion" required>
                </div>
                <div class="inputAnimate">
                    <input type="password" placeholder="Password" name="password" required>
                </div>
                    <div class="custom-select" style="width:200px; margin: 5px;left: 50%; transform: translate(-50%,0);">
                        <select name="sexo">
                            <option value="Masculino">Sexo:</option>
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                            <option value="Otro">Otro</option>
                        </select>
                    </div>
               
                    <div class="custom-select" style="width:200px; margin: 5px;left: 50%; transform: translate(-50%,0);">
                        <select name="turno">
                            <option value="Matutino">Turno:</option>
                            <option value="Matutino">Matutino</option>
                            <option value="Vespertino">Vespertino</option>
                        </select>
                    </div>
                
                <button class="draw" type="submit" name="gen">Generar</button>
                 </div>

           
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>
