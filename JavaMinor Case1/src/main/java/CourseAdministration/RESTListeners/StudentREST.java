package CourseAdministration.RESTListeners;

import CourseAdministration.Utils.StudentBuilder.StudentDirector;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * Created by Remco on 6-10-2015.
 */
@Path("/students")
public class StudentREST {

//    @POST
//    @Path("/create")
//    @Consumes("application/x-www-form-urlencoded")
//    public Response createStudent(MultivaluedMap<String, String> formParams){
//        //TODO Not finished yet, maybe not neccesary
//        StudentDirector director = new StudentDirector();
//        Gson gson = new Gson();
//        return Response.ok(gson.toJson(director.createStudent(formParams))).build();
//    }
}
