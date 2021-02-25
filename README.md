<!-- PROJECT SHIELDS -->



<!-- LOGO DEL PROYECTO -->
<br />
<p align="center">
  <a href="https://github.com/AlvaroMartinezQ/clickandbuy">
    <img src="/statics/logo/company_logo.jpg" alt="Logo" width="450" height="450">
  </a>

  <h3 align="center">Click & Buy</h3>

  <p align="center">
    Simulaci�n de compra y venta online.
    <br />
    <a href="https://github.com/AlvaroMartinezQ/clickandbuy/issues">Reportar un bug</a>
    �
    <a href="https://github.com/AlvaroMartinezQ/clickandbuy/issues">Solicitar una nueva funcionalidad</a>
  </p>
</p>



<!-- TABLA DE CONTENIDOS -->
<details open="open">
  <summary><h2 style="display: inline-block">Tabla de contenidos</h2></summary>
  <ol>
    <li>
      <a href="#acerca-del-proyecto">Acerca del proyecto</a>
      <ul>
        <li><a href="#tecnolog�as">Tecnolog�as</a></li>
      </ul>
    </li>
    <li>
      <a href="#empecemos">Empecemos</a>
      <ul>
        <li><a href="#pre-requisitios">Pre requisitos</a></li>
        <li><a href="#instalaci�n">Instalaci�n</a></li>
      </ul>
    </li>
    <li><a href="#uso">Uso</a></li>
    <li><a href="#mapa-de-trabajo">Mapa de desarrollo</a></li>
    <li><a href="#contribuciones">Contribuciones</a></li>
    <li><a href="#licencia">Licencia</a></li>
    <li><a href="#contacto">Contacto</a></li>
    <li><a href="#agradecimientos">Agradecimientos</a></li>
  </ol>
</details>



<!-- ACERCA DEL PROYECTO -->
## Acerca del proyecto

Proyecto de la asignatura de Desarrollo de Aplicaciones Distribuidas del grado de Ingenier�a de Computadores de la Universidad Rey Juan Carlos, Madrid, Espa�a. 
El objetivo principal de la aplicaci�n es dar funcionalidad a una simulaci�n de una tienda de compra de objetos.

La idea principal es el desarrollo de una tienda de venta online, en donde hay productos, los cuales son proporcionados por usuarios (proveedores), y a su vez, estos pueden ser comprados por otros usuarios. Por otro lado, la gesti�n de la tienda es llevada a cabo por los administradores de la misma, los cuales podr�n a�adir, dar de baja o eliminar productos. Tambi�n gestionar la p�gina web y a sus usuarios.
El registro de usuario ser� gratuito, sin embargo, se podr�n visualizar productos sin necesidad de estar registrado.
Un usuario podr� a�adir productos a su cesta de la compra de forma preliminar a su compra. Cuando �ste haya finalizado, se generar� un pedido que ser� registrado en el sistema, contando con el usuario due�o del pedido, los productos que lo forman, el precio... etc.

Partes:
* La parte p�blica del sistema ser� la libre visualizaci�n de productos, b�squeda de los mismos y navegaci�n por la p�gina.
* La parte privada del sistema ser� la compra de productos, el acceso al perfil del usuario, la utilizaci�n del ranking, la publicaci�n de comentarios, la administraci�n y gesti�n de la web.

La aplicaci�n dispondr� de dos sistemas:
* Sistema de compra - venta: descrito anteriormente.
* Sistema interno de informaci�n: la aplicaci�n principal se comunicar� con un servicio externo de generaci�n de informes sobre la tienda (pedidos, stock, usuarios...). Este sistema ser� una funcionalidad �nicamnete accesible por los administradores de la tienda, que podr�n encontrar en el apartado de administraci�n de la p�gina web.

Entidades:

* Usuario
```sh
	Esta entidad representa a los usuarios del sistema.
	* Cada usuario tendr�, si es proveedor, tantos productos como este quiera publicar.
 ```
* Administradores
```sh
	Esta entidad representa a los administradores del sistema.
 ```
 
Hay 3 roles de administradores principales del sistema, estos son:
* Manager: este usuario ser� el responsable de la tienda y/o jefe. En principio solo habr� 1 usuario manager. Solo �l podr� a�adir nuevos administradores, dar de baja usuarios y productos, modificar valores en las tablas...
* T�cnico: este usuario ser� el responsable de controlar a los usuarios y productos. Tambi�n ser� capaz de recoger informes de la tienda con el futuro servicio a implementar.
* Staff: este usuario ser� responsable de controlar los productos.

* Producto
```sh
	Esta entidad representa a los productos del sistema.
 ```
* Pedido
```sh
	Esta entidad representa a los pedidos del sistema.
	* Trendr� una clave for�nea referente al usuario que realiza el pedido.
	* Tendr� claves for�neas a los productos del pedido.
 ```
Los pedidos funcionar�n de la siguiente manera: cada vez que se crea un nuevo pedido con n productos se guardar�n n entradas en la tabla de pedidos con un id �nico. Si se quiere a�adir un nuevo pedido a un pedido existente, se ha de proporcionar el id �nico de ese pedido, si no existe se crea un pedido nuevo.
 
 * Rating
 ```sh
	Esta entidad representa a los ratings de los pedidos.
	* Tendr� una clave for�nea referente al usuario que realiza el pedido.
	* Tendr� una clave for�nea al pedido en cuesti�n.
 ```

* UML

 <a href="https://github.com/AlvaroMartinezQ/clickandbuy">
    <img src="/statics/uml/uml_fase2.jpg" alt="uml">
  </a>

### Tecnolog�as

* [Java](https://www.java.com/es/)
* [Spring](https://spring.io/)
* [Docker](https://www.docker.com/)
* [MySQL](https://www.mysql.com/)



<!-- EMPECEMOS -->
## Empecemos

Para descargar una copia local del proyecto sigue los siguientes pasos

### Pre requisitios

Pasos:

* En caso de usar MySQL:
	```sh
	Levantar una instancia de MySQL Server en local.
	```
	```sh
	Crear una base de datos con el nombre <clickandbuy>
	```
	Opcional: crear un usuario y darle privilegios en la base de datos creada. Si no, utilizar el usuario root del sistema.
	```sh
	Modificar el fichero application.properties con tu usuario y contrase�a.
	```
	
* En caso de usar Docker:
  ```sh
  TODO: futuros comandos.
  ```

### Instalaci�n

1. Clona el repositorio
   ```sh
   git clone https://github.com/AlvaroMartinezQ/clickandbuy.git
   ```
2. TODO



<!-- EJEMPLOS DE USO -->
## Uso

TODO: ejemplos de uso de la aplicaci�n.


<!-- ROADMAP -->
## Mapa de trabajo

TODO: mapa de desarrollo (semanas, hitos, metas...)

<!-- CONTRIBUCIONES -->
## Contribuciones

Las contribuciones a este proyecto est�n cerradas ya que es un desarrollo de aplicaci�n para un proyecto final de la universidad. Sin embargo, **el reporte de alg�n bug se apreciar� por los desarrolladores**.

Todo el nuevo c�digo debe de subirse a una nueva rama, �no subir cambios directamente a la rama <strong>master</strong>!

1. Crea la rama para tu desarrollo (`git checkout -b feature-<nombre de la nueva caracter�stica>`).
2. Haz commit de tus cambios, no pases los 50 caracteres en la descripcion (`git commit -m 'feature: '<descripci�n>'`).
3. Sube tu rama (`git push origin <nombre de la rama>`).
4. Abre una pull request, opcional.

Si quieres mergear ramas sigue los siguientes pasos:

Primero, desde la rama que quieres mergear:
1. Descarga posibles cambios, actualiza la rama (`git pull`).
2. Mergea tu rama con la rama principal de desarrollo, en este caso dev (`git merge dev`).
3. Si no presenta conflictos, cambia a la rama de desarrollo (`git checkout dev`).

Segundo, desde la rama de desarrollo:
1. Descarga posibles cambios, actualiza la rama (`git pull`).
2. Mergea la rama que acabas de desarrollar (`git merge <rama a mergear>`).
3. Sube la rama actualizada (`git push`).
4. Borra tu rama de trabajo de tu entorno local (`git branch -d <rama a borrar>`).
5. Borra tu rama del repositorio (`git push -d origin <rama a borrar>`).

<!-- LICENCIA -->
## Licencia

Este proyecto est� sujeto a la licencia `Apache License 2.0`. Para obtener m�s informaci�n sobre esta, acceder al fichero LICENSE.

<!-- CONTACTO -->
## Contacto

* Alvaro Martinez - a.martinezq.2017@alumnos.urjc.es - https://github.com/AlvaroMartinezQ
* Patricia Tarazaga - p.tarazaga.2018@alumnos.urjc.es - https://github.com/patri-create
* Luis Blay - l.blay.2017@alumnos.urjc.es - https://github.com/lblay

<!-- AGRADECIMIETNOS -->
## Agradecimientos

* [Universidad Rey Juan Carlos](https://www.urjc.es/)

