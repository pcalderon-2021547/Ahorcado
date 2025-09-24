var palabra = "";
var guardada;
var oculta = [];
var hueco = document.getElementById("palabra");
var Intentos = 6;
var teclado = [];
var Inicio = document.getElementById("reset");

let tiempoRestante = 300;
let timerInterval;
let temporizadorElement = document.getElementById("temporizador");
let jugando = false;


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
        guardada = {pista: "No hay pista"};
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

    let parte = 6 - Intentos; 
    if (parte <= 6) {
        document.getElementById("image" + parte).classList.add("fade-in");
    }
}


// Mostrar pistas
function pista() {
    if (guardada) {
        document.getElementById("hueco-pista").innerHTML =
                `${guardada.pista || ""}`;
    }
}


function compruebaFin() {
    // Obtener los modales y elementos
    const modalGano = document.getElementById("modalGano");
    const mensajeGano = document.getElementById("mensajeGano");
    const btnReiniciarGano = document.getElementById("btnReiniciarGano");
    const cerrarModalGano = document.getElementById("cerrarModalGano");
    const imagenGano = document.getElementById("imagenGano");

    const modalPerdio = document.getElementById("modalPerdio");
    const mensajePerdio = document.getElementById("mensajePerdio");
    const btnReiniciarPerdio = document.getElementById("btnReiniciarPerdio");
    const cerrarModalPerdio = document.getElementById("cerrarModalPerdio");
    const imagenPerdio = document.getElementById("imagenPerdio");

    if (oculta.indexOf("_") === -1) {
        // Si ganó
        mensajeGano.textContent = "¡Felicidades Ganaste!";
        modalGano.style.display = "block";
        btnReiniciarGano.onclick = () => location.reload();
        cerrarModalGano.onclick = () => modalGano.style.display = "none";
    } else if (Intentos === 0) {
        // Si perdió
        mensajePerdio.textContent = "Perdiste malooo!";
        modalPerdio.style.display = "block";

        btnReiniciarPerdio.onclick = () => location.reload();
        cerrarModalPerdio.onclick = () => modalPerdio.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target === modalGano) {
            modalGano.style.display = "none";
        }
        if (event.target === modalPerdio) {
            modalPerdio.style.display = "none";
        }
    }
}

function actualizarTemporizador() {
    let minutos = Math.floor(tiempoRestante / 60);
    let segundos = tiempoRestante % 60;
    segundos = segundos < 10 ? "0" + segundos : segundos;
    temporizadorElement.innerText = `${minutos}:${segundos}`;

    if (tiempoRestante === 0) {
        clearInterval(timerInterval);
        mostrarModalTiempo(); 
    } else {
        tiempoRestante--;
    }
}

function mostrarModalTiempo() {
    const modalTiempo = document.getElementById("modalTiempo");
    const btnReiniciarTiempo = document.getElementById("btnReiniciarTiempo");
    const cerrarModalTiempo = document.getElementById("cerrarModalTiempo");

    // Mostrar el modal
    modalTiempo.style.display = "block";

    btnReiniciarTiempo.onclick = function() {
        modalTiempo.style.display = "none";
        reiniciar();  
    };


    cerrarModalTiempo.onclick = function() {
        modalTiempo.style.display = "none";
        reiniciar();  
    };


    window.onclick = function(event) {
        if (event.target === modalTiempo) {
            modalTiempo.style.display = "none";
        }
    };
}

function iniciarTemporizador() {
    if (!jugando) {
        if (tiempoRestante <= 0 || tiempoRestante === 300) {
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
