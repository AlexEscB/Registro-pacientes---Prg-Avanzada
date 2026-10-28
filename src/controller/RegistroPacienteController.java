package controller;

import model.Paciente;
import repository.PacienteRepository;
import util.ValidacionUtil;
import view.paneles.FormularioPacientePanel;
import view.paneles.ResumenPacientePanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class RegistroPacienteController {
    private FormularioPacientePanel formulario;
    private ResumenPacientePanel resumen;
    private final PacienteRepository repository;

    public RegistroPacienteController(PacienteRepository repository) {
        this.repository = repository;
    }

    public void setFormulario(FormularioPacientePanel formulario) {
        this.formulario = formulario;
    }

    public void setResumen(ResumenPacientePanel resumen) {
        this.resumen = resumen;
    }

    public void actualizarResumen() {
        if (formulario == null || resumen == null) {
            return;
        }
        Paciente p = formulario.getPacienteFromForm();
        resumen.actualizar(p, formulario.getFechaNacimientoTexto());
    }

    public void actualizarEdadYResumen() {
        if (formulario == null || resumen == null) {
            return;
        }
        String fechaStr = formulario.getFechaNacimientoTexto();
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            formulario.setEdadTexto("");
            actualizarResumen();
            return;
        }
        LocalDate fecha = ValidacionUtil.parseFecha(fechaStr);
        if (fecha == null || fecha.isAfter(LocalDate.now())) {
            formulario.setEdadTexto("");
        } else {
            int edad = ValidacionUtil.calcularEdad(fecha);
            formulario.setEdadTexto(edad >= 0 ? edad + " años" : "");
        }
        actualizarResumen();
    }

    public void guardar() {
        if (formulario == null) {
            return;
        }
        Paciente p = formulario.getPacienteFromForm();
        String errores = validar(p);
        if (!errores.isEmpty()) {
            JOptionPane.showMessageDialog(formulario, errores, "Errores de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (repository.exists(p.getTipoDocumento(), p.getNumeroDocumento())) {
            JOptionPane.showMessageDialog(formulario, "Ya existe un paciente con el mismo tipo y número de documento.", "Documento duplicado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        repository.save(p);
        JOptionPane.showMessageDialog(formulario, "Paciente registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        formulario.limpiarFormulario();
        actualizarResumen();
    }

    private String validar(Paciente p) {
        StringBuilder sb = new StringBuilder();
        if (p.getTipoDocumento() == null || p.getTipoDocumento().trim().isEmpty()) {
            sb.append("- Tipo de documento es obligatorio.\n");
        }
        if (p.getNumeroDocumento() == null || p.getNumeroDocumento().trim().isEmpty()) {
            sb.append("- Número de documento es obligatorio.\n");
        } else {
            String num = p.getNumeroDocumento().trim();
            if (!ValidacionUtil.esSoloDigitos(num)) {
                sb.append("- Número de documento debe contener solo dígitos.\n");
            } else if (num.length() < 6 || num.length() > 12) {
                sb.append("- Número de documento debe tener entre 6 y 12 dígitos.\n");
            }
        }
        if (!ValidacionUtil.esSoloLetrasYEspacios(p.getNombres())) {
            sb.append("- Nombres son obligatorios y solo deben contener letras y espacios.\n");
        }
        if (!ValidacionUtil.esSoloLetrasYEspacios(p.getApellidos())) {
            sb.append("- Apellidos son obligatorios y solo deben contener letras y espacios.\n");
        }
        String fechaStr = formulario.getFechaNacimientoTexto();
        LocalDate fecha = ValidacionUtil.parseFecha(fechaStr);
        if (fecha == null) {
            sb.append("- Fecha de nacimiento obligatoria en formato dd/MM/yyyy.\n");
        } else if (fecha.isAfter(LocalDate.now())) {
            sb.append("- Fecha de nacimiento no puede ser futura.\n");
        }
        if (p.getGenero() == null || p.getGenero().trim().isEmpty()) {
            sb.append("- Género es obligatorio.\n");
        }
        if (p.getTelefonoFijo() != null && !p.getTelefonoFijo().trim().isEmpty()) {
            if (!ValidacionUtil.esSoloDigitosOEspacios(p.getTelefonoFijo())) {
                sb.append("- Teléfono fijo debe contener solo dígitos y espacios.\n");
            }
        }
        if (p.getTelefonoCelular() == null || p.getTelefonoCelular().trim().isEmpty()) {
            sb.append("- Teléfono celular es obligatorio.\n");
        } else {
            String cel = p.getTelefonoCelular().trim().replace(" ", "");
            if (!ValidacionUtil.esSoloDigitos(cel) || cel.length() != 10) {
                sb.append("- Teléfono celular debe tener exactamente 10 dígitos.\n");
            }
        }
        if (!ValidacionUtil.esEmailValido(p.getCorreo())) {
            sb.append("- Correo electrónico no tiene un formato válido.\n");
        }
        if (p.getDireccion() == null || p.getDireccion().trim().isEmpty()) {
            sb.append("- Dirección de residencia es obligatoria.\n");
        }
        if (p.getEps() == null || p.getEps().trim().isEmpty()) {
            sb.append("- EPS es obligatoria.\n");
        }
        return sb.toString();
    }

    public void cancelar() {
        if (formulario == null || resumen == null) {
            return;
        }
        Paciente p = formulario.getPacienteFromForm();
        boolean hayDatos = (p.getNumeroDocumento() != null && !p.getNumeroDocumento().trim().isEmpty()) ||
                (p.getNombres() != null && !p.getNombres().trim().isEmpty()) ||
                (p.getApellidos() != null && !p.getApellidos().trim().isEmpty()) ||
                (formulario.getFechaNacimientoTexto() != null && !formulario.getFechaNacimientoTexto().trim().isEmpty()) ||
                (p.getTelefonoFijo() != null && !p.getTelefonoFijo().trim().isEmpty()) ||
                (p.getTelefonoCelular() != null && !p.getTelefonoCelular().trim().isEmpty()) ||
                (p.getCorreo() != null && !p.getCorreo().trim().isEmpty()) ||
                (p.getDireccion() != null && !p.getDireccion().trim().isEmpty()) ||
                (p.getOcupacion() != null && !p.getOcupacion().trim().isEmpty()) ||
                (p.getObservaciones() != null && !p.getObservaciones().trim().isEmpty());
        if (hayDatos) {
            int resp = JOptionPane.showConfirmDialog(formulario, "¿Desea cancelar? Se perderán los datos ingresados.", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                formulario.limpiarFormulario();
                actualizarResumen();
            }
        } else {
            formulario.limpiarFormulario();
            actualizarResumen();
        }
    }
}
