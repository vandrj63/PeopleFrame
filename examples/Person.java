package examples;
public class Person{
String firstName;
String lastName;
String SS_num;
String age;
//ask Eclipse to add getters, setters and constructors… but here are a few:
//what does public mean here? A client instantiating this class can use the field or method
public String getAge(){return age;}//see the convention for getter setter names?
public void setAge(String age){this.age=age;}//I just showed you another java convention… what?
public String getFirstName(){return firstName;}
public Person(){firstName=lastName="unknown";SS_num="999-99-9999";age="";}
public Person(String firstName, String lastName, String sS_num, String age) {
super();
this.firstName = firstName;
this.lastName = lastName;
SS_num = sS_num;
this.age = age;
}
public String getLastName() {
return lastName;
}
public void setLastName(String lastName) {
this.lastName = lastName;
}
public String getSS_num() {
return SS_num;
}
public void setSS_num(String sS_num) {
SS_num = sS_num;
}
public void setFirstName(String firstName) {
this.firstName = firstName;
}

}