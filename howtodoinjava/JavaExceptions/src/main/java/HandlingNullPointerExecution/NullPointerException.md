# NPE
Unchecked Exception that extends RuntimeException
- doesn't force us to use catch block to handle it. 

## HOW THEY OCCUR
- trying to access/modify an object that hasn't been initialized yet. 

## COMMON CASES WHEN THEY OCCUR
- invoking methods against uninitialized objects
- parameters passed into a method are null
- calling toString() on uninitialized object
- comparing object properties in if block w/o checking null equality
- incorrect config for frameworks like Spring which work on dependency injection
- using synchronized on an uninitialized object
- chained statements (i.e. multiple method calls) in a single statement


# HANDLING TECHNIQUES

## TERNARY OPERATOR

    String str = (param == null) ? "NA" : param;
    
## Apache Commons StringUtils

    if (StringUtils.isNotEmpty(obj.getvalue())) {
        String s = obj.getvalue();
       
    }

## Check Method Arguments for null very early
This is the Fail Fast approach. 
- by adding checking code early on, we can identify the failure more intuitively
- allowing the possibility of null to break code downstream becomes harder to identify as a root cause.

## Consider the use of primitives
Primitives don't allow nulls. 
- downside is that primitives require default values, which can lead to data consistency/validity issues

## Carefully Consider Method Chaining
ref.method1().method2().method3().method4();
- this kind of statement can be hard to debug, because the NPE will only show the line that it occurred.

## use String.valueOf() Instead of toString()
- String.valueOf() doesn't throw an NPE. (It prints the word 'null')

## Avoid returning NPE
- instead of returning null, return empty Strings or Collections.

    EX
    
        List<String> data = null;
        public List getDataDemo() {
        
            if (data == null)
                return Collections.EMPTY_LIST;
            return data;
        }
        
## Discourage passing of Null parameters entirely
- this can be enforced by overloading methods. 
    - i.e. instead of a single method that allows a nullable parameter, we have two 
    methods, one that doesnt' include the parameter, and one that does w/ a non-null value.
    
## Call String.equals(String) on Safe NONNULL Strings.

    EX:
    
        BAD
            // This WILL cause an NPE
            if (param.equals("check me") {
                // impl code
            }
            
        GOOD
        
            // This won't cause an NPE.
            if ("check me".equals(param) {
                // impl code
            }
