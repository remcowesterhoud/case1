package CourseAdministration.Controllers;

import CourseAdministration.Models.Course;
import CourseAdministration.Models.CourseInstance;
import CourseAdministration.Utils.DB.CourseDataHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Remco on 6-10-2015.
 */
public class CourseInstanceController {

    public boolean courseInstanceExists(int id){
        CourseDataHandler dataHandler = new CourseDataHandler();
        return dataHandler.courseInstanceExists(id);
    }

    public CourseInstance getCourseInstance(int id){
        CourseDataHandler dataHandler = new CourseDataHandler();
        ResultSet resultSet = dataHandler.getCourseInstance(id);

        try {
            resultSet.next();
            Course course = new Course(resultSet.getString("CODE"), resultSet.getString("TITLE"));
            CourseInstance instance = new CourseInstance(resultSet.getInt("ID"), course, resultSet.getDate("START_DATE"), resultSet.getInt("DURATION"));
            return instance;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
