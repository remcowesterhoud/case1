package CourseAdministration.Utils.DB;

import CourseAdministration.Models.BVStudent;
import CourseAdministration.Models.Student;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Remco on 6-10-2015.
 */
public class StudentDataHandler extends DataHandler{

    //Returns student ID
    public int createBVStudent(BVStudent student){
        try {
            //Create Person in DB first, if number of rows updated > 0 create BV student
            int id = createGenericStudent(student.getFirstName(), student.getLastName());
            if (id > 0) {
                getDBConnection();
                OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("INSERT INTO STUDENT_BV (PERSON_ID, COMPANY, DEPARTMENT, QUOTATION_NUMBER) VALUES (?, ?, ?, ?)");
                statement.setInt(1, id);
                statement.setString(2, student.getCompany());
                statement.setString(3, student.getDepartment());
                statement.setInt(4, student.getQuotationNumber());
                statement.executeUpdate();
                return id;
            }
            else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int createGenericStudent(String fName, String lName){
        try {
            getDBConnection();
            String[] generatedColumns = {"ID"};
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("INSERT INTO PERSON (ID, FIRST_NAME, LAST_NAME) VALUES (0, ?, ?)", generatedColumns); //ID gets overwritten by Trigger
            statement.setString(1, fName);
            statement.setString(2, lName);
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            result.next();
            return result.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean studentExists(Student student){
        try{
            getDBConnection();
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("SELECT * FROM PERSON WHERE FIRST_NAME = ? AND LAST_NAME = ?");
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
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

    public int findStudentID(Student student){
        try{
            getDBConnection();
            OraclePreparedStatement statement = (OraclePreparedStatement) conn.prepareStatement("SELECT ID FROM PERSON WHERE FIRST_NAME = ? AND LAST_NAME = ?");
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            ResultSet result = statement.executeQuery();

            int id = result.getInt("ID");
            return id;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
