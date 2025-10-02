# 🎮 Simulador de Batalla RPG

---

## 📋 Descripción del Proyecto

Sistema de batalla por turnos para un juego de rol donde un jugador enfrenta enemigos en combate. El proyecto implementa conceptos avanzados de POO incluyendo herencia, polimorfismo, encapsulación y el patrón MVC.

---

## 🎯 Características Principales

### ✅ Requisitos Funcionales Implementados

- **Sistema de Combatientes**
    - Jugadores con 2 roles: Guerrero y Explorador
    - Enemigos con 2 tipos: Goblin y Orco
    - Versiones normales y jefes de cada enemigo

- **Sistema de Batalla por Turnos**
    - Combate entre 1 jugador y 1-3 enemigos aleatorios
    - Turnos alternados con acciones estratégicas
    - Registro de últimas 3 acciones

- **Habilidades Especiales**
    - Cada tipo de enemigo tiene habilidades únicas
    - Versiones jefe tienen habilidades adicionales
    - Jugadores usan ítems como habilidad especial

- **Sistema de Ítems**
    - Ítems de curación (pociones)
    - Ítems de potenciación (aumentan ataque)
    - Cantidad limitada según el rol
    - Consumo al usar

---

## 🏗️ Estructura del Proyecto

```
proyecto/
├── src/
│   ├── Combatiente.java      # Clase abstracta base
│   ├── Jugador.java           # Clase abstracta de jugadores
│   ├── Guerrero.java          # Clase concreta: Guerrero
│   ├── Explorador.java        # Clase concreta: Explorador
│   ├── Enemigo.java           # Clase abstracta de enemigos
│   ├── Goblin.java            # Clase concreta: Goblin
│   ├── GoblinJefe.java        # Clase concreta: Goblin Jefe
│   ├── Orco.java              # Clase concreta: Orco
│   ├── OrcoJefe.java          # Clase concreta: Orco Jefe
│   ├── Item.java              # Clase de ítems
│   ├── Batalla.java           # Controlador de batalla
│   ├── LogBatalla.java        # Registro de acciones
│   └── BattleSimulator.java   # Vista principal (Main)
├── docs/
│   ├── UML.puml               # Diagrama de clases
│   └── Analisis.pdf           # Documento de análisis
└── README.md
```

---

## 🎲 Mecánicas del Juego

### Clases de Jugador

| Clase | Vida | Ataque | Capacidad Ítems | Estilo |
|-------|------|--------|-----------------|--------|
| **Guerrero** | 120 HP | 25 | 3 ítems | Tanque con alto daño |
| **Explorador** | 100 HP | 20 | 6 ítems | Versátil y estratégico |

### Tipos de Enemigos

#### 🟢 Goblin (Normal)
- **Vida:** 60 HP
- **Ataque:** 15
- **Habilidad:** Esquiva (evita el próximo ataque)

#### 🔴 Goblin Jefe
- **Vida:** 100 HP
- **Ataque:** 25
- **Habilidades:**
    - Esquiva
    - Ataques Furiosos (2 ataques en un turno)

#### 🟢 Orco (Normal)
- **Vida:** 80 HP
- **Ataque:** 20
- **Habilidad:** Regeneración (recupera 25% de vida)

#### 🔴 Orco Jefe
- **Vida:** 140 HP
- **Ataque:** 30
- **Habilidades:**
    - Regeneración
    - Armadura de Piedra (reduce daño a la mitad por 2 turnos)

### Ítems Disponibles

- **Poción Menor:** Restaura 30 HP
- **Poción Mayor:** Restaura 50 HP
- **Elixir Mágico:** Restaura 40 HP
- **Furia de Batalla:** +15 ATK por 1 turno
- **Aceite de Armas:** +15 ATK por 1 turno
- **Veneno Paralizante:** +10 ATK por 1 turno

---

## 🎨 Principios de Diseño Aplicados

### 1. **Herencia**
- Jerarquía clara: `Combatiente` → `Jugador`/`Enemigo` → Clases específicas
- Reutilización de código a través de clases base
- Especialización mediante clases derivadas

### 2. **Polimorfismo**
- Métodos abstractos implementados en subclases
- Comportamientos específicos por tipo de combatiente
- Override de métodos para personalizar funcionalidad

### 3. **Encapsulación**
- Atributos `protected` en clases base
- Atributos `private` en clases concretas
- Getters/Setters donde es necesario
- Ocultamiento de implementación

### 4. **Patrón MVC**
- **Modelo:** Clases de combatientes, ítems
- **Vista:** `BattleSimulator` (interfaz de usuario)
- **Controlador:** `Batalla` (lógica de combate)

### 5. **Buenas Prácticas**
- Nombres descriptivos de variables y métodos
- Comentarios Javadoc en todas las clases
- Constantes para valores fijos
- Manejo de excepciones
- Validación de entrada de usuario

---

## 🚀 Cómo Ejecutar

### Requisitos
- Java JDK 8 o superior
- Compilador Java (javac)

### Compilación
```bash
# Compilar todos los archivos
javac *.java

# O compilar el archivo principal (compilará dependencias automáticamente)
javac BattleSimulator.java
```

### Ejecución
```bash
java BattleSimulator
```

---

## 📖 Guía de Uso

1. **Inicio del Juego**
    - Ejecutar `BattleSimulator`
    - Seleccionar "Iniciar Nueva Batalla"

2. **Creación de Personaje**
    - Ingresar nombre del personaje
    - Elegir clase (Guerrero o Explorador)

3. **Durante la Batalla**
    - Observar el estado de todos los combatientes
    - Revisar las últimas 3 acciones
    - En tu turno elegir:
        - **Atacar:** Seleccionar un enemigo objetivo
        - **Usar Ítem:** Elegir de tu inventario
        - **Pasar Turno:** No hacer nada
        - **Salir:** Abandonar la batalla

4. **Finalización**
    - Victoria: Derrotar a todos los enemigos
    - Derrota: Tu personaje llega a 0 HP

---

## 🎯 Estrategias Recomendadas

### Como Guerrero
- Aprovecha tu alta vida para aguantar daño
- Usa ítems de ataque antes de atacar
- Reserva pociones para emergencias

### Como Explorador
- Usa tu variedad de ítems estratégicamente
- Alterna entre curación y potenciación
- Administra bien tus 6 ítems

### Contra Enemigos
- **Goblins:** Ataca cuando no tengan esquiva activa
- **Orcos:** Daño constante antes de que se regeneren
- **Jefes:** Prioriza curación, son batallas largas

---

## 🧪 Casos de Prueba

### Caso 1: Victoria contra Goblin Normal
- **Entrada:** Guerrero nivel 1, atacar constantemente
- **Esperado:** Victoria con ~70-80 HP restante

### Caso 2: Uso de Ítems
- **Entrada:** Explorador, usar poción cuando HP < 50%
- **Esperado:** Curación exitosa, ítem consumido

### Caso 3: Habilidad de Esquiva
- **Entrada:** Atacar a Goblin con esquiva activa
- **Esperado:** Ataque evitado, sin daño al goblin

### Caso 4: Batalla contra Jefe
- **Entrada:** Cualquier clase vs Orco Jefe
- **Esperado:** Batalla prolongada, múltiples turnos

---

## 📊 Diagrama UML

El diagrama de clases completo se encuentra en `docs/UML.puml` y muestra:
- Jerarquía de herencia completa
- Relaciones entre clases
- Atributos y métodos de cada clase
- Modificadores de visibilidad

## 🎓 Conceptos Aprendidos

1. **Herencia y Polimorfismo**
    - Clases abstractas vs concretas
    - Métodos abstractos y override
    - Reutilización de código

2. **Diseño Orientado a Objetos**
    - Separación de responsabilidades
    - Alta cohesión, bajo acoplamiento
    - Patrón MVC

3. **Programación Defensiva**
    - Validación de entrada
    - Manejo de excepciones
    - Valores por defecto

---

## 📝 Notas del Desarrollador

### Decisiones de Diseño

1. **IA de Enemigos:** Probabilística (30% habilidad, 70% ataque)
2. **Ítems de Ataque:** Boost temporal de 1 turno
3. **Regeneración:** Una sola vez por batalla
4. **Esquiva de Goblin:** Solo evita el próximo ataque

### Posibles Mejoras Futuras

- [ ] Sistema de experiencia y niveles
- [ ] Más tipos de enemigos
- [ ] Más clases de jugador
- [ ] Sistema de inventario persistente
- [ ] Guardado de progreso
- [ ] Modo multijugador
- [ ] Interfaz gráfica (GUI)
- [ ] Efectos de sonido

---

## 👤 Autor

**Sergio Eduardo Tepaz Vela**  
Carné: 25787  
Universidad del Valle de Guatemala  
Curso: CC2008 - POO

---

## 📄 Licencia

Este proyecto es parte de un ejercicio académico para la Universidad del Valle de Guatemala.

---

**¡Que disfrutes del juego! ⚔️🎮**