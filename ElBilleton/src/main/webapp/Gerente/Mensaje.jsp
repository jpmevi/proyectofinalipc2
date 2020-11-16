<%-- 
    Document   : Mensaje
    Created on : Oct 7, 2020, 2:09:23 AM
    Author     : potz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pacienteindexstyle.css?4.1">
    <title>Menu Gerente</title>
</head>

<body style="background: url('${pageContext.request.contextPath}/img/gerente.png')no-repeat center ; background-attachment: fixed;">
    <%@include file="header.jsp" %>
    <main>
        <section>
            
            <div class="overlay">
                <div class="contenedor">
                    <% String mensaje=String.valueOf(request.getParameter("mensaje"));%>
                    <h1 class="texto" ><%= mensaje%></h1>
                    </div>
                 </div>
                

        </section>
    </main>
            
</body>

</html>
