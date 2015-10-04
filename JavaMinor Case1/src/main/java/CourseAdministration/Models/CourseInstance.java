package CourseAdministration.Models;

import java.util.Date;

/**
 * Created by Remco on 4-10-2015.
 */
public class CourseInstance {

    private Course course;
    private Date startDate;
    private int duration;

    public CourseInstance(Course course, Date startDate, int duration) {
        this.course = course;
        this.startDate = startDate;
        this.duration = duration;
    }
}
