# About
[Car Sharing](https://hyperskill.org/projects/140) project is a part of Java Backend Developer track provided by Hyperskill.

- This is an application for managing car rental process. As a manager, you can create companies that will rent cars, manage those companies, add cars.  As a customer, you can rent, return and check rented car.
- Application is working with H2 database.
- Connection to the database is done with the help of JDBC api.

## Functionality
1. The database file name is obtained from the command-line arguments in a format `-databaseFileName someName`. If no arguments are provided, then the database name can be anything.
2. After the connection to the database is established, application outputs menu with the help of `System.out`. Application is getting user's input through `System.in`.

## Menu
You can choose submenu by typing the corresponding number, or type `0` to stop the application.

```
1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
```
### Exit
`Exit` stops the application.

### Manager
`Manager` menu gives you an opportunity to create a company, check company list, add cars to the specified company and check the list of cars.

```
1. Company list
2. Create a company
0. Back
```
- `Back` to return to the main menu.
- `Company list` to log in to the specific company. If company list is empty, the program outputs `The company list is empty!` and returns to the `Manager` menu. If there are already some companies, you can log in to the specific company to manage its cars.
- `Create a company` asks for a company name and creates it.

**Example:** 

```
// user's input is indicated with >>

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
>> 1

1. Company list
2. Create a company
0. Back
>> 1

The company list is empty!

1. Company list
2. Create a company
0. Back
>> 2

Enter the company name:
>> SuperRental

The company was created!

1. Company list
2. Create a company
0. Back
>> 2

Enter the company name:
>> RentUrCar

The company was created!

1. Company list
2. Create a company
0. Back
>> 1

Choose a company:
1. SuperRental
2. RentUrCar
0. Back
>> 1

'SuperRental' company

1. Car list
2. Create a car
0. Back
>> 1

The car list is empty!

1. Car list
2. Create a car
0. Back
>> 2

Enter the car name:
>> VW Golf

The car was added!

1. Car list
2. Create a car
0. Back
>> 2

Enter the car name:
>> Mazda Miata

The car was added!

1. Car list
2. Create a car
0. Back
>> 1

Car list:
1. VW Golf
2. Mazda Miata

1. Car list
2. Create a car
0. Back
>> 0

Choose a company:
1. SuperRental
2. RentUrCar
0. Back
>> 2

'RentUrCar' company

1. Car list
2. Create a car
0. Back
>> 2

Enter the car name:
>> Toyota Yaris

The car was added!

1. Car list
2. Create a car
0. Back
>> 1

Car list:
1. Toyota Yaris

1. Car list
2. Create a car
0. Back
>> 0

Choose a company:
1. SuperRental
2. RentUrCar
0. Back
>> 0

1. Company list
2. Create a company
0. Back
>> 0

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
```

### Customer
`Customer` menu gives you an opportunity to rent and return a car, as well as to check whether a customer already has a rented car.
- If there are no customers, you will not be able to get to the customer menu. You need to create at least one customer first.

**Example:** 

```
// user's input is indicated with >>

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
>> 2

The customer list is empty!

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
>> 3

Enter the customer name:
>> Bob Miller

The customer was added!

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
>> 2

Choose a customer:
1. Bob Miller
0. Back
```
- After you log in as a specific customer, the customer menu appears.
```
1. Rent a car
2. Return a rented car
3. My rented car
0. Back
```
- `Rent a car` to choose a company and a car
- `Return a rented car` to return the car
- `My rented car` to check the details of the rented car

**Example:** 

```
// user's input is indicated with >>

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
>> 2

Choose a customer:
1. Bob Miller
2. Alice Ford
0. Back
>> 1

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
>> 3

You didn't rent a car!

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
>> 2

You didn't rent a car!

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
>> 1

Choose a company:
1. SuperRental
2. RentUrCar
0. Back
>> 1

Choose a car:
1. VW Golf
2. Mazda Miata
0. Back
>> 1

You rented 'VW Golf'

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
>> 3

Your rented car:
VW Golf
Company:
SuperRental

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
>> 0

Choose a customer:
1. Bob Miller
2. Alice Ford
0. Back
>> 2

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
>> 1

Choose a company:
1. SuperRental
2. RentUrCar
0. Back
>> 1

Choose a car:
1. Mazda Miata
0. Back
>> 0

Choose a company:
1. SuperRental
2. RentUrCar
0. Back
>> 2

Choose a car:
1. Toyota Yaris
0. Back
>> 1

You rented 'Toyota Yaris'

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
>> 0

Choose a customer:
1. Bob Miller
2. Alice Ford
0. Back
>> 1

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
>> 2

You've returned a rented car!

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
>> 1

Choose a company:
1. SuperRental
2. RentUrCar
0. Back
>> 2

No available cars in the RentUrCar company

Choose a company:
1. SuperRental
2. RentUrCar
0. Back
>> 1

Choose a car:
1. VW Golf
2. Mazda Miata
0. Back
>> 2

You rented 'Mazda Miata'

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
>> 1

You've already rented a car!

1. Rent a car
2. Return a rented car
3. My rented car
0. Back
>> 0

Choose a customer:
1. Bob Miller
2. Alice Ford
0. Back
>> 0

1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
```

## Tables
The list of created tables in the database:

1. **COMPANY** with the following columns:
   - **ID** with the type `INT`, `PRIMARY KEY` and `AUTO_INCREMENT`.
   - **NAME** with the type `VARCHAR`, `UNIQUE` and `NOT NULL`.

2. **CAR** with the following columns:
   - **ID** with the type `INT`, `PRIMARY KEY` and `AUTO_INCREMENT`.
   - **NAME** with the type `VARCHAR`, `UNIQUE` and `NOT NULL`.
   - **COMPANY_ID** with the type `INT`, `NOT NULL`, `FOREIGN KEY` referring to the **ID** column of the table **COMPANY**.

3. **CUSTOMER** with the following columns:
   - **ID**  with the type `INT`, `PRIMARY KEY` and `AUTO_INCREMENT`.
   - **NAME** with the type `VARCHAR`, `UNIQUE` and `NOT NULL`.
   - **RENTED_CAR_ID** with the type `INT`, `FOREIGN KEY` referring to the **ID** column of the **CAR** table, and this column can be `NULL` in case the customer didn't rent a car.
