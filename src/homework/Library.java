package homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;


public class Library implements Serializable{
    private Vector<Book> books;
    private Vector<BookAndStudent> borrowInfo;
    private Vector<Student> students;
    
    public static Scanner s = new Scanner(System.in);

    public Library() throws FileNotFoundException, IOException, ClassNotFoundException{
        readBooks("copies.dat");
        readStudents("students.dat");
        readLoneInfo("borrowInfo.dat");
   }
   
    public void addBook()throws IOException{
       String a,b,c,d,e;
       int num;
       System.out.println("insert book isbn:");
       a = s.next();
       System.out.println("insert book name:");
       b = s.next();
       System.out.println("insert book writer:");
       c = s.next();
       System.out.println("insert book category:");
       d = s.next();
       System.out.println("insert book releaseYear:");
       e = s.next();
       System.out.println("insert book copies:");
       num = s.nextInt();
       for(int i = 0; i < books.size(); i++){
           if(books.get(i).getIsbn().equals(a)){
                books.get(i).setCopies(books.get(i).getCopies()+num);
                books.get(i).setCopiesAvailable(books.get(i).getCopiesAvailable()+num);
                System.out.println("book got in the library successfully");      
                return;
           }
        }
        books.add(new Book(a,b,c,d,e,num));
        System.out.println("book got in the library successfully");      
    }
   
    public boolean removeBook() throws IOException{
       String isbn = null;
       System.out.println("insert book isbn to remove:");
       isbn = s.next();
       for(int i = 0; i<books.size(); i++){
           if(books.get(i).getIsbn().equals(isbn)){
               if(books.get(i).getCopies() == books.get(i).getCopiesAvailable()){
                   books.remove(i);
                   System.out.println("book got out of library successfully");
                   return true;
               }
               else{
                   System.out.println("the book is currently londed so can't be deleted");
                   return false;
               }
            }
       }
        System.out.println("the book is not found in the library so can't be deleted");
        return false;
        
    } 
    
    public boolean addStudent(){
        String a,b,c,d;
        System.out.println("insert student name:");
        a = s.next();
        System.out.println("insert student family name:");
        b = s.next();
        System.out.println("insert student id:");
        c = s.next();
        System.out.println("insert student mail:");
        d = s.next();
        Student s = new Student(a,b,c,d);
        for(Student temp:students){
            if(s.equals(temp)){
                System.out.println("Student exists in system");
                return false;
            }
        }
        students.add(s);
        System.out.println("student got in the library successfully");
        return true;
    }
    
    public void borrowBook(){
        String temp = null, cat = null, id = null;
        System.out.println("insert your id number");
        Student t = null;
        boolean f = false,z = false;
        id = s.next();
        for(int i = 0; i < students.size(); i++){
            if(id.equals(students.get(i).getId())){
                if(!students.get(i).getDis()){
                    f = true;   
                    t =  students.get(i);
                }
            }
        }
        if(!f){
            System.out.println("you are not in the System or you are not allowed to borrow");
            return;
        }
        if(t.getNumBooks() == 3){
            System.out.println("you are not allowed to have more than 3 books");
            return;
        }
        do{
        System.out.println("insert book category:");
        cat = s.next();
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getCategory().equals(cat) && books.get(i).getCopiesAvailable() != 0){
                System.out.println("isbn is: "+books.get(i).getIsbn()+" name is: "+books.get(i).getName());
            }
        }
        System.out.println("enter the isbn of book you want to take");
        temp = s.next();
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getIsbn().equals(temp)){
                Book b = books.get(i);
                b.setCopiesAvailable(b.getCopiesAvailable()-1);
                borrowInfo.add(new BookAndStudent(new BookCopy(b.getIsbn(),b.getName(),b.getWriter(),b.getCategory(),b.getReleseYear(),(b.getCopies()-b.getCopiesAvailable())),t));
                if(t.getNumBooks() != 3){
                    t.setNumBooks(t.getNumBooks()+1);
                    z = true;
                    System.out.println("book was borrowed successfully");
                }
                else{
                    System.out.println("can only have 3 books at a time");
                    return;
                }
            }
        }
        if(!z)
            System.out.println("book/category was not found");
        System.out.println("if you would like to borrow another book press 1 else press 0");
        if(s.nextInt()== 0)
            return;
        }while(t.getNumBooks()<3);
        System.out.println("can boroow 3 books at a time");  
    }
    
    public void returnBook(){
        String isbn = null;
        String id =null;
        System.out.println("insert student's id ");
        id = s.next();
        System.out.println("insert book's isbn to return");
        isbn = s.next();
        Date d = new Date();
        BookAndStudent temp = null;
        
        for(BookAndStudent bas: borrowInfo){
            if(bas.getBook().getIsbn().equals(isbn) && bas.getStudent().getId().equals(id)){
                if(bas.getDate().getYear()==d.getYear() && bas.getDate().getMonth()==d.getMonth()){
                    if((bas.getStudent().getLate()+(d.getDate()-bas.getDate().getDate()))<=7)
                        bas.getStudent().setLate(bas.getStudent().getLate()+(d.getDate()-bas.getDate().getDate()));
                    else{
                        bas.getStudent().setDisabeld(true);
                        bas.getStudent().setLate(bas.getStudent().getLate()+(d.getDate()-bas.getDate().getDate()));
                    }
                }
                else{
                     bas.getStudent().setDisabeld(true);
                     bas.getStudent().setLate(bas.getStudent().getLate()+(d.getDate()-bas.getDate().getDate()));
                }
                temp = bas; 
                break;
            }
        }
        if(temp == null){
            System.out.println("no book match");
            return;
        }
        borrowInfo.remove(temp);
        for(Book b: books){
            if(b.getIsbn().equals(temp.getBook().getIsbn())){
               b.setCopiesAvailable(b.getCopiesAvailable()+1);
               int x = (temp.getStudent().getNumBooks()-1);
               temp.getStudent().setNumBooks(x);
               System.out.println("Book was successfully returned to libarary");
            }
        }
        for(Student s:students){
            if(s.getId().equals(temp.getStudent().getId())){
                s.setNumBooks(s.getNumBooks()-1);
                s.setDisabeld(temp.getStudent().getDis());
                s.setLate(temp.getStudent().getLateDays());
            }
        }
    }
    
    public void searchBook(){
        System.out.println("insert book description");
        String des = s.next();
        for(Book b:books){
            if(b.getName().indexOf(des)!=-1){
                System.out.println("book isbn is:"+b.getIsbn()+" name of book is:"+b.getName());
            }
        }
        System.out.println("if you would like to borrow one of the books press 1 else press 0");
        int t = s.nextInt();
        if(t == 1)
            borrowBookSIsbn();
        else if(t == 0)
            return;
        else
            System.out.println("not valid value");
    }
    
    public boolean borrowBookSIsbn(){
        String temp = null, id = null;
        System.out.println("insert your id number");
        Student t = null;
        boolean f = false;
        id = s.next();
        for(int i = 0; i < students.size(); i++){
            if(id.equals(students.get(i).getId())){
                if(!students.get(i).getDis()){
                    f = true;   
                    t =  students.get(i);
                }
            }
        }
        if(!f){
            System.out.println("you are not in the System");
            return false;
        }
        if(t.getNumBooks()>=3){
            System.out.println("you are not allowed to have more than 3 books");
            return false;
        }
        System.out.println("enter the isbn of book you want to take");
        temp = s.next();
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getIsbn().equals(temp)){
                Book b = books.get(i);
                b.setCopiesAvailable(b.getCopiesAvailable()-1);
                borrowInfo.add(new BookAndStudent(new BookCopy(b.getIsbn(),b.getName(),b.getWriter(),b.getCategory(),b.getReleseYear(),(b.getCopies()-b.getCopiesAvailable())),t));
                System.out.println("book was borrowed successfully");
                return true;
            }
        }
        System.out.println("book was not found");
        return false;
    }
    
    void save() throws IOException{
        FileOutputStream fos1 = new FileOutputStream("copies.dat");
        FileOutputStream fos2 = new FileOutputStream("students.dat");
        FileOutputStream fos3 = new FileOutputStream("borrowInfo.dat");

        ObjectOutputStream oos = new ObjectOutputStream(fos1);
        oos.writeObject(books);
        oos.close();
        fos1.close();
        oos =  new ObjectOutputStream(fos2);
        oos.writeObject(students);
        oos.close();
        fos2.close();
        oos =  new ObjectOutputStream(fos3);
        oos.writeObject(borrowInfo);
        oos.close();
        fos3.close();
    }
    
    public Vector<Book> getBooks(){return books;}
    public Vector<Student> getStudents(){return students;}
    public Vector<BookAndStudent> getLone(){return borrowInfo;}


    private void readBooks(String ctempcopiestxt) throws FileNotFoundException, IOException, ClassNotFoundException{
        File f = new File(ctempcopiestxt);
        if(f.length() == 0)
            books = new Vector<Book>();
        else{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ctempcopiestxt));
            books = (Vector<Book>) ois.readObject();
            ois.close();
        }
    }

    private void readStudents( String ctempstudentstxt) throws FileNotFoundException, IOException, ClassNotFoundException{
        File f = new File(ctempstudentstxt);
        if(f.length() == 0)
            students = new Vector<Student>();
        else{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ctempstudentstxt));
            students = (Vector<Student>) ois.readObject();
            ois.close();
        }
    }

    private void readLoneInfo(String ctemploneInfotxt) throws FileNotFoundException, IOException, ClassNotFoundException{
        File f = new File(ctemploneInfotxt);
        if(f.length() == 0)
            borrowInfo = new Vector<BookAndStudent>();
        else{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ctemploneInfotxt));
            borrowInfo = (Vector<BookAndStudent>) ois.readObject();
            ois.close();
        }
    }
}  
  