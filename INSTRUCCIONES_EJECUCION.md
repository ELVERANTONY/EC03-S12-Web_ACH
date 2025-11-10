# INSTRUCCIONES DE EJECUCIÓN - SABOR GOURMET

## PASO 1: Configurar la Base de Datos

### Opción A: Crear base de datos desde la consola MySQL

1. Abrir MySQL desde la terminal:
```bash
mysql -u root -p
```

2. Crear la base de datos:
```sql
CREATE DATABASE sabor_gourmet;
USE sabor_gourmet;
```

3. Salir de MySQL:
```sql
exit
```

4. Ejecutar los scripts SQL:
```bash
mysql -u root -p sabor_gourmet < database/schema.sql
mysql -u root -p sabor_gourmet < database/data.sql
```

### Opción B: Usar MySQL Workbench

1. Abrir MySQL Workbench
2. Conectarse a tu servidor MySQL
3. Abrir el archivo `database/schema.sql`
4. Ejecutar el script (botón ⚡)
5. Abrir el archivo `database/data.sql`
6. Ejecutar el script (botón ⚡)

**IMPORTANTE:** Si tu contraseña de MySQL no es "root", edita el archivo:
`src/main/resources/application.properties`

Cambia la línea:
```properties
spring.datasource.password=root
```

Por tu contraseña real.

## PASO 2: Compilar el Proyecto

Desde la carpeta del proyecto, ejecutar:

```bash
.\mvnw.cmd clean install
```

Si te sale algún error de permisos, ejecuta:
```bash
.\mvnw.cmd clean install -DskipTests
```

Deberías ver al final:
```
[INFO] BUILD SUCCESS
```

## PASO 3: Ejecutar la Aplicación

```bash
.\mvnw.cmd spring-boot:run
```

Espera a que aparezca:
```
Started Ev12CholanApplication in X.XXX seconds
```

## PASO 4: Acceder al Sistema

Abre tu navegador en: **http://localhost:8080**

Deberías ver la pantalla de login.

## CREDENCIALES DE ACCESO

| Usuario   | Contraseña  | Rol          |
|-----------|-------------|--------------|
| admin     | admin123    | ADMIN        |
| mozo      | mozo123     | MOZO         |
| cajero    | cajero123   | CAJERO       |
| cocinero  | cocinero123 | COCINERO     |

## CAPTURAS PARA EL INFORME

Aquí están las capturas que debes tomar para tu informe:

### 1. Pantalla de Login
- URL: http://localhost:8080/login
- Mostrar el formulario de inicio de sesión

### 2. Dashboard Principal
- Usuario: admin / admin123
- URL: http://localhost:8080/dashboard
- Mostrar las tarjetas estadísticas y acciones rápidas

### 3. Gestión de Clientes
- URL: http://localhost:8080/clientes
- Captura de la lista de clientes
- Captura del formulario de nuevo cliente

### 4. Gestión de Mesas
- URL: http://localhost:8080/mesas
- Mostrar las mesas con sus estados (disponible, ocupada, etc.)

### 5. Menú de Platos
- URL: http://localhost:8080/platos
- Mostrar el catálogo de platos con precios y categorías

### 6. Crear Pedido (Funcionalidad MOZO)
- Usuario: mozo / mozo123
- URL: http://localhost:8080/pedidos/nuevo
- Mostrar el formulario de crear pedido

### 7. Vista Cocina (Funcionalidad COCINERO)
- Usuario: cocinero / cocinero123
- URL: http://localhost:8080/pedidos/cocina
- Mostrar pedidos pendientes y en preparación

### 8. Gestión de Facturas (Funcionalidad CAJERO)
- Usuario: cajero / cajero123
- URL: http://localhost:8080/facturas
- Mostrar lista de facturas generadas

### 9. Reportes de Ventas
- Usuario: cajero / cajero123
- URL: http://localhost:8080/ventas
- Mostrar dashboard de ventas del día

### 10. Inventario de Insumos (Funcionalidad ADMIN)
- Usuario: admin / admin123
- URL: http://localhost:8080/inventario
- Mostrar lista de insumos con stock actual
- Destacar alertas de stock bajo

### 11. Gestión de Usuarios (Funcionalidad ADMIN)
- Usuario: admin / admin123
- URL: http://localhost:8080/admin/usuarios
- Mostrar lista de usuarios del sistema

### 12. Bitácora del Sistema (Funcionalidad ADMIN - AOP)
- Usuario: admin / admin123
- URL: http://localhost:8080/admin/bitacora
- Mostrar registro de auditoría con operaciones CREATE, UPDATE, DELETE

### 13. Logs en Consola (AOP)
- Tomar captura de la consola mostrando los logs de AOP:
  - Tiempo de ejecución de métodos
  - Registro de operaciones
  - Excepciones (si las hay)

## FLUJO COMPLETO PARA PROBAR

### Flujo 1: Crear un Pedido Completo

1. Login como **mozo** (mozo/mozo123)
2. Ir a **Pedidos → Nuevo Pedido**
3. Seleccionar una mesa disponible
4. Seleccionar un cliente (o dejarlo vacío)
5. Agregar platos al pedido
6. Guardar el pedido
7. Capturar pantalla del pedido creado

### Flujo 2: Procesar en Cocina

1. Login como **cocinero** (cocinero/cocinero123)
2. Ir a **Pedidos → Vista Cocina**
3. Ver los pedidos pendientes
4. Cambiar estado a "En Preparación"
5. Luego cambiar a "Servido"
6. Capturar pantalla del proceso

### Flujo 3: Generar Factura

1. Login como **cajero** (cajero/cajero123)
2. Ir a **Pedidos**
3. Seleccionar un pedido servido
4. Clic en "Facturar"
5. Seleccionar método de pago (Efectivo/Tarjeta/Yape)
6. Generar factura
7. Capturar la factura generada

### Flujo 4: Ver Auditoría

1. Login como **admin** (admin/admin123)
2. Ir a **Administración → Bitácora**
3. Ver los registros de todas las operaciones anteriores
4. Capturar la bitácora mostrando las operaciones CREATE

## DEMOSTRACIÓN DE AOP

### Auditoría (AuditoriaAspect.java)
Automáticamente registra en la tabla `bitacora`:
- Cada vez que guardas un cliente → CREATE
- Cada vez que editas un plato → UPDATE
- Cada vez que eliminas una mesa → DELETE

**Para verificar:**
1. Crea un nuevo cliente
2. Ve a Admin → Bitácora
3. Verás el registro de la operación CREATE

### Logging (LoggingAspect.java)
En la consola verás logs como:
```
→ Iniciando método: ClienteService.save(..)
✓ Método completado exitosamente: ClienteService.save(..)
⏱ Tiempo de ejecución: ClienteService.save - 45 ms
```

### Manejo de Excepciones (ExceptionHandlingAspect.java)
Captura todas las excepciones del sistema y las registra.

## VERIFICAR QUE TODO FUNCIONE

✅ Login exitoso con todos los usuarios
✅ Dashboard carga correctamente
✅ Puedes crear clientes
✅ Puedes crear mesas
✅ Puedes crear platos
✅ Puedes crear pedidos
✅ Puedes generar facturas
✅ La bitácora registra las operaciones
✅ Los logs aparecen en consola
✅ Los roles restringen accesos correctamente

## TROUBLESHOOTING

### Error: "Unable to connect to database"
- Verifica que MySQL esté corriendo
- Confirma usuario y contraseña en application.properties
- Verifica que la base de datos `sabor_gourmet` existe

### Error: "Access Denied 403"
- Verifica que estás usando el usuario correcto para esa funcionalidad
- Revisa la tabla de permisos por rol

### Error: "Port 8080 already in use"
- Detén cualquier otro programa en el puerto 8080
- O cambia el puerto en application.properties:
```properties
server.port=8081
```

## DETENER LA APLICACIÓN

En la terminal donde corre la aplicación, presiona:
```
Ctrl + C
```

## NOTAS IMPORTANTES PARA EL INFORME

1. **Arquitectura MVC:** Menciona que el proyecto usa Model-View-Controller
2. **Spring Security:** Implementa autenticación y autorización por roles
3. **AOP:** Implementa 3 aspectos (Auditoría, Logging, Excepciones)
4. **JPA/Hibernate:** Mapeo objeto-relacional automático
5. **Thymeleaf:** Motor de plantillas server-side
6. **Bootstrap 5:** Framework CSS para diseño responsive
7. **BCrypt:** Encriptación de contraseñas
8. **MySQL:** Base de datos relacional

## ESTRUCTURA DE LA BASE DE DATOS

El sistema tiene 14 tablas:
- usuarios
- clientes
- mesas
- platos
- insumos
- plato_insumos
- pedidos
- detalle_pedidos
- facturas
- detalle_facturas
- proveedores
- compras
- detalle_compras
- bitacora

Todas están relacionadas correctamente con claves foráneas.
