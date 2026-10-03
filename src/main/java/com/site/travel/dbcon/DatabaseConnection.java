package com.site.travel.dbcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {

    private final Dotenv dotenv = Dotenv.load();
    private Session sshSession = null;

    public Connection getConnection() throws SQLException {
        
        String sshHost = dotenv.get("SSH_HOST");
        int sshPort = Integer.parseInt(dotenv.get("SSH_PORT"));
        String sshUser = dotenv.get("SSH_USER"); 
        String sshPassword = dotenv.get("SSH_PASSWORD"); 

       
        int localPort = Integer.parseInt(dotenv.get("LOCAL_FORWARD_PORT"));
        String remoteDbHost = dotenv.get("DB_HOST");
        int remoteDbPort = Integer.parseInt(dotenv.get("DB_PORT"));
        
        String dbUser = dotenv.get("DB_USER");
        String dbPassword = dotenv.get("DB_PASSWORD");
        String dbName = dotenv.get("DB_NAME");

        try {
            // 1. Set up the SSH Tunnel
        if (sshSession == null || !sshSession.isConnected()) {
            JSch jsch = new JSch();
            sshSession = jsch.getSession(sshUser, sshHost, sshPort);
            sshSession.setPassword(sshPassword);
            
            // Skip host key check for local development convenience
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(config);

            System.out.println("Connecting SSH Tunnel...");
            sshSession.connect();
            
            // Forward localPort (5433) to remoteDbHost:remoteDbPort (localhost:5432)
            sshSession.setPortForwardingL(localPort, remoteDbHost, remoteDbPort);
            System.out.println("SSH Tunnel established securely on port " + localPort);
        }
            // 2. Connect via JDBC through the local forwarded port
        String jdbcUrl = "jdbc:postgresql://localhost:" + localPort + "/" + dbName;
        System.out.println("Connecting to Database via JDBC...");
            
        return DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

        } catch (Exception e) {
            throw new SQLException("Failed to establish secure SSH database connection", e);
     }
    }
    // Call this method when your application shuts down to clean up the SSH tunnel
    public void closeTunnel() {
        if (sshSession != null && sshSession.isConnected()) {
            sshSession.disconnect();
            System.out.println("SSH Tunnel closed safely.");
        }
    }
}
