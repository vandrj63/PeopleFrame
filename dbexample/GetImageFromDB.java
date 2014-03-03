package dbexample;

import java.awt.BorderLayout;

import java.awt.Image;

import java.awt.Toolkit;

import java.io.ByteArrayOutputStream;

import java.io.InputStream;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.Statement;

import examples.PicturePanel;

import javax.swing.JFrame;

import java.sql.*;

public class GetImageFromDB extends JFrame {

            public GetImageFromDB() {

                        PicturePanel p = null;

                        try {

                                    Connection conn;

 

                                    Class.forName("com.mysql.jdbc.Driver");

 

                                    // Get a Connection to the database

                                    // getConnection(url,username,pw)

                                    conn = DriverManager.getConnection(

                                                            "jdbc:mysql://localhost/test", "root", "higgins");

                                    // System.out.println("got driver");

                                    ResultSet rs = null;

                                    Statement stmt = conn.createStatement();

                                    String query = "select * from students where id=0";

                                    stmt.execute(query);

                                    rs = stmt.getResultSet();

                                    if (rs == null)

                                                System.out.println("nop result set");

                                    InputStream stream = null;

                                    if (rs.next())

                                                stream = rs.getBinaryStream("picture");

                                    ByteArrayOutputStream output = new ByteArrayOutputStream();

                                    int a1 = stream.read();

                                    while (a1 >= 0) {

                                                output.write((char) a1);

                                                a1 = stream.read();

                                    }

                                    Image myImage = Toolkit.getDefaultToolkit().createImage(

                                                            output.toByteArray());

                                    output.close();

                                    p = new PicturePanel(myImage);

                        } catch (Exception e) {

                                    Image myImage = Toolkit.getDefaultToolkit()

                                                            .createImage("gator.jpg");

                                    //gator will display if there's an error getting image from blob in db

                                    p = new PicturePanel(myImage);

                        } finally {

                                    setSize(400, 400);

                                    add(p, BorderLayout.CENTER);

                                    setVisible(true);

                                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        }

            }

            public static void main(String[] args) {

                        GetImageFromDB gifdm = new GetImageFromDB();

            }

}