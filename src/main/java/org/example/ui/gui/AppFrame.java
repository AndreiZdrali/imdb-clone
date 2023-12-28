package org.example.ui.gui;

import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.user.AccountType;
import org.example.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class AppFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private String currentCardName = "";

    public AppFrame() {
        super("IMDB");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(900, 500));

        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();
        List<MenuOption> options = getUserOptions(user.getUserType());
        sidePanel = createSidePanel(options);
        cardPanel = createCardPanel(options);
        cardLayout = (CardLayout) cardPanel.getLayout();

        mainPanel.add(sidePanel, BorderLayout.WEST);
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);

        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setView(String viewName) {
        currentCardName = viewName;
        cardLayout.show(cardPanel, viewName);
    }

    private JPanel createSidePanel(List<MenuOption> options) {
        JPanel sidePanel = new JPanel(new GridBagLayout());
        sidePanel.setPreferredSize(new Dimension(180, 0));
        sidePanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        //main buttons
        for (MenuOption option : options) {
            sidePanel.add(createSideButtonPanel(option.getName(), () -> setView(option.getName())), gbc);
            gbc.gridy++;
        }

        //logout button
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.SOUTH;

        JPanel logoutButton = createSideButtonPanel("Logout", () -> System.exit(0));
        logoutButton.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

        sidePanel.add(logoutButton, gbc);

        return sidePanel;
    }

    private JPanel createCardPanel(List<MenuOption> options) {
        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(720, 0));
        cardPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));

        cardPanel.setLayout(new CardLayout());

        for (MenuOption option : options)
            cardPanel.add(option.getPanel(), option.getName());

        return cardPanel;
    }

    private JPanel createSideButtonPanel(String text, Runnable onClick) {
        JPanel button = new JPanel();

        button.setPreferredSize(new Dimension(180, 50));
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        button.setLayout(new GridBagLayout());
        button.add(new JLabel(text));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (!currentCardName.equals(text))
                    button.setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(MouseEvent evt) {
                if (currentCardName.equals(text))
                    button.setBackground(Color.GRAY);
                else
                    button.setBackground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent evt) {
                for (Component component : sidePanel.getComponents())
                    component.setBackground(Color.WHITE);
                button.setBackground(Color.GRAY);

                onClick.run();
            }
        });

        return button;
    }

    private List<MenuOption> getUserOptions(AccountType accountType) {
        List<MenuOption> options = new ArrayList<>();
        options.add(MenuOption.List.HOME);
        options.add(MenuOption.List.PRODUCTIONS);
        options.add(MenuOption.List.ACTORS);
        options.add(MenuOption.List.NOTIFICATIONS);
        options.add(MenuOption.List.FAVORITES);

        switch (accountType) {
            case Regular:
                options.add(MenuOption.List.CREATE_REMOVE_REQUEST);
                break;
            case Contributor:
                options.add(MenuOption.List.CREATE_REMOVE_REQUEST);
                options.add(MenuOption.List.SOLVE_REQUEST);
                break;
            case Admin:
                options.add(MenuOption.List.SOLVE_REQUEST);
                options.add(MenuOption.List.MANAGE_USERS);
                break;
            default:
                throw new NotImplementedError();
        }

        return options;
    }
}
