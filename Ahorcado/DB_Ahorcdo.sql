Drop database if exists DB_Ahorcado;
create database DB_Ahorcado;
use DB_Ahorcado;

create table Palabras(
codigoPalabra int auto_increment,
palabra varchar(255),
pista varchar (255),
primary key PK_codigoPalabra(codigoPalabra)
);

create table Usuario(
codigoUsuario int auto_increment,
nombreUsuario varchar(50),
email varchar(250),
CONSTRAINT chk_email CHECK (
        email LIKE '%@gmail.com' OR 
        email LIKE '%@kinal.edu.gt' OR 
        email LIKE '%@outlook.com'
    ),
contra varchar(250),
primary key PK_codigoUsuario (codigoUsuario)
);

Delimiter //

create procedure sp_AgregarPalabra(
    in palabra varchar(255),
    in pista varchar(255)
)
begin
    insert into Palabras (palabra, pista)
    values (palabra, pista);
end //

Delimiter ;

CALL sp_AgregarPalabra('Computadora', 'Dispositivo que procesa informacion');
CALL sp_AgregarPalabra('Electricidad', 'Forma de energia que hace funcionar dispositivos');
CALL sp_AgregarPalabra('Escultura', 'Arte de moldear o tallar un material para formar una figura');
CALL sp_AgregarPalabra('Cinematica', 'Rama de la fisica que estudia el movimiento');
CALL sp_AgregarPalabra('Teclado', 'Dispositivo que permite introducir datos en una computadora');
CALL sp_AgregarPalabra('Estudiante', 'Persona que aprende una ciencia o una profesion');
CALL sp_AgregarPalabra('Automovil', 'Vehiculo de motor utilizado para transporte');
CALL sp_AgregarPalabra('Haceridad', 'Accion de realizar algo con esfuerzo');
CALL sp_AgregarPalabra('Mariposa', 'Insecto con alas coloridas');
CALL sp_AgregarPalabra('Instrumento', 'Objeto utilizado para realizar una actividad');




Delimiter //

create procedure sp_ListarPalabras()
begin
    select codigoPalabra, palabra, pista from Palabras;
end //

Delimiter ;

call sp_ListarPalabras();


Delimiter //

create procedure sp_EliminarPalabra(
    in codigoP int
)
begin
    delete from Palabras
    where codigoPalabra = codigoP;
end //

Delimiter ;

call sp_EliminarPalabra(1);

Delimiter //

create procedure sp_BuscarPalabra(
    in codigoP int
)
begin
    select codigoPalabra, palabra, pista 
    from Palabras
    where codigoPalabra = codigoP;
end //

Delimiter ;

call sp_BuscarPalabra(2);

Delimiter //

create procedure sp_EditarPalabra(
    in codigoP int,
    in nuevaPalabra varchar(255),
    in nuevaPista varchar(255)
)
begin
    update Palabras
    set palabra = nuevaPalabra,
        pista = nuevaPista
    where codigoPalabra = codigoP;
end //

Delimiter ;

call sp_EditarPalabra(2, 'JavaScript', 'Lenguaje de programación usado en la web');

delimiter //
create procedure sp_ObtenerPalabraAleatoria()
begin
    select codigoPalabra, palabra, pista
    from Palabras
    order by rand()
    limit 1;
end //
delimiter ;

CALL sp_ObtenerPalabraAleatoria();
-- ----------------------------------------------------------------

Delimiter / /

create procedure sp_AgregarUsuario(
    in nombreUsuario varchar(50),
    in email varchar(250),
    in contra varchar(250)
)
begin
    insert into Usuario (nombreUsuario, email, contra)
    values (nombreUsuario, email, contra);
end / /

Delimiter ;

call sp_AgregarUsuario('Carlos Mendoza', 'carlosmendoza@gmail.com', 'carlitos23');
call sp_AgregarUsuario('Ana López', 'analopez@kinal.edu.gt', 'pepe23');
call sp_AgregarUsuario('Miguel Ramírez', 'miguelramirez@outlook.com', 'amiguito23');
call sp_AgregarUsuario('Sofia Martínez', 'sofia.martinez@gmail.com', 'miguel23');
call sp_AgregarUsuario('Pedro González', 'pedrogonzalez@kinal.edu.gt', 'holi');


Delimiter / /

create procedure sp_ListarUsuarios()
begin
    select codigoUsuario, nombreUsuario, email from Usuario;
end / /

Delimiter ;

call sp_ListarUsuarios();

Delimiter / /

create procedure sp_BuscarUsuario(
    in codigoU int
)
begin
    select codigoUsuario, nombreUsuario, email from Usuario
    where codigoUsuario = codigoU;
end / /

Delimiter ;


Delimiter / /

create procedure sp_EditarUsuario(
    in codigoU int,
    in nuevonombreUsuario varchar(50),
    in nuevoEmail varchar(250),
    in nuevaContra varchar(250)
)
begin
    update Usuario
    set nombreUsuario = nuevonombreUsuario,
        email = nuevoEmail,
        contra = nuevaContra
    where codigoUsuario = codigoU;
end / /

Delimiter ;


Delimiter / /

create procedure sp_EliminarUsuario(
    in codigoU int
)
begin
    delete from Usuario
    where codigoUsuario = codigoU;
end / /

Delimiter ;




