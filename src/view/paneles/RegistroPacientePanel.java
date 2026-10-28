package view.paneles;

import view.componentes.EncabezadoPanel;
import view.componentes.MenuLateralPanel;

import javax.swing.*;
import java.awt.*;

public class RegistroPacientePanel extends JPanel {
    private FormularioPacientePanel formularioPacientePanel;
    private ResumenPacientePanel resumenPacientePanel;
    private NotaInformativaPanel notaInformativaPanel;

    public RegistroPacientePanel(FormularioPacientePanel formulario, ResumenPacientePanel resumen, NotaInformativaPanel nota) {
        this.formularioPacientePanel = formulario;
        this.resumenPacientePanel = resumen;
        this.notaInformativaPanel = nota;

        setLayout(new BorderLayout());

        add(new EncabezadoPanel(), BorderLayout.NORTH);
        add(new MenuLateralPanel(), BorderLayout.WEST);

        JPanel center = new JPanel(new BorderLayout());
        center.add(new BannerTituloPanel(), BorderLayout.NORTH);

        JPanel contenido = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 20, 20, 20);

        // Columna izquierda (70%)
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 1;
        contenido.add(formularioPacientePanel, gbc);

        // Columna derecha (30%)
        JPanel derecha = new JPanel();
        derecha.setLayout(new BoxLayout(derecha, BoxLayout.Y_AXIS));
        derecha.add(resumenPacientePanel);
        derecha.add(Box.createRigidArea(new Dimension(0, 15)));
        derecha.add(notaInformativaPanel);
        derecha.add(Box.createVerticalGlue());

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.insets = new Insets(10, 10, 20, 20);
        contenido.add(derecha, gbc);

        center.add(contenido, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);
    }

    public FormularioPacientePanel getFormularioPacientePanel() {
        return formularioPacientePanel;
    }

    public ResumenPacientePanel getResumenPacientePanel() {
        return resumenPacientePanel;
    }
}
