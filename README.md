# Groupie: La Red Social de Contenido de Pago para Artistas
### Desarrollo de Aplicaciones Web (DAW)
#### Desarrolladores: Pablo Lopez Vidal & Rafael Mancina Castro

## Índice
1. [Introducción](#introducción)
2. [Funcionalidades y Tecnologías Utilizadas](#funcionalidades-y-tecnologías-utilizadas)
3. [Guía de Instalación](#guía-de-instalación)
4. [Guía de Uso](#guía-de-uso)
5. [Documentación](#documentación)
6. [Interfaz de Usuario (Figma)](#interfaz-de-usuario-figma)
7. [Conclusión](#conclusión)
8. [Contribuciones, Agradecimientos, Referencias](#contribuciones-agradecimientos-referencias)
9. [Contacto](#contacto)

## Introducción
**Groupie** es una innovadora red social diseñada específicamente para artistas que desean monetizar su contenido. Los usuarios pueden subir y acceder a contenido exclusivo mediante un sistema de pago, creando una plataforma donde los artistas pueden conectar directamente con sus fans y generar ingresos.

### Justificación
La creciente demanda de plataformas donde los artistas puedan compartir su trabajo y recibir una compensación justa nos llevó a desarrollar **Groupie**. Nuestro objetivo es proporcionar una herramienta eficaz y fácil de usar que satisfaga tanto a los creadores de contenido como a los consumidores.

### Objetivos
- Crear una plataforma segura y confiable para la monetización de contenido.
- Facilitar la conexión directa entre artistas y fans.
- Proporcionar una experiencia de usuario intuitiva y atractiva.

### Motivación
La falta de plataformas dedicadas exclusivamente a la monetización directa del contenido artístico nos motivó a desarrollar **Groupie**. Queremos empoderar a los artistas, ofreciéndoles un espacio donde puedan prosperar económicamente sin intermediarios innecesarios.

## Funcionalidades y Tecnologías Utilizadas
### Funcionalidades
- **Login y Registro**: Sistema seguro de autenticación de usuarios.
  
  <img src="https://github.com/Sukera27/Groupie/assets/122563964/94d96aaa-e5f1-4666-954d-bc3dfb887f26" alt="Login" style="width: 100 height: 200">
  <img src="https://github.com/Sukera27/Groupie/assets/122563964/bc09c83e-e358-48a4-9df9-0e9870714360" alt="Registro" style="width: 100 height: 200">


- **Home**: La página principal muestra el contenido de los artistas a los que sigues, permitiéndote acceder rápidamente a las últimas publicaciones y actualizaciones de tu red de artistas favoritos,tambien cuenta con un boton que muestra los chats que has iniciado.
 <img src="https://github.com/Sukera27/Groupie/assets/122563964/afac6ee1-d921-4c79-a77c-6dcc075a4278" alt="Home" style="width: 100 height: 200">
  <img src="https://github.com/Sukera27/Groupie/assets/122563964/f487cbf3-3cff-488f-b5b0-5858cf69f848" alt="Home" style="width: 100 height: 200">

- **Suscripciones**: Los usuarios a los que estes suscritos apareceran en esta sección.
  
  <img src="https://github.com/Sukera27/Groupie/assets/122563964/6bd5ba95-582c-4a6c-b553-fda92f8f3a56" alt="Home" style="width: 100 height: 200">

- **Perfil de Usuario**: Gestión de perfil, publicaciones y cerrar sesión.
  
    <img src="https://github.com/Sukera27/Groupie/assets/122563964/3882d114-c98a-49c4-833d-2c0a6b3ac10c" alt="Perfil de Usuario" style="width: 100 height: 200">
    <img src="https://github.com/Sukera27/Groupie/assets/122563964/8b4198f8-4566-407a-bd76-345c34c79329" alt="Perfil de Usuario" style="width: 100 height: 200">
    
- **Ventana de Búsqueda**: Herramienta de búsqueda avanzada para encontrar artistas o otros usuarios.
    <img src="https://github.com/Sukera27/Groupie/assets/122563964/1d7e8d8f-4875-4655-9134-d9b3d22be727" alt="Ventana de Búsqueda" style="width: 100 height: 200">
    
- **Perfil de otros Usuarios**: Página de perfil de otros usuarios o artistas donde se pueden ver sus publicaciones. Incluye la opción de seguir al usuario y un chat privado para comunicarse directamente con ellos.También podemos pagar una suscripcion atraves de PayPal y ver su contenido privado.
  
    <img src="https://github.com/Sukera27/Groupie/assets/122563964/3d2d69f5-4738-405e-a844-b07bf4719255" alt="Perfil de otros Usuarios" style="width: 100 height: 200">
    <img src="https://github.com/Sukera27/Groupie/assets/122563964/898a2541-efc0-4f57-a3b2-b58bcf7160ea" alt="Perfil de otros Usuarios" style="width: 100 height: 200">
    
- **Chat Privado**: Chat 1-1 con persistencia de historial.
  
    <img src="https://github.com/Sukera27/Groupie/assets/122563964/b79a6922-aa07-416a-a770-b3e449d8fd76" alt="Chat Privado" style="width: 100 height: 200">
    <img src="https://github.com/Sukera27/Groupie/assets/122563964/b277567e-2fef-4590-8245-a7c23cb11c0c" alt="Chat Privado" style="width: 100 height: 200">


    
### Tecnologías Utilizadas

<img src="https://github.com/Sukera27/Groupie/assets/122563964/add4a25a-8d63-4c5d-9882-1199330242ba" alt="Spring" width="100" height="80" >
<img src="https://github.com/Sukera27/Groupie/assets/122563964/7754ddb1-3f33-4620-bcc1-48258ea00fdf" alt="express" width="100" height="80" >
<img src="https://github.com/Sukera27/Groupie/assets/122563964/3e74fefc-06b2-4eb8-bd73-37448deee9a2" alt="React" width="100" height="80" >
<img src="https://github.com/Sukera27/Groupie/assets/122563964/03d84092-c46e-4d11-b50c-65636cbeb375" alt="NodeJs" width="100" height="80" >
<img src="https://github.com/Sukera27/Groupie/assets/122563964/25d7c155-1203-4717-b744-4d7e86e589da" alt="Socketio" width="100" height="80" >


#### BackEnd
- **MicroServicios**:
 Todos los microservicios están realizados con Spring Boot.
  - **Usuarios**: Gestión de la información de los usuarios, realizado con Spring Security para garantizar la seguridad en la autenticación y autorización.
  - **Publicaciones**: Manejo de contenido subido por los artistas.
  - **Mensajería**: Asegura la persistencia de los mensajes de chat entre los usuarios, garantizando que las conversaciones se mantengan almacenadas y accesibles.
- **ServidorNode**: El servidor Node se encarga de ser el intermediario para recibir los mensajes del chat y enviarlos a los otros usuarios. Está realizado en Node.js, utilizando Express y la tecnología de Socket.io.
#### FrontEnd
- **Cliente React**: Interfaz de usuario.

## Guía de Instalación
1. Clonar el repositorio:
    ```bash
    git clone https://github.com/Sukera27/Groupie
    ```
2. Navegar al directorio del proyecto(Front React):
    ```bash
    cd groupie
    ```
3. Instalar dependencias:
    ```bash
    npm install
    ```
4. Configurar las variables de entorno en un archivo `.env` (localhost).
5. Iniciar Front en React:
    ```bash
    npm start
    ```
6. Navegar al directorio del proyecto(ServerNode):
    ```bash
    cd ServerNode
    ```
7. Iniciar ServerNode:
    ```bash
    npm run dev
    ```
## Guía de Uso
1. Registrarse o iniciar sesión en la plataforma.
2. Explorar el contenido disponible en la página principal.
3. Suscribirse a los artistas favoritos para acceder a su contenido exclusivo.
4. Utilizar la ventana de búsqueda para encontrar nuevos artistas y publicaciones.
5. Gestionar el perfil y las suscripciones desde la sección de usuario.

## Documentación
Consulta la documentación completa del proyecto [aquí](https://docs.google.com/document/d/1scLwYsosC89odxVE4nI8ci9onEjLT0-gBL2m05zuHOw/edit).

## Interfaz de Usuario (Figma)
Accede al diseño de la interfaz de usuario en Figma [aquí](https://www.figma.com/design/6sxkwrkO05aoq29foIWYXg/Untitled?node-id=1-11&m=dev).

## Conclusión
**Groupie** busca revolucionar la forma en que los artistas comparten y monetizan su contenido. Hemos creado una plataforma intuitiva y eficiente que satisface tanto a los creadores como a los consumidores de contenido.

## Contribuciones, Agradecimientos, Referencias
Agradecemos especialmente a todos los profesores que nos han formado en la Escuela Profesional Vedruna de Sevilla. En particular, queremos expresar nuestra profunda gratitud a Joaquín, quien siempre ha ido más allá, motivándonos a superarnos y marcando la diferencia en nuestra formación.


## Contacto
Para cualquier consulta o sugerencia, por favor contacta a:
- **Pablo Lopez Vidal & Rafael Mancina Castro**
- **Correo Electrónico Rafael:** rafael.mancina@a.vedrunasevillasj.es 
- **Correo Electrónico Pablo:**  pablo.lopez@a.vedrunasevillasj.es 
- **LinkedIn Rafael:** https://www.linkedin.com/in/rafael-mancina-castro-a53a68209/
- **LinkedIn Pablo:** https://www.linkedin.com/in/pablolopezvidal/
