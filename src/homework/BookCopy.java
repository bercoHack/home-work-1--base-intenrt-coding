package homework;

import java.io.Serializable;

public class BookCopy implements Serializable{
    private String isbn;
    private String name;
    private String writer;
    private String category;
    private String releaseYear;
    private int copyNumber;
    
    public BookCopy(String isbn, String name, String writer, String category, String releaseYear, int copyNumber){
        this.isbn = isbn;
        this.name = name;
        this.writer = writer;
        this.category = category;
        this.releaseYear = releaseYear;
        this.copyNumber = copyNumber;
    }
    public boolean equals(Object o){
        BookCopy b = null;
        if(o instanceof BookCopy)
            b = (BookCopy) o;
        else
            return false;
        if(isbn.equals(b.isbn))
            return true;
        return false;
    }
    public String getIsbn(){
        return isbn;
    }
    public String getCategory(){
       return category;
   }
    public String getName(){
       return name;
   }
}
