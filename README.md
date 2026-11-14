# Palabras hijas — juego de anagramas en Java

Juego de consola en Java en el que hay que descubrir una palabra a partir de sus letras desordenadas y encontrar las palabras que se pueden formar con ellas.

> Trabajo final de la asignatura **Fundamentos de Programación** · Grado en Ingeniería Informática · Universidad de Valladolid · Curso 2021-2022

## Qué hace

- Elige al azar una **palabra base** de 6 o más letras de `diccionario.txt` y la muestra desordenada.
- Busca en el diccionario sus **palabras hijas**: palabras más cortas que se forman solo con letras de la base, sin repetir ninguna letra más veces de las que aparece.
- Elige 5 hijas al azar como **hijas previstas** y las muestra como huecos (`_____`) que se rellenan al acertarlas.
- Puntúa cada acierto: la palabra base y las hijas previstas suman tantos puntos como letras tienen; cualquier otra hija del diccionario suma un punto extra.
- Avisa de entradas no válidas (`Palabra invalida`), de palabras que no están en el diccionario y de palabras ya acertadas.
- Guarda la puntuación acumulada entre partidas en `puntuacion.txt` y pregunta si se quiere jugar otra partida.

## Tecnologías

- Java (solo biblioteca estándar: `java.util.Scanner`, `java.io`, `Math.random`).

## Aspectos técnicos destacados

- **Programación estructurada con métodos estáticos**: toda la lógica está dividida en funciones pequeñas (`desordenar`, `esPalabraHija`, `esValido`, `palabrasHijasPrevistas`, ...) que llama `main`.
- **Comprobación de palabras hijas**: `esPalabraHija` recorre las letras de la candidata y elimina de la base cada letra usada, de modo que no se puede usar una letra más veces de las que tiene la base.
- **Desordenado sin repeticiones**: `desordenar` usa un array de `boolean` para no elegir dos veces la misma posición al barajar las letras.
- **Ficheros de texto**: el diccionario se lee con `Scanner` sobre `File`, y la puntuación se escribe con `PrintWriter`.
- El diccionario está ordenado por longitud decreciente; el programa se apoya en ese orden para localizar las palabras base.

## Estructura del repositorio

```text
FProg/
├── practica.java     # Programa completo (clase practica)
└── diccionario.txt   # Diccionario de palabras, una por línea
```

## Requisitos

- JDK (cualquier versión reciente con `javac` y `java`).

## Cómo compilar y ejecutar

Desde la carpeta del repositorio, para que el programa encuentre `diccionario.txt`:

```bash
javac practica.java
java practica
```

En cada turno se escribe una palabra y se pulsa Intro. Al terminar una partida, escribe `si` para jugar otra. El programa crea `puntuacion.txt` en el directorio de trabajo.

## Autoría

Trabajo en pareja (grupo Z9), según la cabecera de `practica.java`:

- Javier García González
- Javier Esgueva Sto. Domingo

---

Forma parte de [UVa-Projects](https://github.com/JavivuG/UVa-Projects).
