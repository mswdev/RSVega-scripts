package antiban.api;

import java.io.*;
import java.net.Socket;

public class ClientSocketManager {

    private final String SERVER_IP;

    private final int SERVER_PORT;

    private Socket SOCKET;

    /**
     * Constructs the client socket manager.
     *
     * @param server_ip   The socket server ip.
     * @param server_port The socket server port.
     */
    ClientSocketManager(String server_ip, int server_port) {
        this.SERVER_IP = server_ip;
        this.SERVER_PORT = server_port;
    }

    /***
     * Initializes the client socket with the constructed ip and port.
     * */
    public void initialize() {
        try {
            SOCKET = new Socket(SERVER_IP, SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the client socket.
     */
    public void close() {
        try {
            SOCKET.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the client socket.
     */
    public Socket getSocket() {
        return SOCKET;
    }
}
