# Detector de Mutantes 📡
![image](https://github.com/user-attachments/assets/684b1d11-40ed-42c8-af9b-1ae4d39272c3)


## Acerca del repositorio
El presente repositorio corresponde al famoso ejercicio técnico de Mercado Libre sobre los mutantes. El mismo fue realizado como entrega para el primer parcial de la materia Desarrollo de Software correspondiente a la carrera Ingeniería en Sistemas de Información.

## Consigna del ejercicio
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Mens. 
Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN. 
Para eso te ha pedido crear un programa con un método o función con la siguiente firma: 

                                              boolean isMutant(String[] dna); 

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.
Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical. 
**Niveles a realizar:**
1)  Programa que cumpla con el método pedido por magneto
2)  Crear una API REST, hostear esa API en un cloud computing libre, crear el servicio “/mutant/” en donde se pueda detectar si un humano es mutante enviando la secuencia de ADN mediante un HTTP POST con un Json.
3)  Anexar una base de datos, la cual guarde los ADN’s verificados con la API. Solo 1 registro por ADN.  Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las verificaciones de ADN.

## Instrucciones de cómo utilizar la API y características
La API utiliza la base de datos H2. 
Está hosteada en el cloud computing libre Render: https://mutantesxmen.onrender.com .
Podremos interactuar con la misma mediante un cliente como Postman. Veamos un ejemplo.

### Método POST (nivel 2)
En Postman se crea una request o solicitud de tipo POST y se coloca en el cuerpo de la misma, en formato JSON, la información que se quiere enviar. En nuestro caso enviaremos, para una persona, su nombre, apellido, edad y secuencias de ADN. Este método POST accede al endpoint "/mutant" por lo que la solictud se verá de esta forma:

                                        https://mutantesxmen.onrender.com/mutant
y el cuerpo de la misma así:
```
{
  "nombre": "Pedro",
  "apellido": "Gomez",
  "edad": 55,
  "adn": [
        "ACGT",
        "CAGT",
        "TTAT",
        "AGAC"
      ]
}
```
Esta solicitud, al ser enviada, guardará en la base de datos a Pedro Gómez y devolverá un código status `403 FORBIDDEN` ya que, debido a sus secuencias de ADN, esta persona no es un mutante.
Si quisiéramos ahora enviar a alguien que, debido a sus secuencias de ADN, sí es mutante, obtendremos un código status `200 OK`.

```
{
    "nombre": "Wade",
    "apellido": "Wilson",
    "edad": 35,
    "adn": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCATA",
        "TCACTG"
    ]
}
```
Las secuencias de ADN de estas dos personas son distintas no solo porque una de ellas es mutante y la otra no, sino porque además son de distintos tamaños. El detector de mutantes requiere que la matriz que se ingrese:
- sea de NxN (es decir, cuadrada)
- no posea bases nitrogenadas incorrectas (letras indebidas)
- no sea vacía
- no sea de tipo null

Llegado el caso en el que la matriz no cumpla con los ítems anteriores obtendremos un mensaje de error:
                                    `{"error":"Error, por favor intente más tarde"}`.
El error específico está programado para mostrarse por la "consola".

### Método GET STATS (nivel 3)
En Postman se crea un request o solicitud de tipo GET. La misma accede al endopoint "/stats" y está destinada a brindar información acerca de cuántos humanos y mutantes hay, y el ratio entre estos. La solictud se ve así:
                                    `https://mutantesxmen.onrender.com/stats`
y su respuesta, en este caso, es:
                                `{count_mutant_dna:7, count_human_dna:3, ratio:2.0}`

### Otros métodos
Se implementaron también otros métodos. A todos ellos se accede con "/mutant"
- GET ALL, para obtener todas las personas en la base de datos ya sean mutantes o no: `https://mutantesxmen.onrender.com/mutant`
- GET ONE, para obtener una persona específica: `https://mutantesxmen.onrender.com/mutant/2`
- GET DTO, para obtener información reducida acerca de un mutante. A diferencia de los dos anteriores, este solo muestra el nombre, apellido y si es mutante o no: `https://mutantesxmen.onrender.com/mutant/DTO/2`
- DELETE, para eliminar a una persona específica: `https://mutantesxmen.onrender.com/mutant/3`
- UPDATE, para actualizar a una persona específica. `https://mutantesxmen.onrender.com/mutant/1`

## Pruebas de Stress
Se realizaron con JMeter. Se simuló hasta 1110 usuarios consultando a la vez:
- Rendimiento 6.7/sec
- %Error 0,009%
