package CourseAdministration.Controllers;

import CourseAdministration.Models.Course;
import CourseAdministration.Models.CourseInstance;
import CourseAdministration.Utils.DB.CourseDataHandler;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    public ArrayList<CourseInstance> getCourseInstancesByWeekAndYear(int week, int year){
        CourseDataHandler dataHandler = new CourseDataHandler();
        ArrayList<CourseInstance> instances = new ArrayList<CourseInstance>();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.YEAR, year);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DATE, 5);
        Date endDate = calendar.getTime();
        ResultSet resultSet = dataHandler.getCourseInstancesByDate(startDate, endDate);

        if (resultSet != null){
            try {
                while(resultSet.next()) {
                    Course course = new Course(resultSet.getString("CODE"), resultSet.getString("TITLE"));
                    CourseInstance instance = new CourseInstance(resultSet.getInt("ID"), course, resultSet.getDate("START_DATE"), resultSet.getInt("DURATION"));
                    instances.add(instance);
                }
                return instances;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        else{
            return null;
        }
    }
}
