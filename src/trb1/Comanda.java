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

public class Comanda {

    private int mesa;
    private String codigo;
    private boolean aberto;
    ArrayList<ItemComanda> itensComanda;

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<ItemComanda> getItensComanda() {
        return itensComanda;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Comanda(int mesa, String codigo) {
        this.mesa = mesa;
        this.codigo = codigo;
        this.aberto = true;
        itensComanda = new ArrayList<ItemComanda>();
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    @Override
    public String toString() {
        return "Comanda " + getCodigo() + " " + (aberto ? "[Aberto]" : "[Fechado]");
    }

    public void acrescentarItem(int codigo, int quantidade) {
        itensComanda.add(new ItemComanda(codigo, quantidade));
    }

}
