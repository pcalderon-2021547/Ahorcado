var palabra = "";
var guardada;
var oculta = [];
var hueco = document.getElementById("palabra");
var Intentos = 6;
var teclado = [];
var Inicio = document.getElementById("reset");

let tiempoRestante = 300; // 5 minutos
let timerInterval;
let temporizadorElement = document.getElementById("temporizador");
let jugando = false;

// --- Obtener palabra desde BD ---
async function obtenerPalabraBD() {
    try {
        const respuesta = await fetch('./Controlador?accion=obtenerPalabra');
        if (!respuesta.ok)
            throw new Error("Error al obtener palabra");
        const data = await respuesta.json();
        palabra = data.palabra.toUpperCase();
        guardada = data;
        return palabra;
    } catch (err) {
        console.error("Error:", err);
        palabra = "ERROR";
        guardada = {pista: "No hay pista webon"};
        return palabra;
    }
}

// Pintar guiones
function pintarGuiones(num) {
    oculta = [];
    for (var i = 0; i < num; i++)
        oculta[i] = "_";
    hueco.innerHTML = oculta.join(" ");
}

// Generar abecedario
function generaABC(a, z) {
    document.getElementById("abcdario").innerHTML = "";
    for (var i = a.charCodeAt(0); i <= z.charCodeAt(0); i++) {
        let letra = String.fromCharCode(i).toUpperCase();
        document.getElementById("abcdario").innerHTML +=
                `<button value='${letra}' onclick='intento("${letra}")' class='letra' id='${letra}'>${letra}</button>`;
    }
    teclado = document.getElementsByClassName('letra');
}

// Intento
function intento(letra) {
    document.getElementById(letra).disabled = true;
    if (palabra.indexOf(letra) != -1) {
        for (var i = 0; i < palabra.length; i++) {
            if (palabra[i] === letra)
                oculta[i] = letra;
        }
        hueco.innerHTML = oculta.join(" ");
        mostrarMensaje("Bien!", "verde");
    } else {
        Intentos--;
        document.getElementById("intentos").innerHTML = Intentos;
        mostrarMensaje("Fallo!", "rojo");
        actualizarImagen();
    }
    compruebaFin();
}

function mostrarMensaje(msg, color) {
    const acierto = document.getElementById("acierto");
    acierto.innerHTML = msg;
    acierto.className = `acierto ${color}`;
    setTimeout(() => acierto.className = "", 800);
}

// Actualizar imagen según intentos
function actualizarImagen() {
    for (let i = 0; i <= 6; i++) {
        document.getElementById("image" + i).classList.remove("fade-in");
    }
    // Mostrar la parte correspondiente según los intentos restantes
    let parte = 6 - Intentos; // si quieres que se muestre desde la base
    if(parte <= 6){
        document.getElementById("image" + parte).classList.add("fade-in");
    }
}


// Mostrar pistas
function pista() {
    if (guardada) {
        document.getElementById("hueco-pista").innerHTML =
                `${guardada.pista || ""}}`;
    }
}

// Comprobar fin
function compruebaFin() {
    if (oculta.indexOf("_") === -1) {
        document.getElementById("msg-final").innerHTML = "¡Felicidades Ganaste!";
        document.getElementById("msg-final").className += " zoom-in";
        for (let i = 0; i < teclado.length; i++)
            teclado[i].disabled = true;
        Inicio.innerHTML = "Otra Vez";
        Inicio.onclick = () => location.reload();
        clearInterval(timerInterval);
    } else if (Intentos === 0) {
        document.getElementById("msg-final").innerHTML = "Perdiste malooo!";
        document.getElementById("msg-final").className += " zoom-in";
        for (let i = 0; i < teclado.length; i++)
            teclado[i].disabled = true;
        Inicio.innerHTML = "Volver a intentar";
        Inicio.onclick = () => location.reload();
        clearInterval(timerInterval);
    }
}

// Temporizador
function actualizarTemporizador() {
    let minutos = Math.floor(tiempoRestante / 60);
    let segundos = tiempoRestante % 60;
    segundos = segundos < 10 ? "0" + segundos : segundos;
    temporizadorElement.innerText = `${minutos}:${segundos}`;

    if (tiempoRestante === 0) {
        clearInterval(timerInterval);
        alert("¡El tiempo ha terminado!");
        reiniciar();
    } else
        tiempoRestante--;
}

// Funciones para temporizador
function iniciarTemporizador() {
    if (!jugando) {
        // Si el temporizador no estaba corriendo
        if (tiempoRestante <= 0 || tiempoRestante === 300) {
            // Empieza desde 5:00 si no había comenzado o ya se había terminado
            tiempoRestante = 300;
            temporizadorElement.innerText = "5:00";
        }
        timerInterval = setInterval(actualizarTemporizador, 1000);
        habilitarTeclado();
        jugando = true;
    }
}

function pausarTemporizador() {
    clearInterval(timerInterval);
    deshabilitarTeclado();
}

// Iniciar juego
async function inicio() {
    await obtenerPalabraBD();
    pintarGuiones(palabra.length);
    generaABC("a", "z");
    Intentos = 6;
    document.getElementById("intentos").innerHTML = Intentos;
    tiempoRestante = 300;
    clearInterval(timerInterval);
    timerInterval = setInterval(actualizarTemporizador, 1000);
    habilitarTeclado();
    jugando = true;
    actualizarImagen();
}

// Reiniciar juego
function reiniciar() {
    clearInterval(timerInterval);
    tiempoRestante = 300;
    Intentos = 6;
    document.getElementById("intentos").innerHTML = Intentos;
    document.getElementById("acierto").innerHTML = "";
    document.getElementById("msg-final").innerHTML = "";
    habilitarTeclado();
    inicio();
    jugando = false;
    temporizadorElement.innerHTML = "5:00";
}

function reanudarTemporizador() {
    if (!jugando === false) {               // Solo si no está corriendo
        clearInterval(timerInterval);       // Asegurarse de no tener intervalos previos
        timerInterval = setInterval(actualizarTemporizador, 1000);
        habilitarTeclado();
        jugando = true;
    }
}



// Habilitar/Deshabilitar teclado
function deshabilitarTeclado() {
    for (let i = 0; i < teclado.length; i++)
        teclado[i].disabled = true;
}
function habilitarTeclado() {
    for (let i = 0; i < teclado.length; i++)
        teclado[i].disabled = false;
}

// Inicio automático al cargar
window.onload = inicio;
