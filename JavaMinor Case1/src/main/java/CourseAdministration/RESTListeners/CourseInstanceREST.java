package CourseAdministration.RESTListeners;

import CourseAdministration.Controllers.CourseInstanceController;
import CourseAdministration.Controllers.EnrollmentController;
import CourseAdministration.Models.CourseInstance;
import CourseAdministration.Utils.CourseInstance.CourseParser;
import CourseAdministration.Utils.DB.CourseDataHandler;
import com.google.gson.Gson;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * Created by Remco on 5-10-2015.
 */
@Path("/instances")
public class CourseInstanceREST {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourseInstance(@PathParam("id") int id){
        CourseInstanceController controller = new CourseInstanceController();
        CourseInstance instance = controller.getCourseInstance(id);
        if (instance != null) {
            Gson gson = new Gson();
            return Response.ok(gson.toJson(instance)).build();
        }
        else {
            return Response.ok("Instance not found").build();
        }
    }

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
            String message = courseParser.parse(uploadedInputStream);
            CourseDataHandler dataHandler = new CourseDataHandler();
            int rowsUpdated = dataHandler.createCourseInstances(courseParser.getCourseInstances());
            message += "; " + rowsUpdated + " rows added";

            return Response.ok(message).build();
        }
    }

    @POST
    @Path("/{id}/enrollments")
    @Consumes("application/x-www-form-urlencoded")
    public Response createEnrollment(@PathParam("id") int id, MultivaluedMap<String, String> formParams){
        EnrollmentController controller = new EnrollmentController();
        return controller.createEnrollment(id, formParams);
    }
}
