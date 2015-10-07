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
}
