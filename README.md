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

Partes del servicio principal:
* La parte pública del sistema será la posibilidad de crear usuarios para navegar por la web.
* La parte privada del sistema será la compra de productos, el acceso al perfil del usuario, el acceso al merketplace del sistema, el uso del sistema de ranking, la publicación de productos, la administración y gestión de la web... Entre otros.

La aplicación dispondrá de dos sistemas:
* Sistema de compra - venta: descrito anteriormente.
* Sistema interno de información: la aplicación principal se comunicará con un servicio externo de generación de informes sobre la tienda (pedidos, stock, usuarios...). También dispone de un chat en directo desarrollado con **web sockets**.

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
 
Hay 2 roles de administradores principales del sistema, estos son:
<p>
:arrow_forward: Manager: este usuario será el responsable de la tienda y/o jefe. Habrá un Manager inicial, ya que con éste usuario, podremos dar de alta nuevos administradores, dar de baja usuarios, modificar y eliminar productos, limpiar ratings, modificar valores en las tablas...
</p>
<p>
:arrow_forward: Staff: este usuario será responsable de controlar los productos y resolver dudas por el chat del sistema.
</p>

* Producto
```sh
	Esta entidad representa a los productos del sistema.
 ```
* Pedido
```sh
	Esta entidad representa a los pedidos del sistema.
	* Trendrá una clave foránea referente al usuario que realiza el pedido.
	* Cada pedido generado tendrá una lista de carritos.
```
:arrow_forward: Los pedidos funcionarán de la siguiente manera: cada vez que se crea un nuevo pedido se crea un meta-pedido que contiene tantas unidades de ese producto como el pedido al que corresponde. La entidad Pedido también tendrá una clave foránea al usuario que lo ha solicitado.

* Carro
```sh
	Esta entidad representa el carrito de compras del sistema.
	* El carrito está compuesto de productos.
```
* Rating
```sh
	Esta entidad representa a los ratings de los pedidos.
	* Tendrá una clave foránea referente al usuario que compra un producto.
	* Tendrá una clave foránea al producto en cuestión.
```
<br />
Diagramas de la aplicación:

* UML

<!--
<a href="https://github.com/AlvaroMartinezQ/clickandbuy">
    <img src="/statics/uml/uml_fase2.jpg" alt="uml">
</a>
-->

TODO

* Modelo E/R


<a href="https://github.com/AlvaroMartinezQ/clickandbuy">
    <img src="/statics/modeloer/ModeloER.JPG" alt="modeler">
</a>  


Pantallas de navegación:  

<br>Pantalla principal, home  
  
<a href="https://github.com/AlvaroMartinezQ/clickandbuy">
    <img src="/statics/pantallas/new/home.JPG" alt="modeler">
</a>  
<br>Pantalla para crear usuario, sing up  
  
<a href="https://github.com/AlvaroMartinezQ/clickandbuy">
    <img src="/statics/pantallas/new/register.JPG" alt="modeler">
</a>  
<br>Pantalla para acceder al sistema, sing in  
  
<a href="https://github.com/AlvaroMartinezQ/clickandbuy">
    <img src="/statics/pantallas/new/login.JPG" alt="modeler">
</a>  
<br>Pantalla principal de la tienda, marketplace  
  
<a href="https://github.com/AlvaroMartinezQ/clickandbuy">
    <img src="/statics/pantallas/new/marketplace.JPG" alt="modeler">
</a>  
  

### Tecnologías

* [Java](https://www.java.com/es/)
* [Maven](https://maven.apache.org/)
* [Spring](https://spring.io/)
* [Docker](https://www.docker.com/)
* [MySQL](https://www.mysql.com/)
* [Bootstrap](https://getbootstrap.com/)



<!-- EMPECEMOS -->
## Empecemos

Para descargar una copia local del proyecto sigue los siguientes pasos :rocket:

### Pre requisitios

Pasos:
* Maven
	```sh
	Instalar Maven para correr las aplicaciones.
	```

En caso de descargar el repositorio en local:
* MySQL
	```sh
	Instalar MySQL Server en local.
	```
	```sh
	Levantar una instancia de MySQL Server.
	```
	```sh
	Crear una base de datos con el nombre que prefieras. Recomendamos <clickandbuy>.
	```
	Opcional: crear un usuario y darle privilegios en la base de datos creada. Si no, utilizar el usuario root del sistema.  
	  
	Renombrar el fichero application.properties.template dentro de clickandbuy-app a application.properties en la carpeta donde este se encuentra. Cambiar la BD usada (el nombre) al igual que el usuario y su contraseña. Renombrar el fichero application.properties.template dentro de clickandbuy-api a application.properties (en este caso no hacen falta más cambios en el fichero).  
	```sh
	Completar el fichero application.properties con tu usuario, contraseña y nombre de la DB recién creados.
	``` 
	  
	También es necesario renombrar los ficheros config.properties.template a config.properties <strong>en ambas aplicaciones</strong> e incluir un cliente de correo GMAIL (dirección de email) y su contraseña para que la aplicación sea capaz de enviar correos electrónicos, si no este apartado de la aplicación no funcionará. Sin embargo la aplicación es completamente funcional sin estos datos (imprimirá un mensaje por consola pero se puede obviar).  
  
* Docker:
  ```sh
  TODO: futuros comandos.
  ```

### Instalación

1. Clona el repositorio.
   ```sh
   git clone https://github.com/AlvaroMartinezQ/clickandbuy.git
   ```
2. Levantar instancias de las APPs. Navegar a las carpetas del proyecto en dos terminales diferentes.

Si se quiere correr la aplicación sin ficheros JAR. Desde la carpeta raíz del proyecto lanzar el siguiente comando <strong>en ambas terminales previamente abiertas</strong>:
   ```sh
   mvn spring-boot:run
   ```

Si se quiere correr la aplicación con ficheros JAR. Desde la carpeta raíz del proyecto lanzar el siguiente comando <strong>en ambas terminales previamente abiertas</strong> para compilar las aplicaciones:
   ```sh
   mvn package
   ```
Navegar a la carpeta target del proyecto y lanzar el siguiente comando <strong>en ambas terminales previamente abiertas</strong>:
   ```sh
   java -jar clickandbuy-app-0.0.1-SNAPSHOT.jar
   ```
   ```sh
   java -jar clickandbuy-api-0.0.1-SNAPSHOT.jar
   ```

<!-- EJEMPLOS DE USO -->
## Uso

Servicio principal ->

1 ejemplo sencillo de como crear un usuario y entrar al sistema.

TODO

1 ejemplo sencillo de como modificar los datos de usuarios.

![changeUserData](https://user-images.githubusercontent.com/46459810/114321137-79824400-9b19-11eb-97d8-269d1e6aef23.gif)

1 ejemplo sencillo de como subir un producto.

TODO

Servicio interno ->

1 ejemplo de como pedir reportes de usuarios o información personal.

TODO

1 ejemplo con dos cuentas simultáneas en diferentes navegadores del chat del sistema. El usuario de la izquierda es un usuario administrador del sistema mientras que el de la derecha es un usuario normal.

![userChat](https://user-images.githubusercontent.com/46459810/114321248-1a70ff00-9b1a-11eb-88fb-f7e72f8601a6.gif)


<!-- ROADMAP -->
## Mapa de trabajo

TODO: mapa de desarrollo (semanas, hitos, metas...)

<!-- CONTRIBUCIONES -->
## Contribuciones

Las contribuciones a este proyecto están cerradas ya que es un desarrollo de aplicación para un proyecto final de la universidad. Sin embargo, **el reporte de algún bug se apreciará por los desarrolladores**. :octocat:

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

Este proyecto está sujeto a la licencia `Apache License 2.0`. Para obtener más información sobre esta, acceder al fichero <a href="https://github.com/AlvaroMartinezQ/clickandbuy/blob/master/LICENSE">LICENSE</a>.

<!-- CONTACTO -->
## Contacto

* Alvaro Martinez - a.martinezq.2017@alumnos.urjc.es - <a href="https://github.com/AlvaroMartinezQ">Github</a>
* Patricia Tarazaga - p.tarazaga.2018@alumnos.urjc.es - <a href="https://github.com/patri-create">Github</a>
* Luis Blay - l.blay.2017@alumnos.urjc.es - <a href="https://github.com/lblay">Github</a>

<!-- AGRADECIMIETNOS -->
## Agradecimientos

* [Universidad Rey Juan Carlos](https://www.urjc.es/)

