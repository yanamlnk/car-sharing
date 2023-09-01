package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CompanyDaoImplementation implements CompanyDao{

    @Override
    public void createCompany(String companyName, Database database) {
        String query = String.format(Query.INSERT_COMPANY, companyName);
        database.execute(query);
    }

    @Override
    public Company selectById(int id, Database database) {
        String query = String.format(Query.SELECT_COMPANY_BY_ID, id);
        try (Connection conn =  DriverManager.getConnection(database.getURL());
             Statement stmt = conn.createStatement();
             ResultSet sqlResults = stmt.executeQuery(query);
        ) {
            while (sqlResults.next()) {
                String name = sqlResults.getString("NAME");
                return new Company(id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Company> selectAll(Database database) {
        String query = String.format(Query.SELECT_ALL, "COMPANY");
        ArrayList<Company> result = new ArrayList<>();
        try (Connection conn =  DriverManager.getConnection(database.getURL());
             Statement stmt = conn.createStatement();
             ResultSet sqlResults = stmt.executeQuery(query);
        ) {
            while (sqlResults.next()) {
                int id = sqlResults.getInt("ID");
                String name = sqlResults.getString("NAME");
                Company company = new Company(id, name);
                result.add(company);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
