package CourseAdministration.Utils.Directors;

import CourseAdministration.Models.BVStudent;
import CourseAdministration.Models.Student;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Student createBVStudent(ResultSet resultSet) throws SQLException{
        resultSet.next();
        return new
                BVStudent.BVStudentBuilder(resultSet.getInt("ID"))
                .firstName(resultSet.getString("FIRST_NAME"))
                .lastName(resultSet.getString("LAST_NAME"))
                .company(resultSet.getString("COMPANY"))
                .department(resultSet.getString("DEPARTMENT"))
                .quotationNumber(resultSet.getInt("QUOTATION_NUMBER"))
                .build();
    }

    public Student createPrivateStudent(MultivaluedMap<String, String> params){
        //TODO create private student (not in scope)
        return null;
    }
}
