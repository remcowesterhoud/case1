package CourseAdministration.Models;

import CourseAdministration.Utils.Paths;

/**
 * Created by Remco on 4-10-2015.
 */
public class Course {

    private String self, code, title;

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
        this.self = Paths.URL.getURI() + Paths.COURSE.getURI() + code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
