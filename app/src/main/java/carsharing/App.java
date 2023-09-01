package carsharing;

public class App {
    public static void main(String[] args) {
        Database database = new Database(args);
        Menu menu = new Menu(database);
        menu.start();
    }
}
