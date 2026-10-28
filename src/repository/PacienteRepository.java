package repository;

import model.Paciente;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {
    private static final List<Paciente> pacientes = new ArrayList<>();

    public boolean exists(String tipoDocumento, String numeroDocumento) {
        if (tipoDocumento == null || numeroDocumento == null) {
            return false;
        }
        for (Paciente p : pacientes) {
            if (tipoDocumento.equals(p.getTipoDocumento()) && numeroDocumento.equals(p.getNumeroDocumento())) {
                return true;
            }
        }
        return false;
    }

    public void save(Paciente paciente) {
        pacientes.add(paciente);
    }

    public List<Paciente> findAll() {
        return new ArrayList<>(pacientes);
    }
}
