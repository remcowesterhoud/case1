package CourseAdministration.Utils;

/**
 * Created by Remco on 6-10-2015.
 */
public enum Paths {
    URL("http://localhost:8081/"),
    STUDENT("students/"),
    INSTANCE("instances/"),
    COURSE("courses/");

    private final String URI;
    Paths(String URI){
        this.URI = URI;
    }

    public String getURI() {
        return URI;
    }
}
