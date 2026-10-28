# Especificación: Panel "Registrar paciente" (Java Swing)

Documento pensado para ser leído por una IA que implementará el panel en **Java Swing**. Describe únicamente la estructura, los campos y el comportamiento. No incluye detalles gráficos finos (colores exactos, sombras, tipografías).

---

## 1. Contexto

Aplicación de escritorio de un **Laboratorio Clínico**. Este panel permite registrar un paciente nuevo. Está dentro de una ventana principal con encabezado y menú lateral, y se muestra al seleccionar la opción **Pacientes**.

Idioma de la interfaz: **español (Colombia)**.

---

## 2. Estructura general de la ventana

```
JFrame (ventana principal)
├── NORTH   : Encabezado (nombre del laboratorio, notificaciones, usuario)
├── WEST    : Menú lateral de navegación
└── CENTER  : Panel "Registrar paciente"
              ├── Banner de título (título + subtítulo)
              └── Contenido (2 columnas)
                  ├── Columna izquierda (principal): Formulario
                  └── Columna derecha (secundaria): Resumen + Nota informativa
```

### 2.1 Encabezado
- Título: **"Laboratorio Clínico"**, subtítulo: *"Resultados que generan confianza"*.
- Derecha: icono de notificaciones con contador (ej. 3) y bloque de usuario: nombre **"Fernando Pérez"**, rol **"Administrador"**.

### 2.2 Menú lateral (ícono + texto)
1. Inicio
2. **Pacientes** (opción activa/seleccionada)
3. Toma de muestras
4. Resultados
5. Reportes
6. Configuración

### 2.3 Banner de título del panel
- Título: **"Registrar paciente"**
- Subtítulo: *"Complete la información del paciente para continuar con el proceso de atención."*

---

## 3. Formulario (columna izquierda)

Convención: `*` = campo obligatorio (se marca con asterisco rojo junto a la etiqueta). Los campos se organizan en una cuadrícula de **2 columnas** (sugerido `GridBagLayout` o `MigLayout`). Cada campo = etiqueta arriba + componente debajo.

### 3.1 Sección "Datos personales"

| Fila | Campo | Obligatorio | Componente Swing | Valor de ejemplo | Notas |
|---|---|---|---|---|---|
| 1 | Tipo de documento | Sí | `JComboBox<String>` | Cédula de ciudadanía | Opciones sugeridas: Cédula de ciudadanía, Tarjeta de identidad, Cédula de extranjería, Pasaporte, Registro civil |
| 1 | Número de documento | Sí | `JTextField` | 1234567890 | Solo dígitos (pasaporte podría admitir alfanumérico) |
| 2 | Nombres | Sí | `JTextField` | Juan Carlos | |
| 2 | Apellidos | Sí | `JTextField` | Ramírez López | |
| 3 | Fecha de nacimiento | Sí | Campo de fecha (`JFormattedTextField` con máscara o `JDatePicker`/`JDateChooser`) con ícono de calendario | 15/04/1985 | Formato `dd/MM/yyyy` |
| 3 | Edad | No (calculado) | `JTextField` **no editable** | 40 años | Se calcula automáticamente desde la fecha de nacimiento. Es un campo angosto, ubicado entre la fecha y el género |
| 3 | Género | Sí | `JComboBox<String>` | Masculino | Opciones sugeridas: Masculino, Femenino, Otro |

La fila 3 tiene **tres elementos**: Fecha de nacimiento (ancho medio), Edad (angosto) y Género (ancho de columna derecha).

### 3.2 Sección "Información de contacto"

| Fila | Campo | Obligatorio | Componente | Placeholder | Notas |
|---|---|---|---|---|---|
| 1 | Teléfono fijo | No | `JTextField` | 601 1234567 | |
| 1 | Teléfono celular | Sí | `JTextField` | 300 123 4567 | 10 dígitos, solo números |
| 2 | Correo electrónico | No | `JTextField` | juan.ramirez@example.com | Si se llena, validar formato de correo |
| 2 | Dirección de residencia | Sí | `JTextField` | Carrera 10 # 20 - 30 | |

### 3.3 Sección "Información adicional"

| Fila | Campo | Obligatorio | Componente | Valor por defecto / ejemplo | Notas |
|---|---|---|---|---|---|
| 1 | EPS | Sí | `JComboBox<String>` | Nueva EPS | Lista de EPS (cargada de datos o lista fija) |
| 1 | Régimen | No | `JComboBox<String>` | Contributivo | Opciones sugeridas: Contributivo, Subsidiado, Especial, Particular |
| 2 | Ocupación | No | `JTextField` | Ingeniero de sistemas | |
| 2 | Estado civil | No | `JComboBox<String>` | Soltero(a) | Opciones sugeridas: Soltero(a), Casado(a), Unión libre, Divorciado(a), Viudo(a) |
| 3 | ¿Tiene alguna condición médica relevante? | No | `JComboBox<String>` | No | Opciones: No, Sí |
| 3 | Observaciones | No | `JTextArea` dentro de `JScrollPane` (2 a 3 filas) | *(vacío)* — placeholder: "Puede agregar información adicional aquí..." | Texto libre |

### 3.4 Botones de acción (parte inferior del formulario)
- **Izquierda:** botón **"Cancelar"** (secundario, con ícono ✕). Cierra/limpia el formulario y vuelve a la vista anterior, pidiendo confirmación si hay datos escritos.
- **Derecha:** botón **"Guardar paciente"** (principal, con ícono de guardar). Valida y guarda.

---

## 4. Columna derecha

### 4.1 Tarjeta "Resumen" (vista previa en vivo)
Muestra los datos del paciente conforme se escriben en el formulario. Es **solo lectura** (usar `JLabel`).

Estructura, de arriba hacia abajo:
1. Avatar genérico (ícono de persona) + **nombre completo** (Nombres + Apellidos) y debajo **tipo abreviado + número de documento** (ej. `CC 1234567890`).
2. Fecha de nacimiento: `15/04/1985 (40 años)`
3. Teléfono celular: `300 123 4567`
4. Correo electrónico: `juan.ramirez@example.com`
5. Dirección: `Carrera 10 # 20 - 30`
6. EPS: `Nueva EPS`
7. Régimen: `Contributivo`

Cada ítem tiene: ícono a la izquierda, etiqueta pequeña arriba y valor debajo.

Si un campo está vacío, mostrar un guion (`—`) en lugar del valor.

### 4.2 Tarjeta informativa "Tenga en cuenta"
- Ícono de información + título **"Tenga en cuenta"**.
- Texto: *"Los datos del paciente son necesarios para la generación de la orden médica, la trazabilidad de las muestras y la entrega de resultados."*
- Es estática (sin interacción).

---

## 5. Comportamiento

1. **Edad automática:** al cambiar la fecha de nacimiento, calcular los años cumplidos a la fecha actual y mostrarlos como `"{n} años"` en el campo Edad (no editable). Si la fecha es inválida o futura, dejar el campo vacío.
2. **Resumen en vivo:** cada campo del formulario actualiza su dato correspondiente en la tarjeta Resumen (usar `DocumentListener` en los campos de texto y `ActionListener`/`ItemListener` en los combos).
3. **Abreviatura del documento** en el resumen: Cédula de ciudadanía → `CC`, Tarjeta de identidad → `TI`, Cédula de extranjería → `CE`, Pasaporte → `PA`, Registro civil → `RC`.
4. **Guardar paciente:**
   - Validar todos los campos obligatorios y formatos.
   - Si hay errores: marcar los campos con error (borde en rojo o mensaje corto debajo) y enfocar el primero con error. No guardar.
   - Si todo es válido: guardar y mostrar mensaje de éxito (`JOptionPane`), luego limpiar el formulario o navegar según el flujo de la aplicación.
5. **Cancelar:** si hay datos escritos, preguntar confirmación (`JOptionPane.showConfirmDialog`); si se confirma, limpiar y volver.
6. **Documento duplicado:** al guardar, verificar que no exista ya un paciente con el mismo tipo y número de documento.

---

## 6. Validaciones

| Campo | Regla |
|---|---|
| Tipo de documento | Debe haber una opción seleccionada |
| Número de documento | Obligatorio; solo dígitos; longitud razonable (6 a 12) |
| Nombres / Apellidos | Obligatorios; solo letras, espacios y tildes/ñ |
| Fecha de nacimiento | Obligatoria; formato `dd/MM/yyyy`; no puede ser futura |
| Género | Obligatorio |
| Teléfono fijo | Opcional; si se llena, solo dígitos y espacios |
| Teléfono celular | Obligatorio; exactamente 10 dígitos |
| Correo electrónico | Opcional; si se llena, formato válido (`texto@dominio.ext`) |
| Dirección de residencia | Obligatoria |
| EPS | Obligatoria |
| Demás campos | Sin validación estricta |

---

## 7. Sugerencia de organización del código

- `RegistroPacientePanel extends JPanel` — panel principal (banner + contenido).
- `FormularioPacientePanel extends JPanel` — las tres secciones y los botones.
- `ResumenPacientePanel extends JPanel` — tarjeta de resumen.
- `NotaInformativaPanel extends JPanel` — tarjeta "Tenga en cuenta".
- Una clase modelo `Paciente` con los atributos: tipoDocumento, numeroDocumento, nombres, apellidos, fechaNacimiento, genero, telefonoFijo, telefonoCelular, correo, direccion, eps, regimen, ocupacion, estadoCivil, condicionMedica, observaciones.
- Las secciones del formulario pueden ser `JPanel` con `TitledBorder` o con un `JLabel` de título más un separador (`JSeparator`).
- Layout sugerido: `BorderLayout` en el panel principal; en el contenido, `GridBagLayout` con la columna izquierda más ancha (aprox. 70 %) y la derecha más angosta (aprox. 30 %); dentro del formulario, `GridBagLayout` de 2 columnas.
