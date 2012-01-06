package org.salvador_dali.psychsys.ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;


public class TableDemo extends JFrame {
    private boolean DEBUG = true;

    public TableDemo() {
        super("TableDemo");

        Object[][] data = {
            {"Ella Fitzgerald", "How High the Moon", "Verve Jazz Masters", "5"},
            {"Louis Jordan", "Buzz Me", "Let the Good Times Rool, CD 4", "1"},
            {"Stuff Smith & his Onyx Club Boys", "You'se a Viper", "Viper Mad Blues", "8"},
            {"Jelly Roll Morton", "Mama's Got a Baby", "Last Sessions", "24"}
        };

        String[] columnNames = {"Artist", "Song", "Album", "Track"};

        final JTable            table       = new JTable(data, columnNames);
        final JPopupMenu        menu        = new JPopupMenu();
        final JToolBar          toolBar     = new JToolBar();
        final XTableColumnModel columnModel = new XTableColumnModel();
        
        table.setColumnModel(columnModel);
        table.createDefaultColumnsFromModel();

        toolBar.add(new JButton(new javax.swing.AbstractAction("ALL") {
            public void actionPerformed(ActionEvent e) {
                columnModel.setAllColumnsVisible();
            }
        }));
        
        toolBar.add(new JButton(new javax.swing.AbstractAction("Artist") {
            public void actionPerformed(ActionEvent e) {
                TableColumn column  = columnModel.getColumnByModelIndex(0);
                boolean     visible = columnModel.isColumnVisible(column);
                columnModel.setColumnVisible(column, !visible);
            }
        }));
        toolBar.add(new JButton(new javax.swing.AbstractAction("Song") {
            public void actionPerformed(ActionEvent e) {
                TableColumn column  = columnModel.getColumnByModelIndex(1);
                boolean     visible = columnModel.isColumnVisible(column);
                columnModel.setColumnVisible(column, !visible);
            }
        }));
        toolBar.add(new JButton(new javax.swing.AbstractAction("Album") {
            public void actionPerformed(ActionEvent e) {
                TableColumn column  = columnModel.getColumnByModelIndex(2);
                boolean     visible = columnModel.isColumnVisible(column);
                columnModel.setColumnVisible(column, !visible);
            }
        }));
        toolBar.add(new JButton(new javax.swing.AbstractAction("Track") {
            public void actionPerformed(ActionEvent e) {
                TableColumn column  = columnModel.getColumnByModelIndex(3);
                boolean     visible = columnModel.isColumnVisible(column);
                columnModel.setColumnVisible(column, !visible);
            }
        }));
        
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                maybeShowPopup(e);
            }

            public void mouseReleased(MouseEvent e) {
                maybeShowPopup(e);
            }

            private void maybeShowPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(toolBar,       BorderLayout.NORTH);
        getContentPane().add(scrollPane,    BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        TableDemo frame = new TableDemo();
        frame.pack();
        frame.setVisible(true);
    }
}
