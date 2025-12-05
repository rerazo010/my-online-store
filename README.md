PROCESO DE INSTALACIÓN Y CONFIGURACIÓN

- Instalar sql server express edition
  * instalar desde un contenedor Docker para mas facilidad
  * configurarlo en puerto 1433
  * establecer su usuario y contraseña que posteriormente le pasara a los demás servicios
  * las tablas se crearan automáticamente cuando cargue cada servicio

- Descargar proyecto el cual contiene 7 servicios y 1 carpeta de configuración 

- Configurar servicios 
  * configurar config-service 
     . Crear un repositorio en GitHub para guardar archivo .yml de configuración que se encuentran en la carpeta microservice-config
     . Proporcionar acceso mediante tocken de Github en archivo de configuración application.yaml
     . Dejar cargada la carpeta microservice-config en repositorio en la rama main
     . Proporcionar una key de encriptación para servicio de configuración en la propiedad encrypt.key en el archivo application.yaml

  * Los demás servicios que son:
     . client-service
     . order-service
     . payment-service
     . product-service
     Estos servicio cargaran su configuración para el archivo application.yaml desde el servicio config-service

- Servicio discovery-service, este contiene el servidor eureka server en el cual se registran los demás servicios
  * Establecer clave de acceso colocando lo que desee, tomar en cuenta que esta clave se le proporciona a los demás servicios para que puedan conectarse 
  Esto en las variables de entorno ${user_eureka} y ${pass_eureka}

- Orden de carga de los servicios
  * 1 discovery-service, para que pueda registrar los demás servicios
  * 2 config-service, para que los demás servicios puedan encontrar su archivo de configuración en github
  * 3 gateway-service, para la salida de todos los servicios por el puerto 8080
  * 4 cargar el resto ser servicios sin importar el orden

- Ser proporciona un set de pruebas de postman en el archivo my_store.postman_collection.json 
- para las pruebas en la aplicación Postman hacer los siguiente:
  * crear un usuario en client
  * hacer login en client colocando el token proporcionado por el enpoint de login en el tipo de autenticación Bearer Token
  * de esta misma forma puede consultar productos, crear órdenes y pagar