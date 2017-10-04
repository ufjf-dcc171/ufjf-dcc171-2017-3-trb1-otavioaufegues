/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trb1;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Otávio
 */
class Janela extends JFrame {

    private int nrComanda = 1;
    private final List<Mesa> mesas;
    private ArrayList<ItemCardapio> cardapio;
    private final JList<Comanda> ItensCardapio = new JList<>(new DefaultListModel<>());
    private final JList<Mesa> listaMesas = new JList<>(new DefaultListModel<>());
    private final JList<Comanda> listaComandas = new JList<>(new DefaultListModel<>());
    private final JButton btnAbrirMesa = new JButton("Abrir Mesa");
    private final JButton btnFecharMesa = new JButton("Fechar Mesa");
    private final JButton btnAbrirComanda = new JButton("Abrir Comanda");
    private final JButton btnFecharComanda = new JButton("Fechar Comanda");
    private final JButton btnAdicionarItem = new JButton("Fazer Pedido");

    public Janela(List<Mesa> mesas) throws HeadlessException {
        super("Restaurante");
        this.mesas = mesas;
        criarCardapio();

        listaMesas.setModel(new MesaListModel(mesas));
        setLayout(new GridLayout(2, 2, 10, 10));

        add(new JScrollPane(listaMesas));
        add(new JScrollPane(listaComandas));

        JPanel botoesMesa = new JPanel(new GridLayout(2, 2));
        JPanel botoesComandas = new JPanel(new GridLayout(2, 2));
        add(botoesMesa);
        botoesMesa.add(btnAbrirMesa);
        botoesMesa.add(btnFecharMesa);

        add(botoesComandas);
        botoesComandas.add(btnAbrirComanda);
        botoesComandas.add(btnFecharComanda);
        botoesComandas.add(btnAdicionarItem);

        btnAdicionarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comanda selecionada = listaComandas.getSelectedValue();
                if (!listaComandas.isSelectionEmpty() && selecionada.isAberto()) {
                    Comanda comanda = (Comanda) listaComandas.getSelectedValue();
                    JanelaComanda janelaComanda = new JanelaComanda(comanda, cardapio);
                    janelaComanda.setSize(500, 500);
                    janelaComanda.setLocationRelativeTo(null);
                    janelaComanda.setVisible(true);
                    janelaComanda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }
        });

        btnFecharComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!listaComandas.isSelectionEmpty()) {
                    Comanda comanda = (Comanda) listaComandas.getSelectedValue();
                    comanda.setAberto(false);
                    listaComandas.repaint();
                }
            }
        });

        btnAbrirComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!listaMesas.isSelectionEmpty()) {
                    Mesa selecionada = listaMesas.getSelectedValue();
                    if (selecionada.isAberto()) {
                        Comanda novaComanda = new Comanda(selecionada.getNumero(), String.valueOf(nrComanda++));
                        selecionada.abrirComanda(novaComanda);
                        listaComandas.setModel(new ComandasListModel(selecionada.getComandas()));
                        listaComandas.repaint();
                    }
                }
            }
        }
        );

        listaMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaMesas.addListSelectionListener(
                new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e
            ) {
                Mesa selecionada = listaMesas.getSelectedValue();
                if (selecionada != null) {
                    listaComandas.setModel(new ComandasListModel(selecionada.getComandas()));
                } else {
                    listaComandas.setModel(new DefaultListModel<>());
                }
            }
        }
        );

        btnAbrirMesa.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (listaMesas.isSelectionEmpty()) {
                    return;
                }
                Mesa selecionada = listaMesas.getSelectedValue();
                selecionada.setAberto(true);
                selecionada.setHoraAbertura(getTime());
                listaMesas.repaint();
            }
        }
        );

        btnFecharMesa.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (listaMesas.isSelectionEmpty()) {
                    return;
                }
                Mesa selecionada = listaMesas.getSelectedValue();
                boolean flag = false;
                for (Comanda comanda : selecionada.getComandas()) {
                    if (comanda.isAberto()) {
                        flag = true;
                    }
                }
                if (!flag) {
                    selecionada.setAberto(false);
                    selecionada.setHoraFechamento(getTime());
                    listaMesas.repaint();

                    JanelaFechamento janelaFechamento = new JanelaFechamento(selecionada, cardapio);
                    janelaFechamento.setSize(500, 500);
                    janelaFechamento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    janelaFechamento.setLocationRelativeTo(null);
                    janelaFechamento.setVisible(true);

                }
            }
        }
        );
    }

    private void criarCardapio() {
        cardapio = new ArrayList<>();
        cardapio.add(new ItemCardapio(1, "Agua", 2.5));
        cardapio.add(new ItemCardapio(2, "Refrigerante", 3.0));
        cardapio.add(new ItemCardapio(3, "Cerveja", 8.5));
        cardapio.add(new ItemCardapio(4, "Pizza", 25.5));
        cardapio.add(new ItemCardapio(5, "Espetinho", 10.5));
        cardapio.add(new ItemCardapio(6, "Hamburger", 15.5));
    }

    private String getTime() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
    }

}
