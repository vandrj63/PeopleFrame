package examples;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
public class BuildTable {
     
      public static void main(String[] args) throws Exception {
 
            try {
                  // Step 1: Load the JDBC driver.
                  Class.forName("org.gjt.mm.mysql.Driver");
                  // Get a Connection to the database
                  Connection conn = DriverManager.getConnection(
                              "jdbc:mysql://localhost/test", "root", "");
 
                  System.out.println("Got Connection.");
                  String dbName="Test";
                  String createString =
                    "create table " + dbName +
                    ".Coffee " +
                    "(Coffee_ID integer NOT NULL, " +
                    "Coffee_NAME varchar(40) NOT NULL, " +
                    "Price double NOT NULL, " +
                     "PRIMARY KEY (Coffee_ID))";
 
               Statement stmt = null;
                try {
                    stmt = conn.createStatement();
                    stmt.executeUpdate(createString);
                } catch (SQLException e) {
                    System.out.println(e.toString());
                } finally {
                    if (stmt != null) { stmt.close(); }
                }
                  conn.close();
            } catch (ClassNotFoundException e) {
 
                  System.out.println("couldn't load driver");
 
            }
 
            catch (SQLException e) {
 
                  System.out.println("couldn't getconnection");
 
            }
 
      }
}