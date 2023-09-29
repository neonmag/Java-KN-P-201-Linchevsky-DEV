package step.learning.oop;

import com.google.gson.*;

import javax.lang.model.element.NestingKind;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Library {
    private final List<Literature> funds;

    public Library() {
        funds = new LinkedList<>();
    }

    public List<Literature> GetFunds()
    {
        return funds;
    }

    public void save() throws IOException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        FileWriter writer = new FileWriter( "./src/main/resources/library.json" ) ;
        writer.write( gson.toJson( this.GetFunds() ) ) ;
        writer.close() ;
    }
    public void add(Literature literature){
        funds.add(literature);
    }

    public void printAllCards(){
        for (Literature literature : funds){
            System.out.println(literature.GetCard());
        }
    }
    public void load() throws RuntimeException{
        try(InputStreamReader reader = new InputStreamReader(
                Objects.requireNonNull(
                        this.getClass().getClassLoader().getResourceAsStream("library.json")))){
            this.funds.clear();
            for(JsonElement item: JsonParser.parseReader( reader ).getAsJsonArray()){
                this.funds.add(this.fromJson(item.getAsJsonObject()));
            }
        }
        catch (IOException ex)
        {
            throw new RuntimeException( ex );
        }
        catch (NullPointerException ignored) {
            throw new RuntimeException("Resource not found");
        }
        catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Literature fromJson(JsonObject jsonObject) throws ParseException
    {
        Class<?>[] literatures = {Book.class, Journal.class, Newspaper.class};
        try {
            for(Class<?> literature: literatures)
            {
                Method isParseableFromJson = literature.getDeclaredMethod("isParseableFromJson", JsonObject.class);
                isParseableFromJson.setAccessible(true);
                boolean res = (boolean) isParseableFromJson.invoke(null, jsonObject);
                if(res)
                {
                    Method fromJson = literature.getDeclaredMethod("fromJson", JsonObject.class);
                    fromJson.setAccessible(true);
                    return (Literature) fromJson.invoke(null, jsonObject);
                }
            }


        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if(jsonObject.has("author")){
            return new Book(
                    jsonObject.get("title").getAsString(),
                    jsonObject.get("author").getAsString()
            );
        }
        else if(jsonObject.has("number"))
        {
            return new Journal(
                    jsonObject.get("title").getAsString(),
                    jsonObject.get("number").getAsInt()
            );
        }
        else if(jsonObject.has("date"))
        {
                return new Newspaper(
                        jsonObject.get("title").getAsString(),
                        jsonObject.get("date").getAsString()
                );
        }
        throw new ParseException("Literature type ignorized", 0);
    }

    public void printCopyable(){
        for(Literature literature: funds)
        {
            if(isCopyable(literature))
            {
                System.out.println(literature.GetCard());
            }
        }
    }
    public void printNonCopyable(){
        for(Literature literature: funds)
        {
            if(!isCopyable(literature))
            {
                System.out.println(literature.GetCard());
            }
        }
    }

    public boolean isCopyable(Literature literature)
    {
        return literature instanceof Copyable;
    }

    public void printPeriodic()
    {
        for(Literature literature: funds)
        {
            if(isPeriodic(literature))
            {
                Periodic listAsPeriodic = (Periodic) literature;
                System.out.println(listAsPeriodic.GetPeriod() + " " + literature.GetCard());
            }
        }
    }
    public void printPeriodic2()
    {
        for(Literature literature: funds)
        {
            try{
                Method getPeriodMethod = literature.getClass().getDeclaredMethod("GetPeriod");
                System.out.println(getPeriodMethod.invoke(literature) + " " + literature.GetCard());
            }
            catch (Exception ignored)
            {

            }
        }
    }
    public void printNonPeriodic()
    {
        for(Literature literature: funds)
        {
            if(!isPeriodic(literature))
            {
                System.out.println(literature.GetCard());
            }
        }
    }
    public boolean isPeriodic(Literature literature)
    {
        return literature instanceof Periodic;
    }
}
