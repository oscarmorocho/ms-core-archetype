<h1 align="center">
    <br>
	Micro Service ms-ddd-archetype
	<br>
  <a href="https://quarkus.io/"><img src="https://design.jboss.org/quarkus/logo/final/PNG/quarkus_logo_horizontal_rgb_1280px_default.png" alt="quarkus_logo_horizontal_rgb_1280px_default" width="100">
  </a>
  <a href="https://www.graalvm.org/"><img src="https://www.graalvm.org/resources/img/home/logo-coloured.svg" alt="graalvm" width="100">
  </a>
  <a href="https://www.docker.com/"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Docker_%28container_engine%29_logo.svg/1024px-Docker_%28container_engine%29_logo.svg.png" alt="docker" width="100">
  </a>
</h1>

<p align="center">
	<a href=""><img src="https://img.shields.io/badge/build-check status-green?logo=jenkins&logoColor=white&style=plastic" alt="Jenkins"></a>
  	<a href="https://apiservicechile.slack.com/ssb/redirect?entry_point=get_started"><img src="https://img.shields.io/badge/Slack-Join our channel-purple?logo=slack&style=plastic" alt="slack-99"></a>
  	<a><img src="https://img.shields.io/badge/Quarkus-v1.3.2-blue?logo=quarkus&style=plastic" alt="quarkus"></a>
  	<a><img src="https://img.shields.io/badge/GraalVM-20.3-orange?logo=java&style=plastic" alt="graalvm"></a>
</p>


## Table of Contents
- [Descripción del Servicio](#Descripción-del-servicio)
- [Primeros pasos](#Primeros-pasos)
    - [Pre-requisitos](#Pre-requisitos)
- [Testing](#Testing)
    - [Tests Unitario](#Tests-Unitario)
    - [Tests de Integración](#Tests-de-Integración)
- [Despliegue](#Despliegue)
- [Construido con](#Construido-con:)
- [Versionamiento](#Versionamiento)
- [Autores](#Autores)
- [Contacto](#Contacto)
- [Contribución](#Contribución)

## Descripción del servicio

Este arquetipo esta basado en domain-driven design (DDD) and hexagonal architecture.
https://apiservice2.atlassian.net/wiki/spaces/DE/pages/772735342/1.4.1+DDD+en+simple
https://apiservice2.atlassian.net/wiki/spaces/DE/pages/802390443/1.1.1+Arquitectura+hexagonal

En el podemos encontrar ejemplos funcionales de como:
1- Exponer una interfaz gRPC.
2- Consumir un servicio gRPC.
3- Exponer servicios Rest.
4- Como conectar con una cola MQ.
5- Estructura basada en domain driven design.

### Pre-requisitos

Los reqisitos para usar este proyecto estan mencionados en el siguiente link:
https://apiservice2.atlassian.net/wiki/spaces/DE/pages/726466601/Creaci+n+de+un+microservicio#Pre-requisitos

Para la ejecución de forma corecta de este template debemos tener acceso a una base de datos en postgres, la cual debemos configurar en los properties.

## Primeros pasos
Para ejecutar el proyecto en local debemos usar el siguiente comando:
```
./mvnw compile quarkus:dev
```

### Despliegue

El despliegue del componente se hace mediante el pipeline. Para el cual el rol de devops deberá crear el pipeline para el componente.

---

## Testing

Se debe explicar como se ejecutan las pruebas, si son automatizadas y de existir un reporte, como lo persistimos para quede traza del mismo.


### Tests Unitarios

Para comprobar el reusltado de los test se debe ejecutar el comando:

```
mvn test
```

### Tests de Integración

Para ejecutar los test de integración en la tarpeta test debejos primero installar los modulos npm.

```
npm install
```
y luego ejecutar:
```
npm test
```


## Construido con:
* [Quarkus](https://github.com/quarkusio/quarkus) - El framework utilizado.
* [Maven](https://maven.apache.org/) - La herramienta de compilacion.
* [GraalVM](https://www.graalvm.org/) - El runtime para el proceso compilación.
* [SDKman](https://sdkman.io/) - SDK Manager

## Versionamiento

PENDIENTE.

## Autores

* **Mauricio Ponce** - *Initial work* - [mponce-apiservice](https://github.com/mponce-apiservice)
* **Eduardo Rosales** - *Initial work* - [skilledboy](https://github.com/skilledboy)

Vease tambien la lista [contribuidores](https://github.com/skilledboy/tarjeta-credito) quienes participaron en el proyecto.


## Contacto

Debemos mencionar los datos de contacto de quienes gestionan el proyecto:

* **Mauricio Ponce** - [Escríbeme](mponce@apiservice.cl)

Adicionalmente, unete a la discucion en los canales de comunicacion:   <a href="https://apiservicechile.slack.com/ssb/redirect?entry_point=get_started"><img src="https://img.shields.io/badge/Slack-Join our channel-purple?logo=slack&style=plastic" alt="slack-99"></a>

## Contribución

Se debe especificar el proceso para empezar a trabajar sobre el proyecto y como se gestionan las contribuciones sobre el mismo.

