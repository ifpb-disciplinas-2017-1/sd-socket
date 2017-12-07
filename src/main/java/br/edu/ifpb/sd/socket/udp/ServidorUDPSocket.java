package br.edu.ifpb.sd.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/12/2017, 16:30:31
 */
public class ServidorUDPSocket {

    public static void main(String[] args) {
        try {
            System.out.println("Servidor");
            DatagramSocket socket = new DatagramSocket(1234);
            byte[] dados = new byte[100];
            DatagramPacket pacote = new DatagramPacket(dados, dados.length);
            System.out.println("Estou esperando a mensagem");
            socket.receive(pacote);
            String mensagem = new String(pacote.getData(), 0, pacote.getLength());
            System.out.println("Mensagem: " + mensagem);
            InetAddress envio = pacote.getAddress();
            int portaEnvio = pacote.getPort();
            byte[] msg = "Olá tudo bem!!".getBytes();
            DatagramPacket pacoteEnvio = new DatagramPacket(msg, msg.length, envio, portaEnvio);
            socket.send(pacoteEnvio);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
