package CourseAdministration.RESTListeners;

import CourseAdministration.Controllers.StudentController;
import CourseAdministration.Models.Student;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Remco on 6-10-2015.
 */
@Path("/students")
public class StudentREST {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("id") int id){
        StudentController controller = new StudentController();
        Student student = controller.getStudent(id);
        if (student != null){
            Gson gson = new Gson();
            return Response.ok(gson.toJson(student)).build();
        }
        else{
            return Response.ok("Student could not be found.").build();
        }
    }
}
