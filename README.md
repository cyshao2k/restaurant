# API Restaurant

El api restaurant es un proyecto realizado para completar una prueba técnica de evaluación.

## Ejecutar la aplicación

* Clonar el repositorio al equipo local

```
$ git clone {URL}
```

* Ejecutar la aplicación

```
$ mvn spring-boot:run
```

## Historias de usuario

* Encontrar mesa disponible

```
El cliente llega al restaurante y se le debe asignar una mesa disponible
```

* Entregar el Menú de comidas

```
El cliente revisa el menú para elegir su orden
```

* Ordenes de comida

```
El cliente solicita un grupo de platos, que puede ser en diferentes ocaciones, agregando ordenes a la mesa
```

* Pagar la cuenta

```
El cliente solicita pagar el total de las ordenes asignadas a la mesa
```

## Diseño

El diseño del API desarrollada se basa en criterios funcionales y no funcionales, tal como el nivel de madures según la escala Richarson.

* GET /restaurant/api/tables

```
Lista el conjunto de mesas del restaurant, con el estado asociado
```

* GET /restaurant/api/dishes

```
Lista el conjuto de platos del menú, disponibles para el cliente
```

* GET /restaurant/api/tables/{id}

```
Obtiene información de la mesa con identificador {id}
```

* PUT /restaurant/api/tables/{id}

```
Body JSON (application/json)

{ "status" : "unavailable" }

Actualiza el estado de la mesa
estados posibles:
- available
- unavailable
```

* POST /restaurant/api/orders

```
Body JSON (application/json)

{
    "table": {
        "id": 1,
        "status": "unavailable"
    },
    "dishes": [
        {
            "id": 4,
            "name": "Frensh Fries",
            "cost": 2000
        },
        {
            "id": 2,
            "name": "Meat",
            "cost": 6000
        }
    ]
}

Ingresa una nueva orden a la mesa asignada
```

* GET /restaurant/api/tables/{id}/orders

```
Lista la totalidad de ordenes asociadas a la mesa
```

* POST /restaurant/api/tables/{id}/bill

```
Genera el pago de las ordenes asignadas a la mesa con id {id}
```

* GET /restaurant/api/bills?filterByDay={date}

```
Lista el conjunto de cuentas pagadas el día {date} definido.
El formato de la fecha debe ser yyyyMMdd, por ejemplo: 20190710 (año 2019, mes 07, día 10)
```

## Arquitectura

La aplicación fue construida como un proyecto Maven Spring Boot y hace uso de una base de datos H2 DataBase.