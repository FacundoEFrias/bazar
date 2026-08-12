# 🛍️ API REST - Bazar System

API REST desarrollada con **Java 21** y **Spring Boot 3** para la gestión integral de un bazar. El sistema permite administrar clientes, productos y la generación de ventas con sus respectivos detalles.

---

## 🚀 Tecnologías Utilizadas

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3.3.2
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** MySQL
* **Documentación:** Swagger UI (SpringDoc OpenAPI 2.5.0)
* **Herramientas & Librerías:**
    * Lombok (reducción de código boilerplate)
    * Jakarta Bean Validation (validación de datos de entrada)
    * RestControllerAdvice (manejo global de excepciones)
    * Maven (gestión de dependencias)

---

## 🛠️ Funcionalidades Principales

### 👤 Clientes (`/clientes`)
* Crear, listar, obtener por ID, editar y eliminar clientes.

### 📦 Productos (`/productos`)
* Crear, listar, obtener por ID, editar y eliminar productos.
* Consulta de productos con stock crítico (stock < 5).

### 🛒 Ventas (`/ventas`)
* Registrar nuevas ventas con asociación automática de cliente y lista de productos.
* Obtener el total de ventas del día y monto acumulado.
* Consultar la venta con el monto más alto.
* Obtener lista de productos pertenecientes a una venta específica.

---

## 🛡️ Manejo de Errores y Validaciones

La API cuenta con un controlador de excepciones global (`@RestControllerAdvice`) que captura:
* Fallos de validación en payloads DTO / Entities (`@Valid`, `@NotBlank`, `@Min`, etc.).
* Excepciones por parámetros de ruta inválidos o recursos no encontrados.
* Respuestas de error estandarizadas con código HTTP, mensaje claro y timestamp.

---

## 📑 Documentación Interactiva (Swagger UI)

Una vez ejecutada la aplicación localmente, podés acceder a la interfaz de Swagger para probar todos los endpoints de manera interactiva:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**