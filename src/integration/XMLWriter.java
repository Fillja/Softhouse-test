package src.integration;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import src.integration.models.PersonModel;

public class XMLWriter {
    
    public void PrintToXML(List<PersonModel> list){
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = docBuilder.newDocument();

            Element rootElement = document.createElement("people");
            document.appendChild(rootElement);

            /*
            Child elements:
            person -> 
                firstname, 
                lastname, 
                address ->
                    street,
                    city,
                    zip,
                phone ->
                    mobile,
                    landline,
                family ->
                    name,
                    born,
                    address ->
                        street,
                        city,
                        zip,
                    phone ->
                        mobile,
                        landline,
            */
            for(PersonModel person : list){
                Element personElement = document.createElement("person");
                rootElement.appendChild(personElement);

                Element firstName = document.createElement("firstname");
                firstName.setTextContent(person.firstName);
                personElement.appendChild(firstName);
            }

            Transformer transformer  = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("./src/integration/resources/output.xml1"));
            transformer.transform(source, result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
