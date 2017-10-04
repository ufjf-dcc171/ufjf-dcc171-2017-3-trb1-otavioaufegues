/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trb1;

import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Otávio
 */
public class JanelaFechamento extends JFrame {

    ArrayList<ItemCardapio> cardapio;
    JList<String> DetalhesFechamento;
//

    public JanelaFechamento(Mesa m, ArrayList<ItemCardapio> cardapio) throws HeadlessException {
        super("Fechamento Mesa " + m.getNumero());
        this.cardapio = cardapio;
        imprimirDadosFechamento(m);
    }

    private void imprimirDadosFechamento(Mesa m) {
        Box vertical = Box.createVerticalBox();
        vertical.add(new JLabel("Fechamento da mesa " + m.getNumero()));
        vertical.add(new JLabel(" "));
        vertical.add(new JLabel("Hora abertura: " + m.getHoraAbertura()));
        vertical.add(new JLabel("Hora fechamento: " + m.getHoraFechamento()));
        vertical.add(new JLabel(" "));
        vertical.add(new JLabel(" "));
        for (Comanda comanda : m.getComandas()) {
            vertical.add(new JLabel("Comanda #" + comanda.getCodigo()));
            double valorTotal = 0;
            for (ItemComanda itemComanda : comanda.getItensComanda()) {
                ItemCardapio itemCardapio = buscarItemCardapio(itemComanda.getCodigoItem());
                vertical.add(new JLabel(itemCardapio.getNome() + ", Valor Unidade: R$" + itemCardapio.getValorUnitario()));
                vertical.add(new JLabel("Qtd: " + itemComanda.getQuantidade() + " x R$" + itemCardapio.getValorUnitario() + " = R$" + itemComanda.getQuantidade() * itemCardapio.getValorUnitario()));
                valorTotal += itemComanda.getQuantidade() * itemCardapio.getValorUnitario();
                vertical.add(new JLabel(" "));
            }
            vertical.add(new JLabel(" "));
            vertical.add(new JLabel("Valor Total da Mesa: R$" + valorTotal));
            vertical.add(new JLabel(" "));
        }
        add(vertical);
    }

    private ItemCardapio buscarItemCardapio(int codigoItem) {
        for (ItemCardapio ic : cardapio) {
            if (ic.getCodigo() == codigoItem) {
                return ic;
            }
        }
        return null;
    }
}
