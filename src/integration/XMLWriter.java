package src.integration;

import java.io.File;
import java.util.List;
import java.util.Optional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import src.integration.models.FamilyModel;
import src.integration.models.PersonModel;

public class XMLWriter {
    
    public void PrintToXML(List<PersonModel> list){
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = docBuilder.newDocument();

            Element rootElement = document.createElement("people");
            document.appendChild(rootElement);

            for(PersonModel person : list){
                // Root - Person
                Element personElement = CreateXMLElement("person", rootElement, document);

                // First & last name
                CreateXMLElement("firstName", personElement, person.firstName, document);
                CreateXMLElement("lastname", personElement, person.lastName, document);

                // Address
                if (person.addresses.isPresent()){
                    Element addressElement = CreateXMLElement("address", personElement, document);
                    CreateXMLElement("street", addressElement, person.addresses.get().street, document);
                    CreateXMLElement("city", addressElement, person.addresses.get().city, document);

                    if(person.addresses.get().zip.isPresent()){
                        CreateXMLElement("zip", addressElement, person.addresses.get().zip.get(), document);
                    }
                }
                
                // Phone
                if (person.phoneNumbers.isPresent()){
                    Element phoneElement = CreateXMLElement("phone", personElement, document);
                    CreateXMLElement("mobile", phoneElement, person.phoneNumbers.get().mobile, document);
                    CreateXMLElement("landline", phoneElement, person.phoneNumbers.get().landline, document);
                }

                // Family
                List<FamilyModel> familyList = person.familyMembers;
                if (!familyList.isEmpty()){
                    for(FamilyModel familyMember : familyList){

                        Element familyElement = CreateXMLElement("family", personElement, document);
                        CreateXMLElement("name", familyElement, familyMember.name, document);
                        CreateXMLElement("born", familyElement, String.valueOf(familyMember.birthDate), document);

                        // Family address
                        if(familyMember.addresses.isPresent()){
                            Element addressElement = CreateXMLElement("address", familyElement, document);
                            CreateXMLElement("street", addressElement, familyMember.addresses.get().street, document);
                            CreateXMLElement("city", addressElement, familyMember.addresses.get().city, document);
        
                            if(person.addresses.get().zip.isPresent()){
                                CreateXMLElement("zip", addressElement, familyMember.addresses.get().zip.get(), document);
                            }
                        }

                        // Family phone
                        if(familyMember.phoneNumbers.isPresent()){
                            Element phoneElement = CreateXMLElement("phone", familyElement, document);
                            CreateXMLElement("mobile", phoneElement, familyMember.phoneNumbers.get().mobile, document);
                            CreateXMLElement("landline", phoneElement, familyMember.phoneNumbers.get().landline, document);
                        }
                    }
                }
            }

            Transformer transformer  = TransformerFactory.newInstance().newTransformer();

            //Formatting with indentation
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("./src/integration/resources/output.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Create Element without content
    public static Element CreateXMLElement(String elementName, Element parentElement, Document document){
        Element createdElement = document.createElement(elementName);
        parentElement.appendChild(createdElement);

        return createdElement;
    }

    // Overload: Text-content without optional string
    public static Element CreateXMLElement(String elementName, Element parentElement, String textContent, Document document){
        Element createdElement = document.createElement(elementName);
        createdElement.setTextContent(textContent);
        parentElement.appendChild(createdElement);

        return createdElement;
    }

    // Overload: Text-content with optional string
    public static Element CreateXMLElement(String elementName, Element parentElement, Optional<String> textContent, Document document){
        Element createdElement = document.createElement(elementName);
        if(textContent.isPresent()){
            createdElement.setTextContent(textContent.get());
        }
        parentElement.appendChild(createdElement);

        return createdElement;
    }
}
