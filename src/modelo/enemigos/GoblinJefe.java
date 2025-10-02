package modelo.enemigos;

import modelo.combatientes.Combatiente;

import java.util.ArrayList;

/**
 * Clase GoblinJefe - Versión mejorada del Goblin.
 * Además de esquivar, puede realizar ataques furiosos múltiples.
 */
public class GoblinJefe extends Goblin {
    private static final int VIDA_BASE = 100;
    private static final int ATAQUE_BASE = 25;
    private int ataquesFuriosos;

    /**
     * Constructor del Goblin Jefe
     */
    public GoblinJefe() {
        super(true);
        this.ataquesFuriosos = 2; // Puede usar esta habilidad 2 veces
    }

    /**
     * Habilidad adicional del jefe: Ataques furiosos
     * Realiza múltiples ataques en un solo turno
     */
    public void ataquesFuriosos(Combatiente objetivo) {
        if (ataquesFuriosos > 0 && this.estaVivo() && objetivo.estaVivo()) {
            // Realiza 2 ataques consecutivos con daño reducido
            objetivo.recibirDanio((int)(this.poderAtaque * 0.6));
            if (objetivo.estaVivo()) {
                objetivo.recibirDanio((int)(this.poderAtaque * 0.6));
            }
            ataquesFuriosos--;
        }
    }

    /**
     * Sobrescribe tomarTurno para incluir ataques furiosos
     */
    @Override
    public void tomarTurno(ArrayList<Combatiente> objetivos) {
        if (objetivos.isEmpty() || !this.estaVivo()) {
            return;
        }

        // 40% de probabilidad de usar habilidad especial
        int decision = random.nextInt(100);

        if (decision < 20 && ataquesFuriosos > 0) {
            // Usar ataques furiosos
            Combatiente objetivo = objetivos.get(random.nextInt(objetivos.size()));
            ataquesFuriosos(objetivo);
        } else if (decision < 40) {
            // Usar esquiva
            habilidadEspecial(objetivos);
        } else {
            // Ataque normal
            Combatiente objetivo = objetivos.get(random.nextInt(objetivos.size()));
            atacar(objetivo);
        }
    }

    @Override
    public String mensajeInicio() {
        return nombre + " aparece rugiendo furiosamente. \"¡SOY EL LÍDER DE LA TRIBU!\"";
    }

    @Override
    public String mensajeMuerte() {
        return nombre + " cae de rodillas... \"Imposible... derrotado por... humanos...\"";
    }

    @Override
    public String mensajeVictoria() {
        return nombre + " levanta su arma triunfante. \"¡NADIE PUEDE CONTRA MÍ!\"";
    }

    public int getAtaquesFuriosos() {
        return ataquesFuriosos;
    }
}
