package examples;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import examples.Person2;


public class PeopleFrameApp2 extends JFrame implements ActionListener {
private String buttonNames[] = { "First", "Last", "Next", "Previous", "Add", "Edit" };
private PicturePanel picPanel;
private JButton buttons[];
private JLabel nameLabel, SSLabel, ageLabel;
private ArrayList<Person2> people;
private Image gator;
private String Fname, Lname, Ssnum, Agefield, Filename;
Toolkit toolkit;
private int currentIndex = 0;
private ArrayList<Image> peoplePics;

protected Person2 tempPerson; 

public PeopleFrameApp2() {
super("View People");
// build widgets
people = new ArrayList<Person2>();
buttons = new JButton[buttonNames.length];

peoplePics = new ArrayList<Image>();// allocate arraylist for
// images
toolkit = Toolkit.getDefaultToolkit();
nameLabel = new JLabel("name here");
SSLabel = new JLabel("SS here");
ageLabel = new JLabel("age here");
gator = toolkit.createImage("gator.jpg");
picPanel = new PicturePanel(gator);
// set layout
Container c = getContentPane();
c.setLayout(new BorderLayout());
JPanel south = new JPanel();
south.setLayout(new GridLayout(2, 1));
JPanel buttonPanel = new JPanel();
buttonPanel.setLayout(new GridLayout(1, buttons.length));
JPanel infoPanel = new JPanel();
infoPanel.setLayout(new GridLayout(1, 3));
for (int k = 0; k < buttons.length; k++) {
/*
* use loop to instantiate button, add listener to button, add
* button to panel
*/
buttons[k] = new JButton(buttonNames[k]);
buttons[k].addActionListener(this);
buttonPanel.add(buttons[k]);
}
infoPanel.add(nameLabel);
infoPanel.add(SSLabel);
infoPanel.add(ageLabel);
south.add(infoPanel);
south.add(buttonPanel);
c.add(south, BorderLayout.SOUTH);
c.add(picPanel, BorderLayout.CENTER);
// visibility
setVisible(true);
// screen position
setBounds(100, 100, 500, 500);
// closing
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}

public static void main(String[] args) {
PeopleFrameApp2 myApp = new PeopleFrameApp2();
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


} else if (e.getActionCommand().equals("Add")){// add
Person2 person = MultiFieldDialog.showCreateDialog(this);
currentIndex = people.size();
people.add(person);
String pic = person.getPicture();
peoplePics.add(toolkit.createImage(pic));

}else if (e.getActionCommand().equals("Edit")){// edit
Person2 person = MultiFieldDialog2.showCreateDialog(this, Fname, Lname, Agefield, Ssnum, Filename);
people.set(currentIndex, person);
String pic = person.getPicture();
peoplePics.set(currentIndex, toolkit.createImage(pic));	
}

}

display();
}
// actionPerformed

public void display() {
Person2 current = people.get(currentIndex);
nameLabel.setText("Name: " + current.getLastName());
SSLabel.setText("SS: " +current.getSS_num());
ageLabel.setText("Age: " + current.getAge());
picPanel.update(peoplePics.get(currentIndex));

Fname = current.getFirstName();
Lname = current.getLastName();
Agefield = current.getAge();
Ssnum = current.getSS_num();
Filename = current.getPicture();
}

public ArrayList<Person2> getPeople(){
return people;
}
}