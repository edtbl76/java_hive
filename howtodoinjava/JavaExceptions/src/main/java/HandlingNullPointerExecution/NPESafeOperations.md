# NullPointerException safe Operations

## instanceOf() operator
    
    if (data instanceof DataThing) {
        // impl code
    }

## Accessing STATIC members of a class.
When dealing w/ static variables/methods you won't get NPE (even if you have your reference
variable pointing to null)
- static variables/method calls are bonded during compile time based on CLASS NAME and not
associated w/ the object


    MyObject obj = null;
    String attrib = obj.staticAttribute;    // no NPE, because it is a static var defined in
                                            // class MyObject
                                            
                                            
                                            
    
