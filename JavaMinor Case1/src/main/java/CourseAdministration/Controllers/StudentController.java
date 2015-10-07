package CourseAdministration.Controllers;

import CourseAdministration.Models.BVStudent;
import CourseAdministration.Models.Student;
import CourseAdministration.Utils.DB.StudentDataHandler;
import CourseAdministration.Utils.Directors.StudentDirector;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Remco on 6-10-2015.
 */
public class StudentController {

    public boolean studentExists(Student student){
        StudentDataHandler dataHandler = new StudentDataHandler();
        return dataHandler.studentExists(student);
    }

    public int createStudent(MultivaluedMap<String, String> formParams){
        StudentDirector director = new StudentDirector();
        Student student = director.createStudent(formParams);

        StudentDataHandler dataHandler = new StudentDataHandler();

        if (student instanceof BVStudent){
            return dataHandler.createBVStudent((BVStudent) student);
        }
        else{
            //TODO create private student (out of scope)
            return 0;
        }
    }

    public Student getStudent(int id){
        StudentDataHandler dataHandler = new StudentDataHandler();
        ResultSet resultSet = dataHandler.getStudent(id);
        StudentDirector director = new StudentDirector();
        try{
            return director.createBVStudent(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
