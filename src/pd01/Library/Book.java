package pd01.Library;

public class Book {
    String title;
    String author;
    public Boolean taken;
    public Book(String title, String author){
        this.title = title;
        this.author = author;
        this.taken = false;
    }

    @Override
    public String toString() {
        return String.format("Tytuł: %s; Autor: %s", title, author);
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}
