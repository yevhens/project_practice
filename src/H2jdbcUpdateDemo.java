import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.jdbcx.JdbcDataSource;


public class H2jdbcUpdateDemo {
    // JDBC driver name and database URL and database credentials
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";
    static final String USER = "sa";
    static final String PASS = "";







    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBCdatasource
            JdbcDataSource ds=new JdbcDataSource();
            ds.setURL(DB_URL);
            ds.setUser(USER);
            ds.setPassword(PASS);


            // STEP 2: Open a connection
            System.out.println("Connecting to a database...");
            conn=ds.getConnection();

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "UPDATE Registration " + "SET age = 50 WHERE id in (100, 101)";
            stmt.executeUpdate(sql);

            // Now you can extract all the records
            // to see the updated records
            sql = "SELECT id, first, last, age FROM Registration";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                // Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Goodbye!");
    }
} 