package modelo.jugadores;
import modelo.items.Item;
import modelo.combatientes.Combatiente;

import java.util.ArrayList;

/**
 * Clase abstracta que representa a un jugador.
 * Los jugadores pueden usar ítems como habilidad especial.
 */
public abstract class Jugador extends Combatiente {
    protected ArrayList<Item> items;
    protected int capacidadItems;
    protected int boostAtaqueActual;

    /**
     * Constructor para jugadores
     * @param nombre Nombre del jugador
     * @param vida Puntos de vida base
     * @param ataque Poder de ataque base
     * @param capacidad Capacidad de ítems
     */
    protected Jugador(String nombre, int vida, int ataque, int capacidad) {
        super(nombre, vida, ataque);
        this.capacidadItems = capacidad;
        this.items = new ArrayList<>();
        this.boostAtaqueActual = 0;
        inicializarItems();
    }

    /**
     * Usa un ítem específico del inventario
     * @param indiceItem Índice del ítem en la lista
     * @param objetivo Combatiente objetivo del ítem
     * @return true si se usó exitosamente, false en caso contrario
     */
    public boolean usarItem(int indiceItem, Combatiente objetivo) {
        if (indiceItem >= 0 && indiceItem < items.size()) {
            Item item = items.get(indiceItem);

            // Aplicar efecto según el tipo
            if (item.getTipo().equalsIgnoreCase("ataque")) {
                this.boostAtaqueActual = item.getValor();
            } else {
                item.usar(objetivo);
            }

            items.remove(indiceItem);
            return true;
        }
        return false;
    }

    /**
     * Agrega un ítem al inventario si hay espacio
     * @param item Ítem a agregar
     * @return true si se agregó, false si no hay espacio
     */
    public boolean agregarItem(Item item) {
        if (items.size() < capacidadItems) {
            items.add(item);
            return true;
        }
        return false;
    }

    /**
     * Muestra la lista de ítems disponibles
     * @return String con los ítems formateados
     */
    public String mostrarItems() {
        if (items.isEmpty()) {
            return "No tienes ítems disponibles.";
        }

        StringBuilder sb = new StringBuilder("Ítems disponibles:\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(String.format("[%d] %s\n", i + 1, items.get(i)));
        }
        return sb.toString();
    }

    /**
     * Verifica si el jugador tiene ítems
     * @return true si tiene ítems, false en caso contrario
     */
    public boolean tieneItems() {
        return !items.isEmpty();
    }

    /**
     * Sobrescribe el ataque para incluir boost temporal
     */
    @Override
    public void atacar(Combatiente objetivo) {
        if (this.estaVivo() && objetivo.estaVivo()) {
            int dañoTotal = this.poderAtaque + this.boostAtaqueActual;
            objetivo.recibirDanio(dañoTotal);

            // Resetear boost después de usar
            if (this.boostAtaqueActual > 0) {
                this.boostAtaqueActual = 0;
            }
        }
    }

    /**
     * Método abstracto para inicializar ítems según el rol
     */
    protected abstract void inicializarItems();

    public ArrayList<Item> getItems() {
        return items;
    }
}
