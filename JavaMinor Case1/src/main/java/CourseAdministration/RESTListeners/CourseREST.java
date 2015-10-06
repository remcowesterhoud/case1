package CourseAdministration.RESTListeners;

import CourseAdministration.Utils.CourseInstance.CourseParser;
import CourseAdministration.Utils.DB.CourseDataHandler;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * Created by Remco on 5-10-2015.
 */
@Path("/courses")
public class CourseREST {

    @POST
    @Path("/import")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response importCourseInstances(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail){
        String fileName = fileDetail.getFileName();
        int temp = fileName.lastIndexOf(".");
        String extension = fileName.substring(temp);

        if (!extension.equals(".txt")){
            return Response.ok("Uploaded file is not a .txt file").build();
        }
        else {
            CourseParser courseParser = new CourseParser();
            String message = courseParser.readCourseInstanceFile(uploadedInputStream);
            CourseDataHandler dataHandler = new CourseDataHandler();
            int rowsUpdated = dataHandler.createCourseInstances(courseParser.getCourseInstances());
            message += "; " + rowsUpdated + " rows added";

            return Response.ok(message).build();
        }
    }
}
