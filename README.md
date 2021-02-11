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
Un usuario podr� a�adir productos a su cesta de la compra de forma preliminar a su compra. Cuando �ste haya finalizado, se generar� un pedido que ser� registrado en el sistema, contando con el usuario due�o del pedido, los productos que lo forman, el precio...etc.

Partes:
* La parte p�blica del sistema ser� la libre visualizaci�n de productos, b�squeda de los mismos y navegaci�n por la p�gina.
* La parte privada del sistema ser� la compra de productos, el acceso al perfil del usuario, la utilizaci�n del ranking, la publicaci�n de comentarios, la administraci�n y gesti�n de la web.

La aplicaci�n dispondr� de dos sistemas:
* Sistema de compra - venta: descrito anteriormente.
* Sistema interno de informaci�n: la aplicaci�n principal se comunicar� con un servicio externo de generaci�n de informes sobre la tienda (pedidos, stock, usuarios...). Este sistema ser� una funcionalidad �nicamnete accesible por los administradores de la tienda, que podr�n encontrar en el apartado de administraci�n de la p�gina web.

Entidades:

* Usuario
```sh
	Esta entidad representa a los usuarios del sistema
 ```
* Administradores
```sh
	Esta entidad representa a los administradores del sistema
 ```
* Producto
```sh
	Esta entidad representa a los productos del sistema
	* Tendr� una clave for�nea referente al usuario que provee el producto.
 ```
* Pedido
```sh
	Esta entidad representa a los pedidos del sistema
	* Trendr� una clave for�nea referente al usuario que realiza el pedido.
	* Tendr� claves for�neas a los productos del pedido.
 ```
 * Rating
 ```sh
	Esta entidad representa a los ratings de los pedidos
	* Tendr� una clave for�nea referente al usuario que realiza el pedido.
	* Tendr� una clave for�nea al pedido en cuesti�n.
 ```

### Tecnolog�as

* [Java](https://www.java.com/es/)
* [Spring](https://spring.io/)
* [Docker](https://www.docker.com/)



<!-- EMPECEMOS -->
## Empecemos

Para descargar una copia local del proyecto sigue los siguientes pasos

### Pre requisitios

Pasos:
* npm
  ```sh
  TODO: futuros comandos
  ```

### Instalaci�n

1. Clona el repositorio
   ```sh
   git clone https://github.com/AlvaroMartinezQ/clickandbuy.git
   ```
2. TODO



<!-- EJEMPLOS DE USO -->
## Uso

TODO: ejemplos de uso de la aplicaci�n


<!-- ROADMAP -->
## Mapa de trabajo

TODO: mapa de desarrollo (semanas, hitos, metas...)

<!-- CONTRIBUCIONES -->
## Contribuciones

Las contribuciones a este proyecto est�n cerradas ya que es un desarrollo de aplicaci�n para un proyecto final de la universidad. Sin embargo, **el reporte de alg�n bug se apreciar� por los desarrolladores**.

<!-- LICENCIA -->
## Licencia

TODO: seleccionar licencia al final del proyecto

<!-- CONTACTO -->
## Contacto

* Alvaro Martinez - a.martinezq.2017@alumnos.urjc.es - https://github.com/AlvaroMartinezQ
* Patricia Tarazaga - p.tarazaga.2018@alumnos.urjc.es - https://github.com/patri-create
* Luis Blay - l.blay.2017@alumnos.urjc.es - https://github.com/lblay

<!-- AGRADECIMIETNOS -->
## Agradecimientos

* [Universidad Rey Juan Carlos](https://www.urjc.es/)

