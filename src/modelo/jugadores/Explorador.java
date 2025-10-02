package modelo.jugadores;

import modelo.items.Item;
import modelo.combatientes.Combatiente;

import java.util.ArrayList;

/**
 * Clase Explorador - Stats balanceados, muchos ítems.
 * Especializado en versatilidad y uso de ítems.
 */
public class Explorador extends Jugador {
    private static final int VIDA_BASE = 100;
    private static final int ATAQUE_BASE = 20;
    private static final int CAPACIDAD_ITEMS = 6;

    /**
     * Constructor del Explorador
     * @param nombre Nombre del explorador
     */
    public Explorador(String nombre) {
        super(nombre, VIDA_BASE, ATAQUE_BASE, CAPACIDAD_ITEMS);
    }

    /**
     * Inicializa los ítems del explorador (amplia variedad)
     */
    @Override
    protected void inicializarItems() {
        items.add(new Item("Poción Menor", "curacion", 30, "Restaura 30 HP"));
        items.add(new Item("Poción Mayor", "curacion", 50, "Restaura 50 HP"));
        items.add(new Item("Poción Menor", "curacion", 30, "Restaura 30 HP"));
        items.add(new Item("Veneno Paralizante", "ataque", 10, "Aumenta ATK en 10 por 1 turno"));
        items.add(new Item("Aceite de Armas", "ataque", 15, "Aumenta ATK en 15 por 1 turno"));
        items.add(new Item("Elixir Mágico", "curacion", 40, "Restaura 40 HP"));
    }

    /**
     * Implementación del turno del explorador
     * El turno es controlado por el usuario desde BattleSimulator
     */
    @Override
    public void tomarTurno(ArrayList<Combatiente> objetivos) {
        // Este método será controlado por la interfaz de usuario
        // No necesita implementación aquí ya que el usuario controla las acciones
    }

    /**
     * Habilidad especial del explorador: usar ítems
     */
    @Override
    public void habilidadEspecial(ArrayList<Combatiente> objetivos) {
        // La habilidad especial del jugador es usar ítems
        // Controlado por la interfaz de usuario
    }

    @Override
    public String mensajeInicio() {
        return nombre + " emerge de las sombras, listo para la batalla.";
    }

    @Override
    public String mensajeMuerte() {
        return nombre + " cae al suelo... \"Sabía que... esto era... arriesgado...\"";
    }

    @Override
    public String mensajeVictoria() {
        return nombre + " sonríe con confianza. \"Otro desafío superado.\"";
    }

    @Override
    public String toString() {
        return String.format("[EXPLORADOR] %s - HP: %d/%d | ATK: %d | Ítems: %d/%d",
                nombre, vidaActual, vidaMaxima, poderAtaque, items.size(), capacidadItems);
    }
}
