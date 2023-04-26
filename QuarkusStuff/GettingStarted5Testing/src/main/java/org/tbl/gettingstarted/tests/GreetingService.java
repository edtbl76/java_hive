package org.tbl.gettingstarted.tests;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    public String greeting(String name) {
        return "'sup " + name;
    }
}
