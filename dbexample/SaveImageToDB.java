package dbexample;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.sql.*;

 
public class SaveImageToDB {

            /*

             * I made a table MyPictures with autogen primary key id, name varchar 20

             * and picture of type blob

             */

            static Connection conn;

 

            public static void main(String[] args) throws Exception {

                        Class.forName("com.mysql.jdbc.Driver");

                        // Get a Connection to the database

                        // getConnection(url,username,pw)

                        conn = DriverManager.getConnection("jdbc:mysql://localhost/test",

                                                "root", "higgins");

                       //  System.out.println("got driver");

 

                        int rows = 0;

                        FileInputStream fin = null;

                        OutputStream out = null;

 

                        conn.setAutoCommit(false);

           

 

                        String binaryFileName = "giraffe.jpg";

 

                        File image = new File(binaryFileName);

 

                        fin = new FileInputStream(image);

 

                        String query = "insert into MyPictures(name, photo ) values (?,?)";

                        PreparedStatement ps = conn.prepareStatement(query);

                        ps.setString(1, binaryFileName);

                        ps.setBinaryStream(2, (InputStream) fin, (int) image.length());

                        ps.execute();

                        conn.commit();

                        fin.close();

                        conn.close();    }}