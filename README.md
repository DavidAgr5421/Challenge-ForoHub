# ForoHub Backend API

Este es un proyecto backend desarrollado como parte del curso de backend de Alura Oracle, que implementa una API RESTful para la gestión de un foro. La aplicación incluye autenticación basada en JWT y usa Spring Security para proteger las rutas.

## 🚀 Tecnologías Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Security**
- **JWT (Json Web Token)**
- **BCrypt** para encriptación de contraseñas
- **HMAC256** para firmar los tokens JWT
- **MySQL** como base de datos relacional
- **Hibernate/JPA** para la interacción con la base de datos

---

## ✨ Funcionalidades

### **1. Autenticación**
- **Login:**  
  La autenticación se realiza a través de JWT. Para obtener el token, se debe realizar una solicitud POST a la ruta:

#POST: localhost:8080/login

El cuerpo de la solicitud debe contener las credenciales de usuario (login y contraseña) en formato JSON:

json
{
  "login": "usuarioEjemplo",
  "password": "contraseñaSegura"
}

#Repuesta Esperada

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer"
}

#2. Gestión de Tópicos:
Se implementan operaciones CRUD completas para la entidad Topico. Además, se incluyen dos funcionalidades adicionales:

-Búsqueda personalizada por curso y año:
-++Permite buscar tópicos filtrando por curso y/o año. Los parámetros son opcionales, lo que significa que puedes buscar con solo uno de ellos o con ambos.

  -**sql**
  -**Copy**
  -**Edit**
  -**GET: localhost:8080/topicos/search?curso=<nombre_curso>&year=<año>**
-Listado de 10 tópicos más antiguos:
-++Retorna los primeros 10 tópicos creados, ordenados en forma ascendente según la fecha de creación.

  -**bash**
  -**Copy**
  -**Edit**
  -**GET: localhost:8080/topicos/date10**

## 📂 Endpoints Disponibles

### **Autenticación**
| Método | Endpoint    | Descripción                 |
|--------|-------------|-----------------------------|
| POST   | `/login`    | Autenticación de usuarios   |

### **Tópicos**
| Método | Endpoint                  | Descripción                                |
|--------|---------------------------|--------------------------------------------|
| GET    | `/topicoss`                 | Listar todos los tópicos                  |
| GET    | `/topicos/{id}`            | Obtener un tópico por ID                  |
| POST   | `/topicos`                 | Crear un nuevo tópico                     |
| PUT    | `/topicos/{id}`            | Actualizar un tópico existente            |
| DELETE | `/topicos/{id}`            | Eliminar un tópico por ID                 |
| GET    | `/topicos/search?curso=&year=` | Buscar tópicos por curso y/o año      |
| GET    | `/topicos/date10`          | Listar los 10 tópicos más antiguos        |
----------------------------------------------------------------------------------
