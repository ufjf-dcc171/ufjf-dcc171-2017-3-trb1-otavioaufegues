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
public class ItemCardapio {

    private int codigo;
    private String nome;
    private double valorUnitario;

    public ItemCardapio(int codigo, String nome, double valorUnitario) {
        this.codigo = codigo;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nome + " (R$" + valorUnitario + ")";
    }

}

