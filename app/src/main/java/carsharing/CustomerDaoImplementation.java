package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDaoImplementation implements CustomerDao {

    @Override
    public void createCustomer(String customerName, Database database) {
        String query = String.format(Query.INSERT_CUSTOMER, customerName);
        database.execute(query);
    }

    @Override
    public ArrayList<Customer> selectAll(Database database) {
        String query = String.format(Query.SELECT_ALL, "CUSTOMER");
        ArrayList<Customer> result = new ArrayList<>();
        try (Connection conn =  DriverManager.getConnection(database.getURL());
             Statement stmt = conn.createStatement();
             ResultSet sqlResults = stmt.executeQuery(query);
        ) {
            while (sqlResults.next()) {
                int id = sqlResults.getInt("ID");
                String name = sqlResults.getString("NAME");
                int rentedCarId = sqlResults.getInt("RENTED_CAR_ID");
                Customer customer = new Customer(id, name, rentedCarId);
                result.add(customer);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateReturnedCar (Customer customer, Database database) {
        String query = String.format(Query.UPDATE_CUSTOMER_RETURN, customer.getId());
        database.execute(query);
    }

    @Override
    public void updateRentedCar (Customer customer, Car car, Database database) {
        String query = String.format(Query.UPDATE_CUSTOMER_RENT, car.getId(), customer.getId());
        database.execute(query);
    }
}

