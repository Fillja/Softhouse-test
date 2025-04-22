package src.integration.models;

import java.util.Optional;

public class FamilyModel {
    public String name;

    public int birthDate; 

    public Optional<PhoneModel> phoneNumbers;

    public Optional<AddressModel> addresses;

    public FamilyModel(String name, int birthDate, Optional<PhoneModel> phoneNumbers, Optional<AddressModel> addresses){
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumbers = phoneNumbers;
        this.addresses = addresses;
    }
}
