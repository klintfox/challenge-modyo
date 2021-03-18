# Challenge-Modyo

# Instrucciones
Para el back-end se pide construir una API REST usando Java Spring Boot que exponga la información al Pokedex. 
Esta API debe consumir el servicio PokeApi https://pokeapi.co/ para obtener la información. La documentación la puedes encontrar en https://pokeapi.co/docs/v2

Desde el lado de frontend solo necesitaremos las diferentes llamadas por curl o postman (esto es parte del entregable) a las APIs expuestas y poder obtener por cada uno de los pokemones (foto y su información básica):
- Tipo (type)
- Peso (weight)
- Listado de Habilidades (ability)

También, al pinchar sobre uno se debe mostrar su ficha descriptiva junto a su foto e información detallada:
- Información Básica (la misma del listado)
- Descripción
- Evoluciones

- Si deseas (opcional) puedes hacer un front-end que haga las llamadas mencionadas arriba, y puedes elegir el lenguaje de tu preferencia.

La aplicación debe ser desplegada en AWS, Azure, GCP o Heroku, a elección. Es importante que documentes cómo se realiza el despliegue. El código de la aplicación debe estar alojado en algún repositorio Git al que debes darnos acceso. 

Cualquier funcionalidad extra es bienvenida. Idealmente se espera que la aplicación cuente con tests y que las respuestas de Poke Api sean almacenadas en una capa de caché para mejorar el rendimiento de las respuestas.  ¡tendrá puntuación extra! 

Lo que evaluaremos con este desafío, es que seas capaz de: 
- Integrar una API externa.
- Exponer un API usando Java Spring Boot.
- Consumir API desde front-end 
- Desplegar en la nube.
- Manejar errores.
- Mantener un código ordenado y de calidad. 

# Solución

## Docker 

- Paso 1: Construir la imagen en dockerDesktop con el siguiente comando
 ```sh 
 docker build -t kfroman/pokedex:0.0.2.RELEASE .  
  ```

- Paso 2: ejecutar imagen construida localmente para comprobar que no hay errores con el siguiente comando
 ```sh 
 docker run -d -p 9152:9152 --name=pokedex kfroman/pokedex:0.0.2.RELEASE 
 ```

- Paso 3: Realizar el push de imagen construida a DockerHub con el siguiente comando
```sh 
docker push kfroman/pokedex:0.0.2.RELEASE
```
- Releases Realizados pen DockerHub
 ```sh 
 https://hub.docker.com/repository/docker/kfroman/pokedex
 ```
## GCloud
- Paso 1: Crear un Cluster 
- Paso 2: Conectarse al cluster por consola (obtener informacion del cluster para conexión)
- Paso 3: Crear el despliegue con el siguiente comando por consola
```sh 
kubectl create deployment pokedex --image=kfroman/pokedex:0.0.2.RELEASE 
```

- Paso 4: Exponer el servicio con el siguiente comando
```sh 
kubectl expose deployment pokedex --type=LoadBalancer --port=9152 
```

#Postman

En el repositorio se encuentra el archivo Api Poledex.postman_collection.json el cual se puede importar y probar ambos métodos para cumplir con los puntos indicados en el texto ocomo alternativa se pueden usan los siguientes enlaces 

- Requerimiento 1: Verbo Get - Url: http://34.69.52.7:9152/v1/pokemon/basic-information/pikachu
- Requerimiento 2: Verbo Get - Url: http://34.69.52.7:9152/v1/pokemon/advance-information/pikachu
