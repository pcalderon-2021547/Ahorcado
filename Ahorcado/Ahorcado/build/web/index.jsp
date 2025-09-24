
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Bienvenido A Mi Ahorcado Por Favor Inicie Sesion</title>
        <link rel="stylesheet" href="css/Sesion.css">

    </head>

    <nav>
        <h2 class="titulo">Juego ahorcado</h2>
        <div class="links">
        </div>
    </nav>

        <form class="container" action="Validar" method="POST">
  <div class="input-container">
    <div class="input-content">
      <div class="input-dist">
        <div class="input-type">
          <input class="input-is" type="text" name="txtEmail" required="Se requiere usuario" placeholder="Correo" />
          <input
            class="input-is"
            name="txtContrasena"
            placeholder="Contrasenia"
            type="password"
            required="se requiere la contraseña"
          />
        </div>
      </div>
    </div>
  </div>
  <button type="submit" name="accion" value="btnIngresar" class="submit-button">Iniciar sesion</button>
</form>
</body>
</html>
