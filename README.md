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
Un usuario podrá añadir productos a su cesta de la compra de forma preliminar a su compra. Cuando éste haya finalizado, se generará un pedido que será registrado en el sistema, contando con el usuario dueño del pedido, los productos que lo forman, el precio... etc.

Partes:
* La parte pública del sistema será la libre visualización de productos, búsqueda de los mismos y navegación por la página.
* La parte privada del sistema será la compra de productos, el acceso al perfil del usuario, la utilización del ranking, la publicación de comentarios, la administración y gestión de la web.

La aplicación dispondrá de dos sistemas:
* Sistema de compra - venta: descrito anteriormente.
* Sistema interno de información: la aplicación principal se comunicará con un servicio externo de generación de informes sobre la tienda (pedidos, stock, usuarios...). Este sistema será una funcionalidad únicamnete accesible por los administradores de la tienda, que podrán encontrar en el apartado de administración de la página web.

Entidades:

* Usuario
```sh
	Esta entidad representa a los usuarios del sistema.
	* Cada usuario tendrá, si es proveedor, tantos productos como este quiera publicar.
 ```
* Administradores
```sh
	Esta entidad representa a los administradores del sistema.
 ```
 
Hay 3 roles de administradores principales del sistema, estos son:
* Manager: este usuario será el responsable de la tienda y/o jefe. En principio solo habrá 1 usuario manager. Solo él podrá añadir nuevos administradores, dar de baja usuarios y productos, modificar valores en las tablas...
* Técnico: este usuario será el responsable de controlar a los usuarios y productos. También será capaz de recoger informes de la tienda con el futuro servicio a implementar.
* Staff: este usuario será responsable de controlar los productos.

* Producto
```sh
	Esta entidad representa a los productos del sistema.
 ```
* Pedido
```sh
	Esta entidad representa a los pedidos del sistema.
	* Trendrá una clave foránea referente al usuario que realiza el pedido.
	* Tendrá claves foráneas a los productos del pedido.
```
Los pedidos funcionarán de la siguiente manera: cada vez que se crea un nuevo pedido con n productos se guardarán n entradas en la tabla de pedidos con un id único. Si se quiere añadir un nuevo pedido a un pedido existente, se ha de proporcionar el id único de ese pedido, si no existe se crea un pedido nuevo.
 
* Rating
```sh
	Esta entidad representa a los ratings de los pedidos.
	* Tendrá una clave foránea referente al usuario que realiza el pedido.
	* Tendrá una clave foránea al pedido en cuestión.
```
<br />
Diagramas de la aplicación:

* UML

<a href="https://github.com/AlvaroMartinezQ/clickandbuy">
    <img src="/statics/uml/uml_fase2.jpg" alt="uml">
</a>
  
* Modelo E/R

<a href="https://github.com/AlvaroMartinezQ/clickandbuy">
    <img src="/statics/uml/ModeloER.PNG" alt="modeler">
</a>

Pantallas de navegaci�n:

*Main
  
### Tecnologías

* [Java](https://www.java.com/es/)
* [Spring](https://spring.io/)
* [Docker](https://www.docker.com/)
* [MySQL](https://www.mysql.com/)
* [Bootstrap](https://getbootstrap.com/)



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
	Modificar el fichero application.properties con tu usuario y contraseña.
	```
	NOTA: renombrar el fichero application.properties.template a application.properties en la carpeta donde este se encuentra 
	
* En caso de usar Docker:
  ```sh
  TODO: futuros comandos.
  ```

### Instalación

1. Clona el repositorio
   ```sh
   git clone https://github.com/AlvaroMartinezQ/clickandbuy.git
   ```
2. TODO



<!-- EJEMPLOS DE USO -->
## Uso

Si iniciamos la aplicación a través de Eclipse, esta será desplegada y accesible a través de la ip y puerto <strong>127.0.0.1:8080</strong>

* Página de inicio: <url -> 127.0.0.1:8080/> home del sitio web. En esta pantalla somos capaces de registrarnos, continuar al sitio web sin acceder a ninguna cuenta, o acceder al sitio web con, por el momento, una simulación de login. Los botones siguientes recuperar datos e informar de una incidencia están aún por aprobar su desarrollo y uso.

<img src="/statics/navimgs/home.JPG" alt="home">

* Página de crear cuenta: <url -> 127.0.0.1:8080/createaccount>. En esta página y a través de un formulario podemos registrarnos como usuarios en la web.

<img src="/statics/navimgs/createAcc.JPG" alt="newacc">

* Página de inicio de sesión: <url -> 127.0.0.1:8080/login>. En esta página y a través de un formulario podemos entrar en la web y, por el momento, simular un login.

<img src="/statics/navimgs/login.JPG" alt="login">

* Página principal de visualización: <url -> 127.0.0.1:8080/productView>. Es esta página se muestran todos los productos a la venta a la vez que una serie de botones de enlaces rápidos que redireccionan a páginas de gestión de la web.

<img src="/statics/navimgs/mainView.JPG" alt="mainview">

* Página de error: <url -> cualquiera que no exista, por ejemplo: 127.0.0.1:8080/no_funciono>. Esta página es una redirección de /error cuando ocurre un fallo en el servidor, ya sea un error 403, 500, 400... También se ha ocultado información sensible respectiva al servidor de que sea imprimida por pantalla en el navegador. 

<img src="/statics/navimgs/error.JPG" alt="error">


<!-- ROADMAP -->
## Mapa de trabajo

TODO: mapa de desarrollo (semanas, hitos, metas...)

<!-- CONTRIBUCIONES -->
## Contribuciones

Las contribuciones a este proyecto están cerradas ya que es un desarrollo de aplicación para un proyecto final de la universidad. Sin embargo, **el reporte de algún bug se apreciará por los desarrolladores**.

Todo el nuevo código debe de subirse a una nueva rama, ¡no subir cambios directamente a la rama <strong>master</strong>!

1. Crea la rama para tu desarrollo (`git checkout -b feature-<nombre de la nueva característica>`).
2. Haz commit de tus cambios, no pases los 50 caracteres en la descripcion (`git commit -m 'feature: '<descripción>'`).
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

Este proyecto está sujeto a la licencia `Apache License 2.0`. Para obtener más información sobre esta, acceder al fichero LICENSE.

<!-- CONTACTO -->
## Contacto

* Alvaro Martinez - a.martinezq.2017@alumnos.urjc.es - https://github.com/AlvaroMartinezQ
* Patricia Tarazaga - p.tarazaga.2018@alumnos.urjc.es - https://github.com/patri-create
* Luis Blay - l.blay.2017@alumnos.urjc.es - https://github.com/lblay

<!-- AGRADECIMIETNOS -->
## Agradecimientos

* [Universidad Rey Juan Carlos](https://www.urjc.es/)

