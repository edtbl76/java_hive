package Wildcard;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BasicWildcardExample {

    public static void main(String[] args) {
        Collection<?> coll = new ArrayList<>();
        List<? extends Number> list = new ArrayList<>();
        Pair<String, ?> pair = new Pair<String, Object>() {
            @Override
            public String getLeft() {
                return null;
            }

            @Override
            public Object getRight() {
                return null;
            }

            @Override
            public Object setValue(Object value) {
                return null;
            }
        };
    }
}
