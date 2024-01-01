package org.example.ui.gui.views;

import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.production.Production;
import org.example.services.ProductionService;
import org.example.user.Admin;
import org.example.user.Contributor;
import org.example.user.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ProductionsView extends JPanel {
    JPanel leftPanel;
    JPanel rightPanel;

    JTextField searchField;
    JButton searchButton;
    JTable productionsTable;
    DefaultTableModel tableModel;
    JTextArea infoArea;
    JButton addReviewButton;
    JButton editProductionButton;
    JButton addProductionButton;
    JButton deleteProductionButton;

    public ProductionsView() {
        setLayout(new BorderLayout());

        leftPanel = createLeftPanel();
        rightPanel = createRightPanel();

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    private JPanel createLeftPanel() {
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
        tableModel.addColumn("Title");
        tableModel.addColumn("Type");

        productionsTable.setModel(tableModel);

        productionsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        productionsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        productionsTable.getColumnModel().getColumn(2).setPreferredWidth(40);

        for (Production production : ProductionService.getProductions()) {
            tableModel.addRow(new Object[] {
                    production.getId(),
                    production.getTitle(),
                    production.getType()
            });
        }

        productionsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableOnClick(evt);
            }
        });

        JScrollPane scrollPane = new JScrollPane(productionsTable);

        leftPanel.add(searchPanel, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        return leftPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(650, 0));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        infoArea = new JTextArea("Select a production to view more information");
        infoArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(infoArea);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        addReviewButton = new JButton("Add review");
        editProductionButton = new JButton("Edit");
        addProductionButton = new JButton("Add");
        deleteProductionButton = new JButton("Delete");

        addReviewButton.setEnabled(false);
        editProductionButton.setEnabled(false);
        deleteProductionButton.setEnabled(false);

        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        switch (user.getUserType()) {
            case Regular -> {
                buttonsPanel.add(addReviewButton);
            }
            case Contributor, Admin -> {
                buttonsPanel.add(editProductionButton);
                buttonsPanel.add(addProductionButton);
                buttonsPanel.add(deleteProductionButton);
            }
            default -> throw new IllegalStateException("Unexpected value: " + user.getUserType());
        }

        addReviewButton.addActionListener(e -> {
            int row = productionsTable.getSelectedRow();
            String title = (String) productionsTable.getValueAt(row, 1);
            Production production = ProductionService.getProductionByTitle(title);

            AddReviewDialog addReviewDialog = new AddReviewDialog(production);
            addReviewDialog.setVisible(true);

            tableModel.fireTableDataChanged();
        });

        editProductionButton.addActionListener(e -> {
            throw new NotImplementedError();
        });

        addProductionButton.addActionListener(e -> {
            JPanel addProductionPanel = new AddProductionDialog();
            int result = JOptionPane.showConfirmDialog(
                    null,
                    addProductionPanel,
                    "Add production",
                    JOptionPane.OK_CANCEL_OPTION
            );

            //TODO: AICI AM RAMAS
            if (result == JOptionPane.OK_OPTION)
                return;
        });

        deleteProductionButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this production?",
                    "Delete production",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                int row = productionsTable.getSelectedRow();
                String title = (String) productionsTable.getValueAt(row, 1);
                ProductionService.removeProduction(ProductionService.getProductionByTitle(title));
                tableModel.removeRow(row);
            }
        });

        rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.add(buttonsPanel, BorderLayout.SOUTH);

        return rightPanel;
    }

    private void tableOnClick(MouseEvent evt) {
        int row = productionsTable.rowAtPoint(evt.getPoint());
        int col = productionsTable.columnAtPoint(evt.getPoint());
        if (row < 0 || col < 0)
            return;

        addReviewButton.setEnabled(false);
        editProductionButton.setEnabled(false);
        deleteProductionButton.setEnabled(false);

        Production production = ProductionService.getProductionByTitle((String) productionsTable.getValueAt(row, 1));
        assert production != null;
        infoArea.setText(production.info());

        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();
        switch (user.getUserType()) {
            case Regular -> {
                addReviewButton.setEnabled(true);
            }
            case Contributor -> {
                Contributor<?> contributor = (Contributor<?>) user;
                if (contributor.getProductionsContribution().contains(production)) {
                    editProductionButton.setEnabled(true);
                    deleteProductionButton.setEnabled(true);
                }
            }
            case Admin -> {
                Admin<?> admin = (Admin<?>) user;
                if (admin.getProductionsContribution().contains(production)) {
                    editProductionButton.setEnabled(true);
                    deleteProductionButton.setEnabled(true);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + user.getUserType());
        }
    }
}
