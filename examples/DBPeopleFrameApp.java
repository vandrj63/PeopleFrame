package examples;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import examples.Person2;
import examples.PeopleService;
 
public class DBPeopleFrameApp extends JFrame implements ActionListener {
      private String rowbuttonNames[] = { "First", "Last", "Next", "Previous" };
      private String colbuttonNames[] = { "Add", "Delete", "Update",
                  "Empty Table" };
      private PicturePanel picPanel;
      private JButton rowbuttons[];
      private JButton colbuttons[];
 
      private JLabel nameLabel, SSLabel, ageLabel;
      private ArrayList<Person2> people;
      private Image gator;
      Toolkit toolkit;
      private int currentIndex = 0;
      private ArrayList<Image> peoplePics;
      private final String DRIVER = "com.mysql.jdbc.Driver";
      private final String URL = "jdbc:mysql://localhost/test";
      private final String USERNAME = "root";
      private final String PASSWORD = "higgins";
      private Connection connection = null;
      private String sql;
      private Image empty, startup;
      private String Fname, Lname, Ssnum, Agefield, Filename;
      int a1;
 
      public DBPeopleFrameApp() {
            super("View People");
            // build widgets
 
            people = new ArrayList<Person2>();
            rowbuttons = new JButton[rowbuttonNames.length];
            colbuttons = new JButton[colbuttonNames.length];
            peoplePics = new ArrayList<Image>();// allocate arraylist for images
            toolkit = Toolkit.getDefaultToolkit();
            empty = toolkit.createImage("empty.jpg");
            startup = toolkit.createImage("startup.jpg");
            nameLabel = new JLabel("name here");
            SSLabel = new JLabel("SS here");
            ageLabel = new JLabel("age here");
 
            picPanel = new PicturePanel(startup);
            // set layout
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            JPanel south = new JPanel();
            south.setLayout(new GridLayout(2, 1));
            JPanel buttonPanelA = new JPanel();
            JPanel buttonPanelB = new JPanel();
            buttonPanelA.setLayout(new GridLayout(1, rowbuttons.length));
            buttonPanelB.setLayout(new GridLayout(colbuttons.length, 1));
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new GridLayout(1, 3));
            for (int k = 0; k < rowbuttons.length; k++) {
                  /*
                   * use loop to instantiate button, add listener to button, add
                   * button to panel
                   */
                  rowbuttons[k] = new JButton(rowbuttonNames[k]);
                  rowbuttons[k].addActionListener(this);
                  buttonPanelA.add(rowbuttons[k]);
            }
            for (int k = 0; k < colbuttons.length; k++) {
                  /*
                   * use loop to instantiate button, add listener to button, add
                   * button to panel
                   */
                  colbuttons[k] = new JButton(colbuttonNames[k]);
                  colbuttons[k].addActionListener(this);
                  buttonPanelB.add(colbuttons[k]);
            }
            infoPanel.add(nameLabel);
            infoPanel.add(SSLabel);
            infoPanel.add(ageLabel);
            south.add(infoPanel);
            south.add(buttonPanelA);
            c.add(buttonPanelB, BorderLayout.EAST);
            c.add(south, BorderLayout.SOUTH);
            c.add(picPanel, BorderLayout.CENTER);
            // visibility
            setVisible(true);
            // screen position
            setBounds(100, 100, 800, 600);
            loadPeopleArray();
 
            // closing
            addWindowListener(new WindowAdapter() {
                  public void windowClosing(WindowEvent w) {
                        try {
                              connection.close();
                        } catch (SQLException e) {
                        }
                        setVisible(false);
                        w.getWindow().dispose();
                  }
            });
      }
 
      public static void main(String[] args) {
            DBPeopleFrameApp myApp = new DBPeopleFrameApp();
      }
 
      public void loadPeopleArray() {
          try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                sql = "SELECT * FROM students";

                Statement stmt = connection.createStatement();

                //System.out.println("got con");
                if (stmt.execute(sql)) {
                      System.out.println("result set obtained");
                      ResultSet rs = stmt.getResultSet();
                      
                      while (rs.next()) {
                            Person2 guy = new Person2();
                            guy.setFirstName((String) rs.getObject("FirstName"));
                            guy.setAge((String) rs.getObject("Age"));
                            guy.setLastName((String) rs.getObject("LastName"));
                            
                            InputStream stream = null;
                            stream = rs.getBinaryStream("Picture");
                            ByteArrayOutputStream output = new ByteArrayOutputStream();
                            
                            try{
                            int a1 = stream.read();

                            while (a1 >= 0) {

                                        output.write((char) a1);

                                        a1 = stream.read();

                            }
                            
                            
                            guy.setSS_num((String) rs.getObject("SS_num"));
                            System.out.println(guy.getFirstName() + " "
                                        + guy.getLastName());
                            people.add(guy);
                             String pic = guy.getPicture();
                             //peoplePics.add(toolkit.createImage(pic));
                             Image myImage = Toolkit.getDefaultToolkit().createImage(

                                     output.toByteArray());
                             peoplePics.add(myImage);
                             output.close();}
                            catch (Exception e){}
                      }
                } // if
          }// try
          
          catch (SQLException e) {
                e.printStackTrace();

          } catch (ClassNotFoundException e) {
                e.printStackTrace();
          }
          
    }
 
      @Override
      public void actionPerformed(ActionEvent e) {
            if (people.size() == 0 && !e.getActionCommand().equals("Add")) {
                  JOptionPane.showMessageDialog(this, "no people yet", "ERROR",
                              JOptionPane.ERROR_MESSAGE);
            } else {
                  if (e.getActionCommand().equals("First")) {
                        currentIndex = 0;
                  } else if (e.getActionCommand().equals("Last")) {
                        currentIndex = people.size() - 1;
                  } else if (e.getActionCommand().equals("Next")) {
                        currentIndex = (currentIndex + 1) % people.size();
                  } else if (e.getActionCommand().equals("Previous")) {
                        currentIndex = currentIndex == 0 ? people.size() - 1
                                    : currentIndex - 1;
                  } else if (e.getActionCommand().equals("Add")) {// add
                        addGuy();
                  } else if (e.getActionCommand().equals("Empty Table")) {//
                        emptyTable();
                  } else if (e.getActionCommand().equals("Delete")) {// delete
                        deleteGuy();
                  } else if (e.getActionCommand().equals("Update")){
                        update();
                        display();
                  }
                  {
                  }// if
                  display();
            }
      }// actionPerformed
 
      public void addGuy() {

  	    int sizeOF = people.size();
              Person2 person = MultiFieldDialog.showCreateDialog(this);
              currentIndex = people.size();
              people.add(person);
              String pic = person.getPicture();
              peoplePics.add(toolkit.createImage(pic));
              
              FileInputStream fin = null;
              
              String binaryFileName = person.getPicture();

              File image = new File(binaryFileName);

              try {
  				fin = new FileInputStream(image);
  			} catch (FileNotFoundException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
              
              
              String query = " insert into students (FirstName,LastName, Age, SS_num, Picture)"
                          + " values (?, ?, ?, ?, ?)";
              try {
                    PreparedStatement preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setString(1, person.getFirstName());
                    preparedStmt.setString(2, person.getLastName());
                    preparedStmt.setString(3, person.getAge());
                    preparedStmt.setString(4, person.getSS_num());
                   // preparedStmt.setString(5, binaryFileName);
  		           preparedStmt.setBinaryStream(5, (InputStream) fin, (int) image.length());
                    
                   // preparedStmt.setInt(6, sizeOF);

                    preparedStmt.execute();
                    preparedStmt.close();
                    
                   
              } catch (SQLException sqle) {
                    System.out.println(sqle.toString());
              }
        }
 
      public void update() {
          
    	  Person2 person = MultiFieldDialog2.showCreateDialog(this, Fname, Lname, Agefield, Ssnum, Filename);
    	  String pic = person.getPicture();
    	  FileInputStream fin = null;
          
          String binaryFileName = person.getPicture();

          File image = new File(binaryFileName);
          
          try {
				fin = new FileInputStream(image);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  peoplePics.set(currentIndex, toolkit.createImage(pic));	
    	  try{
    	  PreparedStatement ps = connection.prepareStatement("UPDATE students SET LastName = ?, FirstName = ?, Age = ?, SS_num = ?, Picture = ? WHERE id= ? ");
    	  
    	  ps.setString(1, person.getLastName());
          ps.setString(2, person.getFirstName());
          ps.setString(3, person.getAge());
          ps.setString(4, person.getSS_num());
         // ps.setString(5, person.getPicture());
          ps.setBinaryStream(5, (InputStream) fin, (int) image.length());
          ps.setInt(6, currentIndex+1);
          ps.execute();
          ps.close();
    	  } catch  (SQLException sqle) {
              System.out.println(sqle.toString());
          }
      }
 
      public void deleteGuy() {
            String ssnum = (people.get(currentIndex)).getSS_num();
            people.remove(currentIndex);
            peoplePics.remove(currentIndex);
            currentIndex = 0;
            String query = " DELETE FROM students where SS_num=?";
 
            try {
                  PreparedStatement preparedStmt = connection.prepareStatement(query);
                  preparedStmt.setString(1, ssnum);
                  int num = preparedStmt.executeUpdate();
                  // message if num==1 success
                  preparedStmt.close();
            } catch (SQLException sqle) {
                  System.out.println(sqle.toString());
            }
 
      }
 
      public void emptyTable() {
            try {// first empty
                  Statement statement = connection.createStatement();
 
                  /*
                   * TRUNCATE is faster than DELETE since it does not generate
                   * rollback information and does not fire any delete triggers
                   */
                  statement.executeUpdate("TRUNCATE students");
 
                  System.out.println("Successfully truncated test_table");
                  peoplePics.clear();
                  people.clear();
 
            } catch (SQLException exp) {
                  System.out.println("Could not truncate test_table "
                              + exp.getMessage());
            }
      }
 
      public void display() {
            if (currentIndex >= 0 && currentIndex < people.size()) {
                  Person2 current = people.get(currentIndex);
                  nameLabel.setText("Last Name: " + current.getLastName());
                  SSLabel.setText("SS Num: " + current.getSS_num());
                  ageLabel.setText("Age: " + current.getAge());// setText requires
                                                                                          // string arg
                  picPanel.update(peoplePics.get(currentIndex));
                  
                  Fname = current.getFirstName();
                  Lname = current.getLastName();
                  Agefield = current.getAge();
                  Ssnum = current.getSS_num();
                  Filename = current.getPicture();
            } else {
                  picPanel.update(empty);
            }
      }
}