package modelo.enemigos;

import modelo.combatientes.Combatiente;

import java.util.ArrayList;

/**
 * Clase Goblin - Enemigo ágil con habilidad de esquiva.
 * Los goblins pueden esquivar ataques activando su habilidad.
 */
public class Goblin extends Enemigo {
    private static final int VIDA_BASE = 60;
    private static final int ATAQUE_BASE = 15;
    protected boolean esquivaActiva;

    /**
     * Constructor del Goblin
     * @param esJefe Si es versión jefe o normal
     */
    public Goblin(boolean esJefe) {
        super(esJefe ? "Goblin Jefe" : "Goblin", VIDA_BASE, ATAQUE_BASE, esJefe);
        this.tipoEnemigo = "Goblin";
        this.esquivaActiva = false;
    }

    /**
     * Habilidad especial: Activa esquiva para evitar el próximo ataque
     */
    @Override
    public void habilidadEspecial(ArrayList<Combatiente> objetivos) {
        this.esquivaActiva = true;
        // El mensaje se registrará en el log de batalla
    }

    /**
     * Sobrescribe recibirDanio para aplicar esquiva
     */
    @Override
    public void recibirDanio(int danio) {
        if (esquivaActiva) {
            esquivaActiva = false;
            // Esquiva exitosa, no recibe daño
            return;
        }
        super.recibirDanio(danio);
    }

    @Override
    public String mensajeInicio() {
        return nombre + " aparece con una risa maliciosa. \"¡Jejeje!\"";
    }

    @Override
    public String mensajeMuerte() {
        return nombre + " cae derrotado con un grito agudo.";
    }

    @Override
    public String mensajeVictoria() {
        return nombre + " baila sobre sus enemigos caídos. \"¡Victoria!\"";
    }

    public boolean isEsquivaActiva() {
        return esquivaActiva;
    }
}