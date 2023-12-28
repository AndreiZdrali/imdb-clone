package org.example.ui.gui;

import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.user.AccountType;
import org.example.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AppFrame extends JFrame {
    JPanel mainPanel;
    JPanel sidePanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public AppFrame() {
        super("IMDB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();
        List<MenuOption> options = getUserOptions(user.getUserType());
        sidePanel = createSidePanel(options);
        cardPanel = createCardPanel(options);
        cardLayout = (CardLayout) cardPanel.getLayout();

        mainPanel.add(sidePanel, BorderLayout.WEST);
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);

        pack();
        setVisible(true);
    }

    private JPanel createSidePanel(List<MenuOption> options) {
        JPanel sidePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        for (MenuOption option : options) {
            JButton button = new JButton(option.getName());
            button.addActionListener(e -> setView(option.getName()));
            sidePanel.add(button, gbc);
            gbc.gridy++;
        }

        return sidePanel;
    }

    private JPanel createCardPanel(List<MenuOption> options) {
        JPanel cardPanel = new JPanel();

        cardPanel.setLayout(new CardLayout());

        //toti au login dar nu apare in sidebar
        cardPanel.add(MenuOption.List.LOGIN.getPanel(), MenuOption.List.LOGIN.getName());

        for (MenuOption option : options)
            cardPanel.add(option.getPanel(), option.getName());

        return cardPanel;
    }

    private List<MenuOption> getUserOptions(AccountType accountType) {
        List<MenuOption> options = new ArrayList<>();

        switch (accountType) {
            case Regular:
                options.add(MenuOption.List.MAIN_MENU);
                options.add(MenuOption.List.PRODUCTIONS);
                break;
            case Contributor:
                options.add(MenuOption.List.MAIN_MENU);
                options.add(MenuOption.List.PRODUCTIONS);
                break;
            case Admin:
                options.add(MenuOption.List.MAIN_MENU);
                options.add(MenuOption.List.PRODUCTIONS);
                break;
            default:
                throw new NotImplementedError();
        }

        return options;
    }

    public void setView(String viewName) {
        cardLayout.show(cardPanel, viewName);
    }
}
