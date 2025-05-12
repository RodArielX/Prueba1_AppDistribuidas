package Universitarios_servidor.servicio;

import Universitarios_servidor.modelo.Estudiante;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class Servidor {
    private ArrayList<Estudiante> estudiantes;

    public Servidor() {
        estudiantes = new ArrayList<>();
        cargarEstudiantes();
    }

    private void cargarEstudiantes() {
        estudiantes.add(crearEstudiante(1, "Carlos", "123456789", "Ingeniería", 5, true));
        estudiantes.add(crearEstudiante(2, "Ana", "987654321", "Medicina", 3, false));
        estudiantes.add(crearEstudiante(3, "Luis", "111111111", "Derecho", 6, true));
        estudiantes.add(crearEstudiante(4, "Valeria", "222222222", "Arquitectura", 4, false));
        estudiantes.add(crearEstudiante(5, "Jose", "333333333", "Psicología", 2, true));
        estudiantes.add(crearEstudiante(6, "Lucía", "444444444", "Informática", 1, true));
        estudiantes.add(crearEstudiante(7, "Miguel", "555555555", "Educación", 3, false));
        estudiantes.add(crearEstudiante(8, "Elena", "666666666", "Negocios", 5, true));
        estudiantes.add(crearEstudiante(9, "Mario", "777777777", "Música", 2, true));
        estudiantes.add(crearEstudiante(10, "Jonathan", "888888888", "Aplicaciones Distribuidas", 6, true)); // tú mismo
    }

    private Estudiante crearEstudiante(int id, String nombre, String tel, String carrera, int sem, boolean gratuidad) {
        Estudiante e = new Estudiante();
        e.setId(id);
        e.setNombre(nombre);
        e.setTelefono(tel);
        e.setCarrera(carrera);
        e.setSemestre(sem);
        e.setGratuidad(gratuidad);
        return e;
    }

    public void servicio() throws Exception {
        DatagramSocket socket = new DatagramSocket(5000);
        byte[] buffer = new byte[1024];
        System.out.println("Servidor UDP iniciado en el puerto 5000...");

        while (true) {
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);

            String recibido = new String(request.getData(), 0, request.getLength());
            System.out.println("Consulta recibida: " + recibido);

            String respuesta = buscarEstudiantePorId(recibido.trim());

            byte[] dataRespuesta = respuesta.getBytes();
            DatagramPacket response = new DatagramPacket(
                    dataRespuesta, dataRespuesta.length,
                    request.getAddress(), request.getPort());

            socket.send(response);
        }
    }

    private String buscarEstudiantePorId(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            for (Estudiante e : estudiantes) {
                if (e.getId() == id) {
                    return e.toString();
                }
            }
        } catch (NumberFormatException e) {
            return "ID inválido.";
        }
        return "Estudiante no encontrado.";
    }
}
