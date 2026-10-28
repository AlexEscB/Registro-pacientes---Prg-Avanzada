package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ValidacionUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private static final Pattern LETRAS_ESPACIOS = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$");
    private static final Pattern SOLO_DIGITOS_ESPACIOS = Pattern.compile("^[0-9 ]*$");
    private static final Pattern SOLO_DIGITOS = Pattern.compile("^[0-9]+$");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean esSoloLetrasYEspacios(String s) {
        if (s == null) {
            return false;
        }
        String trimmed = s.trim();
        if (trimmed.isEmpty()) {
            return false;
        }
        return LETRAS_ESPACIOS.matcher(trimmed).matches();
    }

    public static boolean esEmailValido(String s) {
        if (s == null || s.trim().isEmpty()) {
            return true;
        }
        return EMAIL_PATTERN.matcher(s.trim()).matches();
    }

    public static boolean esSoloDigitos(String s) {
        if (s == null) {
            return false;
        }
        return SOLO_DIGITOS.matcher(s.trim()).matches();
    }

    public static boolean esSoloDigitosOEspacios(String s) {
        if (s == null || s.trim().isEmpty()) {
            return true;
        }
        return SOLO_DIGITOS_ESPACIOS.matcher(s.trim()).matches();
    }

    public static LocalDate parseFecha(String s) {
        if (s == null) {
            return null;
        }
        String trimmed = s.trim();
        if (trimmed.isEmpty()) {
            return null;
        }
        try {
            LocalDate fecha = LocalDate.parse(trimmed, FORMATTER);
            return fecha;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static int calcularEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return -1;
        }
        LocalDate hoy = LocalDate.now();
        if (fechaNacimiento.isAfter(hoy)) {
            return -1;
        }
        int edad = hoy.getYear() - fechaNacimiento.getYear();
        if (hoy.getDayOfYear() < fechaNacimiento.getDayOfYear()) {
            edad--;
        }
        if (edad < 0) {
            edad = 0;
        }
        return edad;
    }
}
