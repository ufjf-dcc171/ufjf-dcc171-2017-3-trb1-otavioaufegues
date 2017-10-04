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
public class ItemComanda {

    private int codigoItem;
    private int quantidade;

    public ItemComanda(int codigoItem, int quantidade) {
        this.codigoItem = codigoItem;
        this.quantidade = quantidade;
    }

    public int getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(int codigoItem) {
        this.codigoItem = codigoItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}

