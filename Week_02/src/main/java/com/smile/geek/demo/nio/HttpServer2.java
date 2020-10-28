package com.smile.geek.demo.nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhufang
 * @date 2020/10/27 5:19 下午
 */
public class HttpServer2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8082);

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() ->{
                    service(socket);
                }).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void service(Socket socket) {
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP 1.1 200 OK");
            printWriter.println("Content-type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.write("hello nio");
            printWriter.close();
            socket.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
