package com.oop.Introduction;

public class BaseExample {

    private String thingToInherit;

    BaseExample(String thingToInherit) { this.thingToInherit = thingToInherit; }

    public String getThingToInherit() {
        return thingToInherit;
    }

    public void setThingToInherit(String thingToInherit) {
        this.thingToInherit = thingToInherit;
    }

    @Override
    public String toString() {
        return thingToInherit;
    }
}

class Offspring  extends BaseExample {
    private String myOwnThing;

    Offspring(String thingToInherit) {
        super(thingToInherit);
    }

    Offspring(String thingToInherit, String myOwnThing) {
        super(thingToInherit);
        this.myOwnThing = myOwnThing;
    }

    @Override
    public String getThingToInherit() {
        return super.getThingToInherit();
    }

    @Override
    public void setThingToInherit(String thingToInherit) {
        super.setThingToInherit(thingToInherit);
    }

    public String getMyOwnThing() {
        return myOwnThing;
    }

    public void setMyOwnThing(String myOwnThing) {
        this.myOwnThing = myOwnThing;
    }

    @Override
    public String toString() {
        return super.getThingToInherit() + " " + myOwnThing;
    }
}
