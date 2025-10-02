package modelo.enemigos;

import modelo.combatientes.Combatiente;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase abstracta base para todos los enemigos.
 * Los enemigos tienen IA básica y habilidades especiales.
 */
public abstract class Enemigo extends Combatiente {
    protected String tipoEnemigo;
    protected boolean esJefe;
    protected Random random;

    /**
     * Constructor base para enemigos
     * @param nombre Nombre del enemigo
     * @param vida Puntos de vida base
     * @param ataque Poder de ataque base
     * @param esJefe Si es versión jefe o normal
     */
    protected Enemigo(String nombre, int vida, int ataque, boolean esJefe) {
        super(nombre, vida, ataque);
        this.esJefe = esJefe;
        this.random = new Random();
        if (esJefe) {
            aplicarBonusJefe();
        }
    }

    /**
     * Aplica mejoras a las estadísticas si es jefe
     */
    protected void aplicarBonusJefe() {
        this.vidaMaxima = (int)(this.vidaMaxima * 1.5);
        this.vidaActual = this.vidaMaxima;
        this.poderAtaque = (int)(this.poderAtaque * 1.3);
    }

    /**
     * IA básica para el turno del enemigo
     * Decide entre atacar o usar habilidad especial
     */
    @Override
    public void tomarTurno(ArrayList<Combatiente> objetivos) {
        if (objetivos.isEmpty() || !this.estaVivo()) {
            return;
        }

        // 30% de probabilidad de usar habilidad especial
        if (random.nextInt(100) < 30) {
            habilidadEspecial(objetivos);
        } else {
            // Atacar a un objetivo aleatorio
            Combatiente objetivo = objetivos.get(random.nextInt(objetivos.size()));
            atacar(objetivo);
        }
    }

    public boolean getEsJefe() {
        return esJefe;
    }

    @Override
    public String toString() {
        String tipo = esJefe ? "[JEFE] " : "";
        return String.format("%s%s - HP: %d/%d | ATK: %d",
                tipo, nombre, vidaActual, vidaMaxima, poderAtaque);
    }
}
