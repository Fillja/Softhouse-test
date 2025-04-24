package src.integration.models;

import java.util.Optional;

public class Family {
    public String name;

    public int birthDate; 

    public Optional<Phone> phoneNumbers;

    public Optional<Address> addresses;

    public Family(String name, int birthDate, Optional<Phone> phoneNumbers, Optional<Address> addresses){
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumbers = phoneNumbers;
        this.addresses = addresses;
    }
}
