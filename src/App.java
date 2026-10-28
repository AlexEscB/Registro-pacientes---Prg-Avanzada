import controller.RegistroPacienteController;
import repository.PacienteRepository;
import view.paneles.FormularioPacientePanel;
import view.paneles.NotaInformativaPanel;
import view.paneles.RegistroPacientePanel;
import view.paneles.ResumenPacientePanel;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PacienteRepository repository = new PacienteRepository();
            ResumenPacientePanel resumen = new ResumenPacientePanel();
            NotaInformativaPanel nota = new NotaInformativaPanel();
            RegistroPacienteController controller = new RegistroPacienteController(repository);
            FormularioPacientePanel formulario = new FormularioPacientePanel(controller);
            controller.setFormulario(formulario);
            controller.setResumen(resumen);
            RegistroPacientePanel panel = new RegistroPacientePanel(formulario, resumen, nota);

            JFrame frame = new JFrame("Laboratorio Clínico - Registrar paciente");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }
}
