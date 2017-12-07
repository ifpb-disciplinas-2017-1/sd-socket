package br.edu.ifpb.sd.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/12/2017, 16:30:09
 */
public class ClienteUDPSocket {

    public static void main(String[] args) {
        try {
            System.out.println("Cliente");
            DatagramSocket socket = new DatagramSocket();
            InetAddress destino = InetAddress.getByName("127.0.0.1");
            String mensagem = "Outra mensagem";
            byte[] dados = mensagem.getBytes();
            DatagramPacket pacote = new DatagramPacket(dados, dados.length,destino, 1234);
            socket.send(pacote);
            System.out.println("Mensagem enviada");
            byte[] dadosRecebidos = new byte[14];
            DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
            socket.receive(pacoteRecebido);
            System.out.println("Recebido: " + new String(pacoteRecebido.getData()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
