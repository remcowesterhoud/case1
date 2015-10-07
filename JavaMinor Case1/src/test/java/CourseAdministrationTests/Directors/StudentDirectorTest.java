package CourseAdministrationTests.Directors;

import CourseAdministration.Models.BVStudent;
import CourseAdministration.Utils.Directors.StudentDirector;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Remco on 7-10-2015.
 */
public class StudentDirectorTest {

    private StudentDirector director;
    private MultivaluedMap<String, String> params;
    private ArrayList<String> studentType, firstName, lastName, company, department, quotation;

    @Before
    public void setup(){
        director = new StudentDirector();
        params = new MultivaluedMapImpl();
        studentType = new ArrayList<String>();
        firstName = new ArrayList<String>();
        lastName = new ArrayList<String>();
        company = new ArrayList<String>();
        department = new ArrayList<String>();
        quotation = new ArrayList<String>();
    }

    @Test
    public void createStudentTestBV(){
        studentType.add("bv");
        params.put("studentType", studentType);
        firstName.add("Testname");
        params.put("firstName", firstName);
        lastName.add("TestLastName");
        params.put("lastName", lastName);
        company.add("TestCompany");
        params.put("company", company);
        department.add("TestDepartment");
        params.put("department", department);
        quotation.add("12345");
        params.put("quotationNumber", quotation);

        BVStudent student = (BVStudent) director.createStudent(params);
        assertThat(student.getFirstName(), equalTo("Testname"));
        assertThat(student.getLastName(), equalTo("TestLastName"));
        assertThat(student.getCompany(), equalTo("TestCompany"));
        assertThat(student.getDepartment(), equalTo("TestDepartment"));
        assertThat(student.getQuotationNumber(), equalTo(12345));
    }
}
