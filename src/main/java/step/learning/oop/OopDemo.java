package step.learning.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Objects;
public class OopDemo {

    public void runDemo()
    {
        Library library = new Library();
        try{
            library.load();
        }
        catch (RuntimeException ex)
        {
            System.err.println(ex.getMessage() + "Here");
        }
        library.printAllCards();
    }
    public void runGsonFabric()
    {
        String str = "{\"author\":\"D. Knuth\",\"title\":\"Art of programming\"}";
        JsonObject literatureObject = JsonParser.parseString(str).getAsJsonObject();
        Literature literature = null;
        if(literatureObject.has("author")){
            literature = new Book(
                    literatureObject.get("title").getAsString(),
                    literatureObject.get("author").getAsString()
            );
        }
        else if(literatureObject.has("number"))
        {
            literature = new Journal(
                    literatureObject.get("title").getAsString(),
                    literatureObject.get("number").getAsInt()
            );
        }
        else if(literatureObject.has("date"))
        {
            try{
                literature = new Newspaper(
                        literatureObject.get("title").getAsString(),
                        literatureObject.get("date").getAsString()
                );
            }
            catch (ParseException e)
            {
                throw new RuntimeException(e);
            }
        }
        System.out.println(literature.GetCard());
    }

    public void runGson()
    {
        Gson gson = new Gson();
        String str = "{\"author\": \"D. Knuth\", \"title\": \"Art of programming\"}";
        Book book = gson.fromJson(str, Book.class); // typeof
        System.out.println(book.GetCard());
        System.out.println(gson.toJson(book));
        book.setAuthor(null);
        System.out.println(gson.toJson(book));

        Gson gson2 = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        System.out.println(gson2.toJson(book));

        try (
                InputStream bookStream = this.getClass().getClassLoader().getResourceAsStream("book.json");
                InputStreamReader bookReader = new InputStreamReader(Objects.requireNonNull(bookStream));
        ){
            book = gson.fromJson(bookReader, Book.class); // от воно тут на 31 рядок свариться
            System.out.println(book.GetCard()); // саме тут
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }
    public void run()
    {
        Library library = new Library();
        try{
            library.add(new Book("D. Knuth", "Art of programming"));
            library.add(new Newspaper("Daily mail", "2023-09-25"));
            library.add(new Newspaper("Test1", "2023-09-25"));
            library.add(new Newspaper("Test12", "2023-09-25"));
            library.add(new Newspaper("Test13", "2023-09-25"));
            library.add(new Journal("Quantum mechanics", 157));
            library.add(new Journal("Quantum mechanics2", 157));
            library.add(new Journal("Quantum mechanics3", 157));
            //library.save();
            //FileWriter writer = new FileWriter("./src/main/resources/library.json");
           // writer.write(new Gson().toJson(library.GetFunds()));
           // writer.close();
        }
        catch (Exception ex)
        {
            System.err.println("Literature creation error: " + ex.getMessage());
        }
        library.printAllCards();
        System.out.println("--------------COPYABLE--------------");
        library.printCopyable();
        System.out.println("--------------NONCOPYABLE--------------");
        library.printNonCopyable();
        System.out.println("--------------PERIODIC--------------");
        library.printPeriodic2();
        System.out.println("--------------NONPERIODIC--------------");
        library.printNonPeriodic();
    }

}


/*
Бібліотека
Моделюємо многосховище (бібліотеку) у якому є каталог (перелік наявних видань)
Видання є різного типу: книги, газети, журнали, тощо
Кожен тип має однакову картку у каталозі

Абстрагування
Література - термін, що поєднує реальні сутності (книги)
* */

/*
ООП - об'єктно-орієнтована парадигма програмування
Програма - управління об'єктами та їх взаємодією
Програмування - налаштування об'єктів та зв'язків
Види зв'язків:
 - спадкування
 - асоціація
 - композиція
 - агрегація
 - залежність
* */