package br.edu.ifpb.sd.socket.udp.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.stream.IntStream;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/12/2017, 22:33:23
 */
public class ServidorUDPMulticast {

    public static void main(String[] args) {

        try {
            System.setProperty("java.net.preferIPv4Stack", "true");
            System.out.println("Servidor");
//            System.getSecurityManager().checkAccept("224.0.0.3", 1000);
            int porta = 8888;
            MulticastSocket msocket = new MulticastSocket(porta);
            InetAddress grupo = InetAddress.getByName("224.0.0.3");
            msocket.joinGroup(grupo);
            ReceberDatagrama service = new ReceberDatagrama(msocket);
            IntStream.range(0, 5).forEach(a -> {
                System.out.println(service.lerMensagem());
            });
            msocket.leaveGroup(grupo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private static void lerMensagem(MulticastSocket msocket) {
//        try {
//            System.out.println("Esperando pela mensagem");
//            byte[] dados = new byte[100];
//            DatagramPacket pacote = new DatagramPacket(dados, dados.length);
//            msocket.receive(pacote);
//            System.out.println("Mensagem: " + new String(pacote.getData(), 0, pacote.getLength()));
//        } catch (IOException ex) {
//            Logger.getLogger(ServidorUDPMulticast.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}

class ReceberDatagrama {

    private final MulticastSocket msocket;

    public ReceberDatagrama(MulticastSocket msocket) {
        this.msocket = msocket;
    }

    public String lerMensagem() {
        try {
            System.out.println("Esperando pela mensagem");
            byte[] dados = new byte[100];
            DatagramPacket pacote = new DatagramPacket(dados, dados.length);
            msocket.receive(pacote);
            return new String(pacote.getData(), 0, pacote.getLength());
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível receber a mensagem", ex);
        }
    }
}
