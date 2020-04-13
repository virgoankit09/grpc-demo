package connectionmanager;

public class Test {


    public static void main(String[] args) throws NoConnectionException {
        ConnectionManager connectionManager = ConnectionManager.getConnectionManager();
        connectionManager.getConnection();

    }
}
