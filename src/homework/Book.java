
package homework;

import java.io.Serializable;

public class Book implements Serializable{
    private int copies;
    private int copiesAvailable;
    private String isbn;
    private String name;
    private String writer;
    private String category;
    private String releaseYear;

    Book(String a, String b, String c, String d, String e, int num) {
        copies = num;
        copiesAvailable = num;
        isbn = a;
        name = b;
        writer = c;
        category = d;
        releaseYear = e;
    }

    String getIsbn() {return isbn;}

    int getCopies() {return copies;}
    
    int getCopiesAvailable() {return copiesAvailable;}

    void setCopies(int i) {copies = i;}

    String getCategory() {return category;}

    String getName() {return name;}

    String getReleseYear() {return releaseYear;}
    
    String getWriter() {return writer;}

    void setCopiesAvailable(int i) {copiesAvailable = i;}

}
