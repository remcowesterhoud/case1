package CourseAdministrationTests.Utils.CourseInstance;

import CourseAdministration.Models.Course;
import CourseAdministration.Models.CourseInstance;
import CourseAdministration.Utils.CourseInstance.CourseInstanceValidator;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Remco on 5-10-2015.
 */
public class CourseInstanceValidatorTest {

    private CourseInstanceValidator validator;
    private Course course;
    private CourseInstance courseInstance;

    @Before
    public void setup() throws ParseException{
        validator = new CourseInstanceValidator();
        course = new Course("tst101", "Test Course");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        courseInstance = new CourseInstance(0, course, df.parse("05/10/2015"), 5, 1500);
    }

    @Test
    public void testValidateCourseInstanceSuccess(){
        boolean result = validator.validateCourseInstance(courseInstance);
        assertTrue(result);
    }

    @Test
    public void testValidateCourseInstanceCourseNull(){
        Course course = null;
        courseInstance.setCourse(course);
        boolean result = validator.validateCourseInstance(courseInstance);
        assertFalse(result);
    }

    @Test
    public void testValidateCourseInstanceCourseCodeEmpty(){
        course.setCode("");
        boolean result = validator.validateCourseInstance(courseInstance);
        assertFalse(result);
    }

    @Test
    public void testValidateCourseInstanceCourseTitleEmpty(){
        course.setTitle("");
        boolean result = validator.validateCourseInstance(courseInstance);
        assertFalse(result);
    }

    @Test
    public void testValidateCourseInstanceDurationSmall(){
        courseInstance.setDuration(0);
        boolean result = validator.validateCourseInstance(courseInstance);
        assertFalse(result);
    }

    @Test
    public void testValidateCourseInstanceDurationBig(){
        courseInstance.setDuration(6);
        boolean result = validator.validateCourseInstance(courseInstance);
        assertFalse(result);
    }

    @Test
    public void testValidateCourseInstanceDateInWeekend() throws ParseException{
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        courseInstance.setStartDate(df.parse("08/10/2015"));
        boolean result = validator.validateCourseInstance(courseInstance);
        assertFalse(result);
    }
}
