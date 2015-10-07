package CourseAdministration.Controllers;

import CourseAdministration.Models.*;
import CourseAdministration.Utils.DB.EnrollmentDataHandler;
import CourseAdministration.Utils.Directors.StudentDirector;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Remco on 6-10-2015.
 */
public class EnrollmentController {

    public Response createEnrollment(int instanceID, MultivaluedMap<String, String> formParams){
        Response response;
        Student student = new StudentDirector().createStudent(formParams);
        StudentController studentController = new StudentController();
        boolean studentExists = studentController.studentExists(student);
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

    public ArrayList<Enrollment> getEnrollmentsByWeekAndYear(int week, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.YEAR, year);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DATE, 5);
        Date endDate = calendar.getTime();

        EnrollmentDataHandler dataHandler = new EnrollmentDataHandler();
        ResultSet result = dataHandler.getEnrollments(startDate, endDate);
        if (result == null){
            return null;
        }
        else{
            return generateEnrollments(result);
        }
    }

    public ArrayList<Enrollment> generateEnrollments(ResultSet result){
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
        try {
            while (result.next()){
                BVStudent student = new BVStudent.BVStudentBuilder(result.getInt("PERSON_ID"))
                        .firstName(result.getString("FIRST_NAME"))
                        .lastName(result.getString("LAST_NAME"))
                        .company(result.getString("COMPANY"))
                        .department(result.getString("DEPARTMENT"))
                        .quotationNumber(result.getInt("QUOTATION_NUMBER"))
                        .build();
                Course course = new Course(result.getString("CODE"), result.getString("TITLE"));
                CourseInstance instance = new CourseInstance(result.getInt("INSTANCE_ID"), course, result.getDate("START_DATE"), result.getInt("DURATION"), result.getInt("PRICE"));

                enrollments.add(new Enrollment(student, instance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            enrollments = null;
        }
        return enrollments;
    }
}
