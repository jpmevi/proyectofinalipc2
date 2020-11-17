<%-- 
    Document   : MenuCliente
    Created on : Nov 12, 2020, 12:58:18 AM
    Author     : potz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
   <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pacienteindexstyle.css?4.1">
    <title>Menu Cajero</title>
</head>

<body style="background: url('${pageContext.request.contextPath}/img/cajero.png') no-repeat center ; background-attachment: fixed;">
    <%@include file="header.jsp" %>
    <main>
        <section>
            <div class="overlay">
                <div class="contenedor">
                    <h1 class="texto" >Bienvenido ${Cajero.getNombre()}</h1>
                     <c:if test="${enturno == 0}">
                            <p id="error" style="color: red;">No esta en turno para realizar esta accion</p>
                        </c:if>
                    </div>
                 </div>
        </section>
    </main>
            
</body>

</html>