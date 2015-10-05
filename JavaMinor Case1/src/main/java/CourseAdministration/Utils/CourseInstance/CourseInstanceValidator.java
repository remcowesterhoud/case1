package CourseAdministration.Utils.CourseInstance;

import CourseAdministration.Models.Course;
import CourseAdministration.Models.CourseInstance;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Remco on 5-10-2015.
 */
public class CourseInstanceValidator {

    public boolean validateCourseInstance(CourseInstance instance){
        boolean validated = true;

        if (!validateDuration(instance.getDuration())){
            validated = false;
        }
        else if (!validateDate(instance.getStartDate(), instance.getDuration())){
            validated = false;
        }
        else if (!validateCourse(instance.getCourse())){
            validated = false;
        }

        return validated;
    }

    public boolean validateDate(Date date, int duration){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        for (int i = 0; i < duration; i++){
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                return false;
            }
            else {
                calendar.add(Calendar.DATE, 1);
                continue;
            }
        }
        return true;
    }

    public boolean validateDuration(int duration){
        if (duration < 1 || duration > 5){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean validateCourse(Course course){
        if (course == null){
            return false;
        }
        else if (course.getCode().isEmpty()){
            return false;
        }
        else if (course.getTitle().isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }
}
