package carsharing;

import java.util.ArrayList;
import java.util.Scanner;

class Menu {
    private boolean startMenuActive = true;
    private boolean managerMenuActive = true;
    private boolean companyManagerMenuActive = true;
    private boolean carManagerMenuActive = true;
    private boolean customerMenuActive = true;
    private boolean individualCustomerMenuActive = true;
    private boolean rentCarMenuActive = true;

    private final Scanner sc;
    private final CompanyDao companyDao;
    private final CarDao carDao;
    private final CustomerDao customerDao;
    private final Database database;

    Menu (Database database) {
        this.database = database;
        sc = new Scanner(System.in);
        companyDao = new CompanyDaoImplementation();
        carDao = new CarDaoImplementation();
        customerDao = new CustomerDaoImplementation();
    }

    void start() {
        int choice;
        while (startMenuActive){
            System.out.printf("%n1. Log in as a manager%n" +
                    "2. Log in as a customer%n" +
                    "3. Create a customer%n" +
                    "0. Exit%n");

            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> {
                    managerMenuActive = true;
                    managerMenu();
                }
                case 2 -> {
                    customerMenuActive = true;
                    customerMenu();
                }
                case 3 -> {
                    System.out.printf("%nEnter the customer name:%n");
                    String newCustomerName = sc.nextLine();
                    customerDao.createCustomer(newCustomerName, database);
                    System.out.printf("%nThe customer was added!%n");
                }
                case 0 -> startMenuActive = false;
                default -> System.out.printf("%nIncorrect input. Please try again.%n");
            }
        }
    }

    private void managerMenu() {
        int choice;
        while(managerMenuActive) {
            System.out.printf("%n1. Company list%n" +
                    "2. Create a company%n" +
                    "0. Back%n");

            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> {
                    companyManagerMenuActive = true;
                    companyManagerMenu();
                }
                case 2 -> {
                    System.out.printf("%nEnter the company name:%n");
                    String newCompanyName = sc.nextLine();
                    companyDao.createCompany(newCompanyName, database);
                    System.out.printf("%nThe company was created!%n");
                }
                case 0 -> managerMenuActive = false;
                default -> System.out.printf("%nIncorrect input. Please try again.%n");
            }
        }
    }

    void companyManagerMenu() {
        int choice;
        while(companyManagerMenuActive) {
            ArrayList<Company> companies = companyDao.selectAll(database);
            if (companies.isEmpty()) {
                System.out.printf("%nThe company list is empty!%n");
                companyManagerMenuActive = false;
            } else {
                System.out.printf("%nChoose a company:%n");
                int list = 1;
                for (Company company : companies) {
                    System.out.println(list + ". " + company);
                    list++;
                }
                System.out.println("0. Back");

                choice = Integer.parseInt(sc.nextLine());
                if (choice == 0) {
                    companyManagerMenuActive = false;
                } else {
                    Company company = companies.get(choice - 1);
                    carManagerMenuActive = true;
                    carManagerMenu(company);
                }
            }
        }
    }

    void carManagerMenu (Company company) {
        System.out.printf("%n'%s' company%n", company.getName());
        int choice;
        int companyId = company.getId();
        while(carManagerMenuActive) {
            System.out.printf("%n1. Car list%n2. Create a car%n0. Back%n");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> {
                    ArrayList<Car> cars = carDao.selectAll(companyId, database);
                    if (cars.isEmpty()) {
                        System.out.printf("%nThe car list is empty!%n");
                    } else {
                        int list = 1;
                        System.out.printf("%nCar list:%n");
                        for (Car car : cars) {
                            System.out.println(list + ". " + car);
                            list++;
                        }
                    }
                }
                case 2 -> {
                    System.out.printf("%nEnter the car name:%n");
                    String newCarName = sc.nextLine();
                    carDao.createCar(newCarName, companyId, database);
                    System.out.printf("%nThe car was added!%n");
                }
                case 0 -> carManagerMenuActive = false;
                default -> System.out.printf("%nIncorrect input. Please try again%n");
            }
        }
    }

    void customerMenu() {
        int choice;
        while (customerMenuActive) {
            ArrayList<Customer> customers = customerDao.selectAll(database);
            if (customers.isEmpty()) {
                System.out.printf("%nThe customer list is empty!%n");
                customerMenuActive = false;
            } else {
                int list = 1;
                System.out.printf("%nChoose a customer:%n");
                for (Customer customer : customers) {
                    System.out.println(list + ". " + customer);
                    list++;
                }
                System.out.printf("0. Back%n");
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 0) {
                    customerMenuActive = false;
                } else {
                    Customer customer = customers.get(choice - 1);
                    individualCustomerMenuActive = true;
                    individualCustomerMenu(customer);
                }
            }
        }
    }

    void individualCustomerMenu(Customer customer) {
        int choice;
        while(individualCustomerMenuActive) {
            System.out.printf("%n1. Rent a car%n" +
                    "2. Return a rented car%n" +
                    "3. My rented car%n" +
                    "0. Back%n");
            choice = Integer.parseInt(sc.nextLine());
            boolean hasRentedCar = customer.getRentedCarId() != 0;

            switch(choice) {
                case 1:
                    if (hasRentedCar) {
                        System.out.printf("%nYou've already rented a car!%n");
                    } else {
                        rentCarMenuActive = true;
                        rentCar(customer);
                    }
                    break;
                case 2:
                    if (hasRentedCar) {
                        customerDao.updateReturnedCar(customer, database);
                        carDao.updateReturnedCar(customer.getRentedCarId(), database);
                        System.out.printf("%nYou've returned a rented car!%n");
                        customer.setRentedCarId(0);
                    } else {
                        System.out.printf("%nYou didn't rent a car!%n");
                    }
                    break;
                case 3:
                    if (hasRentedCar) {
                        Car car = carDao.selectById(customer.getRentedCarId(), database);
                        Company company = companyDao.selectById(car.getCompanyId(), database);
                        System.out.printf("%nYour rented car:%n%s%nCompany:%n%s%n", car.getName(), company.getName());
                    } else {
                        System.out.printf("%nYou didn't rent a car!%n");
                    }
                    break;
                case 0:
                    individualCustomerMenuActive = false;
                    break;
                default:
                    System.out.printf("%nIncorrect input. Please try again%n");
                    break;
            }
        }
    }

    void rentCar(Customer customer) {
        int choice;
        while(rentCarMenuActive) {
            ArrayList<Company> companies = companyDao.selectAll(database);
            if (companies.isEmpty()) {
                System.out.printf("%nThe company list is empty!%n");
            } else {
                System.out.printf("%nChoose a company:%n");
                int list = 1;
                for (Company company : companies) {
                    System.out.println(list + ". " + company);
                    list++;
                }
                System.out.println("0. Back");
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 0) {
                    rentCarMenuActive = false;
                } else {
                    Company company = companies.get(choice - 1);
                    ArrayList<Car> cars = carDao.selectAllAvailable(company.getId(), database);
                    if (cars.isEmpty()) {
                        System.out.printf("%nNo available cars in the %s company%n", company.getName());
                    } else {
                        int carChoice;
                        System.out.printf("%nChoose a car:%n");
                        int carList = 1;
                        for (Car car : cars) {
                            System.out.println(carList + ". " + car);
                            carList++;
                        }
                        System.out.println("0. Back");
                        carChoice = Integer.parseInt(sc.nextLine());
                        if (carChoice == 0) {
                            continue;
                        } else {
                            Car car = cars.get(carChoice - 1);
                            customerDao.updateRentedCar(customer, car, database);
                            System.out.printf("%nYou rented '%s'%n", car.getName());
                            customer.setRentedCarId(car.getId());
                            carDao.updateRentedCar(customer.getRentedCarId(), database);
                            rentCarMenuActive = false;
                        }
                    }
                }
            }
        }
    }
}

