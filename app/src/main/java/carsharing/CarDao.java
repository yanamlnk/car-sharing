package carsharing;

import java.util.ArrayList;

public interface CarDao {
    void createCar(String carName, int companyId, Database database);
    void updateReturnedCar(int carId, Database database);
    void updateRentedCar(int carId, Database database);
    Car selectById(int id, Database database);
    ArrayList<Car> selectAll(int companyId, Database database);
    ArrayList<Car> selectAllAvailable(int companyId, Database database);
}
