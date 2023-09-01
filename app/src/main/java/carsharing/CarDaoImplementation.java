package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CarDaoImplementation implements CarDao {

    @Override
    public void createCar(String carName, int companyId, Database database) {
        String query = String.format(Query.INSERT_CAR, carName, companyId);
        database.execute(query);
    }

    @Override
    public void updateReturnedCar(int carId, Database database) {
        String query = String.format(Query.UPDATE_CAR_RETURN, carId);
        database.execute(query);
    }

    @Override
    public void updateRentedCar(int carId, Database database) {
        String query = String.format(Query.UPDATE_CAR_RENT, carId);
        database.execute(query);
    }

    @Override
    public Car selectById(int id, Database database) {
        String query = String.format(Query.SELECT_CAR_BY_ID, id);
        try (Connection conn =  DriverManager.getConnection(database.getURL());
             Statement stmt = conn.createStatement();
             ResultSet sqlResults = stmt.executeQuery(query);
        ) {
            while (sqlResults.next()) {
                String name = sqlResults.getString("NAME");
                int companyId = sqlResults.getInt("COMPANY_ID");
                return new Car(id, name, companyId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Car> selectAll(int companyId, Database database) {
        String query = String.format(Query.SELECT_ALL_COMPANY_CARS, companyId);
        ArrayList<Car> result = new ArrayList<>();
        try (Connection conn =  DriverManager.getConnection(database.getURL());
             Statement stmt = conn.createStatement();
             ResultSet sqlResults = stmt.executeQuery(query);
        ) {
            while (sqlResults.next()) {
                int id = sqlResults.getInt("ID");
                String name = sqlResults.getString("NAME");
                Car car = new Car(id, name, companyId);
                result.add(car);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Car> selectAllAvailable(int companyId, Database database) {
        String query = String.format(Query.SELECT_ALL_AVAILABLE_CARS, companyId);
        ArrayList<Car> result = new ArrayList<>();
        try (Connection conn =  DriverManager.getConnection(database.getURL());
             Statement stmt = conn.createStatement();
             ResultSet sqlResults = stmt.executeQuery(query);
        ) {
            while (sqlResults.next()) {
                int id = sqlResults.getInt("ID");
                String name = sqlResults.getString("NAME");
                Car car = new Car(id, name, companyId);
                result.add(car);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

