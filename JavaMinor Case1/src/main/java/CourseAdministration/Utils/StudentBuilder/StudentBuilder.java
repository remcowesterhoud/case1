package CourseAdministration.Utils.StudentBuilder;

import CourseAdministration.Models.Student;

/**
 * Created by Remco on 6-10-2015.
 */
public interface StudentBuilder {

    Student getStudent();
    void buildStudent();
    void setFirstName(String firstName);
    void setLastName(String lastName);
}
