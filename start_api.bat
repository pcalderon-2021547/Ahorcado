@echo off
echo Iniciando API Ahorcado en puerto 8768...
set JAVA_HOME=C:\Program Files\Java\jdk-21
"%JAVA_HOME%\bin\java" -jar "%~dp0ApiAhorcado\target\ApiAhorcado-0.0.1-SNAPSHOT.jar"
pause
