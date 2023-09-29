package step.learning.oop;

import com.google.gson.JsonObject;

import java.text.ParseException;

public class Journal extends Literature implements Copyable {
    private int number;
    private transient String title;
    public Journal(String title, int number) {
        this.setNumber(number);
        super.setTitle(title);
    }
    public static boolean isParseableFromJson ( JsonObject jsonObject){
        String[] requiredFields = {"number", "title"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                return false;
            }
        }
        return true;
    }
    public static Journal fromJson(JsonObject jsonObject) throws ParseException {
        String[] requiredField = {"title","number"};
        for (String field : requiredField){
            if(!jsonObject.has(field)){
                throw new ParseException("Missing required field: " + field,0);
            }
        }
        return new Journal(jsonObject.get(requiredField[0]).getAsString(),
                jsonObject.get(requiredField[1]).getAsInt());
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String GetCard() {
        return String.format(
                "Journal '%s' №%s",
                super.getTitle(),
                this.getNumber()
        );
    }
}