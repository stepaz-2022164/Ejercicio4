package modelo.enemigos;

import modelo.combatientes.Combatiente;

import java.util.ArrayList;

/**
 * Clase Orco - Enemigo resistente con habilidad de regeneración.
 * Los orcos pueden regenerar puntos de vida durante la batalla.
 */
public class Orco extends Enemigo {
    private static final int VIDA_BASE = 80;
    private static final int ATAQUE_BASE = 20;
    protected boolean puedeRegenerarse;

    /**
     * Constructor del Orco
     * @param esJefe Si es versión jefe o normal
     */
    public Orco(boolean esJefe) {
        super(esJefe ? "Orco Jefe" : "Orco", VIDA_BASE, ATAQUE_BASE, esJefe);
        this.tipoEnemigo = "Orco";
        this.puedeRegenerarse = true;
    }

    /**
     * Habilidad especial: Regeneración de vida
     * Recupera un porcentaje de vida máxima
     */
    @Override
    public void habilidadEspecial(ArrayList<Combatiente> objetivos) {
        if (puedeRegenerarse && this.vidaActual < this.vidaMaxima) {
            int curacion = (int)(this.vidaMaxima * 0.25); // Regenera 25% de vida
            this.curar(curacion);
            puedeRegenerarse = false; // Solo puede regenerarse una vez
        }
    }

    @Override
    public String mensajeInicio() {
        return nombre + " entra bramando salvajemente. \"¡GRAAAGH!\"";
    }

    @Override
    public String mensajeMuerte() {
        return nombre + " cae pesadamente al suelo con un último rugido.";
    }

    @Override
    public String mensajeVictoria() {
        return nombre + " golpea su pecho con orgullo. \"¡ORCOS SUPERIORES!\"";
    }

    public boolean isPuedeRegenerarse() {
        return puedeRegenerarse;
    }
}
