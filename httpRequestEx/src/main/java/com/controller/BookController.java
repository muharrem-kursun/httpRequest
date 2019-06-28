package com.controller;

import org.json.JSONObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//sınıfın yolu
@Path("/book")
public class BookController {
    //json veri göndermek için  tanımladık
    JSONObject obj=new JSONObject();
    //method türü
    @GET
    //method yolu
    @Path("/http-request")
    //gönderilen veri türü
    @Produces(MediaType.APPLICATION_JSON)
    public String sendBook()
    {
        try {
            // json object' e ekleme yaptım
            obj.put("personId",1);
            obj.put("name","vadideki zambak");
            obj.put("author","Balzac" );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //veriyi gönderdim
        return obj.toString();
    }
}
