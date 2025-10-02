package modelo.combatientes;

import java.util.ArrayList;

/**
 * Clase abstracta base para todos los combatientes del juego.
 * Define los atributos y comportamientos comunes entre jugadores y enemigos.
 */
public abstract class Combatiente {
    protected String nombre;
    protected int vidaMaxima;
    protected int vidaActual;
    protected int poderAtaque;
    protected boolean vivo;

    /**
     * Constructor base para todos los combatientes
     * @param nombre Nombre del combatiente
     * @param vida Puntos de vida máximos
     * @param ataque Poder de ataque base
     */
    protected Combatiente(String nombre, int vida, int ataque) {
        this.nombre = nombre;
        this.vidaMaxima = vida;
        this.vidaActual = vida;
        this.poderAtaque = ataque;
        this.vivo = true;
    }

    /**
     * Realiza un ataque básico al objetivo reduciendo sus puntos de vida
     * @param objetivo Combatiente que recibe el ataque
     */
    public void atacar(Combatiente objetivo) {
        if (this.estaVivo() && objetivo.estaVivo()) {
            objetivo.recibirDanio(this.poderAtaque);
        }
    }

    /**
     * Recibe daño y reduce los puntos de vida actuales
     * @param danio Cantidad de daño recibido
     */
    public void recibirDanio(int danio) {
        this.vidaActual -= danio;
        if (this.vidaActual <= 0) {
            this.vidaActual = 0;
            this.vivo = false;
        }
    }

    /**
     * Restaura puntos de vida sin exceder el máximo
     * @param cantidad Cantidad de vida a restaurar
     */
    public void curar(int cantidad) {
        this.vidaActual += cantidad;
        if (this.vidaActual > this.vidaMaxima) {
            this.vidaActual = this.vidaMaxima;
        }
    }

    /**
     * Verifica si el combatiente está vivo
     * @return true si tiene vida, false en caso contrario
     */
    public boolean estaVivo() {
        return this.vivo && this.vidaActual > 0;
    }

    // Métodos abstractos que deben implementar las subclases
    public abstract void tomarTurno(ArrayList<Combatiente> objetivos);
    public abstract void habilidadEspecial(ArrayList<Combatiente> objetivos);
    public abstract String mensajeInicio();
    public abstract String mensajeMuerte();
    public abstract String mensajeVictoria();

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getPoderAtaque() {
        return poderAtaque;
    }

    @Override
    public String toString() {
        return String.format("%s - HP: %d/%d | ATK: %d",
                nombre, vidaActual, vidaMaxima, poderAtaque);
    }
}