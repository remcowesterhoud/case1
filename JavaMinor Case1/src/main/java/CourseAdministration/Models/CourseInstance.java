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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
