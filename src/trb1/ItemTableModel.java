/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trb1;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Otávio
 */
public class ItemTableModel extends AbstractTableModel {

    private ArrayList<ItemComanda> itensComanda;
    private ArrayList<ItemCardapio> cardapio;
    private final Comanda comanda;

    public ItemTableModel(Comanda comanda, ArrayList<ItemCardapio> cardapio) {
        this.comanda = comanda;
        this.cardapio = cardapio;
        itensComanda = comanda.getItensComanda();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Codigo";
            case 1:
                return "Nome";
            case 2:
                return "Preco Unitario";
            case 3:
                return "Quantidade";
            case 4:
                return "Preco Total";
            default:
                return "";
        }
    }

    @Override
    public int getRowCount() {
        return itensComanda.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int codigoItem = itensComanda.get(rowIndex).getCodigoItem();
        ItemComanda itemp = getItemComanda(codigoItem);
        ItemCardapio itemc = getItemCardapio(codigoItem);
        switch (columnIndex) {
            case 0:
                return codigoItem;
            case 1:
                return itemc.getNome();
            case 2:
                return itemc.getValorUnitario();
            case 3:
                return itemp.getQuantidade();
            case 4:
                return itemc.getValorUnitario() * itemp.getQuantidade();
            default:
                return "";
        }

    }

    public ItemComanda getItemComanda(int codigo) {
        for (ItemComanda i : itensComanda) {
            if (i.getCodigoItem() == codigo) {
                return i;
            }
        }
        return null;
    }

    public ItemCardapio getItemCardapio(int codigo) {
        for (ItemCardapio i : cardapio) {
            if (i.getCodigo() == codigo) {
                return i;
            }
        }
        return null;
    }

}
