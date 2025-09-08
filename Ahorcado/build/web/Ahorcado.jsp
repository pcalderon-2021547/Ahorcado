<%-- 
    Document   : Ahorcado
    Created on : 2 sept 2025, 08:15:09
    Author     : informatica
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Juego Ahorcado</title>
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body>
        <div class="main-container">
            <h1 class="titulo">Juego Ahorcado</h1>
            <h1 id="msg-final"></h1>
            <h3 id="acierto"></h3>
            <div class="col imagen-palabra"> 
                <h2 class="palabra" id="palabra"></h2>
                <div class="ahorcado-imagen">
                    <img src="img/orca.png" id="image0">
                    <img src="img/cabeza.png" id="image1">
                    <img src="img/cuerpo.png" id="image2">
                    <img src="img/brazo.png" id="image3">
                    <img src="img/brazos.png" id="image4">
                    <img src="img/pieIzquierdo.png" id="image5">
                    <img src="img/completo.png" id="image6">
                </div>
            </div>

            <h3>Intentos restantes: <span id="intentos">6</span></h3>
            <div id="hueco-pista"></div>


            <div id="botones-container">
                <button onclick="inicio()" id="reset">¿Quieres cambiar de palabra?</button>
                <button onclick="pista()" id="pista">Dame una pista!</button>
                <button onclick="reanudarTemporizador()" id="iniciar">Iniciar</button>
                <button onclick="pausarTemporizador()" id="pausa">Pausar</button>
                <button onclick="reiniciar()" id="reiniciar">Reiniciar</button>
            </div>



            <div class="flex-row" id="abcdario"></div>
            <div id="temporizador"></div>
        </div>

        <script src="js/ahorcado.js"></script>
    </body>
</html>

