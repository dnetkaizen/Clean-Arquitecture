# Matricula (Clean Architecture)

Proyecto Java Spring Boot que implementa Clean Architecture para exponer APIs REST de matrícula académica sobre 5 tablas: Facultad, Carrera, Curso, Profesor, Sección. Preparado para pruebas con H2 en memoria (sin necesidad de DB externa) y para ser consultado desde Postman.

## Requisitos
- Java 21 (JDK 21)
- Maven 3.9+

## Clonación
```bash
git clone https://github.com/dnetkaizen/Clean-Arquitecture.git
cd Clean-Arquitecture
```

## Ejecución (perfil por defecto con H2)
```bash
mvn clean spring-boot:run
```
- Base URL: `http://localhost:8085`
- Consola H2: `http://localhost:8085/h2-console`
  - JDBC URL: `jdbc:h2:mem:matricula;MODE=PostgreSQL`
  - User: `matricula`
  - Password: `matricula`

Configuración relevante (src/main/resources/application.yml):
- H2 en memoria con `ddl-auto: update` (Hibernate crea/actualiza tablas desde Entities)
- SQL en logs (`spring.jpa.show-sql=true`)

## Arquitectura (Clean Architecture)

- `domain` (Núcleo del negocio, sin dependencias de frameworks)
  - `model`: Entidades de negocio: `Profesor`, `Seccion`, `Facultad`, `Carrera`, `Curso`.
  - `model/valueobjects`: Objetos de valor para identidades y tipos: `ProfesorId`, `SeccionId`, `CursoId`, `CarreraId`, `FacultadId` (UUID), `Email`, `Dni`, `CodigoCurso`.
  - `repository`: Interfaces de repositorios que el dominio necesita (sin detalles técnicos).
  - `service`: Reglas de negocio y validaciones (unicidad, asociaciones).
  - `exception`: Excepciones de dominio específicas.

- `application` (Casos de uso, orquestación)
  - `port/in`: Interfaces de casos de uso (ej. `RegisterProfesorUseCase`, `CreateSeccionUseCase`, `Find*UseCase`).
  - `port/out`: Interfaces para servicios externos (ej. `EmailNotificationPort`).
  - `service`: Implementaciones de casos de uso. Usan `domain.repository` y `domain.service`; devuelven DTOs de respuesta.
  - `dto/command`: DTOs de entrada para comandos (POST/PUT).
  - `dto/query`: DTOs de entrada para consultas (GET con filtros).
  - `dto/response`: DTOs de salida para clientes.
  - `mapper`: Domain → Response.

- `infrastructure` (Detalles técnicos)
  - `adapter/in/web`: Controladores REST (Spring MVC) que reciben HTTP, validan `@Valid` y delegan a `port/in`.
  - `adapter/out/persistence`:
    - `jpa/entity`: Entities JPA (IDs como `String` para mapear UUID del dominio).
    - `jpa/repository`: Spring Data JPA repositories.
    - `mapper`: Mappers JPA ↔ Domain.
    - `adapter`: Implementaciones de `domain.repository`.
  - `adapter/out/notification`: Implementaciones de `port/out` (ej. `EmailNotificationAdapter` que hace log).

- `shared`
  - `annotation`: Estereotipos `@UseCase`, `@DomainService`, `@Adapter`.

Relación de dependencias:
- `infrastructure` → `application` → `domain`
- `domain` no conoce `application` ni `infrastructure`.

## Endpoints REST
Base URL: `http://localhost:8085`

- Profesores
  - POST `/api/v1/profesores`
  - GET `/api/v1/profesores?especialidad=&tituloAcademico=&activo=`

- Facultades
  - POST `/api/v1/facultades`

- Carreras
  - POST `/api/v1/carreras`
  - GET `/api/v1/carreras/by-facultad?facultadId=&activa=`

- Cursos
  - POST `/api/v1/cursos`
  - GET `/api/v1/cursos/by-carrera?carreraId=&nivelSemestre=&activo=`

- Secciones
  - POST `/api/v1/secciones`
  - GET `/api/v1/secciones/by-curso?cursoId=&periodoAcademico=&profesorId=&activa=`
  - GET `/api/v1/secciones/disponibles?periodoAcademico=&cursoId=&capacidadMinima=`

- H2 Console
  - GET `/h2-console`

## Ejemplos (Postman/curl)

1) Crear Facultad
```http
POST /api/v1/facultades
Content-Type: application/json

{
  "nombre": "Ingeniería",
  "descripcion": "Facultad de Ingeniería",
  "ubicacion": "Campus Norte",
  "decano": "Dra. Pérez"
}
```

2) Crear Carrera
```http
POST /api/v1/carreras
Content-Type: application/json

{
  "facultadId": "<UUID_FACULTAD>",
  "nombre": "Sistemas",
  "descripcion": "Ingeniería de Sistemas",
  "duracionSemestres": 10,
  "tituloOtorgado": "Ing. de Sistemas"
}
```
- Listar por facultad:
  - `GET /api/v1/carreras/by-facultad?facultadId=<UUID_FACULTAD>&activa=true`

3) Crear Curso
```http
POST /api/v1/cursos
Content-Type: application/json

{
  "carreraId": "<UUID_CARRERA>",
  "codigo": "CS101",
  "nombre": "Algoritmos",
  "descripcion": "Introducción a Algoritmos",
  "creditos": 4,
  "nivelSemestre": 1
}
```
- Listar por carrera:
  - `GET /api/v1/cursos/by-carrera?carreraId=<UUID_CARRERA>&nivelSemestre=1&activo=true`

4) Crear Profesor
```http
POST /api/v1/profesores
Content-Type: application/json

{
  "nombre": "Juan",
  "apellido": "Gomez",
  "dni": "12345678",
  "email": "juan@uni.edu",
  "telefono": "999999999",
  "especialidad": "Algoritmos",
  "tituloAcademico": "MSc"
}
```
- Buscar profesores:
  - `GET /api/v1/profesores?especialidad=Algoritmos&tituloAcademico=MSc&activo=true`

5) Crear Sección
```http
POST /api/v1/secciones
Content-Type: application/json

{
  "cursoId": "<UUID_CURSO>",
  "profesorId": "<UUID_PROFESOR>",
  "codigo": "A1",
  "capacidadMaxima": 30,
  "aula": "B-101",
  "horario": "08:00-10:00",
  "dias": "Lunes-Miercoles",
  "periodoAcademico": "2025-1",
  "fechaInicio": "2025-03-01",
  "fechaFin": "2025-07-01"
}
```
- Listar por curso:
  - `GET /api/v1/secciones/by-curso?cursoId=<UUID_CURSO>&periodoAcademico=2025-1&profesorId=<UUID_PROFESOR>&activa=true`
- Secciones disponibles:
  - `GET /api/v1/secciones/disponibles?periodoAcademico=2025-1&cursoId=<UUID_CURSO>&capacidadMinima=10`

## Notas
- Identificadores en el dominio son UUID; en JPA se manejan como `String(36)` y en H2 se crean automáticamente con `ddl-auto: update`.
- Para una futura DB PostgreSQL, agrega configuración de `spring.datasource` y cambia la URL, manteniendo las Entities.
- El adapter de notificación (`EmailNotificationAdapter`) registra en logs los eventos de creación/actualización/cancelación de secciones.

## Troubleshooting
- 405 Method Not Allowed al abrir rutas POST en el navegador: usa Postman/curl para POST y las rutas GET con query params en el navegador.
- H2 console no conecta:
  - Verifica URL y credenciales indicadas arriba.
  - Asegúrate de que la app corre en el puerto 8085.
- Errores de inyección (NoSuchBeanDefinition): asegúrate de que los adapters de persistencia estén presentes e incluidos en el package scanning (están bajo `com.arquitecture.matricula`).
