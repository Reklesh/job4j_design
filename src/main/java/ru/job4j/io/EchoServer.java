package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str.contains("msg=Hello")) {
                        out.write("Hello\r\n".getBytes());
                        System.out.println(str);
                    } else if (str.contains("msg=Exit")) {
                        server.close();
                        out.write("Server is complete\r\n".getBytes());
                        System.out.println(str);
                    } else {
                        out.write("What?\r\n".getBytes());
                        System.out.println(str);
                    }
                    out.flush();
                } catch (IOException e) {
                    LOG.error("Exception in log example", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}