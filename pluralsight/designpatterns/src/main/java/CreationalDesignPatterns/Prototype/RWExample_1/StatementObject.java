package CreationalDesignPatterns.Prototype.RWExample_1;

import java.util.List;

public class StatementObject implements Cloneable {

    private String sql;
    private List<String> parameters;
    private Record record;

    public StatementObject(String sql, List<String> parameters, Record record) {
       this.sql = sql;
       this.parameters = parameters;
       this.record = record;
    }

    public StatementObject clone() {
        try {
            return (StatementObject) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSql() {
        return sql;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public Record getRecord() {
        return record;
    }
}
