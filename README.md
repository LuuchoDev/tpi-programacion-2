# Food Store - Sistema de Gestión de Pedidos

Trabajo Práctico Integrador de **Programación 2**. El proyecto implementa una aplicación Java de consola para gestionar categorías, productos, usuarios y pedidos de comida usando Programación Orientada a Objetos, colecciones en memoria, enums, interfaces y excepciones propias.

## Datos del proyecto

- **Materia**: Programación 2
- **Alumno**: Sosa Luciano Emanuel
- **Lenguaje**: Java
- **Versión objetivo**: Java 21
- **Persistencia**: En memoria con `ArrayList`
- **IDE**: NetBeans

## Enlaces de entrega

- **Video demostrativo**: [https://www.youtube.com/watch?v=39e43ybhUQs&themeRefresh=1](https://www.youtube.com/watch?v=39e43ybhUQs&themeRefresh=1).
- **Documentación PDF**: [Documento de entrega](assets/integradorProgramacion2.pdf).

## Alcance funcional

El sistema permite realizar operaciones CRUD desde un menú de consola:

- **Categorías**:
  - Listar categorías activas.
  - Crear categorías.
  - Editar categorías existentes.
  - Eliminar categorías mediante baja lógica.

- **Productos**:
  - Listar productos activos.
  - Crear productos asociados a una categoría activa.
  - Editar productos.
  - Eliminar productos mediante baja lógica.

- **Usuarios**:
  - Listar usuarios activos.
  - Crear usuarios con rol.
  - Editar usuarios.
  - Eliminar usuarios mediante baja lógica.

- **Pedidos**:
  - Listar pedidos activos.
  - Crear pedidos asociados a un usuario activo.
  - Agregar detalles con productos y cantidades.
  - Actualizar estado y forma de pago.
  - Eliminar pedidos mediante baja lógica.

## Estructura del proyecto

```text
src/
└── integrador/
    └── prog2/
        ├── main/
        |    ├── Main.java
        |    ├── ConsoleReader.java
        |    └── Menu.java
        ├── entities/
        │   ├── BaseEntity.java
        │   ├── Categoria.java
        │   ├── Producto.java
        │   ├── Usuario.java
        │   ├── PerfilUsuario.java
        │   ├── Pedido.java
        │   └── DetallePedido.java
        ├── enums/
        │   ├── Rol.java
        │   ├── Estado.java
        │   └── FormaPago.java
        ├── exception/
        │   ├── NegocioException.java
        │   ├── EntidadNoEncontradaException.java
        │   ├── DuplicadoException.java
        │   ├── ValorInvalidoException.java
        │   └── StockInsuficienteException.java
        ├── interfaces/
        │   └── Calculable.java
        ├── services/
            ├── AbstractService.java
            ├── CategoriaService.java
            ├── ProductoService.java
            ├── UsuarioService.java
            ├── PedidoService.java
            └── DataSeeder.java
              
```

## Modelo de dominio

El modelo sigue el UML base de Food Store:

- `Categoria` agrupa productos.
- `Producto` pertenece a una categoría.
- `Usuario` puede tener pedidos.
- `Pedido` contiene detalles.
- `DetallePedido` referencia un producto.
- `Pedido` implementa la interfaz `Calculable`.
- Las entidades comparten `id`, `eliminado` y `createdAt` mediante `BaseEntity`.

## Reglas de negocio implementadas

- Las bajas son lógicas: se marca `eliminado = true`.
- Los listados muestran solamente entidades activas.
- No se permite crear categorías con nombre vacío.
- No se permite crear categorías con nombre repetido.
- No se permite crear productos sin categoría activa.
- No se permite crear productos con precio menor que 0.
- No se permite crear productos con stock menor que 0.
- No se permite crear usuarios con mail vacío.
- No se permite repetir mail entre usuarios activos.
- No se permite crear pedidos sin usuario activo.
- No se permite guardar pedidos sin detalles.
- No se permite agregar detalles con cantidad menor o igual a 0.
- No se permite agregar detalles si no hay stock suficiente.
- El total del pedido se calcula mediante `calcularTotal()`.


## Excepciones propias

El sistema utiliza excepciones específicas para comunicar errores de dominio:

- `DatosInvalidosException`: datos vacíos, negativos o inconsistentes.
- `EntidadDuplicadaException`: registros repetidos, como categoría por nombre o usuario por mail.
- `EntidadNoEncontradaException`: búsquedas por id inexistente o eliminado.
- `StockInvalidoException`: stock negativo o cantidad mayor al stock disponible.

## Menú principal

Al iniciar, el sistema muestra:

```text
=== SISTEMA DE PEDIDOS (FOOD STORE) ===
1. Categorías
2. Productos
3. Usuarios
4. Pedidos
0. Salir
```

Cada opción abre un submenú con operaciones de listado, creación, edición y eliminación.
