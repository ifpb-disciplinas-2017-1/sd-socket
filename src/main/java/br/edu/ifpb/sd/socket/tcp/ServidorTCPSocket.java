package br.edu.ifpb.sd.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/12/2017, 15:47:56
 */
public class ServidorTCPSocket {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(10999)) {
            System.out.println("Servidor ativo..");
            while (true) {
                Socket client = server.accept();
                Runnable run = () -> {
                    try {
                        InputStream input = client.getInputStream();
                        BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
                        buffer.lines().forEach(m -> System.out.println("mensagem enviada pelo cliente: " + m));
                        client.getOutputStream().write("oi".getBytes());
//                        String readLine = buffer.readLine();
//                        System.out.println("mensagem enviada pelo cliente: " + readLine);
                    } catch (IOException ex) {
                        Logger.getLogger(ServidorTCPSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                };
                new Thread(run).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCPSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
