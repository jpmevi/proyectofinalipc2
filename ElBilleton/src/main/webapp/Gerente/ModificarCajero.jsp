<%-- 
    Document   : ModificarGerente
    Created on : Nov 14, 2020, 4:52:21 AM
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
        <title>Modificar Cajero</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/bgformulario.png') left ; background-attachment: fixed;">
        <%@include file="header.jsp" %>
        <form action="ModificarCajero" method="post" >
            <div class="caja">
                <h2>Modificar Cajero:</h2>
                <h4 style="text-align: left;">Codigo:</h4>
                <div class="inputAnimate">
                    <input type="text" value="${cliente.getCodigo()}" name="codigo" readonly>
                </div>
                <h4 style="text-align: left;">Nombre:</h4>
                <div class="inputAnimate">
                    <input type="text" value="${cliente.getNombre()}" name="nombre" required>
                </div>
                <h4 style="text-align: left;" >DPI:</h4>
                <div class="inputAnimate">
                    <input type="number" min="0" value="${cliente.getDPI()}" name="dpi" required>
                </div>
                <h4 style="text-align: left;" >Direccion:</h4>
                <div class="inputAnimate">
                    <input type="text" value="${cliente.getDireccion()}" name="direccion" required>
                </div>
                <h4 style="text-align: left;" >Password:</h4>
                <div class="inputAnimate">
                    <input type="password" value="${cliente.getPassword()}" name="password" required>
                </div>
                    <div class="custom-select" style="width:200px; margin: 5px;left: 50%; transform: translate(-50%,0); z-index: 99;">
                        <select name="sexo">
                            <option value="${cliente.getSexo()}">${cliente.getSexo()}</option>
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                            <option value="Otro">Otro</option>
                        </select>
                    </div>
                    <div class="custom-select" style=" width:200px; margin: 5px;left: 50%; transform: translate(-50%,0); z-index: 95;">
                        <select name="turno">
                            <option value="${cliente.getTurno()}">${cliente.getTurno()}</option>
                            <option value="Matutino">Matutino</option>
                            <option value="Vespertino">Vespertino</option>
                        </select>
                    </div>
                
                <button class="draw" type="submit" name="gen">Modificar</button>
                 </div>

           
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>
