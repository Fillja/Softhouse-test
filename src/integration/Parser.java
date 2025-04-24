package src.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import src.integration.models.Address;
import src.integration.models.Family;
import src.integration.models.Person;
import src.integration.models.Phone;

public class Parser {
 
    public List<Person> ParseFile(String file){

        try{     
            BufferedReader reader = Files.newBufferedReader(Paths.get(file));
            List<Person> personList = new LinkedList<>();

            //State management
            Person currentPerson = null;
            Family currentFamily = null;
            String line;

            while((line = reader.readLine()) != null){
                if (line.trim().isEmpty()) continue;

                String[] lineValues = line.split("\\|");


                switch(lineValues[0]){  
                    case "P":
                        if(IsEmptyField(lineValues, line)){
                            break;
                        }
                        
                        //Tracking the state by instantiating a new person and resetting family member
                        currentPerson = new Person(lineValues[1], lineValues[2], Optional.empty(), Optional.empty(), new LinkedList<>());
                        personList.add(currentPerson);
                        currentFamily = null;
                    break;

                    case "T":
                        if(IsEmptyField(lineValues, line)){
                            break;
                        }

                        Phone phoneNumbers = new Phone(lineValues[1], lineValues[2]);
                        if(currentFamily != null){
                            currentFamily.phoneNumbers = Optional.of(phoneNumbers);
                        }
                        else if(currentPerson != null){
                            currentPerson.phoneNumbers = Optional.of(phoneNumbers);
                        }
                    break;

                    case "A":
                        if(IsEmptyField(lineValues, line)){
                            break;
                        }

                        String zip = lineValues.length>3 ? lineValues[3] : null; 
                        Address addresses = new Address(lineValues[1], lineValues[2], Optional.ofNullable(zip));
                        if(currentFamily != null){
                            currentFamily.addresses = Optional.of(addresses);
                        }
                        else if (currentPerson != null) {
                            currentPerson.addresses = Optional.of(addresses);
                        }
                    break;

                    case "F":
                        if(IsEmptyField(lineValues, line)){
                            break;
                        }

                        int birthDate = Integer.parseInt(lineValues[2].trim());
                        currentFamily = new Family(lineValues[1], birthDate, Optional.empty(), Optional.empty());
                        if(currentPerson != null){
                            currentPerson.familyMembers.add(currentFamily);
                        }
                    break;

                    default: 
                    break;
                }
            }
            reader.close();

            //Not handled "lazily"
            return personList;
    
        }catch(IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }

        return new LinkedList<>();
    }

    public static boolean IsEmptyField(String[] array, String line){
        if(array.length < 3 || array[1].isBlank() || array[2].isBlank()){
            System.out.println("Invalid line " + line);
            return true;
        }

        return false;
    }
}
