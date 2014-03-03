package examples;
 
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class GetTables {
      static Connection conn;
 
      static Statement st;
 
      public static void main(String[] args) throws Exception {
 
            try {
                  // Step 1: Load the JDBC driver.               
Class.forName("org.gjt.mm.mysql.Driver");
                  // Get a Connection to the database test
                  Connection conn = DriverManager.getConnection(
                              "jdbc:mysql://localhost/test", "root", "");
//using default user/pw
                  System.out.println("Got Connection.");
 
                  st = conn.createStatement();
                  DatabaseMetaData md = conn.getMetaData();
                  ResultSet rs = md.getTables(null, null, "%", null);
                  while (rs.next()) {
                        System.out.println(rs.getString(3));
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