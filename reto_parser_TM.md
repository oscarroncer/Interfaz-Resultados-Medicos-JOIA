# Reto: Implementar el parser de TM y CorrectedIOP

## Contexto del proyecto

Estamos parseando un XML de dispositivo oftalmológico (Topcon CT-1P) con StAX en Java.
La estructura de datos ya está construida y funciona. `Common` y `Patient` se parsean correctamente.

---

## Estructura de clases relevante

```
SuperPaciente
├── Common
├── Patient
├── rightEye (Eye)
│   ├── TM
│   │   ├── lecturas (ArrayList<TMList>)  ← IOP_mmHg, IOP_Pa, ConfidenceIndex
│   │   └── media (TMList)
│   └── CorrectedIOP
│       ├── Param1, Param2, CCT (String)
│       ├── measured (IOPValue)           ← IOP_mmHg, IOP_Pa
│       └── corrected (IOPValue)          ← IOP_mmHg, IOP_Pa
└── leftEye (Eye)
    └── (misma estructura que rightEye)
```

---

## El reto

El parser actual usa una sola variable de contexto `currentElement`.

Para parsear `TM` y `CorrectedIOP` correctamente necesitas **tres variables de contexto**:

| Variable | Propósito | Se actualiza en |
|---|---|---|
| `currentElement` | Nombre del elemento actual | `START_ELEMENT` |
| `grupo` | Prueba activa: `"TM"`, `"CorrectedIOP"`, etc. | `START_ELEMENT` y `END_ELEMENT` |
| `lado` | Ojo activo: `"R"` o `"L"` | `START_ELEMENT` y `END_ELEMENT` |

### Regla de actualización

```
START_ELEMENT "TM"          → grupo = "TM"
START_ELEMENT "R"           → lado = "R"
START_ELEMENT "IOP_mmHg"    → currentElement = "IOP_mmHg"

CHARACTERS "17.0"           → si grupo="TM" y lado="R"
                              → añadir a superPaciente.rightEye.TM.lecturas

END_ELEMENT "R"             → lado = ""
END_ELEMENT "TM"            → grupo = ""
```

---

## Lo que hay que implementar

1. Declarar las variables `grupo` y `lado` en el parser
2. Actualizar `grupo` al entrar y salir de cada bloque de prueba
3. Actualizar `lado` al entrar y salir de `R` y `L`
4. En el `case CHARACTERS`, añadir lógica anidada:
   - si `grupo == "TM"` → asignar a `rightEye.TM` o `leftEye.TM` según `lado`
   - si `grupo == "CorrectedIOP"` → asignar a `rightEye.correctedIOP` o `leftEye.correctedIOP`

## Pregunta pendiente de responder antes de escribir código

Desde `superPaciente`, ¿cuál es la cadena completa de objetos para añadir una lectura al `ArrayList<TMList>` del ojo derecho?

---

## Consideración importante

Cada `<nsTM:List>` es un objeto `TMList` independiente que hay que instanciar, rellenar y añadir al ArrayList. No es un setter directo, es un `add()`.

El objeto `TMList` temporal debe crearse al entrar en `START_ELEMENT "List"` y añadirse al ArrayList al llegar a `END_ELEMENT "List"`.
