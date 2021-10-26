package pd01.Library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    String name;
    List<IGenre> genres;

    public Library(String name, List<IGenre> genres){
        this.name = name;
        this.genres = genres;
    }

    public Library(String name){
        this.name = name;
        genres = new ArrayList<IGenre>();
    }

    public void AddGenre(Genre gen){
        genres.add(gen);
    }

    public IGenre getGenre(int i){
        try {
            return genres.get(i);
        }
        catch (IndexOutOfBoundsException exc){
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public String Info(){
        String info = "";
        for (int i = 0; i < genres.size(); i++) {
            info += String.format("%d - %s", i, genres.get(i).getName());
            info += "\n";
        }
        return info;
    }
}
