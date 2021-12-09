package homework;

import java.io.Serializable;
import java.util.Vector;

public class Student implements Serializable {
   private String name;
   private String familyName;
   private String id;
   private String mail;
   private int numBooks;
   private int lateDays;
   private boolean disabeled;
  
   public Student(String name, String familyName, String id, String mail){
        this.name = name;
        this.familyName = familyName;
        this.id = id;
        this.mail = mail;
        disabeled = false;
        lateDays = 0;
        numBooks = 0;
    }

    public void setNumBooks(int numBooks) {
        this.numBooks = numBooks;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getMail() {
        return mail;
    }

    public int getNumBooks() {
        return numBooks;
    }

    public int getLateDays() {
        return lateDays;
    }

    public boolean isDisabeled() {
        return disabeled;
    }
   public boolean equals(Object o){
        Student temp = null;
        if(o instanceof Student)
           temp = (Student) o;
        if(temp.id.equals(this.id))
            return true;
        return false;
   }
   public String getId(){
       return id;
   }
   public boolean getDis(){
       return disabeled;
   }
   public int getLate(){
       return lateDays;
   }
   public void setDisabeld(boolean b){
       disabeled = b;
   }
   public void setLate(int i){
       lateDays = i;
   }
}
