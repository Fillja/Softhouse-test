package src.integration.models;

import java.util.List;
import java.util.Optional;

public class Person {
    public String firstName;

    public String lastName;
    
    public Optional<Address> addresses;

    public Optional<Phone> phoneNumbers;

    public List<Family> familyMembers;

    public Person(String firstName, String lastName, Optional<Address> addresses, Optional<Phone> phoneNumbers,  List<Family> familyMembers){
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
        this.phoneNumbers = phoneNumbers;
        this.familyMembers = familyMembers;
    }
}
