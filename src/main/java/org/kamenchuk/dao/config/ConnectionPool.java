package org.kamenchuk.dao.config;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum ConnectionPool {
    INSTANCE;

    private final int POOL_SIZE = 10;
    private List<ConnectionProxy> connectionPool;
    private List<ConnectionProxy> usedConnections;
    private final String DRIVER = "org.postgresql.Driver",
            URL = "jdbc:postgresql://localhost:5432/rent_service",
            USER = "postgres",
            PASSWORD = "toor";

    ConnectionPool() {
        connectionPool = new ArrayList<>(POOL_SIZE);
        usedConnections = new ArrayList<>(POOL_SIZE);
        try {
            Class.forName(DRIVER);
            createPool();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPool() throws SQLException {
        for (int i = 0; i < POOL_SIZE; i++) {
            connectionPool.add(createConnection(URL, USER, PASSWORD));
        }
    }

    public synchronized ConnectionProxy getConnection() throws SQLException {
        if(connectionPool.size()>0) {
            ConnectionProxy connection = connectionPool.get(connectionPool.size() - 1);
            connectionPool.remove(connection);
            usedConnections.add(connection);
            return connection;
        }
        else return null;
    }

    public synchronized void returnConnection(ConnectionProxy connection) {
        usedConnections.remove(connection);
        connectionPool.add(connection);
    }

    private static ConnectionProxy createConnection(String url, String user, String password) throws SQLException {
        return new ConnectionProxy(DriverManager.getConnection(url, user, password));
    }

}
