# Decisiones tomadas:

Utilizacion de estructura Treemap<k,v>
Permite buscar por piso o techo mas proximo a un valor mediante busqueda binaria, permitiendo la busqueda en forma logaritmica.
Permite insertar en forma constante debido a que los tiempos van llegando en orden.

El promedio de toda la serie se recalcula a medida que la serie se va cargando a la memoria.

---------------------------------

# Info sobre los endpoints:

## Base endpoint:
http://localhost:9290/bitcoiner/

## Informacion sobre los endpoints:

Los query params utilizados se expresan en formato timestamp expresado de la siguiente manera:
Patron: yyyy-MM-dd'T'HH:mm:ss
Ejemplo: 2021-01-20T17:35:49

### Valor en un timestamp
### http://localhost:9290/bitcoiner/currency
Utiliza un query param: timestamp
Ejemplo de la url
http://localhost:9290/bitcoiner/currency?timestamp=2021-01-21T14:35:49

Ejemplo de respuesta:
{
    "dollarsEquivalent": 34990.0,
    "timestamp": {
        "epoch": 1611166721516
    }
}

### Promedio entre dos timestamp y diferencia con el promedio de toda la serie:
### http://localhost:9290/bitcoiner/average
Utiliza 2 query param: timestamp1 y timestamp2
Ejemplo de la URL:
http://localhost:9290/bitcoiner/average?timestamp1=2021-01-20T17:35:49&timestamp2=2021-01-20T17:35:56

Ejemplo de respuesta:
{
    "average": 35073.1,
    "averageAll": 35096.84285714286,
    "difference": -0.06769534812394795
}

# Como ejecutar el proyecto:

Clonarse el proyecto https://github.com/matiasrosemberg/bitcoiner/

Abrir la consola y correr el siguiente comandos.

mvn clean install

Luego de ello se generará la carpeta target.

Por ultimo, dentro de la carpeta principal del proyecto, ejecutar el siguiente comando en la consola:

java -jar target/bitcoiner-0.0.1-SNAPSHOT.jar 

Esperar 5 segundos y listo!

