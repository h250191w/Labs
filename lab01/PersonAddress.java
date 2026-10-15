
class Address {
    private final String street;
    private final String city;

    Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    String getCity() {
        return city;
    }
}

class Person {
    private final String name;
    private final Address address;   

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    String getName() {
        return name;
    }

    Address getAddress() {
        return address;
    }
}

public class PersonAddressDemo {
    public static void main(String[] args) {
        Person withAddress = new Person("Tendai", new Address("12 Samora Machel Ave", "Harare"));
        System.out.println(withAddress.getName() + " lives in "
                + withAddress.getAddress().getCity());         

        Person noAddress = new Person("Rudo", null);
        try {
            
            System.out.println(noAddress.getAddress().getCity());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + noAddress.getName() + " has no address");
        }
        
        String city = noAddress.getAddress() != null ? noAddress.getAddress().getCity() : "unknown";
        System.out.println("Safe lookup for " + noAddress.getName() + ": " + city);
    }
}
