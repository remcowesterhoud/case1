package CourseAdministration.Controllers;

import CourseAdministration.Models.Student;
import CourseAdministration.Utils.DB.EnrollmentDataHandler;
import CourseAdministration.Utils.StudentBuilder.StudentDirector;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * Created by Remco on 6-10-2015.
 */
public class EnrollmentController {

    public Response createEnrollment(MultivaluedMap<String, String> formParams){
        Response response;
        Student student = new StudentDirector().createStudent(formParams);
        StudentController studentController = new StudentController();
        boolean studentExists = studentController.studentExists(student);

        int instanceID = Integer.parseInt(formParams.getFirst("instanceID"));
        boolean instanceExists = new CourseInstanceController().courseInstanceExists(instanceID);

        if (!instanceExists){
            response = Response.status(400).entity("Instance does not exist").build();
        }
        else {
            int studentID = Integer.parseInt(formParams.getFirst("studentID"));
            if (!studentExists){
                studentID = studentController.createStudent(formParams);
            }

            if (studentID > 0){
                int rowsAdded = createEnrollment(instanceID, studentID);
                response = Response.ok(rowsAdded + " rows added").build();
            }
            else {
                response = Response.status(500).entity("Something went wrong").build();
            }

        }
        return response;
    }

    public int createEnrollment(int instanceID, int studentID){
        EnrollmentDataHandler dataHandler = new EnrollmentDataHandler();
        return dataHandler.createEnrollment(instanceID, studentID);
    }
}
