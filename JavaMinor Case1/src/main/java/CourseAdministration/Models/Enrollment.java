package CourseAdministration.Models;

/**
 * Created by Remco on 6-10-2015.
 */
public class Enrollment {

    private Student student;
    private CourseInstance instance;

    public Enrollment(Student student, CourseInstance instance){
        this.student = student;
        this.instance = instance;
    }

    public Student getStudent() {
        return student;
    }

    public CourseInstance getInstance() {
        return instance;
    }
}
