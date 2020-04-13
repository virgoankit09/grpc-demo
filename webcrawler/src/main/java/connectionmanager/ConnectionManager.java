package connectionmanager;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionManager {

    private static ConnectionManager connectionManager;
    HashMap
    private BlockingQueue<DBConnection> queue = new ArrayBlockingQueue<>(10);

    private ConnectionManager() {
        for(int i=0 ; i < 10; i ++) {
            queue.add(new DBConnection());
        }
    }

    public static ConnectionManager getConnectionManager() {

        if(connectionManager == null) {
            synchronized (connectionManager) {
                if(connectionManager == null) {
                    connectionManager = new ConnectionManager();
                }
            }
        }
        return connectionManager;
    }

    public DBConnection getConnection() throws NoConnectionException {
        DBConnection connection = null;
        try {
            connection =  queue.poll(5000, TimeUnit.MILLISECONDS);
            if(connection == null) {
                throw new NoConnectionException("No connections available");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void putConnection(DBConnection connection) {
        try {
            queue.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DBConnection {

}
