package socket_server;

import database.DBManager;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Map;

public class SocketRequestThread extends Thread {

    private Socket current_request;

    public SocketRequestThread(Socket currentRequest) {
        this.current_request = currentRequest;
    }

    public void run() {
        try (ObjectInputStream INPUT_STREAM = new ObjectInputStream(current_request.getInputStream())) {
            final Map DATA_MAP = (Map) INPUT_STREAM.readObject();
            try {
                DBManager.getDBConnection().createStatement().execute("INSERT INTO account_checker (username) VALUE ('" + DATA_MAP.get("username") + "');");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for(Object entry : DATA_MAP.entrySet()) {
                final String[] ENTRY = entry.toString().split("=");
                final String KEY = ENTRY[0];
                final String VALUE = ENTRY[1];
                try {
                    DBManager.getDBConnection().createStatement().execute("INSERT INTO account_checker (" + KEY + ") VALUE ('" + VALUE + "') WHERE username = '" + DATA_MAP.get("username") + "';");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*public void run() {
        try {
            final BufferedReader READER = new BufferedReader(new InputStreamReader(current_request.getInputStream()));
            final PrintWriter WRITER = new PrintWriter(current_request.getOutputStream(), true);

            while (!current_request.isClosed()) {
                final String INPUT = READER.readLine();
                if (INPUT == null)
                    break;

                System.out.println(INPUT);
                WRITER.println("Hey bby");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
