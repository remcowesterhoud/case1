package CourseAdministration.Utils.DB;

import CourseAdministration.Models.Course;
import CourseAdministration.Models.CourseInstance;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Remco on 5-10-2015.
 */
public class CourseDataHandler extends DataHandler {

    public int createCourseInstances(ArrayList<CourseInstance> instances){
        int count = 0;
        for (CourseInstance instance : instances){
            try {
                //Check if course exists
                ResultSet result = getCourse(instance.getCourse().getCode());
                if (!result.next()){
                    //If course does not exist, create course first
                    createCourse(instance.getCourse());
                }
                else{
                    boolean created = createCourseInstance(instance);
                    if (created){
                        count++;
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
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
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("SELECT * FROM COURSE_INSTANCE INNER JOIN COURSE ON COURSE.CODE = COURSE_INSTANCE.COURSE_CODE WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result;
        }
        catch (SQLException e) {
            return null;
        }
    }

    public ResultSet getCourseInstancesByDate(Date startDate, Date endDate){
        try {
            getDBConnection();
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("SELECT * FROM COURSE_INSTANCE " +
                    "INNER JOIN COURSE ON COURSE.CODE = COURSE_INSTANCE.COURSE_CODE " +
                    "WHERE START_DATE >= ? AND START_DATE < ?");
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            statement.setString(1, df.format(startDate));
            statement.setString(2, df.format(endDate));
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
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
            //If Course instance does not exist add it too the DB
            boolean exists = courseInstanceExists(instance);
            if (!exists) {
                getDBConnection();

                OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("INSERT INTO COURSE_INSTANCE (ID, COURSE_CODE, START_DATE, DURATION) VALUES (1, ?, ?, ?)"); //ID gets overwritten by Trigger
                statement.setString(1, instance.getCourse().getCode());
                DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
                statement.setString(2, df.format(instance.getStartDate()));
                statement.setInt(3, instance.getDuration());
                int count = statement.executeUpdate();

                if (count > 0){
                    return true;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean courseInstanceExists(CourseInstance instance){
        try {
            getDBConnection();
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("SELECT * FROM COURSE_INSTANCE WHERE COURSE_CODE = ? AND START_DATE = ? AND DURATION = ?");
            statement.setString(1, instance.getCourse().getCode());
            DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
            statement.setString(2, df.format(instance.getStartDate()));
            statement.setInt(3, instance.getDuration());
            ResultSet result = statement.executeQuery();

            boolean exists = result.next();
            if (exists){
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

    public boolean courseInstanceExists(int id){
        try{
            getDBConnection();
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("SELECT * FROM COURSE_INSTANCE WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            boolean exists = result.next();
            if (exists){
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
}
