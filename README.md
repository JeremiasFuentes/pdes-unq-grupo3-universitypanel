# University Panel

Integrantes:

- Matias Nicolosi
- Jeremias Fuentes

## Descripcion

Aplicacion para administrar y gestionar cursadas, materias, profesores, grupos y alumnos de una universidad.

## Tecnologias a utilizar

Elegimos como tecnologias el backend con Java usando Spring porque ya las hemos usado, tienen mucho soporte y nos permite hacer consultas facilmente a la bd, una base de datos MariaDb porque es libre, estamos acostimbrados a las bases sql relacionales y parece ser adecuada a las relaciones del tp (materia, alumnos, profesores, etc). y en el front elegimos Angular con typeScript porque Matias trabaja con esas herramientas y tambien nos permiten conectar con el back de forma simple, ademas que el desarrollo es mucho mas fluido por el uso del modelo vista controlador.

## Ejecucion de imagenes

Para levantar la aplicacion, teniendo docker corriendo, se puede ejecutar el comando 'docker-compose up --build' en la carpeta raiz del proyecto para que cree las imagenes y deje disponible la aplicacion en el puerto 8083

## Variables de Entorno

Para la configuracion de la conexion a bases de datos editar el archivo 'spring.env' en la carpeta raiz

## Diagrama UML

![image](https://user-images.githubusercontent.com/39131303/231901049-115ab5ca-74b0-43d1-afe5-70b896d660dc.png)
