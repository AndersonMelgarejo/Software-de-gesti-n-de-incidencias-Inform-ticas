package Structure.Colas;

import Model.Incidencias;
import java.io.Serializable;
import java.util.Comparator;

public class ComparadorIncidencia implements Serializable, Comparator<Incidencias> {
    @Override
    public int compare(Incidencias o1, Incidencias o2) {
        return Integer.compare(o2.getTipoincidencia().getNivelPrioridad(),
                               o1.getTipoincidencia().getNivelPrioridad());
        // Ordena en orden descendente para que las prioridades más altas sean procesadas primero
    }

    private int obtenerNivelNumerico(String nivel) {
        return switch (nivel.toLowerCase()) {
            case "alta" -> 3;
            case "media" -> 2;
            case "baja" -> 1;
            default -> 0; // Nivel desconocido
        };
    }
}
