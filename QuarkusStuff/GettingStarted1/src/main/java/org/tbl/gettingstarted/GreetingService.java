package org.tbl.gettingstarted;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    public String greeting(String name) {
        return "Hello " + name;
    }
}
