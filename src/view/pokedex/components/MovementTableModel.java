/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.pokedex.components;

import java.util.LinkedList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import model.MoveModel;
import model.entities.Move;

/**
 *
 * @author Adrian Vazquez
 */
public class MovementTableModel implements TableModel {

    private List<Move> listOfMoves;
    private boolean choosen[];
    
    private List<TableModelListener> tableModelListeners = new LinkedList<>();

    public MovementTableModel(MoveModel model) {

        listOfMoves = model.getAll();
        choosen = new boolean[model.getAll().size()];
        choosen[0] = true;
    }

    @Override
    public int getRowCount() {
        return listOfMoves.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {

        String columName = "";

        switch (columnIndex) {

            case 0:
                columName = "↓";
                break;
            case 1:
                columName = "Name";
                break;
            case 2:
                columName = "Type";
                break;
            case 3:
                columName = "PP";
                break;
            case 4:
                columName = "Power";
                break;
            case 5:
                columName = "Precision";
                break;
            case 6:
                columName = "Secondary effect";
                break;

        }

        return columName;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        Class c = String.class;

        switch (columnIndex) {

            case 0:
                c = Boolean.class;
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                c = Integer.class;
                break;
            case 4:
                c = Integer.class;
                break;
            case 5:
                c = Integer.class;
                break;
            case 6:
                break;

        }

        return c;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return columnIndex == 0 && rowIndex != 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object obj = null;

        switch (columnIndex) {

            case 0: {

                Boolean b = false;

                try {
                    b = choosen[rowIndex];
                } catch (Exception e) {
                    e.printStackTrace();
                }

                obj = b;
            }
            break;

            case 1: {
                String name = "";

                try {
                    name = listOfMoves.get(rowIndex).getName();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                obj = name;
            }
            break;

            case 2: {
                String name = "";

                try {
                    name = listOfMoves.get(rowIndex).getType().getName();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                obj = name;
            }
            break;

            case 3: {

                Integer pp = 0;

                try {
                    pp = listOfMoves.get(rowIndex).getPp();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                obj = pp;
            }
            break;

            case 4: {

                Integer power = 0;

                try {
                    power = listOfMoves.get(rowIndex).getPower();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                obj = power;
            }
            break;

            case 5: {

                Integer precision = 0;

                try {
                    precision = listOfMoves.get(rowIndex).getPrecision();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                obj = precision;
            }
            break;
            case 6: {
                String name = "";

                try {
                    name = listOfMoves.get(rowIndex).getSecondaryEffect().getName();
                } catch (Exception e) {
                }

                obj = name;
            }
            break;

        }

        return obj;
    }
    
    public List<Move> getSelectedMoves(){
        
        List<Move> selectedMoves = new LinkedList<>();
        
        for (int i = 0; i < this.choosen.length; i++){
            if (this.choosen[i]){
                selectedMoves.add(this.listOfMoves.get(i));
            }
        }
        
        return selectedMoves;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if (columnIndex == 0 && columnIndex < choosen.length) {
            choosen[rowIndex] = (Boolean) aValue;
        }

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        this.tableModelListeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        this.tableModelListeners.add(l);
    }

}
