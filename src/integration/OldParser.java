package src.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import src.integration.models.AddressModel;
import src.integration.models.FamilyModel;
import src.integration.models.PersonModel;
import src.integration.models.PhoneModel;

public class OldParser {
    
    public void ParseFile(){
        try{
            BufferedReader reader = Files.newBufferedReader(Paths.get("src/integration/resources/softhouse.txt"));
            List<PersonModel> personList = new LinkedList<>();
            boolean foundPerson = false;
            String line;

            while((line = reader.readLine()) != null){
                if (line.trim().isEmpty()) continue;

                PersonModel person = new PersonModel("", "", null, null, null);
                String[] personValues = line.split("\\|");

                //First iteration builds the person
                person.firstName = personValues[1];
                person.lastName = personValues[2];

                while (!foundPerson) { 
                    char propertyType = (char)reader.read();
                    
                    //A secondary person has been found, therefore create a new model
                    if(propertyType == 'P'){
                        foundPerson = true;
                        break;
                    }

                    //File empty?
                    if((line = reader.readLine()) == null){
                        break;
                    }                  
                    
                    String[] inputValues = line.split("\\|");

                    switch(propertyType){  
                        case 'T':
                            person.phoneNumbers = Optional.of(new PhoneModel(inputValues[0], inputValues[1]));
                        break;
    
                        case 'A':
                            String zip = inputValues.length>2 ? inputValues[2] : null; 
                            person.addresses = Optional.of(new AddressModel(inputValues[0], inputValues[1], Optional.of(zip)));
                        break;
    
                        case 'F':
                            int birthDate = Integer.parseInt(inputValues[1]);
                            FamilyModel familyMember = new FamilyModel(inputValues[0], birthDate, null, null);
                            person.familyMembers.add(familyMember);
                        break;

                        default: 
                        break;
                    }
                    
                }

                personList.add(person);
            }
    
        }catch(IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
