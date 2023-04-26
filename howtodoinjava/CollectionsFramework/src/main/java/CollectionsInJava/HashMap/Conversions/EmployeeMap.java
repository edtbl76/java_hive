package CollectionsInJava.HashMap.Conversions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement(name="employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeMap {
    private Map<Integer, Employee> employeeMap = new HashMap<>();

    public Map<Integer, Employee> getEmployeeMap() {
        return employeeMap;
    }

    public void setEmployeeMap(Map<Integer, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }
}
