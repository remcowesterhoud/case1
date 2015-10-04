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

        saveToDisk(uploadedInputStream, fileDetail);

        return "File saved successfully";
    }

    private void saveToDisk(InputStream uploadedInputStream, FormDataContentDisposition fileDetail){

        String uploadedFileLocation = "C://Users/Remco/Desktop/" + fileDetail.getFileName();

        try{
            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = uploadedInputStream.read(bytes)) != -1){
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
