package CourseAdministration.Utils.Directors;

import CourseAdministration.Models.BVStudent;
import CourseAdministration.Models.Student;

import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by Remco on 6-10-2015.
 */
public class StudentDirector {

    public Student createStudent(MultivaluedMap<String, String> params){
        String studentType = params.getFirst("studentType");
        if (studentType.equals("bv")){
            return createBVStudent(params);
        }
        else if (studentType.equals("private")){
            return createPrivateStudent(params);
        }
        else{
            return null;
        }
    }

    public Student createBVStudent(MultivaluedMap<String, String> params){
        return new
                BVStudent.BVStudentBuilder(0)
                .firstName(params.getFirst("firstName"))
                .lastName(params.getFirst("lastName"))
                .company(params.getFirst("company"))
                .department(params.getFirst("department"))
                .quotationNumber(Integer.parseInt(params.getFirst("quotationNumber")))
                .build();
    }

    public Student createPrivateStudent(MultivaluedMap<String, String> params){
        //TODO create private student (not in scope)
        return null;
    }
}
