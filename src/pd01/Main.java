package pd01;

import pd01.Library.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);

        Book book1 = new Book("książkaGatunek1", "AutorGatunek1");
        Book book2 = new Book("książkaGatunek2", "AutorGatunek2");
        Book book3 = new Book("książkaGatunek3", "AutorGatunek3");

        Genre gatunek1 = new Genre("gatunek1");
        Genre gatunek2 = new Genre("gatunek2");
        Genre gatunek3 = new Genre("gatunek3");

        gatunek1.Add(book1);
        gatunek2.Add(book2);
        gatunek3.Add(book3);

        Library bibl = new Library("Biblioteka", new ArrayList<IGenre>(Arrays.asList(gatunek1, gatunek2, gatunek3)));

        System.out.println(bibl.getName());
        Map<String,String> users = new HashMap<String,String>();
        users.put("user", "user");
        users.put("admin", "admin");
        String login = "", password = "";
        Boolean loggedIn = false;

        int user = -2;
        while (user != -1) {
            while (!loggedIn && !(users.containsKey(login) && users.get(login).equals(password))) {
                System.out.println("Podaj login");
                login = scanner.nextLine();
                if(login.equals("-1")){
                    user = -1;
                    break;
                }
                System.out.println("Podaj hasło");
                password = scanner.nextLine();
                if(password.equals("-1")){
                    user = -1;
                    break;
                }
            }
            loggedIn = true;
            if (user != -1) {
                System.out.println("(-1 aby zakończyć program)");
                do {
                    System.out.println("Wybierz gatunek");
                    System.out.println(bibl.Info());
                    user = scanner.nextInt();
                }
                while (user != -1 && bibl.getGenre(user) == null);

                int genre = user;

                if (!login.equals("admin") && user != -1) {
                    do{
                        System.out.println("Wybierz książkę, aby wypożyczyć");
                        System.out.println(bibl.getGenre(genre).Info(false));
                        user = scanner.nextInt();
                    } while (user != -1 && bibl.getGenre(genre).getBook(user) != null && bibl.getGenre(genre).getBook(user).taken);
                    if (user != -1 && bibl.getGenre(genre).getBook(user) != null) {
                        bibl.getGenre(genre).getBook(user).taken = true;
                        System.out.println("Wypożyczyłeś książkę: " + bibl.getGenre(genre).getBook(user).toString());
                    }
                } else if(user != -1) {
                    do {
                        System.out.println("0 - dodaj książkę");
                        System.out.println("1 - usuń książkę");
                        System.out.println("2 - importuj książki");
                        System.out.println("3 - eksportuj książki");
                        System.out.println("4 - pokaż książki (zmień status)");
                        user = scanner.nextInt();
                    } while (user != -1 && user != 0 && user != 1 && user != 2 && user != 3 && user != 4);

                    if (user == 0) {
                        scanner.nextLine();
                        System.out.println("Podaj tytuł");
                        String title = scanner.nextLine();
                        System.out.println("Podaj autora");
                        String author = scanner.nextLine();
                        bibl.getGenre(genre).Add(new Book(title, author));
                    } else if (user == 1) {
                        do {
                            System.out.println("Wybierz książkę, aby usunąć");
                            System.out.println(bibl.getGenre(genre).Info(true));
                            user = scanner.nextInt();
                        }
                        while (user != -1 && bibl.getGenre(genre).getBook(user) == null);
                        bibl.getGenre(genre).Remove(user);
                    } else if (user == 2) {
                        scanner.nextLine();
                        System.out.println("Podaj ścieżkę do pliku");
                        String path = scanner.nextLine();
                        bibl.getGenre(genre).Import(path);
                    } else if (user == 3) {
                        bibl.getGenre(genre).Export();
                    } else if (user == 4) {
                        while (user != -1 && bibl.getGenre(genre).getBook(user) == null) {
                            System.out.println("Wybierz książkę, aby zmienić status");
                            System.out.println(bibl.getGenre(genre).Info(true));
                            user = scanner.nextInt();
                        }
                        if (user != -1)
                            bibl.getGenre(genre).getBook(user).taken = !bibl.getGenre(genre).getBook(user).taken;
                    }
                }

                if (user != -1) {
                    System.out.println("Aby zakończyć program wpisz -1, aby wylogować wpisz 0");
                    user = scanner.nextInt();
                    if(user == 0) {
                        loggedIn = false;
                        login = password = "";
                        scanner.nextLine();
                    }
                }
            }
        }
    }
}
