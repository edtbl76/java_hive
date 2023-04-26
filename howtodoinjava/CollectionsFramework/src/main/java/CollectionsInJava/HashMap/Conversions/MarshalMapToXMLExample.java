package CollectionsInJava.HashMap.Conversions;

import javax.xml.bind.*;
import java.io.File;
import java.util.HashMap;

public class MarshalMapToXMLExample {

    public static void main(String[] args) throws JAXBException {
        HashMap<Integer, Employee> map = new HashMap<>();

        Employee emp1 = new Employee();
        Employee emp2 = new Employee();

        emp1.setId(1);
        emp1.setFirstName("Ed");
        emp1.setLastName("Mangini");
        emp1.setIncome(100.00);

        emp2.setId(2);
        emp2.setFirstName("Vanessa");
        emp2.setLastName("Mangini");
        emp2.setIncome(100.00);

        map.put(1, emp1);
        map.put(2, emp2);

        // Add Employees in map
        EmployeeMap employeeMap = new EmployeeMap();
        employeeMap.setEmployeeMap(map);


        // MARSHALLING EXAMPLE
        JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeMap.class);

        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(employeeMap, System.out);
        marshaller.marshal(employeeMap, new File("employees.xml"));

        //UNMARSHALLING

       // jaxbContext = JAXBContext.newInstance(EmployeeMap.class);
        jaxbContext = JAXBContext.newInstance(EmployeeMap.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        EmployeeMap empMap = (EmployeeMap) jaxbUnmarshaller.unmarshal(new File("employees.xml"));

        for(Integer empId : empMap.getEmployeeMap().keySet())
        {
            System.out.println(empMap.getEmployeeMap().get(empId).getFirstName());
            System.out.println(empMap.getEmployeeMap().get(empId).getLastName());
        }



    }
}
