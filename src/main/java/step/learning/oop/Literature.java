package step.learning.oop;

import com.google.gson.JsonObject;

import java.text.ParseException;

public abstract class Literature {
    private String title;
    public abstract String  GetCard();

    public String getTitle() {
        return title;
    }
    private Literature fromJson(JsonObject jsonObject) throws ParseException{
        if(Book.isParseableFromJson(jsonObject)){
            return Book.fromJson(jsonObject);
        }
        else if(Journal.isParseableFromJson(jsonObject)){
            return Journal.fromJson(jsonObject);
        }
        else if(Newspaper.isParseableFromJson(jsonObject)){
            return Newspaper.fromJson(jsonObject);
        }
        throw new ParseException("Literature type unrecognised",0);
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
