<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">       
        <script src="https://kit.fontawesome.com/a81368914c.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

        <img class="wave" src="img/waveazul.png">
        <div class="container">
            <div class="img">
                <img src="img/img2.svg">
            </div>
            <div class="login-content">
                <form action="Ingresar" method="POST">
                    <img src="img/avatar.svg">
                    <h2 class="title">Bienvenido</h2>
                    <div class="input-div one">
                        <div class="i">
                            <i class="fas fa-user"></i>
                        </div>
                        <div class="div">
                            <h5>Usuario</h5>
                            <input type="text" class="input" required="" name="user">
                        </div>
                    </div>
                    <div class="input-div pass">
                        <div class="i"> 
                            <i class="fas fa-lock"></i>
                        </div>
                        <div class="div">
                            <h5>Password</h5>
                            <input type="password" class="input" required="" name="password">
                        </div>

                    </div>
                    <div class="custom-select" style="width:200px;">
                        <select name="atributo">
                            <option value="GERENTE">TIPO DE USUARIO:</option>
                            <option value="GERENTE">Gerente</option>
                            <option value="CLIENTE">Cliente</option>
                            <option value="CAJERO">Cajero</option>
                        </select>
                    </div>
                    <div class="error" >
                        <a href="Administrador/CrearPaciente.jsp">Forgot Password?</a>
                        <input type="submit" class="btn" value="Login">
                        <c:if test="${success == 0}">
                            <p id="error" style="color: red;">Usuario o Contraseña Incorrecto</p>
                        </c:if>
                    </div>
                    
                </form>
            </div>
        </div>

        <script type="text/javascript" src="js/main.js"></script>          
    </body>
</html>

