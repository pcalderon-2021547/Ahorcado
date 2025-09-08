Drop database if exists DB_Ahorcado;
create database DB_Ahorcado;
use DB_Ahorcado;

create table Palabras(
codigoPalabra int auto_increment,
palabra varchar(255),
pista varchar (255),
primary key PK_codigoPalabra(codigoPalabra)
);

Delimiter $$

create procedure sp_AgregarPalabra(
    in palabra varchar(255),
    in pista varchar(255)
)
begin
    insert into Palabras (palabra, pista)
    values (palabra, pista);
end $$

Delimiter ;

call sp_AgregarPalabra('Ahorcado', 'Juego en el que se adivinan letras');
call sp_AgregarPalabra('Python', 'Lenguaje de programación popular');
call sp_AgregarPalabra('Java', 'Lenguaje de programación orientado a objetos');
call sp_AgregarPalabra('Guitarra', 'Instrumento musical de cuerdas que se toca con los dedos o pua');
call sp_AgregarPalabra('Leon', 'Gran felino conocido como el rey de la selva');
call sp_AgregarPalabra('Aventura', 'Experiencia emocionante, generalmente fuera de lo común');
call sp_AgregarPalabra('Madrid', 'Capital de España');
call sp_AgregarPalabra('Cielo', 'Espacio que está por encima de la Tierra, donde están las nubes, el sol y las estrellas');
call sp_AgregarPalabra('Ciencia', 'Conjunto de conocimientos obtenidos mediante la observación y el razonamiento');
call sp_AgregarPalabra('Espejo', 'Superficie reflectante que permite ver una imagen');
call sp_AgregarPalabra('Montaña', 'Elevación natural del terreno, generalmente más alta que una colina');
call sp_AgregarPalabra('Oasis', 'Área fértil en medio de un desierto donde hay agua');



Delimiter $$

create procedure sp_ListarPalabras()
begin
    select codigoPalabra, palabra, pista from Palabras;
end $$

Delimiter ;

call sp_ListarPalabras();


Delimiter $$

create procedure sp_EliminarPalabra(
    in codigoP int
)
begin
    delete from Palabras
    where codigoPalabra = codigoP;
end $$

Delimiter ;

call sp_EliminarPalabra(1);

Delimiter $$

create procedure sp_BuscarPalabra(
    in codigoP int
)
begin
    select codigoPalabra, palabra, pista 
    from Palabras
    where codigoPalabra = codigoP;
end $$

Delimiter ;

call sp_BuscarPalabra(2);

Delimiter $$

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
end $$

Delimiter ;

call sp_EditarPalabra(2, 'JavaScript', 'Lenguaje de programación usado en la web');

DELIMITER //

CREATE PROCEDURE sp_ObtenerPalabraAleatoria()
BEGIN
    SELECT *
    FROM Palabras
    ORDER BY RAND()
    LIMIT 1;
END //

DELIMITER ;

call sp_ObtenerPalabraAleatoria();	
