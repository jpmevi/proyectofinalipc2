<%-- 
    Document   : VerGerentes
    Created on : Nov 14, 2020, 7:03:04 AM
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
        <title>Buscar Cajero</title>
    </head>
    <body style="background: url('${pageContext.request.contextPath}/img/fondo.png') center ; background-attachment: fixed;">
        <%@include file="header.jsp" %>
        <form action="MostrarGerentes" method="post" >
            <div class="cajatabla">
                <h2 >Buscar Cajero:</h2>
                <section class="webdesigntuts-workshop" >
                    <div>
                        <input type="search" placeholder="Filtrar por codigo?" name="filtro">		    	
                        <button>Filtrar</button>
                    </div>
                </section>
                <table class="darkTable">
                    <tr>
                        <th class="text-center">Codigo</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">Turno</th>
                        <th class="text-center">Dpi</th>
                        <th class="text-center">Direccion</th>
                        <th class="text-center">Sexo</th>
                    </tr>
                    <c:forEach items="${Cajeros}" var="gerente">
                        <tr>
                            <td class="text-center">${gerente.getCodigo()}</td>
                            <td class="text-center">${gerente.getNombre()}</td>
                            <td class="text-center">${gerente.getTurno()}</td>
                            <td class="text-center">${gerente.getDPI()}</td>
                            <td class="text-center">${gerente.getDireccion()}</td>
                            <td class="text-center">${gerente.getSexo()}</td>
                            <td>
                                <a class="btn solid" href="${pageContext.request.contextPath}/GenerarCajero?codigo=${gerente.getCodigo()}">Editar Cajero</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/js/text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/select.js"></script>
    </body>
</html>

