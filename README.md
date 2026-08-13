# 🛍️ API REST - Bazar System

API REST desarrollada con **Java 21** y **Spring Boot 3** para la gestión integral de un bazar. El sistema permite administrar clientes, productos y la generación de ventas con sus respectivos detalles y reportes estadísticos.

---

## 🚀 Tecnologías Utilizadas

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** MySQL
* **Documentación:** Swagger UI (SpringDoc OpenAPI)
* **Herramientas & Librerías:**
  * Lombok (reducción de código boilerplate)
  * Jakarta Bean Validation (validación de datos de entrada)
  * RestControllerAdvice (manejo global de excepciones)
  * DTO Pattern (Data Transfer Object para respuestas desacopladas)
  * Maven (gestión de dependencias)

---

## 🛠️ Funcionalidades Principales

### 👤 Clientes
* Crear, listar, obtener por ID, editar y eliminar clientes.

### 📦 Productos
* Crear, listar, obtener por ID, editar y eliminar productos.
* Búsqueda y filtrado dinámico combinado por nombre (parcial e insensible a mayúsculas) y/o rango de precios.
* Consulta de productos con stock crítico (stock < 5).
* Respuestas mapeadas a DTOs para protección de datos sensibles/internos de inventario.

### 🛒 Ventas
* Registrar nuevas ventas con asociación automática de cliente y lista de productos.
* Consultar la lista de productos pertenecientes a una venta específica.
* Obtener la venta con el monto total más alto (incluyendo datos del cliente y cantidad de productos).
* Consultar métricas de ventas por fecha determinada (cantidad de operaciones y sumatoria del dinero recaudado).

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