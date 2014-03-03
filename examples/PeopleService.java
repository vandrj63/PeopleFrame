package examples;

import java.util.*;
import java.io.*;
 
public class PeopleService {
      private Scanner scan;
 
      public PeopleService() {
            try {
                  FileReader fr = new FileReader("data.txt");
                  scan = new Scanner(fr);
            } catch (FileNotFoundException fnfe) {
                  System.out.println("file not found");
            }
      }
 
      public ArrayList<Person> getPeople() {
            ArrayList<Person> people;
            people = new ArrayList<Person>();
            while (scan.hasNext()) {
                  String first = scan.next();
                  String last = scan.next();
                  String ss = scan.next();
                  String age = scan.next();
                  Person temp = new Person(first, last, ss, age);// build new person
                                                                                          // from constructor
                  people.add(temp);
 
            }
            return people;// send back arraylist of people
      }
 
}