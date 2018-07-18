package socket_server;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketManager {

    private static final int PORT = 1024;

    private ServerSocket server_socket;

    /**
     * Initializes the server socket with the specified port.
     * */
    public void initialize() {
        try {
            server_socket = new ServerSocket(PORT);

            final SocketServerThread SERVER_THREAD = new SocketServerThread(server_socket);
            SERVER_THREAD.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the server socket.
     * */
    public void close() {
        try {
            server_socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
