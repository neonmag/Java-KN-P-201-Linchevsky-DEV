package step.learning.oop;

import com.google.gson.JsonObject;

import java.text.ParseException;

public class Book extends  Literature implements Copyable{
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

    public static Book fromJson(JsonObject jsonObject) throws ParseException {
        String[] requiredField = {"author","title"};
        for (String field : requiredField){
            if(!jsonObject.has(field)){
                throw new ParseException("Missing required field: " + field,0);
            }
        }
        return new Book(jsonObject.get(requiredField[0]).getAsString(),
                jsonObject.get(requiredField[1]).getAsString());
    }
    public static boolean isParseableFromJson ( JsonObject jsonObject){
        String[] requiredFields = {"author", "title"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                return false;
            }
        }
        return true;
    }

}
