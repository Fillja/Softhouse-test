package src.integration.models;

import java.util.Optional;

// Zip is kept optional for international purposes.
public class Address {
    public String street;

    public String city;

    public Optional<String> zip;

    public Address(String street, String city, Optional<String> zip){
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}