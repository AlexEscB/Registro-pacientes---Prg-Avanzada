package view.componentes;

import javax.swing.*;
import java.awt.*;

public class MenuLateralPanel extends JPanel {
    public MenuLateralPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 0));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));

        add(menuItem("Inicio", false));
        add(menuItem("Pacientes", true));
        add(menuItem("Toma de muestras", false));
        add(menuItem("Resultados", false));
        add(menuItem("Reportes", false));
        add(menuItem("Configuración", false));
    }

    private JButton menuItem(String text, boolean active) {
        JButton btn = new JButton("  " + text);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setFocusPainted(false);
        if (active) {
            btn.setBackground(new Color(235, 245, 255));
            btn.setOpaque(true);
        }
        btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
        return btn;
    }
}
