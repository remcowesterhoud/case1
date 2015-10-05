package CourseAdministration.Utils.DB;

import CourseAdministration.Models.Course;
import CourseAdministration.Models.CourseInstance;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Remco on 5-10-2015.
 */
public class CourseDataHandler extends DataHandler {

    public boolean createCourseInstances(ArrayList<CourseInstance> instances){
        boolean success = true;

        for (CourseInstance instance : instances){
            try {
                //Check if course exists
                ResultSet result = getCourse(instance.getCourse().getCode());
                if (!result.next()){
                    //If course does not exist, create course first
                    if (!createCourse(instance.getCourse())){
                        success = false;
                    }
                }

                if (!createCourseInstance(instance)){
                    success = false;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public ResultSet getCourse(String code){
        try {
            getDBConnection();
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("SELECT * FROM COURSE WHERE CODE = ?");
            statement.setString(1, code);
            ResultSet result = statement.executeQuery();
            return result;
        } catch (SQLException e) {
            return null;
        }

    }

    public ResultSet getCourseInstance(int id){
        try {
            getDBConnection();
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("SELECT * FROM COURSE_INSTANCE WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result;
        }
        catch (SQLException e) {
            return null;
        }
    }

    public boolean createCourse(Course course){
        try {
            getDBConnection();
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("INSERT INTO COURSE (CODE, TITLE) VALUES (?, ?)");
            statement.setString(1, course.getCode());
            statement.setString(2, course.getTitle());
            int count = statement.executeUpdate();

            if (count > 0){
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createCourseInstance(CourseInstance instance){
        try{
            getDBConnection();
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("INSERT INTO COURSE_INSTANCE (ID, COURSE_CODE, START_DATE, DURATION) VALUES (?, ?, ?, ?)");
            statement.setInt(1, 0); //ID gets overridden by Trigger in DB. Any value will do.
            statement.setString(2, instance.getCourse().getCode());
            DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
            statement.setString(3, df.format(instance.getStartDate()));
            statement.setInt(4, instance.getDuration());
            int count = statement.executeUpdate();

            if (count > 0){
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
