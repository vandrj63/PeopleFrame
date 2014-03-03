package examples;
public class Person2 extends Person {
private String picture;
public Person2() {
// TODO Auto-generated constructor stub
}

public String getPicture() {
return picture;
}

public void setPicture(String picture) {
this.picture = picture;
}

public Person2(String firstName, String lastName, String sS_num, String age) {
super(firstName, lastName, sS_num, age);
// TODO Auto-generated constructor stub
}
public Person2(String firstName, String lastName, String sS_num, String age,String picture) {
super(firstName, lastName, sS_num, age);
setPicture(picture);
}
}