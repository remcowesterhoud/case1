package CourseAdministration.Models;

/**
 * Created by Remco on 4-10-2015.
 */
public abstract class Student {

    private int id;
    private String firstName, lastName;

    public Student(int id) {
        this.id = id;
    }

    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
