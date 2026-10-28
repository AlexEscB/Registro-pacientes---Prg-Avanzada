package view.paneles;

import model.Paciente;
import util.ValidacionUtil;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ResumenPacientePanel extends JPanel {
    private JLabel lblNombre;
    private JLabel lblDocumento;
    private JLabel lblFechaEdad;
    private JLabel lblCelular;
    private JLabel lblCorreo;
    private JLabel lblDireccion;
    private JLabel lblEps;
    private JLabel lblRegimen;

    public ResumenPacientePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        setPreferredSize(new Dimension(280, 0));

        JLabel titulo = new JLabel("Resumen");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 14f));
        add(titulo, BorderLayout.NORTH);

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel avatar = new JLabel("👤");
        avatar.setFont(avatar.getFont().deriveFont(24f));
        JPanel headerText = new JPanel(new GridLayout(2, 1));
        lblNombre = new JLabel("—");
        lblNombre.setFont(lblNombre.getFont().deriveFont(Font.BOLD));
        lblDocumento = new JLabel("—");
        lblDocumento.setForeground(Color.DARK_GRAY);
        headerText.add(lblNombre);
        headerText.add(lblDocumento);
        header.add(avatar);
        header.add(headerText);
        contenido.add(header);

        contenido.add(crearItem("Fecha de nacimiento", lblFechaEdad = new JLabel("—")));
        contenido.add(crearItem("Teléfono celular", lblCelular = new JLabel("—")));
        contenido.add(crearItem("Correo electrónico", lblCorreo = new JLabel("—")));
        contenido.add(crearItem("Dirección", lblDireccion = new JLabel("—")));
        contenido.add(crearItem("EPS", lblEps = new JLabel("—")));
        contenido.add(crearItem("Régimen", lblRegimen = new JLabel("—")));

        add(contenido, BorderLayout.CENTER);
    }

    private JPanel crearItem(String etiqueta, JLabel valor) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        panel.setOpaque(false);
        JLabel lblEtiqueta = new JLabel(etiqueta);
        lblEtiqueta.setForeground(Color.DARK_GRAY);
        lblEtiqueta.setFont(lblEtiqueta.getFont().deriveFont(11f));
        valor.setFont(valor.getFont().deriveFont(12f));
        panel.add(lblEtiqueta);
        panel.add(valor);
        return panel;
    }

    public void actualizar(Paciente paciente, String fechaStr) {
        String nombre = paciente.getNombreCompleto();
        lblNombre.setText(nombre.equals("—") ? "—" : nombre);
        lblDocumento.setText(paciente.getDocumentoAbreviado());

        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            lblFechaEdad.setText("—");
        } else {
            LocalDate fecha = ValidacionUtil.parseFecha(fechaStr);
            if (fecha == null || fecha.isAfter(LocalDate.now())) {
                lblFechaEdad.setText("—");
            } else {
                int edad = ValidacionUtil.calcularEdad(fecha);
                lblFechaEdad.setText(fechaStr.trim() + " (" + edad + " años)");
            }
        }

        lblCelular.setText(vacioADash(paciente.getTelefonoCelular()));
        lblCorreo.setText(vacioADash(paciente.getCorreo()));
        lblDireccion.setText(vacioADash(paciente.getDireccion()));
        lblEps.setText(vacioADash(paciente.getEps()));
        lblRegimen.setText(vacioADash(paciente.getRegimen()));
    }

    private String vacioADash(String s) {
        if (s == null || s.trim().isEmpty()) {
            return "—";
        }
        return s.trim();
    }
}
