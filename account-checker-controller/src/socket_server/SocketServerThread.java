package socket_server;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServerThread extends Thread {

    private ServerSocket server_socket;

    SocketServerThread(ServerSocket server_socket) {
        this.server_socket = server_socket;
    }

    public void run() {
        while (true) {
            try {
                new SocketRequestThread(server_socket.accept()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
