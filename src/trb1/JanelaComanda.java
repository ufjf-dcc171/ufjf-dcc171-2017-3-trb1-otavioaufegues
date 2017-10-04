/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trb1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Otávio
 */
public class JanelaComanda extends JFrame {

    private JTable tabelaItens;
    private Comanda comanda;
    private final ArrayList<ItemCardapio> cardapio;
    private ArrayList<ItemComanda> listaComanda;
    private JComboBox itensCardapio = new JComboBox();
    private JButton btnAdicionar = new JButton("Adicionar Item");
    private JButton btnRemover = new JButton("Remover Item");
    private JTextField txtQuantidade = new JTextField(20);
    private JLabel labelQuantidade = new JLabel("Quantidade");
    private JLabel labelItem = new JLabel("Escolha um produto");
    private JLabel lblValorTotal = new JLabel();

    public JanelaComanda(Comanda comanda, ArrayList<ItemCardapio> cardapio) {
        super("Detalhes do Comanda " + comanda.getCodigo());
        this.comanda = comanda;
        this.cardapio = cardapio;

        itensCardapio.setModel(new DefaultComboBoxModel(cardapio.toArray()));
        tabelaItens = new JTable(new ItemTableModel(comanda, cardapio));
        tabelaItens.setModel(new ItemTableModel(comanda, cardapio));
        tabelaItens.getSelectionModel().addListSelectionListener(new ItensComandaHandler());
        txtQuantidade.setText("1");

        JPanel formulario = new JPanel();
        JPanel tabela = new JPanel();
        setLayout(new GridLayout(2, 3));
        formulario.setLayout(new FlowLayout(FlowLayout.RIGHT));
        formulario.add(btnAdicionar);
        formulario.add(btnRemover);
        formulario.add(itensCardapio);
        formulario.add(labelQuantidade);
        formulario.add(txtQuantidade);
        add(formulario);
        tabela.setLayout(new FlowLayout(FlowLayout.CENTER));
        tabela.add(new JScrollPane(tabelaItens));
        add(tabela);

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!comanda.isAberto()) {
                    return;
                }
                if (tabelaItens.getSelectedRow() >= 0) {
                    int item_deletar = tabelaItens.getSelectedRow();
                    comanda.getItensComanda().remove(item_deletar);
                    ((AbstractTableModel) tabelaItens.getModel()).fireTableDataChanged();
                    tabelaItens.setEnabled(true);
                    atualizarValorTotal();
                }
            }
        });
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!comanda.isAberto()) {
                    return;
                }
                if (isNumeric(txtQuantidade.getText()) && Integer.parseInt(txtQuantidade.getText()) > 0) {
                    if (tabelaItens.getSelectedRow() >= 0) {
                        int cod_item = tabelaItens.getSelectedRow();
                        ItemComanda itemp = comanda.getItensComanda().get(cod_item);
                        ItemCardapio itemc = cardapio.get(cod_item);
                        int novo_cod = ((ItemCardapio) itensCardapio.getSelectedItem()).getCodigo();
                        itemp.setCodigoItem(novo_cod);
                        int nova_quant = Integer.parseInt(txtQuantidade.getText());
                        itemp.setQuantidade(nova_quant);
                        ((AbstractTableModel) tabelaItens.getModel()).fireTableDataChanged();
                        tabelaItens.setEnabled(true);
                    } else {
                        ItemCardapio i = (ItemCardapio) itensCardapio.getSelectedItem();
                        comanda.acrescentarItem(i.getCodigo(), Integer.parseInt(txtQuantidade.getText()));
                        ((AbstractTableModel) tabelaItens.getModel()).fireTableDataChanged();
                        tabelaItens.setEnabled(true);
                    }
                    atualizarValorTotal();
                } else {
                    txtQuantidade.grabFocus();
                }
            }
        });
    }

    public boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private ItemCardapio getItemCardapio(int codigo) {
        return ((ItemTableModel) tabelaItens.getModel()).getItemCardapio(codigo);
    }

    private ItemComanda getItemComanda(int codigo) {
        return ((ItemTableModel) tabelaItens.getModel()).getItemComanda(codigo);
    }

    private void atualizarValorTotal() {
        lblValorTotal.setText(String.valueOf(calcularValorTotal()));
    }

    private double calcularValorTotal() {
        double vt = 0;
        for (ItemComanda i : comanda.getItensComanda()) {
            double vu = getItemCardapio(i.getCodigoItem()).getValorUnitario();
            vt += i.getQuantidade() * vu;
        }
        return vt;
    }

    private class ItensComandaHandler implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (tabelaItens.getSelectedRow() >= 0) {
                int cod_cardapio = (int) tabelaItens.getValueAt(tabelaItens.getSelectedRow(), 0);
                ItemCardapio itemCardapio = getItemCardapio(cod_cardapio);
                ItemComanda itemComanda = getItemComanda(cod_cardapio);
                itensCardapio.setSelectedItem(itemCardapio);
                txtQuantidade.setText(String.valueOf(itemComanda.getQuantidade()));
            }
        }
    }
}
