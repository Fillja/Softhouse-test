package src.integration.models;

import java.util.List;
import java.util.Optional;

public class PersonModel {
    public String firstName;

    public String lastName;
    
    public Optional<AddressModel> addresses;

    public Optional<PhoneModel> phoneNumbers;

    public List<FamilyModel> familyMembers;

    public PersonModel(String firstName, String lastName, Optional<AddressModel> addresses, Optional<PhoneModel> phoneNumbers,  List<FamilyModel> familyMembers){
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
        this.phoneNumbers = phoneNumbers;
        this.familyMembers = familyMembers;
    }
}
