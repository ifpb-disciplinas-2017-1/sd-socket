package br.edu.ifpb.sd.socket.udp.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/12/2017, 22:33:32
 */
public class ClienteUDPMulticast {

    public static void main(String[] args) {
//        System.setProperty("java.net.preferIPv4Stack", "true");
        try (DatagramSocket socket = new DatagramSocket()) {
            System.out.println("Cliente");
            int porta = 8888;
            InetAddress grupo = InetAddress.getByName("224.0.0.3");
            EnviarDatagrama service = new EnviarDatagrama(porta, grupo, socket);
            service.novaMensagem("oi");
//            String mensagem = "Hello";
//            byte[] dados = mensagem.getBytes();
//            DatagramPacket pacote = new DatagramPacket(dados, dados.length, grupo, porta);
//            socket.send(pacote);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class EnviarDatagrama {

    private int porta;// = 8888;
    private InetAddress grupo; //= InetAddress.getByName("224.0.0.3");
    private DatagramSocket socket; //= new DatagramSocket();

    public EnviarDatagrama(int porta, InetAddress grupo, DatagramSocket socket) {
        this.porta = porta;
        this.grupo = grupo;
        this.socket = socket;
    }

    public void novaMensagem(String mensagem) {
        try {
            byte[] dados = mensagem.getBytes();
            DatagramPacket pacote = new DatagramPacket(dados, dados.length, grupo, porta);
            socket.send(pacote);
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível enviar a mensagem", ex);
        }
    }
}
