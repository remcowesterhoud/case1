package CourseAdministration.Utils.StudentBuilder;

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
        BVStudentBuilder builder = new BVStudentBuilder();
        builder.buildStudent();
        builder.setFirstName(params.getFirst("firstName"));
        builder.setLastName(params.getFirst("lastName"));
        builder.setCompany(params.getFirst("company"));
        builder.setDeparment(params.getFirst("department"));
        builder.setQuotationNumber(Integer.parseInt(params.getFirst("quotationNumber")));
        return builder.getStudent();
    }

    public Student createPrivateStudent(MultivaluedMap<String, String> params){
        //TODO create private student (not in scope)
        return null;
    }
}
