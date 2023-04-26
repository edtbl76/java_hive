package Wildcard;

import java.util.ArrayList;

public class UnboundedWildcardParameterizedType {

    /*
        no restriction on type variables
     */
    public static void main(String[] args) {
        ArrayList<?> aList = new ArrayList<>();
        ArrayList<?> bList = new ArrayList<>();
        ArrayList<?> cList = new ArrayList<>();
    }
}
