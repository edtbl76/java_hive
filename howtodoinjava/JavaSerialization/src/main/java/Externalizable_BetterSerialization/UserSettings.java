package Externalizable_BetterSerialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UserSettings implements Externalizable {

    public UserSettings() { }

    private String dontStoreMe;

    private Integer fieldOne;
    private String fieldTwo;
    private boolean fieldThree;

    public String getDontStoreMe() {
        return dontStoreMe;
    }

    public void setDontStoreMe(String dontStoreMe) {
        this.dontStoreMe = dontStoreMe;
    }

    public Integer getFieldOne() {
        return fieldOne;
    }

    public void setFieldOne(Integer fieldOne) {
        this.fieldOne = fieldOne;
    }

    public String getFieldTwo() {
        return fieldTwo;
    }

    public void setFieldTwo(String fieldTwo) {
        this.fieldTwo = fieldTwo;
    }

    public boolean isFieldThree() {
        return fieldThree;
    }

    public void setFieldThree(boolean fieldThree) {
        this.fieldThree = fieldThree;
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        fieldOne = in.readInt();
        fieldTwo = in.readUTF();
        fieldThree = in.readBoolean();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(fieldOne);
        out.writeUTF(fieldTwo);
        out.writeBoolean(fieldThree);
    }

    @Override
    public String toString() {
        return  "UserSettings [doNotStoreMe=" + dontStoreMe + ", fieldOne=" + fieldOne + ", fieldTwo=" + fieldTwo +
                ", fieldThree=" + fieldThree + "]";
    }
}
