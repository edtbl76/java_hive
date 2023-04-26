public class instanceOfExamples {

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {

        Son mike = new Son();

        // Child objects are instances of themselves, their parents and Object.
        String msg = mike instanceof Son ? "Mike is my son" : "Mike is not my son";
        System.out.println(msg);

        msg = mike instanceof Dad ? "Mike is dad. Well that's weird." : "Mike is not dad";
        System.out.println(msg);

        msg = mike instanceof Object ? "Mike is an object...that's rude"  : "Mike is not an object";
        System.out.println(msg);

        /*
            Objects that haven't been assigned do NOT create an instance of the class (no new keyword)
            - therefore they are NOT instances.
         */
        Son son = null;
        msg = son instanceof Son ? "Son is son" : "If an object is set to null...it isn't an instance";
        System.out.println(msg);

        /*
            Parent objects are obviously not instances of their subclasses, but they are of themselves and
            object
         */
        Dad me = new Dad();
        msg = me instanceof Son ? "I'm my own son? That's strange." : "I'm not my own son.";
        System.out.println(msg);

        msg = me instanceof Dad ? "I'm a dad" : "This shouldn't display";
        System.out.println(msg);

        msg = me instanceof Object ? "Fine, I'll be an object." : "This also shouldn't display";
        System.out.println(msg);


        /*
            Checking Typecasting.
         */
        Dad edJunior = new Son();
        Dad dad = edJunior;

        msg = dad instanceof Son ? "I'm also a child of MY dad" : "nil";
        System.out.println(msg);
        System.out.println("This value was accessed through the parent reference with typecasting " + ((Son)dad).value);
    }
}

class Dad {
    int value = 1;
}
class Son extends Dad {
    int value = 10;
}