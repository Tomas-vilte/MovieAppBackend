# PelículasApp

Este es el backend de PelículasApp, una aplicación web para leer y escribir reseñas de películas. Este README proporciona información sobre el backend de la aplicación, incluyendo características, tecnologías utilizadas y instalación.

- **Gestión de reseñas:** Los usuarios pueden buscar y ver reseñas de películas existentes, así como crear nuevas reseñas para compartir sus opiniones sobre las películas que han visto.
- **Interfaz de programación de aplicaciones (API):** El backend proporciona una API RESTful para acceder a las funcionalidades de la aplicación, permitiendo la integración con el frontend y otros servicios.

## Tecnologías utilizadas

- **Lenguaje de programación:** Java
- **Framework:** Spring Framework (incluyendo Spring Boot, Spring Data JPA)
- **Base de datos:** MySQL
- **Gestión de dependencias:** Maven
- **Dockerización:** La aplicación está containerizada con Docker para facilitar la instalación y ejecución en diferentes entornos.
- **Caché con Redis:** Se utiliza Redis para almacenar en caché datos frecuentemente accedidos, mejorando el rendimiento y la escalabilidad de la aplicación.

## Instalación

1. Clona el repositorio desde GitHub:

    ```bash
    git clone git@github.com:Tomas-vilte/MovieAppBackend.git
    ```

## Configuración del Proyecto

Antes de ejecutar el proyecto, asegúrate de configurar las siguientes variables de entorno:

- `EXTERNAL_API_KEY`: Esta variable debe contener tu API_KEY para acceder a la API externa de películas y series. Puedes obtener una clave API registrándote en [The Movie Database (TMDb)](https://www.themoviedb.org/documentation/api) y accediendo a tu panel de control.
- `API_URL`: Esta variable debe contener la URL base de la API externa. La URL base de la API de TMDb es `https://api.themoviedb.org/3/`.

Puedes configurar estas variables de entorno en el archivo [`config.env`](/peliculas-app/src/main/resources/config.env) en la raíz del proyecto. Asegúrate de reemplazar `Aca pone tu API_KEY` con tu clave API de TMDb y `Aca pone tu API_URL` con `https://api.themoviedb.org/3/`.

2. Ejecuta los servicios necesarios utilizando Docker Compose:
    ```bash
    docker compose up -d 
    ```

## Configuración de MySQL y Redis

Para configurar las direcciones IP de MySQL y Redis, sigue estos pasos:

1. Para MySQL, ejecuta el siguiente comando en la terminal para obtener la dirección IP del contenedor MySQL:
   
```bash
docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mysql_movie
```

2. Para Redis, ejecuta el siguiente comando en la terminal para obtener la dirección IP del contenedor Redis:
   
```bash
docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' redis_movie
```

Actualiza las direcciones de MySQL y Redis en el archivo [`application.properties`](/peliculas-app/src/main/resources/application.properties) con las IP obtenidas:


```properties
# MySQL
spring.datasource.url=jdbc:mysql://<IP_MySQL>:3307/moviedb
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.datasource.username=appmovie
spring.datasource.password=appmovie
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.initialization-mode=always
spring.datasource.platform=mysql


# Redis
spring.redis.host=<IP_Redis>
spring.redis.port=6379
```

4. El backend estará disponible en la URL:
    ```
    http://localhost:8080
    ```

## Endpoints de la API y cómo usarlos

- **GET /api/reviews/movies/review/{reviewId}:** Obtiene todas las reseñas de películas.
  - Para utilizar este endpoint, realiza una solicitud GET a `/reviews`. Esto devolverá todas las reseñas de películas almacenadas en la base de datos. por ejemplo:
  ```json
    {
    "status": 200,
    "message": "Review obtenida con exito",
    "data": [
        {
        "id": 30,
        "movie": {
            "id": 1,
            "title": "Título de la película 1",
            "overview": "Descripción de la película 1",
            "release_date": "2023-3-24",
            "popularity": 8.9
            },
            "user": {
                "id": 17,
                "username": "fulano sabalero",
                "email": "fulano@mail.com"
            },
            "rating": 10,
            "review_text": "Buena peli",
            "created_at": "2024-04-12T17:37:00.08908",
            "updated_at": "2024-04-12T17:50:58.703679"
        }
    ]
    } 
    ```

- **POST /api/reviews/movies/create:** Crea una nueva reseña de película.
  - Para utilizar este endpoint, realiza una solicitud POST a `/api/reviews/movies/create` con un JSON que contenga los detalles de la reseña de película a crear, como el título de la película, la calificación y el comentario. Por ejemplo: 
  ```json
    {
      "movie_id": 1,
      "user_id": 1,
      "rating": 5,
      "review_text": "¡Una excelente película!"
    }
    ```
     - La respuesta devolverá un JSON que contiene los detalles de la reseña de película recién creada, incluyendo el ID de la reseña, la película, el usuario, la calificación, el texto de la reseña y las fechas de creación y actualización.
     ```json
    {
        "status": 200,
        "message": "Review de pelicula creada con exito",
        "data": {
            "id": 30,
            "movie": {
                "id": 1,
                "title": "Título de la película 1",
                "overview": "Descripción de la película 1",
                "release_date": "2023-3-24",
                "popularity": 8.9
            },
            "user": {
                "id": 17,
                "username": "fulano sabalero",
                "email": "fulano@mail.com"
            },
            "rating": 5,
            "review_text": "¡Una excelente película!",
            "created_at": "2024-04-12T17:37:00.089079609",
            "updated_at": null
        }
    }
    ```

- **DELETE /api/reviews/movies/review/{reviewId}:** Elimina una reseña de película por su ID.
  - Para utilizar este endpoint, realiza una solicitud DELETE a `/api/reviews/movies/review/{reviewId}`, donde `{reviewId}` es el ID de la reseña de película que deseas eliminar. Esto devuelve.
  ```json
    {
    "status": 200,
    "message": "Review eliminada con exito",
    "data": "Se elimino la review"
    }
    ```

- **PUT /api/reviews/movies/review/{id}:** Actualiza una reseña de película existente por su ID.
  - Para utilizar este endpoint, realiza una solicitud PUT a `/api/reviews/movies/review/{id}`, donde `{id}` es el ID de la reseña de película que deseas actualizar. Incluye un JSON que contenga los campos que deseas actualizar, como la calificación y el texto de la reseña. Por ejemplo:

    ```json
    {
      "rating": 10,
      "review_text": "Buena peli"
    }
    ```

  - La respuesta devolverá un JSON que contiene los detalles de la reseña de película actualizada, incluyendo el ID de la reseña, la película, el usuario, la calificación, el texto de la reseña y las fechas de creación y actualización.

    ```json
    {
        "status": 200,
        "message": "Review actualizada con exito",
        "data": {
            "id": 30,
            "movie": {
                "id": 1,
                "title": "Título de la película 1",
                "overview": "Descripción de la película 1",
                "release_date": "2023-2-12",
                "popularity": 8.9
            },
            "user": {
                "id": 17,
                "username": "fulano sabalero",
                "email": "fulano@mail.com"
            },
            "rating": 10,
            "review_text": "Buena peli",
            "created_at": "2024-04-12T17:37:00.08908",
            "updated_at": "2024-04-12T17:50:58.70367949"
        }
    }
    ```
- **POST /api/users:** Crea un nuevo usuario.
  - Para utilizar este endpoint, realiza una solicitud POST a `/api/users` con un JSON que contenga los detalles del usuario a crear, como el nombre de usuario, la contraseña y el correo electrónico. Por ejemplo:

    ```json
    {
        "username": "fulano",
        "password": "elsabalero",
        "email": "fulano@mail.com"
    }
    ```

  - La respuesta devolverá un JSON que contiene los detalles del usuario recién creado, incluyendo el ID de usuario, el nombre de usuario y el correo electrónico.

    ```json
    {
        "status": 200,
        "message": "Usuario creado con exito",
        "data": {
            "id": 18,
            "username": "fulano",
            "email": "fulano@mail.com"
        }
    }
    ```
- **GET /api/users/{id}:** Obtiene los detalles de un usuario por su ID.
  - Para utilizar este endpoint, realiza una solicitud GET a `/api/users/{id}`, donde `{id}` es el ID del usuario que deseas obtener. La respuesta devolverá un JSON que contiene los detalles del usuario, incluyendo el ID, el nombre de usuario y el correo electrónico.

    ```json
    {
        "status": 200,
        "message": "Usuario obtenido con exito",
        "data": {
            "id": 18,
            "username": "fulano",
            "email": "fulano@mail.com"
        }
    }
    ```
-

- **DELETE /api/users/{id}:** Elimina un usuario por su ID. Esto devuelve.
    ```json
    {
    "status": 200,
    "message": "Usuario eliminado con exito",
    "data": {
        "id": 4,
        "username": "usuario4",
        "email": "usuario4@example.com"
        }
    }
    ```
- **PUT /api/users/{id}:** Actualiza un usuario por su ID.
    - Para utilizar este endpoint, realiza una solicitud PUT a `/api/users/{id}`, donde `{id}` es el ID del usuario que deseas actualizar. Incluye un JSON que contenga los campos que deseas actualizar, como el nombre de usuario, el correo electrónico y la contraseña. Por ejemplo:
        ```json
        {
            "username": "fulano salah",
            "email": "salahfulano@mail.com",
            "password": "salah123456"
        }
        ```
  - La respuesta devolverá un JSON que contiene los detalles del usuario actualizado, incluyendo el ID de usuario, el nombre de usuario y el correo electrónico.

    ```json
    {
        "status": 200,
        "message": "Usuario actualizado con exito",
        "data": {
            "id": 17,
            "username": "fulano salah",
            "email": "salahfulano@mail.com"
        }
    }
    ```
- **POST /api/movies/{id}:** Guarda los datos de una película en la base de datos.
  - Este endpoint guarda los datos de una película en la base de datos. No es necesario pasar ningún cuerpo en la solicitud, ya que los datos se obtienen automáticamente del servicio externo.
  - La respuesta devolverá un JSON que contiene los detalles de la película guardada, incluyendo el ID de la película, el título, la descripción, la fecha de lanzamiento y la popularidad.

    ```json
    {
        "status": 200,
        "message": "Pelicula guardada con exito en la base de datos",
        "data": {
            "id": 929590,
            "title": "Guerra Civil",
            "overview": "En un futuro cercano, un grupo de periodistas de guerra intenta sobrevivir mientras informan la verdad mientras Estados Unidos se encuentra al borde de una guerra civil.",
            "release_date": "2024-04-10",
            "popularity": 287.696
        }
    }
    ```

- **POST /api/series/{id}:** Guarda los datos de una serie en la base de datos.
  - Este endpoint guarda los datos de una serie en la base de datos. No es necesario pasar ningún cuerpo en la solicitud, ya que los datos se obtienen automáticamente del servicio externo.
  - La respuesta devolverá un JSON que contiene los detalles de la serie guardada, incluyendo el nombre, la descripción, el número de episodios, el número de temporadas y la popularidad.

    ```json
    {
        "status": 200,
        "message": "Serie guardada con exito en la base de datos.",
        "data": {
            "name": "The Good Doctor",
            "overview": "La serie sigue a Shaun Murphy, un joven cirujano con autismo y síndrome del sabio de una pequeña ciudad, donde tuvo una infancia problemática. Él se traslada para unirse al prestigioso departamento de cirugía en San José St. Bonaventure Hospital.",
            "number_of_episodes": 126,
            "number_of_seasons": 7,
            "popularity": 1868.792
        }
    }
    ```

- **GET /api/movies/{id}:** Obtiene los detalles de una película por su ID.
  - Este endpoint devuelve los detalles de una película específica según su ID.
  - La respuesta devolverá un JSON que contiene los detalles de la película, incluyendo el título, la descripción, la fecha de lanzamiento, la popularidad, el estado, las compañías de producción, los países de producción, los géneros, el promedio de votos, el número de votos, los ingresos, el presupuesto y la ruta del póster.

    ```json
    {
        "status": 200,
        "message": "Pelicula obtenida con exito",
        "data": {
            "title": "Guerra Civil",
            "homepage": null,
            "overview": "En un futuro cercano, un grupo de periodistas de guerra intenta sobrevivir mientras informan la verdad mientras Estados Unidos se encuentra al borde de una guerra civil.",
            "release_date": "2024-04-10",
            "popularity": 287.696,
            "status": "Released",
            "production_companies": [
                {
                    "name": "DNA Films",
                    "logo_path": "/6a26if3IKy7mlrQuGHHVp6ufQtF.png",
                    "origin_country": "GB"
                },
                ...
            ],
            "production_countries": [
                {
                    "name": "Finland",
                    "iso_3166_1": "FI"
                },
                ...
            ],
            "genres": [
                {
                    "name": "Acción"
                },
                ...
            ],
            "vote_average": 8.1,
            "vote_count": 20.0,
            "revenue": 0,
            "budget": 75000000,
            "poster_path": "/q0oN2KVQ2W94YNwDGmqaYNmiOnR.jpg"
        }
    }
    ```

- **GET /api/series/{id}:** Obtiene los detalles de una serie por su ID.
  - Este endpoint devuelve los detalles de una serie específica según su ID.
  - La respuesta devolverá un JSON que contiene los detalles de la serie, incluyendo el nombre, la descripción, el número de episodios, el número de temporadas, la popularidad, el estado, el eslogan, el promedio de votos, el número de votos, los creadores, los géneros, la fecha del último episodio emitido, la fecha del próximo episodio a emitir, las redes, los países de origen y las temporadas.

    ```json
    {
        "status": 200,
        "message": "Serie obtenida con exito",
        "data": {
            "name": "Halo",
            "poster_path": "/m8tW2ssuqib10BUlUZfbYlfqEfd.jpg",
            mas datos...
        }
    }
    ```