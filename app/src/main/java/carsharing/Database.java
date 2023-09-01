package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class Database {
    private String URL;

    Database (String[] args) {
        try {
            Class.forName("org.h2.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String name = "database";
        if (args.length == 2) {
            name = args[1];
        }
        URL = "jdbc:h2:./app/src/main/resources/db/" + name;

        execute(Query.CREATE_COMPANY_TABLE);
        execute(Query.CREATE_CAR_TABLE);
        execute(Query.CREATE_CUSTOMER_TABLE);
    }

    void execute(String query) {
        try (Connection conn =  DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getURL() {
        return URL;
    }
}
