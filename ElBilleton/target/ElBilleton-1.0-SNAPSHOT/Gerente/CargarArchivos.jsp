<%-- 
    Document   : CargarArchivos
    Created on : Nov 13, 2020, 1:48:08 AM
    Author     : potz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css?2.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/textstyle.css?2.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/select.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/button.css">
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:400,500,700'>
        <title>Crear Medico</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/MenuImage.jpg') no-repeat top center / cover;">
        <%@include file="header.jsp" %>
        <form action="ImportarDatos" method="post" enctype="multipart/form-data">
            <div class="caja">
                <h4>Seleccionar Archivos:</h4>
                    <input type="file" placeholder="Archivos" name="archivo" multiple required>                     
                <button class="draw" type="submit" name="gen">Cargar</button>               
            </div>
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>
