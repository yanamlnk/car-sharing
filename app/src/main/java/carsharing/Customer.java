package carsharing;

public class Customer {
    private final int id;
    private final String name;
    private int rentedCarId;

    Customer(int id, String name, int rentedCarId) {
        this.id = id;
        this.name = name;
        this.rentedCarId = rentedCarId;
    }

    public int getRentedCarId() {
        return rentedCarId;
    }

    public int getId() {
        return id;
    }

    public void setRentedCarId(int rentedCarId) {
        this.rentedCarId = rentedCarId;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
