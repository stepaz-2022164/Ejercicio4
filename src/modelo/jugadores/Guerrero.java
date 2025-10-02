package modelo.jugadores;

import modelo.items.Item;
import modelo.combatientes.Combatiente;

import java.util.ArrayList;

/**
 * Clase Guerrero - Alta vida y ataque, pocos ítems.
 * Especializado en combate cuerpo a cuerpo.
 */
public class Guerrero extends Jugador {
    private static final int VIDA_BASE = 120;
    private static final int ATAQUE_BASE = 25;
    private static final int CAPACIDAD_ITEMS = 3;

    /**
     * Constructor del Guerrero
     * @param nombre Nombre del guerrero
     */
    public Guerrero(String nombre) {
        super(nombre, VIDA_BASE, ATAQUE_BASE, CAPACIDAD_ITEMS);
    }

    /**
     * Inicializa los ítems del guerrero (orientados a combate)
     */
    @Override
    protected void inicializarItems() {
        items.add(new Item("Poción Menor", "curacion", 30, "Restaura 30 HP"));
        items.add(new Item("Poción Menor", "curacion", 30, "Restaura 30 HP"));
        items.add(new Item("Furia de Batalla", "ataque", 15, "Aumenta ATK en 15 por 1 turno"));
    }

    /**
     * Implementación del turno del guerrero
     * El turno es controlado por el usuario desde BattleSimulator
     */
    @Override
    public void tomarTurno(ArrayList<Combatiente> objetivos) {
        // Este método será controlado por la interfaz de usuario
        // No necesita implementación aquí ya que el usuario controla las acciones
    }

    /**
     * Habilidad especial del guerrero: usar ítems
     */
    @Override
    public void habilidadEspecial(ArrayList<Combatiente> objetivos) {
        // La habilidad especial del jugador es usar ítems
        // Controlado por la interfaz de usuario
    }

    @Override
    public String mensajeInicio() {
        return "¡" + nombre + " entra al campo de batalla con su espada en alto!";
    }

    @Override
    public String mensajeMuerte() {
        return nombre + " cae derrotado... \"No... puede... terminar... así...\"";
    }

    @Override
    public String mensajeVictoria() {
        return "¡" + nombre + " alza su arma victorioso! \"¡La batalla es nuestra!\"";
    }

    @Override
    public String toString() {
        return String.format("[GUERRERO] %s - HP: %d/%d | ATK: %d | Ítems: %d/%d",
                nombre, vidaActual, vidaMaxima, poderAtaque, items.size(), capacidadItems);
    }
}
