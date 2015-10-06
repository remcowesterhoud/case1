package CourseAdministration.Utils.StudentBuilder;

import CourseAdministration.Models.BVStudent;
import CourseAdministration.Models.Student;

/**
 * Created by Remco on 6-10-2015.
 */
public class BVStudentBuilder implements StudentBuilder {
    
    private BVStudent student;

    public Student getStudent() {
        return student;
    }

    public void buildStudent() {
        student = new BVStudent(0);
    }

    public void setFirstName(String firstName) {
        student.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        student.setLastName(lastName);
    }

    public void setCompany(String company){
        student.setCompany(company);
    }

    public void setDeparment(String deparment){
        student.setDepartment(deparment);
    }

    public void setQuotationNumber(int quotationNumber){
        student.setQuotationNumber(quotationNumber);
    }
}
