package CourseAdministration.RESTListeners;

import CourseAdministration.Controllers.EnrollmentController;
import CourseAdministration.Models.Enrollment;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by Remco on 7-10-2015.
 */
@Path("/enrollments")
public class EnrollmentREST {

    @GET
    @Path("/{week}/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnrollmentsByWeekAndDay(@PathParam("week") int week, @PathParam("year") int year){
        EnrollmentController controller = new EnrollmentController();
        ArrayList<Enrollment> enrollments = controller.getEnrollmentsByWeekAndYear(week, year);

        Gson gson = new Gson();
        return Response.ok(gson.toJson(enrollments)).build();
    }
}
