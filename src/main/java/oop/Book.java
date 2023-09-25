package oop;

public class Book extends Literature {

    public Book(String author, String title){
        this.setAuthor(author);
        super.setTitle(title);
    }
    private String author;
    @Override
    public String GetCard() {
        return String.format("Book^ %s '%s' ", getAuthor(), getTitle());
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
