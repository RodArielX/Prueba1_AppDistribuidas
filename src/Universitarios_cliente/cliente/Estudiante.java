package Universitarios_cliente.cliente;

import lombok.Getter;
import lombok.Setter;

public class Estudiante {
    @Getter
    @Setter
    private int id;
    @Getter @Setter private String nombre;
    @Getter @Setter private String telefono;
    @Getter @Setter private String carrera;
    @Getter @Setter private int semestre;
    @Getter @Setter private boolean gratuidad;

    @Override
    public String toString() {
        return "ID: " + id + "\nNombre: " + nombre + "\nTeléfono: " + telefono +
                "\nCarrera: " + carrera + "\nSemestre: " + semestre +
                "\nGratuidad: " + (gratuidad ? "Sí" : "No");
    }
}
