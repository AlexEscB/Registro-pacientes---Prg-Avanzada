package view.paneles;

import javax.swing.*;
import java.awt.*;

public class BannerTituloPanel extends JPanel {
    public BannerTituloPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titulo = new JLabel("Registrar paciente");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 20f));
        JLabel subtitulo = new JLabel("Complete la información del paciente para continuar con el proceso de atención.");
        subtitulo.setForeground(Color.DARK_GRAY);

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.add(titulo);
        textPanel.add(subtitulo);

        add(textPanel, BorderLayout.WEST);
    }
}
