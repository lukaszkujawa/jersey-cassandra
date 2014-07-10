package com.example.listener;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
 
import com.example.cassandra.AsynchronousClient;

public class ExampleContextListener implements ServletContextListener {
 
    AsynchronousClient cassandraClient;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        cassandraClient = new AsynchronousClient( ctx.getInitParameter( "DBHost" ) );
        ctx.setAttribute( "DB", cassandraClient );

        /*
        ServletContext ctx = servletContextEvent.getServletContext();
         
        String url = ctx.getInitParameter("DBURL");
        String u = ctx.getInitParameter("DBUSER");
        String p = ctx.getInitParameter("DBPWD");
         
        //create database connection from init parameters and set it to context
        DBConnectionManager dbManager = new DBConnectionManager(url, u, p);
        ctx.setAttribute("DBManager", dbManager);
        System.out.println("Database connection initialized for Application.");
        */
    }
 
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        cassandraClient.close();
        
        /*
        ServletContext ctx = servletContextEvent.getServletContext();
        DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
        dbManager.closeConnection();
        System.out.println("Database connection closed for Application.");
        */
         
    }
     
}