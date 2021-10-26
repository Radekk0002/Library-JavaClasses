package pd01.Library;

import java.io.*;
import java.util.ArrayList;

public class Genre implements IGenre {
    String name;
    ArrayList<Book> books;

    public Genre(String name){
        this.name = name;
        books = new ArrayList<Book>();
    }

    @Override
    public Book getBook(int i){
        try {
            return books.get(i);
        }
        catch (IndexOutOfBoundsException exc){
            return null;
        }
    }

    @Override
    public void Add(Book book){
        books.add(book);
    }

    @Override
    public void Remove(int ind){
        books.remove(ind);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String Info(Boolean admin) {
        String info = "";
        for (int i = 0; i < books.size(); i++) {
            if(!books.get(i).taken && !admin) {
                info += String.format("%d - %s", i, books.get(i));
                info += "\n";
            }
            else if(admin) {
                info += String.format("%d - %s; Czy wypożyczona: %b", i, books.get(i), books.get(i).taken);
                info += "\n";
            }
        }
        return info;
    }

    @Override
    public void Export() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(name + ".txt");
            for (Book book : books) {
                out.println(book.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (out != null)
                out.close();
        }
    }

    @Override
    public void Import(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null){
                var cred = line.split(";");
                Add(new Book(cred[0].split(": ")[1], cred[1].split(": ")[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
