<%-- 
    Document   : index
    Created on : 5/09/2025, 18:29:27
    Author     : pcc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicio de Sesion</title>
        <link rel="stylesheet" href="css/Sesion.css">

    </head>

    <nav>
        <h2 class="titulo">Juego ahorcado</h2>
        <div class="links">
        </div>
    </nav>

    <div class="login-contendor">
        <h2>Bienvenido</h2>
        <p class="subtitulo">Ingresa tus datos para iniciar sesion</p>

        <form action="Validar" method="POST" class="formulario">
            <input type="text" name="txtCorreo" placeholder="Correo" required />
            <input type="password" name="txtContrasena" placeholder="Contrasenia" required />
            <button type="submit" name="btnIngresar" value="Ingresar"class="boton-brillante">Iniciar Sesión</button>
        </form>

    </div>
</body>
</html>
