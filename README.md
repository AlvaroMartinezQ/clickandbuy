<!-- PROJECT SHIELDS -->



<!-- LOGO DEL PROYECTO -->
<br />
<p align="center">
  <a href="https://github.com/AlvaroMartinezQ/clickandbuy">
    <img src="/statics/logo/company_logo.jpg" alt="Logo" width="450" height="450">
  </a>

  <h3 align="center">Click & Buy</h3>

  <p align="center">
    Simulación de compra y venta online.
    <br />
    <a href="https://github.com/AlvaroMartinezQ/clickandbuy/issues">Reportar un bug</a>
    ·
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
        <li><a href="#tecnologías">Tecnologías</a></li>
      </ul>
    </li>
    <li>
      <a href="#empecemos">Empecemos</a>
      <ul>
        <li><a href="#pre-requisitios">Pre requisitos</a></li>
        <li><a href="#instalación">Instalación</a></li>
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

Proyecto de la asignatura de Desarrollo de Aplicaciones Distribuidas del grado de Ingeniería de Computadores de la Universidad Rey Juan Carlos, Madrid, España. 
El objetivo principal de la aplicación es dar funcionalidad a una simulación de una tienda de compra de objetos.

La idea principal es el desarrollo de una tienda de venta online, en donde hay productos, los cuales son proporcionados por usuarios (proveedores), y a su vez, estos pueden ser comprados por otros usuarios. Por otro lado, la gestión de la tienda es llevada a cabo por los administradores de la misma, los cuales podrán añadir, dar de baja o eliminar productos. También gestionar la página web y a sus usuarios.
El registro de usuario será gratuito, sin embargo, se podrán visualizar productos sin necesidad de estar registrado.
Un usuario podrá añadir productos a su cesta de la compra de forma preliminar a su compra. Cuando éste haya finalizado, se generará un pedido que será registrado en el sistema, contando con el usuario dueño del pedido, los productos que lo forman, el precio...etc.

Partes:
* La parte pública del sistema será la libre visualización de productos, búsqueda de los mismos y navegación por la página.
* La parte privada del sistema será la compra de productos, el acceso al perfil del usuario, la utilización del ranking, la publicación de comentarios, la administración y gestión de la web.

La aplicación dispondrá de dos sistemas:
* Sistema de compra - venta: descrito anteriormente.
* Sistema interno de información: la aplicación principal se comunicará con un servicio externo de generación de informes sobre la tienda (pedidos, stock, usuarios...). Este sistema será una funcionalidad únicamnete accesible por los administradores de la tienda, que podrán encontrar en el apartado de administración de la página web.

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
	* Tendrá una clave foránea referente al usuario que provee el producto.
 ```
* Pedido
```sh
	Esta entidad representa a los pedidos del sistema
	* Trendrá una clave foránea referente al usuario que realiza el pedido.
	* Tendrá claves foráneas a los productos del pedido.
 ```
 * Rating
 ```sh
	Esta entidad representa a los ratings de los pedidos
	* Tendrá una clave foránea referente al usuario que realiza el pedido.
	* Tendrá una clave foránea al pedido en cuestión.
 ```

### Tecnologías

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

### Instalación

1. Clona el repositorio
   ```sh
   git clone https://github.com/AlvaroMartinezQ/clickandbuy.git
   ```
2. TODO



<!-- EJEMPLOS DE USO -->
## Uso

TODO: ejemplos de uso de la aplicación


<!-- ROADMAP -->
## Mapa de trabajo

TODO: mapa de desarrollo (semanas, hitos, metas...)

<!-- CONTRIBUCIONES -->
## Contribuciones

Las contribuciones a este proyecto están cerradas ya que es un desarrollo de aplicación para un proyecto final de la universidad. Sin embargo, **el reporte de algún bug se apreciará por los desarrolladores**.

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

