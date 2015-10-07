package CourseAdministration.Models;

import CourseAdministration.Utils.Paths;

import java.util.Date;

/**
 * Created by Remco on 4-10-2015.
 */
public class CourseInstance {

    private String self;
    private int id;
    private Course course;
    private Date startDate;
    private int duration, price;

    public CourseInstance(int id, Course course, Date startDate, int duration, int price) {
        this.id = id;
        this.course = course;
        this.startDate = startDate;
        this.duration = duration;
        this.price = price;
        this.self = Paths.URL.getURI() + Paths.INSTANCE.getURI() + id;
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
