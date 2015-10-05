package CourseAdministration.RESTListeners;

import CourseAdministration.Utils.CourseInstance.CourseParser;
import CourseAdministration.Utils.DB.CourseDataHandler;
import CourseAdministration.Utils.DB.DataHandler;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
        CourseParser courseParser = new CourseParser();
        String message = courseParser.readCourseInstanceFile(uploadedInputStream);

        CourseDataHandler dataHandler = new CourseDataHandler();
        dataHandler.createCourseInstances(courseParser.getCourseInstances());

        return Response.ok(message).build();
    }
}
