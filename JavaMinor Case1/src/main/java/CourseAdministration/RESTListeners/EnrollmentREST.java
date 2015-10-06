package CourseAdministration.RESTListeners;

import CourseAdministration.Controllers.EnrollmentController;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * Created by Remco on 6-10-2015.
 */
@Path("/enrollments")
public class EnrollmentREST {

    @POST
    @Path("/create")
    @Consumes("application/x-www-form-urlencoded")
    public Response createEnrollment(MultivaluedMap<String, String> formParams){
        EnrollmentController controller = new EnrollmentController();
        return controller.createEnrollment(formParams);
    }
}
