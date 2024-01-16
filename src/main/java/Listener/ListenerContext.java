package Listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.time.LocalDateTime;

@WebListener
public class ListenerContext implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application started! Time: " + LocalDateTime.now());
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application closing! Time: " + LocalDateTime.now());
    }
}
