package book;

public class Book {

    private final String  Barcode;
    private final String Title;
    private final String  Author;
    private final int No_Of_Page;
    private final boolean HasRead;
    private final int Rating;

    public Book(String  Barcode, String Title,String Author,int No_Of_Page,boolean HasRead,int Rating) {
        this.Barcode = Barcode;
        this.Title = Title;
        this.Author = Author;
        this.No_Of_Page = No_Of_Page;
        this.HasRead = HasRead;
        this.Rating = Rating;
    }

    public String getBarcode() {
        return Barcode;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }
    
    public int  getNo_Of_Page() {
        return No_Of_Page;
    }
    public boolean  getHasRead() {
        return HasRead;
    }
    public int  getRating() {
        return Rating;
    }
}