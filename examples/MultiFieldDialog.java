package examples;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MultiFieldDialog extends JDialog implements ActionListener {
/**
* 
*/
private static final long serialVersionUID = 1L;
private JTextField fName;
private JTextField lName;
private JTextField agefield;
private JTextField filename;
private JTextField ssnum;
private JButton bAdd;
private JButton bCancel;
private static Person2 person;

private MultiFieldDialog(JFrame parentFrame,boolean modal) {
super(parentFrame,modal);
person=new Person2();
JPanel myPanel = new JPanel();
getContentPane().add(myPanel);
fName=new JTextField(15);
lName=new JTextField(15);
agefield=new JTextField(15);
ssnum=new JTextField(15);
filename=new JTextField(15);
myPanel.setLayout(new GridLayout(6,2));
myPanel.add(new JLabel("first name"));
myPanel.add(fName);
myPanel.add(new JLabel("last name"));
myPanel.add(lName);
myPanel.add(new JLabel("ss number"));
myPanel.add(ssnum);
myPanel.add(new JLabel("age"));
myPanel.add(agefield);
myPanel.add(new JLabel("pic file"));
myPanel.add(filename);
bCancel = new JButton("Cancel");
bCancel.addActionListener(this);
myPanel.add(bCancel);
bAdd = new JButton("Add Person");
bAdd.addActionListener(this);
myPanel.add(bAdd); 
pack();
setLocationRelativeTo(parentFrame);
setVisible(true);
}

@Override
public void actionPerformed(ActionEvent ae) {
if(ae.getSource()==bAdd){

person.setFirstName(fName.getText());
person.setLastName(lName.getText());
person.setAge(agefield.getText());
person.setPicture(filename.getText());
person.setSS_num(ssnum.getText());

}
else {
person=null;
}
setVisible(false);
dispose();
}

public static Person2 showCreateDialog(JFrame parentFrame){
new MultiFieldDialog(parentFrame,true);

return person;
}

}