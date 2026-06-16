# Proyecto base — Parcial ARSW

Estructura Maven lista. Java 21. JUnit incluido por si hay pruebas.

## Estructura
```
parcial/
├── pom.xml
└── src/
    ├── main/java/edu/eci/arsw/   <- aquí van clases
    └── test/java/edu/eci/arsw/   <- aquí van los tests
```

## Comandos

Compilar (revisar que el código no tenga errores):
```
mvn compile
```

Correr los tests (verificar que tu solución funciona):
```
mvn test
```

Correr el programa principal:
```
mvn exec:java -Dexec.mainClass="edu.eci.arsw.Main"
```
(o simplemente el triángulo verde ▶ en IntelliJ)


