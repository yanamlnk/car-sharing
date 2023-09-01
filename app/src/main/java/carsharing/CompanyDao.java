package carsharing;

import java.util.ArrayList;

public interface CompanyDao {
    void createCompany(String companyName, Database database);
    Company selectById(int id, Database database);
    ArrayList<Company> selectAll(Database database);
}
