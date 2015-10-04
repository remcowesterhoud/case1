package CourseAdministration.Controllers;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

/**
 * Created by Remco on 4-10-2015.
 */

@Path("/file")
public class Test {

    @GET
    public Response test(){
        return Response.status(200).entity("Test123").build();
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadFile(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail){

        String content = readFile(uploadedInputStream);

        BufferedReader reader = new BufferedReader(new StringReader(content));
        String line;
        try {
            while ((line = reader.readLine()) != null){
                System.out.println("Line: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    private String readFile(InputStream uploadedInputStream){
        try {
            System.out.println("Total file size to read (in bytes): " + uploadedInputStream.available());

            String fileContent = "";
            int content;
            while ((content = uploadedInputStream.read()) != -1){
                fileContent += (char) content;
            }

            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return "Something went wrong";
        }
    }
}
