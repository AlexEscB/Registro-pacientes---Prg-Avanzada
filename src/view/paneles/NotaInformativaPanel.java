package view.paneles;

import javax.swing.*;
import java.awt.*;

public class NotaInformativaPanel extends JPanel {
    public NotaInformativaPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        setPreferredSize(new Dimension(280, 0));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        header.setOpaque(false);
        JLabel icono = new JLabel("ℹ");
        JLabel titulo = new JLabel("Tenga en cuenta");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 12f));
        header.add(icono);
        header.add(titulo);
        add(header, BorderLayout.NORTH);

        JTextArea texto = new JTextArea("Los datos del paciente son necesarios para la generación de la orden médica, la trazabilidad de las muestras y la entrega de resultados.");
        texto.setWrapStyleWord(true);
        texto.setLineWrap(true);
        texto.setEditable(false);
        texto.setOpaque(false);
        texto.setFont(texto.getFont().deriveFont(11f));
        texto.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(texto, BorderLayout.CENTER);
    }
}
