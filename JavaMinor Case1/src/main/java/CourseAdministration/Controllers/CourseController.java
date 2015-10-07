package CourseAdministration.Controllers;

import CourseAdministration.Models.Course;
import CourseAdministration.Utils.DB.CourseDataHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Remco on 7-10-2015.
 */
public class CourseController {

    public Course getCourse(String code){
        CourseDataHandler dataHandler = new CourseDataHandler();
        ResultSet resultSet = dataHandler.getCourse(code);

        try {
            resultSet.next();
            Course course = new Course(resultSet.getString("CODE"), resultSet.getString("TITLE"));
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
