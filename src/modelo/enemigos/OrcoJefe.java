package modelo.enemigos;

import modelo.combatientes.Combatiente;

import java.util.ArrayList;

/**
 * Clase OrcoJefe - Versión mejorada del Orco.
 * Además de regenerarse, puede activar una armadura que reduce el daño recibido.
 */
public class OrcoJefe extends Orco {
    private static final int VIDA_BASE = 140;
    private static final int ATAQUE_BASE = 30;
    private boolean armaduraActivada;
    private int turnosArmadura;

    /**
     * Constructor del Orco Jefe
     */
    public OrcoJefe() {
        super(true);
        this.armaduraActivada = false;
        this.turnosArmadura = 0;
    }

    /**
     * Habilidad adicional del jefe: Armadura de Piedra
     * Reduce el daño recibido por 2 turnos
     */
    public void activarArmadura() {
        if (!armaduraActivada && this.estaVivo()) {
            this.armaduraActivada = true;
            this.turnosArmadura = 2;
        }
    }

    /**
     * Sobrescribe recibirDanio para aplicar reducción de armadura
     */
    @Override
    public void recibirDanio(int danio) {
        if (armaduraActivada) {
            // Reduce el daño a la mitad
            danio = danio / 2;
            turnosArmadura--;

            if (turnosArmadura <= 0) {
                armaduraActivada = false;
            }
        }
        super.recibirDanio(danio);
    }

    /**
     * Sobrescribe tomarTurno para incluir armadura
     */
    @Override
    public void tomarTurno(ArrayList<Combatiente> objetivos) {
        if (objetivos.isEmpty() || !this.estaVivo()) {
            return;
        }

        int decision = random.nextInt(100);

        // Si la vida está baja, prioriza regeneración
        if (puedeRegenerarse && this.vidaActual < this.vidaMaxima * 0.4) {
            habilidadEspecial(objetivos);
        } else if (decision < 25 && !armaduraActivada) {
            // Activar armadura
            activarArmadura();
        } else if (decision < 40 && puedeRegenerarse) {
            // Regenerarse
            habilidadEspecial(objetivos);
        } else {
            // Ataque normal
            Combatiente objetivo = objetivos.get(random.nextInt(objetivos.size()));
            atacar(objetivo);
        }
    }

    @Override
    public String mensajeInicio() {
        return nombre + " entra al combate con armadura de batalla. \"¡SOY EL SEÑOR DE LA GUERRA!\"";
    }

    @Override
    public String mensajeMuerte() {
        return nombre + " cae con estruendo... \"Mi... legado... continuará...\"";
    }

    @Override
    public String mensajeVictoria() {
        return nombre + " ruge victorioso. \"¡NINGÚN ENEMIGO PUEDE DETENERME!\"";
    }

    public boolean isArmaduraActivada() {
        return armaduraActivada;
    }

    public int getTurnosArmadura() {
        return turnosArmadura;
    }
}
