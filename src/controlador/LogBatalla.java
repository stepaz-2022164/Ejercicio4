package controlador;

import java.util.ArrayList;

/**
 * Clase LogBatalla - Mantiene un registro de las últimas acciones en batalla.
 * Muestra las últimas 3 acciones realizadas por los combatientes.
 */
public class LogBatalla {
    private ArrayList<String> acciones;
    private static final int MAX_ACCIONES = 3;

    /**
     * Constructor del log de batalla
     */
    public LogBatalla() {
        this.acciones = new ArrayList<>();
    }

    /**
     * Agrega una nueva acción al log
     * Mantiene solo las últimas MAX_ACCIONES acciones
     * @param accion Descripción de la acción realizada
     */
    public void agregarAccion(String accion) {
        acciones.add(accion);

        // Mantener solo las últimas MAX_ACCIONES
        if (acciones.size() > MAX_ACCIONES) {
            acciones.remove(0);
        }
    }

    /**
     * Muestra las últimas acciones registradas
     * @return String formateado con las últimas acciones
     */
    public String mostrarUltimasAcciones() {
        if (acciones.isEmpty()) {
            return "No hay acciones registradas aún.";
        }

        StringBuilder sb = new StringBuilder("\n═══ ÚLTIMAS ACCIONES ═══\n");
        for (String accion : acciones) {
            sb.append("• ").append(accion).append("\n");
        }
        sb.append("═══════════════════════\n");

        return sb.toString();
    }

    /**
     * Limpia todas las acciones del log
     */
    public void limpiar() {
        acciones.clear();
    }
}