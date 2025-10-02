package main;

import controlador.Batalla;
import modelo.jugadores.Explorador;
import modelo.jugadores.Guerrero;
import modelo.jugadores.Jugador;

import java.util.Scanner;

/**
 * Clase principal BattleSimulator - Vista del sistema.
 * Maneja la interfaz de usuario y el flujo principal del juego.
 *
 * @author Tu Nombre
 * @version 1.0
 */
public class BattleSimulator {
    private static Scanner scanner = new Scanner(System.in);
    private static Jugador jugadorActual = null;

    /**
     * Método principal que inicia el simulador
     */
    public static void main(String[] args) {
        mostrarBienvenida();

        boolean continuar = true;

        while (continuar) {
            mostrarMenuPrincipal();
            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    if (jugadorActual == null) {
                        jugadorActual = crearJugador();
                    }
                    if (jugadorActual != null) {
                        iniciarNuevaBatalla(jugadorActual);
                        // Resetear jugador después de la batalla
                        jugadorActual = null;
                    }
                    break;
                case 2:
                    mostrarInstrucciones();
                    break;
                case 3:
                    System.out.println("\n¡Gracias por jugar! ¡Hasta la próxima aventura!");
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida. Por favor elige una opción válida.");
            }
        }

        scanner.close();
    }

    /**
     * Muestra el mensaje de bienvenida
     */
    private static void mostrarBienvenida() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          ⚔️  SIMULADOR DE BATALLA - RPG  ⚔️");
        System.out.println("=".repeat(60));
        System.out.println("\n¡Bienvenido al simulador de combate por turnos!");
        System.out.println("Prepárate para enfrentar enemigos peligrosos...\n");
    }

    /**
     * Muestra el menú principal del juego
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n" + "─".repeat(40));
        System.out.println("           MENÚ PRINCIPAL");
        System.out.println("─".repeat(40));
        System.out.println("1. Iniciar Nueva Batalla");
        System.out.println("2. Ver Instrucciones");
        System.out.println("3. Salir");
        System.out.println("─".repeat(40));
        System.out.print("Elige una opción: ");
    }

    /**
     * Crea un nuevo jugador seleccionando su rol
     * @return Jugador creado o null si se cancela
     */
    private static Jugador crearJugador() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                 CREACIÓN DE PERSONAJE");
        System.out.println("=".repeat(60));

        System.out.print("\nIngresa el nombre de tu personaje: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            nombre = "Héroe";
        }

        System.out.println("\nElige tu clase:");
        System.out.println("─".repeat(40));
        System.out.println("1. GUERRERO");
        System.out.println("   • Vida: 120 HP");
        System.out.println("   • Ataque: 25");
        System.out.println("   • Ítems: 3 (orientados a combate)");
        System.out.println("   • Estilo: Tanque resistente con alto daño");
        System.out.println();
        System.out.println("2. EXPLORADOR");
        System.out.println("   • Vida: 100 HP");
        System.out.println("   • Ataque: 20");
        System.out.println("   • Ítems: 6 (amplia variedad)");
        System.out.println("   • Estilo: Versátil con muchas opciones");
        System.out.println("─".repeat(40));
        System.out.print("Elige tu clase (1-2): ");

        int clase = leerEntero();

        Jugador jugador = null;

        switch (clase) {
            case 1:
                jugador = new Guerrero(nombre);
                System.out.println("\n✓ ¡" + nombre + " el Guerrero ha sido creado!");
                break;
            case 2:
                jugador = new Explorador(nombre);
                System.out.println("\n✓ ¡" + nombre + " el Explorador ha sido creado!");
                break;
            default:
                System.out.println("\nClase inválida. Creando Guerrero por defecto...");
                jugador = new Guerrero(nombre);
        }

        System.out.println("\n" + jugador);
        System.out.println("\n¡Presiona ENTER para continuar!");
        scanner.nextLine();

        return jugador;
    }

    /**
     * Inicia una nueva batalla con el jugador especificado
     * @param jugador Jugador que participará en la batalla
     */
    private static void iniciarNuevaBatalla(Jugador jugador) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("              PREPARANDO BATALLA...");
        System.out.println("=".repeat(60));
        System.out.println("\nGenerando enemigos...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignorar
        }

        Batalla batalla = new Batalla(jugador);
        batalla.iniciarBatalla();

        System.out.println("\n¡Presiona ENTER para volver al menú!");
        scanner.nextLine();
    }

    /**
     * Muestra las instrucciones del juego
     */
    private static void mostrarInstrucciones() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    INSTRUCCIONES");
        System.out.println("=".repeat(60));

        System.out.println("\n【OBJETIVO】");
        System.out.println("Derrota a todos los enemigos sin morir en el intento.");

        System.out.println("\n【MECÁNICAS】");
        System.out.println("• El combate se desarrolla por turnos");
        System.out.println("• En tu turno puedes: Atacar, Usar Ítems o Pasar");
        System.out.println("• Los enemigos atacan automáticamente en su turno");
        System.out.println("• Cada batalla enfrenta de 1 a 3 enemigos aleatorios");

        System.out.println("\n【CLASES】");
        System.out.println("GUERRERO: Alto HP y ataque, pocos ítems");
        System.out.println("EXPLORADOR: Stats balanceados, muchos ítems");

        System.out.println("\n【ENEMIGOS】");
        System.out.println("• GOBLIN: Ágil, puede esquivar ataques");
        System.out.println("• GOBLIN JEFE: Esquiva + Ataques furiosos múltiples");
        System.out.println("• ORCO: Resistente, puede regenerarse");
        System.out.println("• ORCO JEFE: Regeneración + Armadura que reduce daño");

        System.out.println("\n【ÍTEMS】");
        System.out.println("• Pociones: Restauran puntos de vida");
        System.out.println("• Potenciadores: Aumentan ataque por 1 turno");
        System.out.println("• Los ítems se consumen al usarse");

        System.out.println("\n【CONSEJOS】");
        System.out.println("• Usa ítems estratégicamente");
        System.out.println("• Observa los patrones de ataque enemigos");
        System.out.println("• Los jefes son más fuertes pero dan más experiencia");
        System.out.println("• No desperdicies ítems cuando estés con vida alta");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n¡Presiona ENTER para volver!");
        scanner.nextLine();
    }

    /**
     * Lee un entero del usuario con manejo de excepciones
     * @return El número ingresado o -1 si hay error
     */
    private static int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}