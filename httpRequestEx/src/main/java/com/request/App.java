package com.request;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Hello world!
 *
 */
@Path("/app")
public class App
{
    private static HttpURLConnection connection;
    @Path("/take-data")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public  String takedata()
{

    BufferedReader bufferedReader ;
    String line ;
    StringBuffer responseContent = new StringBuffer();
    try {
        //gidecemiz url
        URL url = new URL("http://localhost:9090/httpRequestEx_war_exploded/rest/book/http-request");
        //http conecction ile url arasındaki bağlantıyı sağladık
        connection =  (HttpURLConnection) url.openConnection();
        //request setup
        //istek türünü belirledik
        connection.setRequestMethod("GET");
        //bağlantı timeout süresi
        connection.setConnectTimeout(5000);
        //okuma zaman aşımı
        connection.setReadTimeout(5000);
        //bağlantının durumu (istenilen 200 dönmesidir)
        int status = connection.getResponseCode();
        System.out.println("responce kod "+status);
        //hata var mı ?
        if (status >299)
        {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            while ((line=bufferedReader.readLine())!=null)
            {
                responseContent.append(line);
            }

        }
        //hata yoksa
        else {
            //karşından gelen mesajı aldık
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line=bufferedReader.readLine())!=null)
            {
                //String buffer nesnesine append metodu ile gelen veriyi ekledik
                responseContent.append(line);
            }
        }
        bufferedReader.close();
        //gelen veriyi consola yazdırdık
        System.out.println(responseContent.toString());
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    //postman ' a yadırdık
    return  responseContent.toString();
}
}
