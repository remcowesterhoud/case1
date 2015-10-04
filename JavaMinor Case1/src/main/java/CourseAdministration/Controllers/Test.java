package CourseAdministration.Controllers;

import com.sun.jersey.core.header.FormDataContentDisposition;
//import com.sun.jersey.multipart.FormDataParam;

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

//    @POST
//    @Path("/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response fileTest(@FormDataParam("file")InputStream uploadInputStream, @FormDataParam("file")FormDataContentDisposition fileDetail){
//        String uploadedFileLocation = "c://uploadedFiles/" + fileDetail.getFileName();
//
//        saveToFile(uploadInputStream, uploadedFileLocation);
//        String output = "File uploaded via Jersey based RESTful Webservice to: " + uploadedFileLocation;
//
//        return Response.status(200).entity(output).build();
//    }
//
//    private void saveToFile(InputStream uploadedInputStream, String uploadedFileLocation){
//        try {
//            OutputStream out = null;
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            out = new FileOutputStream(new File(uploadedFileLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1){
//                out.write(bytes, 0, read);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
