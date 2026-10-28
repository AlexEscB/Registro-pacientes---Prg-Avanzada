package view.componentes;

import javax.swing.*;
import java.awt.*;

public class EncabezadoPanel extends JPanel {
    public EncabezadoPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 70));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        left.setOpaque(false);
        JLabel tituloLab = new JLabel("Laboratorio Clínico");
        tituloLab.setFont(tituloLab.getFont().deriveFont(Font.BOLD, 16f));
        JLabel subtituloLab = new JLabel("Resultados que generan confianza");
        subtituloLab.setForeground(Color.DARK_GRAY);
        JPanel leftText = new JPanel(new GridLayout(2, 1));
        leftText.setOpaque(false);
        leftText.add(tituloLab);
        leftText.add(subtituloLab);
        left.add(leftText);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        right.setOpaque(false);
        JLabel notifIcon = new JLabel("🔔 3");
        JPanel userPanel = new JPanel(new GridLayout(2, 1));
        userPanel.setOpaque(false);
        JLabel userName = new JLabel("Karina Andrea");
        userName.setHorizontalAlignment(SwingConstants.RIGHT);
        userName.setFont(userName.getFont().deriveFont(Font.BOLD));
        JLabel userRole = new JLabel("Administrador");
        userRole.setHorizontalAlignment(SwingConstants.RIGHT);
        userRole.setForeground(Color.DARK_GRAY);
        userPanel.add(userName);
        userPanel.add(userRole);
        right.add(notifIcon);
        right.add(userPanel);

        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
    }
}
