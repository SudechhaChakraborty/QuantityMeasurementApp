package com.bridglabz.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {

    private static final int POOL_SIZE = 5;
    private static final Queue<Connection> pool = new LinkedList<>();

    static {
        try {
            String url = ApplicationConfig.get("db.url");
            String user = ApplicationConfig.get("db.user");
            String pass = ApplicationConfig.get("db.password");

            for (int i = 0; i < POOL_SIZE; i++) {
                pool.add(DriverManager.getConnection(url, user, pass));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error creating pool");
        }
    }

    public static synchronized Connection getConnection() {
        return pool.poll();
    }

    public static synchronized void releaseConnection(Connection conn) {
        pool.offer(conn);
    }
}