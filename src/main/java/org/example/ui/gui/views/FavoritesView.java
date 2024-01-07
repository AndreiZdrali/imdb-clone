package org.example.ui.gui.views;

import org.example.IMDB;
import org.example.production.Actor;
import org.example.production.Production;
import org.example.services.ActorService;
import org.example.services.ProductionService;
import org.example.user.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FavoritesView extends ProductionsView {
    JButton removeFavoriteButton;

    public FavoritesView() {
        super();
    }

    @Override
    protected JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(320, 0));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(260, 30));

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(60, 30));

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        productionsTable = new JTable();
        productionsTable.setDefaultEditor(Object.class, null);
        productionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("No");
        tableModel.addColumn("Name/Title");
        tableModel.addColumn("Type");

        productionsTable.setModel(tableModel);

        productionsTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        productionsTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        productionsTable.getColumnModel().getColumn(2).setPreferredWidth(40);

        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        for (Comparable<?> favorite : user.getFavorites()) {
            if (favorite instanceof Actor actor) {
                tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, actor.getName(), "Actor"});
            } else {
                Production production = (Production) favorite;
                tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, production.getTitle(), production.getType()});
            }
        }

        productionsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                tableOnClick(evt);
            }
        });

        JScrollPane scrollPane = new JScrollPane(productionsTable);

        leftPanel.add(searchPanel, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        return leftPanel;
    }

    @Override
    protected JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(650, 0));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        infoArea = new JTextArea("Select a favorite to view more information");
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(infoArea);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        removeFavoriteButton = new JButton("Remove favorite");

        buttonsPanel.add(removeFavoriteButton);

        removeFavoriteButton.addActionListener(e -> {
            int row = productionsTable.getSelectedRow();
            if (row < 0)
                return;

            User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();
            if (productionsTable.getValueAt(row, 2).equals("Actor")) {
                Actor actor = ActorService.getActorByName((String) productionsTable.getValueAt(row, 1));
                assert actor != null;
                user.removeFavorite(actor);
            } else {
                Production production = ProductionService.getProductionByTitle((String) productionsTable.getValueAt(row, 1));
                assert production != null;
                user.removeFavorite(production);
            }

            tableModel.removeRow(row);
            productionsTable.clearSelection();
            infoArea.setText("Select a favorite to view more information");
        });

        rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.add(buttonsPanel, BorderLayout.SOUTH);

        return rightPanel;
    }

    @Override
    protected void tableOnClick(MouseEvent evt) {
        int row = productionsTable.rowAtPoint(evt.getPoint());
        int col = productionsTable.columnAtPoint(evt.getPoint());
        if (row < 0 || col < 0)
            return;

        if (productionsTable.getValueAt(row, 2).equals("Actor")) {
            Actor actor = ActorService.getActorByName((String) productionsTable.getValueAt(row, 1));
            assert actor != null;
            infoArea.setText(actor.getBiography());
        } else {
            Production production = ProductionService.getProductionByTitle((String) productionsTable.getValueAt(row, 1));
            assert production != null;
            infoArea.setText(production.info());
        }
    }
}
