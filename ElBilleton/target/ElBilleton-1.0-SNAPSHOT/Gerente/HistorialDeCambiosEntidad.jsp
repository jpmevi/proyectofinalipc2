<%-- 
    Document   : HistorialDeCambiosEntidad
    Created on : Nov 14, 2020, 8:42:01 PM
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
        <title>Buscar Historial</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/fondo.png') center ; background-attachment: fixed;">
        <%@include file="header.jsp" %>
        <form action="ObtenerHistorial" method="post" >
            <div class="cajatabla">
                <h2 >Buscar Historial:</h2>

                <div class="custom-select" style="width:200px; margin: 5px;left: 50%; transform: translate(-50%,0);">
                    <select name="entidad">
                        <option value="cliente">Buscar por:</option>
                        <option value="cliente">Cliente</option>
                        <option value="gerente">Gerente</option>
                        <option value="cajero">Cajero</option>
                    </select>
                </div>
                <button class="draw" type="submit" name="gen">Buscar</button>
                <table class="darkTable">
                    <tr>
                        <th class="text-center">Codigo</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">DPI</th>
                        <th class="text-center">Direccion</th>
                        <th class="text-center">Sexo</th>
                        <th class="text-center">Codigo Usuario</th>
                    </tr>
                    <c:forEach items="${Historial}" var="cliente">
                        <tr>
                            <td class="text-center">${cliente.getCodigo()}</td>
                            <td class="text-center">${cliente.getNombre()}</td>
                            <td class="text-center">${cliente.getDPI()}</td>
                            <td class="text-center">${cliente.getDireccion()}</td>
                            <td class="text-center">${cliente.getSexo()}</td>
                            <td class="text-center">${cliente.getEntidad_codigo()}</td>
                            <c:if test="${si == 1}">
                                <td>
                                    <a class="btn solid" href="${pageContext.request.contextPath}/MostrarDpiHistorial?codigo=${cliente.getCodigo()}" target="_blank">Ver DPI</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    
                </table>
            </div>
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>

