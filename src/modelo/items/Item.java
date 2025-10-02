package modelo.items;

import modelo.combatientes.Combatiente;

/**
 * Clase que representa los ítems utilizables por el jugador.
 * Los ítems pueden curar, aumentar ataque, o tener otros efectos.
 */
public class Item {
    private String nombre;
    private String tipo;
    private int valor;
    private String descripcion;

    /**
     * Constructor para crear un ítem
     * @param nombre Nombre del ítem
     * @param tipo Tipo de ítem (curacion, ataque, etc.)
     * @param valor Valor numérico del efecto
     * @param descripcion Descripción del efecto
     */
    public Item(String nombre, String tipo, int valor, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    /**
     * Usa el ítem sobre un combatiente objetivo
     * @param objetivo Combatiente que recibe el efecto
     */
    public void usar(Combatiente objetivo) {
        switch (tipo.toLowerCase()) {
            case "curacion":
                objetivo.curar(valor);
                break;
            case "ataque":
                // El aumento de ataque se maneja en la clase Jugador
                break;
            default:
                break;
        }
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", nombre, descripcion);
    }
}
