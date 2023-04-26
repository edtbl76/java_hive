package Methods;

import java.util.Arrays;
import java.util.List;

public class AbstractEnumMethods {

    public static void main(String[] args) {
        List<NewDirection> newList = Arrays.asList(NewDirection.values());
        newList.forEach(NewDirection::printDirection);
    }
}

enum NewDirection {

    EAST {
        @Override
        public void printDirection() {
            System.out.println("You are moving east. You better start swimming!");
        }
    },
    WEST {
        @Override
        public void printDirection() {
           System.out.println("You are moving west. The rest of the country is there.");
        }
    },
    NORTH {
        @Override
        public void printDirection() {
            System.out.println("You are moving north. New Hampshire sucks.");
        }
    },
    SOUTH {
        @Override
        public void printDirection() {
            System.out.println("You are moving south. Providence ahead!");
        }
    };

    public abstract void printDirection();
}
