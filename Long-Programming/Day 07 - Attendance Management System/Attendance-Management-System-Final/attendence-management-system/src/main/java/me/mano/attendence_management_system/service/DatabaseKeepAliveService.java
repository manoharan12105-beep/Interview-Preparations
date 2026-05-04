package me.mano.attendence_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DatabaseKeepAliveService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Run this task every 4 hours (14,400,000 milliseconds)
    // You can adjust this to be more frequent if needed (e.g. 3600000 for 1 hour)
    @Scheduled(fixedRate = 3600000)
    public void pingDatabase() {
        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("Keep-alive ping sent to Aiven Database to prevent automatic shutdown.");
        } catch (Exception e) {
            System.err.println("Keep-alive ping failed: " + e.getMessage());
        }
    }
}
