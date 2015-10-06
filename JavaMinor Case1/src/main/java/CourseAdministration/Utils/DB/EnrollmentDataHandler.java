package CourseAdministration.Utils.DB;

import oracle.jdbc.OraclePreparedStatement;

import java.sql.SQLException;

/**
 * Created by Remco on 6-10-2015.
 */
public class EnrollmentDataHandler extends DataHandler {

    public int createEnrollment(int instanceID, int studentID){
        try {
            getDBConnection();
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("INSERT INTO COURSE_ENROLLMNENT (PERSONID, COURSE_INSTANCEID) VALUES (?, ?)");
            statement.setInt(1, studentID);
            statement.setInt(2, instanceID);
            int count = statement.executeUpdate();
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
