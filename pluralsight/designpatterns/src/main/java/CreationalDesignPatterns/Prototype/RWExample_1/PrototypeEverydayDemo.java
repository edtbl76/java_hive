package CreationalDesignPatterns.Prototype.RWExample_1;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrototypeEverydayDemo {

    public static void main(String[] args) {
        String sql = "select * from denmark where city = ?";
        List<String> parameters = new ArrayList<>();
        parameters.add("Crow");

        Record record = new Record();


        StatementObject statementObject1 = new StatementObject(sql, parameters, record);
        System.out.println(statementObject1.getSql());
        System.out.println(statementObject1.getParameters());
        System.out.println(statementObject1.getRecord());

        /*
            This is an example of a shallow copy, because we are only passing the references of the arrayList and
            the record.

            If we did a DEEP COPY, we'd get new ArrayList and a new record
         */
        StatementObject statementObject2 = statementObject1.clone();
        System.out.println(statementObject2.getSql());
        System.out.println(statementObject2.getParameters());
        System.out.println(statementObject2.getRecord());

    }
}
