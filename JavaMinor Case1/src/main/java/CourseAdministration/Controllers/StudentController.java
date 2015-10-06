package CourseAdministration.Controllers;

import CourseAdministration.Models.BVStudent;
import CourseAdministration.Models.Student;
import CourseAdministration.Utils.DB.StudentDataHandler;
import CourseAdministration.Utils.StudentBuilder.StudentDirector;

import javax.ws.rs.core.MultivaluedMap;

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

    public int findStudentID(Student student){
        StudentDataHandler dataHandler = new StudentDataHandler();
        return dataHandler.findStudentID(student);
    }
}
