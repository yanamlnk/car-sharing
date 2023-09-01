package carsharing;

import java.util.ArrayList;

public interface CustomerDao {

    void createCustomer(String customerName, Database database);
    ArrayList<Customer> selectAll(Database database);
    void updateReturnedCar(Customer customer, Database database);
    void updateRentedCar(Customer customer, Car car, Database database);
}