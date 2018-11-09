package sphiinx.script.public_script.spx_tutorial_island.api;

import java.io.*;
import java.net.Socket;

public class ClientSocketManager {

    private final String server_ip;
    private final int server_port;
    private Socket socket;

    /**
     * Constructs the client socket manager.
     *
     * @param server_ip   The socket server ip.
     * @param server_port The socket server port.
     */
    ClientSocketManager(String server_ip, int server_port) {
        this.server_ip = server_ip;
        this.server_port = server_port;
    }

    /***
     * Initializes the client socket with the constructed ip and port.
     * */
    public void initialize() {
        try {
            socket = new Socket(server_ip, server_port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the client socket.
     */
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the client socket.
     */
    public Socket getSocket() {
        return socket;
    }
}
