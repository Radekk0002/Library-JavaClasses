package pd01.Library;

public interface IGenre extends IExportable, IImportable {
    void Add(Book book);

    void Remove(int ind);

    String getName();

    String Info(Boolean admin);

    public Book getBook(int i);
}
