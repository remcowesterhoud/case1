package CourseAdministration.Utils.DB;

import oracle.jdbc.OraclePreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public ResultSet getEnrollments(Date startDate, Date endDate){
        try{
            getDBConnection();
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement(
                    "SELECT s.COMPANY, s.DEPARTMENT, s.QUOTATION_NUMBER, " +
                            "p.ID AS PERSON_ID, p.FIRST_NAME, p.LAST_NAME, " +
                            "ci.ID AS INSTANCE_ID, ci.START_DATE, ci.DURATION, " +
                            "c.CODE, c.TITLE " +
                            "FROM PERSON p " +
                            "INNER JOIN STUDENT_BV s ON p.ID = s.PERSON_ID " +
                            "INNER JOIN COURSE_ENROLLMNENT ce ON p.ID = ce.PERSONID " +
                            "INNER JOIN COURSE_INSTANCE ci ON ci.ID = ce.COURSE_INSTANCEID " +
                            "INNER JOIN COURSE c ON c.CODE = ci.COURSE_CODE " +
                            "WHERE ci.START_DATE >= ? AND ci.START_DATE < ?");
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            statement.setString(1, df.format(startDate));
            statement.setString(2, df.format(endDate));
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
