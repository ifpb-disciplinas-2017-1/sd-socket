package br.edu.ifpb.sd.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/12/2017, 15:42:19
 */
public class ClienteTCPSocket {

    public static void main(String[] args) {

        try (Socket client = new Socket("localhost", 10999)) {
            String mensagem = "oi\n\rjob";
            try (OutputStream outputStream = client.getOutputStream()) {
//                Thread.sleep(3000);
                outputStream.write(mensagem.getBytes());

                InputStream input = client.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
                buffer.lines().forEach(m -> System.out.println("mensagem enviada pelo servidor: " + m));
            }
        } catch (IOException ex) {
//        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ClienteTCPSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
