package homework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static Scanner s = new Scanner(System.in);
    public static void main(String[] args)throws FileNotFoundException, IOException, ClassNotFoundException{
        LoadSystem();
    }

    private static void LoadSystem() throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println("Hello, you just entered the libarary system\nchoose the type of action you would like to do, or 0 to exit");
        Library l = new Library();
        while(true){
            System.out.println("enter the number of action you would like to commit");
            System.out.println("0) exit system");
            System.out.println("1) add a book");
            System.out.println("2) remove a book");
            System.out.println("3) add new student");
            System.out.println("4) borrow a book - must be a registered student");
            System.out.println("5) return a book");
            System.out.println("6) search a book - can be by a not full description");
            int action = s.nextInt();
            switch(action){
               case(0):
                   l.save();
                   return;
               case(1):
                   l.addBook();
                   continue;
               case(2):
                   l.removeBook();
                   continue;
               case(3):
                   l.addStudent();
                   continue;
               case(4):
                   l.borrowBook();
                   continue;
               case(5):
                   l.returnBook();
                   continue;
               case(6):
                   l.searchBook();
                   continue;
            }
        }
    }
}
