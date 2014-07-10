package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;

import javax.servlet.ServletContext;
import com.example.cassandra.Client;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;


@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@Context ServletContext context) {
        String output = "";
        Client client =  (Client) context.getAttribute("DB");
        client.execute("USE testspace;");

        ResultSet rs = client.execute("SELECT * from users;");
        for (Row row : rs ) {
            output += row.getString("lname") + "|";
        }
        
        return output;
    }
}
