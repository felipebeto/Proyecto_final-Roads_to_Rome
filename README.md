# Roads to Rome

Videojuego 2D estilo **Roguelite** ambientado en la época del Imperio Romano de Occidente.

## Integrantes del grupo

- Luis Castellanos
- Agustín Passerini
- Felipe Camps Palacios

## Descripción

El jugador controla a un personaje encarcelado en las catacumbas por una profecía divina, quien deberá encontrar la manera de escapar. La jugabilidad se basa en mazmorras generadas de forma procedural, compuestas por salas con enemigos que hay que derrotar para avanzar. Al vencerlos, los enemigos sueltan objetos y oro que ayudan al jugador en su huida.

El juego combina exploración, combate y aventura en una vista **top-down / cenital**, con estética **pixel art**.

## Tecnologías utilizadas

- **Java 8** (actualización 481)
- **[LibGDX](https://libgdx.com/)** 1.14.2 — framework principal del juego
- **Tiled** — es una herramienta que permite crear y editar mapas 2D de forma
sencilla mediante pequeños cuadrados llamados tiles. También permite agregar
capas, enemigos, cofres y varias cosas más al mapa.
- **SQLite** — es una base de datos que permite guardar de forma permanente
los datos del jugador, como el nombre, el puntaje, el oro y las estadísticas
de la partida, para que no se pierdan al cerrar el juego.
- Control de versiones con **Git / GitHub**

## Cómo compilar y ejecutar

### Requisitos previos

- [JDK 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) (u OpenJDK equivalente) instalado y configurado en el `PATH`.
- [Git](https://git-scm.com/) instalado.
- (Opcional) Un IDE con soporte para proyectos Gradle, como IntelliJ IDEA.

### Pasos para compilar y ejecutar

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/<usuario-o-organizacion>/roads-to-rome.git
   cd roads-to-rome
   ```

2. Compilar y ejecutar la versión de escritorio usando el wrapper de Gradle incluido en el proyecto:

   En Linux / macOS:
   ```bash
   ./gradlew desktop:run
   ```

   En Windows:
   ```bash
   gradlew.bat desktop:run
   ```

   > La primera ejecución puede tardar más de lo normal ya que Gradle descargará las dependencias necesarias.

## Estado actual del proyecto

Configuración inicial y estructura del proyecto.


## Enlace a la wiki
[Wiki de Roads to Rome](https://github.com/felipebeto/Proyecto_final-Roads_to_Rome/wiki/Propuesta-del-Proyecto:-Roads-to-Rome)
