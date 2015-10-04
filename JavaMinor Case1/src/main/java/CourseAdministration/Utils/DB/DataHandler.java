package CourseAdministration.Utils.DB;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Remco on 4-10-2015.
 */
public class DataHandler {
    private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
    private String userid = "case1";
    private String password = "admin";
    private Connection conn;

    public void getDBConnection() throws SQLException {
        OracleDataSource ds;
        ds = new OracleDataSource();
        ds.setURL(jdbcUrl);
        conn = ds.getConnection(userid, password);
    }
}
