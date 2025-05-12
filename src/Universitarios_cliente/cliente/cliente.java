package Universitarios_cliente.cliente;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class cliente {

    private static final String IP_SERVIDOR = "172.31.116.82";
    private static final int PUERTO_SERVIDOR = 5000;

    public static String consultarEstudiantePorId(int id) {
        String respuesta = "";
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress ipServidor = InetAddress.getByName(IP_SERVIDOR);

            byte[] bufferEnvio = String.valueOf(id).getBytes();

            DatagramPacket paqueteEnvio = new DatagramPacket(
                    bufferEnvio,
                    bufferEnvio.length,
                    ipServidor,
                    PUERTO_SERVIDOR
            );

            socket.send(paqueteEnvio);

            byte[] bufferRespuesta = new byte[1024];
            DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);

            socket.receive(paqueteRespuesta);

            respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());

        } catch (Exception e) {
            respuesta = "Error al conectar con el servidor.";
            e.printStackTrace();
        }

        return respuesta;
    }
}


