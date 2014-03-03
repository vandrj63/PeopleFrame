package examples;
import javax.swing.*;
import java.awt.*;
public class PicturePanel extends JPanel {
/**
* 
*/
private static final long serialVersionUID = 1L;
Image image;
public void update(Image x) {
image = x;
repaint();
}
public PicturePanel(Image x) {
image = x;
repaint();
}
public void paint(Graphics g) {
super.paintComponent(g);
g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
}
}