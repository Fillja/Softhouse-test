package src.integration.models;

import java.util.List;
import java.util.Optional;

public class PersonModel {
    public String firstName;

    public String lastName;
    
    public Optional<AddressModel> adresses;

    public Optional<PhoneModel> phoneNumbers;

    public List<FamilyModel> familyMembers;

    public PersonModel(String firstName, String lastName, Optional<AddressModel> adresses, Optional<PhoneModel> phoneNumbers,  List<FamilyModel> familyMembers){
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
        this.phoneNumbers = phoneNumbers;
        this.familyMembers = familyMembers;
    }
}
