# 🛒 Sistema de Gestión para Bazar

Aplicación backend desarrollada en Spring Boot para la gestión integral de un comercio, que incluye control de productos, clientes, ventas y la integración con una API externa para la importación automática de stock.

---

## 🚀 Tecnologías y Herramientas Utilizadas

*   **Java:** Versión moderna con Streams y programación funcional.
*   **Spring Boot** (Web, Data JPA)
*   **Spring Data JPA / Hibernate:** Para la persistencia y mapeo objeto-relacional.
*   **RestTemplate:** Para el consumo de APIs externas.
*   **Lombok:** Para reducir código repetitivo de getters, setters y constructores.
*   **JUnit 5 & Mockito:** Para testing unitario.
*   **Base de datos:** H2 (en memoria para desarrollo y pruebas).
*   **Swagger/OpenAPI:** Documentación interactiva de la API.

---
## 🛠️ Arquitectura y Estructura del Proyecto

El proyecto sigue una arquitectura de capas estándar:
*   **Controller:** Expone los endpoints REST para la interacción con el cliente.
*   **Service:** Contiene la lógica de negocio y procesamiento de datos mediante Streams de Java.
*   **Repository:** Interfaces que extienden de JpaRepository para la comunicación con la base de datos.
*   **Model / Entity:** Representación de las entidades de negocio (`Producto`, `Cliente`, `Venta`).
*   **DTO:** Objetos de transferencia de datos optimizados para reportes, consultas específicas y consumo de APIs externas.

---
## 📌 Funcionalidades Principales

*   **Gestión de Productos y Clientes:** Operaciones CRUD completas para el stock y la administración de compradores.
*   **Gestión de Ventas:** Registro de ventas asociadas a clientes y listas de productos, control de stock y cálculos de negocio.
*   **Consumo de API Externa:** Importación automática de productos desde un servicio externo (DummyJSON) utilizando RestClient/RestTemplate.
*   **Procesamiento de Datos con Streams:**
    *   Filtrado avanzado de productos por nombre, rangos de precios y disponibilidad.
    *   Cálculos estadísticos, como la obtención del total y la cantidad de ventas por fecha.
    *   Determinación de la venta de mayor monto con detalle de cliente y productos.
---

## 📖 Documentación de la API (Swagger)

El proyecto incluye Swagger UI para probar los endpoints de forma interactiva. Una vez que la aplicación esté corriendo, podés acceder a la documentación en:
`http://localhost:8080/swagger-ui.html`

---

## 🐳 Dockerización

Para levantar el entorno completo (aplicación + base de datos) usando Docker, utilizá:
```bash
docker-compose up --build
```
---

## 🧪 Pruebas Unitarias (Testing)

El proyecto cuenta con una suite robusta de tests unitarios implementados con JUnit 5 y Mockito, asegurando el correcto funcionamiento de las capas de servicio:
*   Mockeo de repositorios para aislar y blindar la lógica de negocio.
*   Pruebas de flujos exitosos para Productos, Clientes y Ventas.

Para ejecutar los tests, podés correr el siguiente comando en tu terminal:
```bash
./mvnw test
```
---

## 👤 Autor

* **Facundo Emanuel Frías**

* **Backend Software Developer**

[🔗 LinkedIn](https://www.linkedin.com/in/facundoemanuelfrias/) | [🐙 GitHub](https://github.com/FacundoEFrias)