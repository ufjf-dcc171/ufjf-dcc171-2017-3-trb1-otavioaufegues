/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trb1;

/**
 *
 * @author Otávio
 */
import java.util.ArrayList;

public class Mesa {

    private int numero;
    private boolean aberto;
    private String horaAbertura;
    private String horaFechamento;
    private ArrayList<Comanda> comandas;

    public Mesa(int numero) {
        this.numero = numero;
        this.aberto = false;
        this.horaAbertura = "-";
        this.horaFechamento = "-";
    }

    public ArrayList<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(ArrayList<Comanda> comandas) {
        this.comandas = comandas;
    }

    public void abrirComanda(Comanda p) {
        comandas.add(p);
    }

    public String getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(String horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public String getHoraFechamento() {
        return horaFechamento;
    }

    public void setHoraFechamento(String horaFechamento) {
        this.horaFechamento = horaFechamento;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Mesa " + this.numero + " " + (aberto ? "[Aberto]" : "[Fechado]");
    }

}
