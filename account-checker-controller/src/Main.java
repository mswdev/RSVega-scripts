import database.DBManager;
import socket_server.SocketManager;

public class Main {

    private static final SocketManager SOCKET_MANAGER = new SocketManager();

    public static void main(String[] args) {
        DBManager.establishConnection();
        if (DBManager.getDBConnection() != null)
            System.out.println("Successfully connected to the database.");

        SOCKET_MANAGER.initialize();
    }


}
