package controlador;

import modelo.combatientes.Combatiente;
import modelo.enemigos.*;
import modelo.items.Item;
import modelo.jugadores.Jugador;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase Batalla - Controlador principal del sistema de combate.
 * Maneja el flujo de batalla, turnos y condiciones de victoria/derrota.
 */
public class Batalla {
    private Jugador jugador;
    private ArrayList<Enemigo> enemigos;
    private ArrayList<Combatiente> combatientes;
    private int turnoActual;
    private boolean batallaEnCurso;
    private LogBatalla logBatalla;
    private Random random;
    private Scanner scanner;

    /**
     * Constructor de Batalla
     * @param jugador El jugador que participará en la batalla
     */
    public Batalla(Jugador jugador) {
        this.jugador = jugador;
        this.enemigos = new ArrayList<>();
        this.combatientes = new ArrayList<>();
        this.turnoActual = 0;
        this.batallaEnCurso = false;
        this.logBatalla = new LogBatalla();
        this.random = new Random();
        this.scanner = new Scanner(System.in);

        generarEnemigos();
        organizarTurnos();
    }

    /**
     * Genera entre 1 y 3 enemigos aleatorios
     */
    private void generarEnemigos() {
        int cantidadEnemigos = random.nextInt(3) + 1; // 1 a 3 enemigos

        for (int i = 0; i < cantidadEnemigos; i++) {
            int tipoEnemigo = random.nextInt(4); // 4 tipos posibles
            Enemigo enemigo;

            switch (tipoEnemigo) {
                case 0:
                    enemigo = new Goblin(false);
                    break;
                case 1:
                    enemigo = new GoblinJefe();
                    break;
                case 2:
                    enemigo = new Orco(false);
                    break;
                case 3:
                    enemigo = new OrcoJefe();
                    break;
                default:
                    enemigo = new Goblin(false);
            }

            enemigos.add(enemigo);
        }
    }

    /**
     * Organiza el orden de turnos
     */
    private void organizarTurnos() {
        combatientes.clear();
        combatientes.add(jugador);
        combatientes.addAll(enemigos);
    }

    /**
     * Inicia y ejecuta el loop principal de la batalla
     */
    public void iniciarBatalla() {
        batallaEnCurso = true;

        // Mensajes de inicio
        logBatalla.agregarAccion(jugador.mensajeInicio());
        for (Enemigo enemigo : enemigos) {
            logBatalla.agregarAccion(enemigo.mensajeInicio());
        }

        // Loop principal de batalla
        while (batallaEnCurso) {
            mostrarEstadoBatalla();

            Combatiente combatienteActual = combatientes.get(turnoActual);

            if (combatienteActual.estaVivo()) {
                procesarTurno(combatienteActual);
            }

            if (verificarCondicionesVictoria()) {
                batallaEnCurso = false;
                break;
            }

            siguienteTurno();
        }

        finalizarBatalla();
    }

    /**
     * Muestra el estado actual de todos los combatientes
     */
    private void mostrarEstadoBatalla() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    ESTADO DE BATALLA");
        System.out.println("=".repeat(60));

        System.out.println("\n【JUGADOR】");
        System.out.println(jugador);

        System.out.println("\n【ENEMIGOS】");
        for (int i = 0; i < enemigos.size(); i++) {
            if (enemigos.get(i).estaVivo()) {
                System.out.println("[" + (i + 1) + "] " + enemigos.get(i));
            } else {
                System.out.println("[" + (i + 1) + "] " + enemigos.get(i).getNombre() + " [DERROTADO]");
            }
        }

        System.out.println(logBatalla.mostrarUltimasAcciones());
    }

    /**
     * Procesa el turno de un combatiente
     * @param combatiente El combatiente que tomará su turno
     */
    private void procesarTurno(Combatiente combatiente) {
        if (combatiente instanceof Jugador) {
            turnoJugador();
        } else if (combatiente instanceof Enemigo) {
            turnoEnemigo((Enemigo) combatiente);
        }
    }

    /**
     * Maneja el turno del jugador con menú interactivo
     */
    private void turnoJugador() {
        System.out.println("\n【TU TURNO】");
        System.out.println("1. Atacar");
        System.out.println("2. Usar Ítem");
        System.out.println("3. Pasar turno");
        System.out.println("4. Salir de la batalla");
        System.out.print("Elige una opción: ");

        int opcion = leerEntero();

        switch (opcion) {
            case 1:
                realizarAtaqueJugador();
                break;
            case 2:
                usarItemJugador();
                break;
            case 3:
                logBatalla.agregarAccion(jugador.getNombre() + " decidió pasar su turno.");
                break;
            case 4:
                System.out.println("\n¡Has abandonado la batalla!");
                batallaEnCurso = false;
                break;
            default:
                System.out.println("Opción inválida. Se pasa el turno.");
                logBatalla.agregarAccion(jugador.getNombre() + " se confundió y perdió su turno.");
        }
    }

    /**
     * Realiza un ataque del jugador
     */
    private void realizarAtaqueJugador() {
        ArrayList<Enemigo> enemigosVivos = obtenerEnemigosVivos();

        if (enemigosVivos.isEmpty()) {
            System.out.println("No hay enemigos para atacar.");
            return;
        }

        System.out.println("\n¿A quién quieres atacar?");
        for (int i = 0; i < enemigosVivos.size(); i++) {
            System.out.println((i + 1) + ". " + enemigosVivos.get(i).getNombre());
        }
        System.out.print("Elige un objetivo: ");

        int eleccion = leerEntero() - 1;

        if (eleccion >= 0 && eleccion < enemigosVivos.size()) {
            Enemigo objetivo = enemigosVivos.get(eleccion);
            int vidaAntes = objetivo.getVidaActual();

            jugador.atacar(objetivo);

            int dañoRealizado = vidaAntes - objetivo.getVidaActual();

            if (dañoRealizado > 0) {
                logBatalla.agregarAccion(jugador.getNombre() + " atacó a " + objetivo.getNombre() +
                        " causando " + dañoRealizado + " de daño.");
            } else {
                logBatalla.agregarAccion(objetivo.getNombre() + " esquivó el ataque de " + jugador.getNombre() + "!");
            }

            if (!objetivo.estaVivo()) {
                logBatalla.agregarAccion(objetivo.mensajeMuerte());
            }
        } else {
            System.out.println("Objetivo inválido.");
        }
    }

    /**
     * Permite al jugador usar un ítem
     */
    private void usarItemJugador() {
        if (!jugador.tieneItems()) {
            System.out.println("¡No tienes ítems disponibles!");
            logBatalla.agregarAccion(jugador.getNombre() + " buscó un ítem pero no encontró ninguno.");
            return;
        }

        System.out.println("\n" + jugador.mostrarItems());
        System.out.print("Elige un ítem (0 para cancelar): ");

        int eleccion = leerEntero() - 1;

        if (eleccion == -1) {
            System.out.println("Acción cancelada.");
            return;
        }

        if (eleccion >= 0 && eleccion < jugador.getItems().size()) {
            Item item = jugador.getItems().get(eleccion);

            // Determinar el objetivo
            Combatiente objetivo;
            if (item.getTipo().equalsIgnoreCase("curacion")) {
                objetivo = jugador; // Los ítems de curación se usan en el jugador
            } else {
                objetivo = jugador; // Los ítems de ataque también afectan al jugador
            }

            jugador.usarItem(eleccion, objetivo);
            logBatalla.agregarAccion(jugador.getNombre() + " usó " + item.getNombre() + ".");
        } else {
            System.out.println("Ítem inválido.");
        }
    }

    /**
     * Maneja el turno de un enemigo con IA
     */
    private void turnoEnemigo(Enemigo enemigo) {
        ArrayList<Combatiente> objetivos = new ArrayList<>();
        objetivos.add(jugador);

        int vidaAntes = jugador.getVidaActual();

        // Determinar acción del enemigo
        int decision = random.nextInt(100);

        if (decision < 30) {
            // Usar habilidad especial
            if (enemigo instanceof GoblinJefe) {
                GoblinJefe jefe = (GoblinJefe) enemigo;
                if (jefe.getAtaquesFuriosos() > 0) {
                    jefe.ataquesFuriosos(jugador);
                    logBatalla.agregarAccion(enemigo.getNombre() + " usó Ataques Furiosos contra " + jugador.getNombre() + "!");
                    return;
                }
            } else if (enemigo instanceof OrcoJefe) {
                OrcoJefe jefe = (OrcoJefe) enemigo;
                if (!jefe.isArmaduraActivada()) {
                    jefe.activarArmadura();
                    logBatalla.agregarAccion(enemigo.getNombre() + " activó Armadura de Piedra!");
                    return;
                } else if (jefe.isPuedeRegenerarse() && jefe.getVidaActual() < jefe.getVidaMaxima() * 0.5) {
                    int vidaAntesRegen = jefe.getVidaActual();
                    jefe.habilidadEspecial(objetivos);
                    int vidaRecuperada = jefe.getVidaActual() - vidaAntesRegen;
                    logBatalla.agregarAccion(enemigo.getNombre() + " se regeneró " + vidaRecuperada + " HP!");
                    return;
                }
            } else if (enemigo instanceof Goblin) {
                Goblin goblin = (Goblin) enemigo;
                goblin.habilidadEspecial(objetivos);
                logBatalla.agregarAccion(enemigo.getNombre() + " activó Esquiva!");
                return;
            } else if (enemigo instanceof Orco) {
                Orco orco = (Orco) enemigo;
                if (orco.isPuedeRegenerarse() && orco.getVidaActual() < orco.getVidaMaxima() * 0.5) {
                    int vidaAntesRegen = orco.getVidaActual();
                    orco.habilidadEspecial(objetivos);
                    int vidaRecuperada = orco.getVidaActual() - vidaAntesRegen;
                    logBatalla.agregarAccion(enemigo.getNombre() + " se regeneró " + vidaRecuperada + " HP!");
                    return;
                }
            }
        }

        // Ataque normal
        enemigo.atacar(jugador);
        int dañoRealizado = vidaAntes - jugador.getVidaActual();

        if (dañoRealizado > 0) {
            logBatalla.agregarAccion(enemigo.getNombre() + " atacó a " + jugador.getNombre() +
                    " causando " + dañoRealizado + " de daño.");
        }

        if (!jugador.estaVivo()) {
            logBatalla.agregarAccion(jugador.mensajeMuerte());
        }
    }

    /**
     * Obtiene la lista de enemigos vivos
     */
    private ArrayList<Enemigo> obtenerEnemigosVivos() {
        ArrayList<Enemigo> vivos = new ArrayList<>();
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                vivos.add(enemigo);
            }
        }
        return vivos;
    }

    /**
     * Verifica si la batalla ha terminado
     * @return true si se cumplieron condiciones de victoria/derrota
     */
    private boolean verificarCondicionesVictoria() {
        // Verificar si el jugador murió
        if (!jugador.estaVivo()) {
            return true;
        }

        // Verificar si todos los enemigos murieron
        boolean todosEnemigosMuertos = true;
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                todosEnemigosMuertos = false;
                break;
            }
        }

        return todosEnemigosMuertos;
    }

    /**
     * Avanza al siguiente turno
     */
    private void siguienteTurno() {
        do {
            turnoActual = (turnoActual + 1) % combatientes.size();
        } while (!combatientes.get(turnoActual).estaVivo() && batallaEnCurso);
    }

    /**
     * Finaliza la batalla y muestra el resultado
     */
    private void finalizarBatalla() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                  FIN DE LA BATALLA");
        System.out.println("=".repeat(60));

        if (jugador.estaVivo()) {
            System.out.println("\n¡VICTORIA!");
            System.out.println(jugador.mensajeVictoria());
            System.out.println("\nTodos los enemigos han sido derrotados.");
        } else {
            System.out.println("\n¡DERROTA!");
            System.out.println("Has sido derrotado en batalla...");
            for (Enemigo enemigo : enemigos) {
                if (enemigo.estaVivo()) {
                    System.out.println(enemigo.mensajeVictoria());
                }
            }
        }

        System.out.println("\n" + "=".repeat(60));
    }

    /**
     * Lee un entero del usuario con manejo de excepciones
     */
    private int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
