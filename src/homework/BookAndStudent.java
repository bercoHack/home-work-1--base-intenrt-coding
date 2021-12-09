package homework;

import java.io.Serializable;
import java.util.Date;

public class BookAndStudent implements Serializable{
   private Student holderOfBook;
   private BookCopy book;
   private Date date;
   
   public BookAndStudent(BookCopy b, Student s){
       book = b;
       holderOfBook = s;
       date = new Date();
   }
   public BookCopy getBook(){
       return book;
   }
   public Student getStudent(){
       return holderOfBook;
   }
   public Date getDate(){
       return date;
   }
   public void setDate(int i){
       date.setDate(i);
   }
}
