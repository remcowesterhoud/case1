package CourseAdministration.RESTListeners;

import CourseAdministration.Controllers.CourseController;
import CourseAdministration.Models.Course;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Remco on 7-10-2015.
 */
@Path("/courses")
public class CourseREST {

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourse(@PathParam("code") String code){
        CourseController controller = new CourseController();
        Course course = controller.getCourse(code);

        if (course != null){
            Gson gson = new Gson();
            return Response.ok(gson.toJson(course)).build();
        }
        else{
            return Response.ok("Course not found").build();
        }
    }
}
