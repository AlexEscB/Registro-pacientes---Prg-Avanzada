package view.paneles;

import controller.RegistroPacienteController;
import model.Paciente;

import javax.swing.*;
import java.awt.*;

public class FormularioPacientePanel extends JPanel {
    private final RegistroPacienteController controller;

    // Datos personales
    private JComboBox<String> comboTipoDoc;
    private JTextField txtNumDoc;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtFechaNac;
    private JTextField txtEdad;
    private JComboBox<String> comboGenero;

    // Contacto
    private JTextField txtTelFijo;
    private JTextField txtTelCelular;
    private JTextField txtCorreo;
    private JTextField txtDireccion;

    // Adicional
    private JComboBox<String> comboEPS;
    private JComboBox<String> comboRegimen;
    private JTextField txtOcupacion;
    private JComboBox<String> comboEstadoCivil;
    private JComboBox<String> comboCondicion;
    private JTextArea txtObservaciones;

    private JButton btnCancelar;
    private JButton btnGuardar;

    public FormularioPacientePanel(RegistroPacienteController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        int fila = 0;

        // Sección Datos personales
        gbc.gridx = 0; gbc.gridy = fila; gbc.gridwidth = 2;
        form.add(seccionTitulo("Datos personales"), gbc);
        fila++;

        // Fila 1
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = fila;
        form.add(campoConEtiqueta("Tipo de documento *", comboTipoDoc = comboTipoDoc()), gbc);
        gbc.gridx = 1; gbc.gridy = fila;
        form.add(campoConEtiqueta("Número de documento *", txtNumDoc = new JTextField()), gbc);
        fila++;

        // Fila 2
        gbc.gridx = 0; gbc.gridy = fila;
        form.add(campoConEtiqueta("Nombres *", txtNombres = new JTextField()), gbc);
        gbc.gridx = 1; gbc.gridy = fila;
        form.add(campoConEtiqueta("Apellidos *", txtApellidos = new JTextField()), gbc);
        fila++;

        // Fila 3
        JPanel fila3 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(0, 0, 0, 8);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.weightx = 0.5;
        gbc3.gridx = 0; gbc3.gridy = 0;
        fila3.add(campoConEtiqueta("Fecha de nacimiento *", txtFechaNac = new JTextField(10)), gbc3);
        gbc3.weightx = 0.25;
        gbc3.gridx = 1;
        txtEdad = new JTextField(6);
        txtEdad.setEditable(false);
        fila3.add(campoConEtiqueta("Edad", txtEdad), gbc3);
        gbc3.weightx = 0.25;
        gbc3.gridx = 2; gbc3.insets = new Insets(0, 0, 0, 0);
        fila3.add(campoConEtiqueta("Género *", comboGenero = comboGenero()), gbc3);
        gbc.gridx = 0; gbc.gridy = fila; gbc.gridwidth = 2;
        form.add(fila3, gbc);
        fila++;

        // Sección Contacto
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = fila;
        form.add(seccionTitulo("Información de contacto"), gbc);
        fila++;

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = fila;
        form.add(campoConEtiqueta("Teléfono fijo", txtTelFijo = new JTextField()), gbc);
        gbc.gridx = 1; gbc.gridy = fila;
        form.add(campoConEtiqueta("Teléfono celular *", txtTelCelular = new JTextField()), gbc);
        fila++;

        gbc.gridx = 0; gbc.gridy = fila;
        form.add(campoConEtiqueta("Correo electrónico", txtCorreo = new JTextField()), gbc);
        gbc.gridx = 1; gbc.gridy = fila;
        form.add(campoConEtiqueta("Dirección de residencia *", txtDireccion = new JTextField()), gbc);
        fila++;

        // Sección Adicional
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = fila;
        form.add(seccionTitulo("Información adicional"), gbc);
        fila++;

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = fila;
        form.add(campoConEtiqueta("EPS *", comboEPS = comboEPS()), gbc);
        gbc.gridx = 1; gbc.gridy = fila;
        form.add(campoConEtiqueta("Régimen", comboRegimen = comboRegimen()), gbc);
        fila++;

        gbc.gridx = 0; gbc.gridy = fila;
        form.add(campoConEtiqueta("Ocupación", txtOcupacion = new JTextField()), gbc);
        gbc.gridx = 1; gbc.gridy = fila;
        form.add(campoConEtiqueta("Estado civil", comboEstadoCivil = comboEstadoCivil()), gbc);
        fila++;

        gbc.gridx = 0; gbc.gridy = fila;
        form.add(campoConEtiqueta("¿Tiene alguna condición médica relevante?", comboCondicion = comboCondicion()), gbc);
        gbc.gridx = 1; gbc.gridy = fila;
        JPanel obsPanel = campoConEtiqueta("Observaciones", new JScrollPane(txtObservaciones = new JTextArea(3, 20)));
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        form.add(obsPanel, gbc);
        fila++;

        JScrollPane scrollForm = new JScrollPane(form);
        scrollForm.setBorder(null);
        add(scrollForm, BorderLayout.CENTER);

        // Botones
        JPanel botones = new JPanel(new BorderLayout());
        botones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnCancelar = new JButton("✕ Cancelar");
        btnGuardar = new JButton("Guardar paciente");
        JPanel leftBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftBtns.add(btnCancelar);
        JPanel rightBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightBtns.add(btnGuardar);
        botones.add(leftBtns, BorderLayout.WEST);
        botones.add(rightBtns, BorderLayout.EAST);
        add(botones, BorderLayout.SOUTH);

        // Listeners
        btnGuardar.addActionListener(e -> controller.guardar());
        btnCancelar.addActionListener(e -> controller.cancelar());
        txtFechaNac.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { controller.actualizarEdadYResumen(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { controller.actualizarEdadYResumen(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { controller.actualizarEdadYResumen(); }
        });
        javax.swing.event.DocumentListener dl = new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { controller.actualizarResumen(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { controller.actualizarResumen(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { controller.actualizarResumen(); }
        };
        txtNumDoc.getDocument().addDocumentListener(dl);
        txtNombres.getDocument().addDocumentListener(dl);
        txtApellidos.getDocument().addDocumentListener(dl);
        txtTelFijo.getDocument().addDocumentListener(dl);
        txtTelCelular.getDocument().addDocumentListener(dl);
        txtCorreo.getDocument().addDocumentListener(dl);
        txtDireccion.getDocument().addDocumentListener(dl);
        txtOcupacion.getDocument().addDocumentListener(dl);
        txtObservaciones.getDocument().addDocumentListener(dl);
        comboTipoDoc.addActionListener(e -> controller.actualizarResumen());
        comboGenero.addActionListener(e -> controller.actualizarResumen());
        comboEPS.addActionListener(e -> controller.actualizarResumen());
        comboRegimen.addActionListener(e -> controller.actualizarResumen());
        comboEstadoCivil.addActionListener(e -> controller.actualizarResumen());
        comboCondicion.addActionListener(e -> controller.actualizarResumen());
    }

    private JPanel seccionTitulo(String titulo) {
        JPanel p = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 13f));
        p.add(lbl, BorderLayout.WEST);
        p.add(new JSeparator(), BorderLayout.SOUTH);
        p.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        return p;
    }

    private JPanel campoConEtiqueta(String etiqueta, JComponent comp) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        JLabel lbl = new JLabel(etiqueta);
        if (etiqueta.contains("*")) {
            String texto = etiqueta.replace("*", "");
            lbl.setText(texto + " *");
        }
        p.add(lbl, BorderLayout.NORTH);
        p.add(comp, BorderLayout.CENTER);
        return p;
    }

    private JComboBox<String> comboTipoDoc() {
        JComboBox<String> cb = new JComboBox<>(new String[]{"Cédula de ciudadanía", "Tarjeta de identidad", "Cédula de extranjería", "Pasaporte", "Registro civil"});
        return cb;
    }

    private JComboBox<String> comboGenero() {
        JComboBox<String> cb = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        return cb;
    }

    private JComboBox<String> comboEPS() {
        JComboBox<String> cb = new JComboBox<>(new String[]{"Nueva EPS", "Sanitas", "Sura", "Compensar", "Famisanar", "Otra"});
        return cb;
    }

    private JComboBox<String> comboRegimen() {
        JComboBox<String> cb = new JComboBox<>(new String[]{"Contributivo", "Subsidiado", "Especial", "Particular"});
        return cb;
    }

    private JComboBox<String> comboEstadoCivil() {
        JComboBox<String> cb = new JComboBox<>(new String[]{"Soltero(a)", "Casado(a)", "Unión libre", "Divorciado(a)", "Viudo(a)"});
        return cb;
    }

    private JComboBox<String> comboCondicion() {
        JComboBox<String> cb = new JComboBox<>(new String[]{"No", "Sí"});
        return cb;
    }

    public Paciente getPacienteFromForm() {
        Paciente p = new Paciente();
        p.setTipoDocumento((String) comboTipoDoc.getSelectedItem());
        p.setNumeroDocumento(txtNumDoc.getText());
        p.setNombres(txtNombres.getText());
        p.setApellidos(txtApellidos.getText());
        String fechaStr = txtFechaNac.getText();
        if (fechaStr != null && !fechaStr.trim().isEmpty()) {
            p.setFechaNacimiento(util.ValidacionUtil.parseFecha(fechaStr));
        }
        p.setGenero((String) comboGenero.getSelectedItem());
        p.setTelefonoFijo(txtTelFijo.getText());
        p.setTelefonoCelular(txtTelCelular.getText());
        p.setCorreo(txtCorreo.getText());
        p.setDireccion(txtDireccion.getText());
        p.setEps((String) comboEPS.getSelectedItem());
        p.setRegimen((String) comboRegimen.getSelectedItem());
        p.setOcupacion(txtOcupacion.getText());
        p.setEstadoCivil((String) comboEstadoCivil.getSelectedItem());
        p.setCondicionMedica((String) comboCondicion.getSelectedItem());
        p.setObservaciones(txtObservaciones.getText());
        return p;
    }

    public String getFechaNacimientoTexto() {
        return txtFechaNac.getText();
    }

    public void setEdadTexto(String texto) {
        txtEdad.setText(texto);
    }

    public void limpiarFormulario() {
        comboTipoDoc.setSelectedIndex(0);
        txtNumDoc.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtFechaNac.setText("");
        txtEdad.setText("");
        comboGenero.setSelectedIndex(0);
        txtTelFijo.setText("");
        txtTelCelular.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        comboEPS.setSelectedIndex(0);
        comboRegimen.setSelectedIndex(0);
        txtOcupacion.setText("");
        comboEstadoCivil.setSelectedIndex(0);
        comboCondicion.setSelectedIndex(0);
        txtObservaciones.setText("");
    }

    public JTextField getTxtNumDoc() { return txtNumDoc; }
    public JTextField getTxtNombres() { return txtNombres; }
    public JTextField getTxtApellidos() { return txtApellidos; }
    public JTextField getTxtFechaNac() { return txtFechaNac; }
    public JComboBox<String> getComboGenero() { return comboGenero; }
    public JTextField getTxtTelCelular() { return txtTelCelular; }
    public JTextField getTxtCorreo() { return txtCorreo; }
    public JTextField getTxtDireccion() { return txtDireccion; }
    public JComboBox<String> getComboEPS() { return comboEPS; }
}
