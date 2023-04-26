# String Class

    Strings are treated as an object that is a sequence of characters. 
    
    String literals can be created directly OR
    They can be called via one of ELEVEN different constructors that support an
    initial value using different sources (such as an Array of characters for example)
    
# SUPER COMMON java.lang.String methods


    length()        returns number of characters contained in the String object.
    *.concat()      concatenates the arg to the method to the string that prepends
                    the method (i.e. the *)
                    
                    
### Creating Format Strings

    Methods of java.lang.String to print output w/ formatted numbers. 

    format()    returns a String object that allows the creation of 
                formatted strings that can be reused as opposed to a 
                one-time print statement.
                
                
# java.lang.String methods


    1   char        charAt(int index)       
                    - Returns character at specified index
    
    2   int         compareTo(Object o)
                    - Compares String to another object
                
    3   int         compareTo(String anotherString)
                    - Compares 2 strings lexicographically
                
    4   int         compareToIgnoreCase(String str)
                    - Compares 2 strings lexcicographically, ignoring case
                
    5   String      concat(String str) 
                    - Concatenates specified string to the end of 'this' String
                
    6   boolean     contentEquals(StringBuffer sb) 
                    - Returns true, if and only if, 'this' String represents the
                    same sequence of characters as the StringBuffer
                    
    7   static String   
                    copyValueOf(char[] data)
                    - Returns a String that represents the character sequence
                    in the array specified.
                    
    8   static String
                    copyValueOf(char[] data, int offset, int count)
                    - Returns a String that represents the character sequence 
                    in the array specified.
                    
    9   boolean     endsWith(String suffix)
                    - Tests if the string ends w/ the specified suffix
                    
    10  boolean     equals(Object anObject)
                    - Compares the string to the specified object
                    
    11  boolean     equalsIgnoreCase(String anotherString)
                    - Compares 'this' String to anotherString, ignoring case
                    
    12  byte        getBytes()
                    - Encodes 'this' String into a sequence of bytes using 
                    platform's DEFAULT CHARSET, storing the result into a new
                    byte array.
                    
    13  byte[]      getBytes(String charsetName)
                    - Encodes 'this' String into a sequence of bytes using 
                    the charsetName, storing the result into a new byte array.
                    
    14  void        getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
                    - Copies characters from this string into the destinatino
                    character array
                    
    15  int         hashCode()
                    - returns a hash code for 'this' String
                    
    16  int         indexOf(int ch)
                    - returns index within string of first occurrence of 
                    specified character
                    
    17  int         indexOf(int ch, int fromIndex)
                    - Returns the index within 'this' string of the 1st occurence
                    of the specified character, starting at fromIndex
                    
    18  int         indexOf(String str)
                    - Returns the index within 'this' string of the 1st occurence
                    of the specified substring.
                    
    19  int         indexOf(String str,int fromIndex)
                    - Returns the index within 'this' string of the 1sty occurence
                    of the specified substring, starting from fromIndex
                    
    20  String      intern()
                    - Returns a canonical representation for the String object
                    
    21  int         lastIndexOf(int ch)
                    - returns index within 'this' String of the last occurence
                    of the specified character
                    
    22  int         lastIndexOf(int ch, int fromIndex)
                    - returns index within 'this' String of the last occurence
                    of the specified character, searching backwards starting at
                    fromIndex
                    
    23  int         lastIndexOf(String str)
                     - returns index within 'this' String of the last occurence
                     of the specified substring
                        
    24  int         lastIndexOf(String str, int fromIndex)
                    -  returns index within 'this' String of the last occurence
                    of the specified string, searching backwards starting at
                    fromIndex
    
    25  int         length()
                    - returns the number of characters in 'this' String
           
    26  boolean     matches(String regex) 
                    - returns true if 'this' String matches given RegEx
                    
    27  boolean     regionMatches(boolean ignoreCase, int toffset, String other,
                    int ooffset, int len)
                    - returns true if the two regions are equal.
                    
    28  boolean     regionMatches(int toffset, String other, int ooffset, int len)
                    - returns true if the two regions are equal.
    
    29  String      replace(char oldChar, char newChar)
                    - returns a new String resulting from replacing all occurences
                    of oldChar in 'this' String w/ 'newChar'
                    
    30  String      replaceAll(String regex, String replacement)
                    - replaces each substring of 'this' string that matches
                    given RegEx w/ given replacement
                    
    31  String      replaceFirst(String regex, String replacement)
                    - replaces the first substring of 'this' string that 
                    matches the given RegEx w/ the given replacement
                    
    32  String[]    split(String regex) 
                    - Splits 'this' string around matches of the given RegEx
                    
    33  String[]    split(String regex, int limit)
                    - Splits 'this' String around matches of the given RegEx
             
    34  boolean     startsWith(String prefix) 
                    - returns true if 'this' String starts w/ specified prefix
                    
    35  boolean     startsWith(String prefix, int toffset)
                    - returns true if 'this' String starts w/ the specified 
                    prefix beginning at the specified index. 
                    
    36  CharSequence  
                    subSequence(int beginIndex, int endIndex)
                    - returns a new character sequence that is a subsequence of
                    'this' sequence
                    
    37  String      substring(int beginIndex)
                    - returns a new string that is a substring of 'this' String
                    
    38  String      substring(int beginIndex, int endIndex)
                    - returns a new string that is a substring of this string.
                    
    39  char[]      toCharArray()
                    - converts 'this' String to a new 'char' array
                    
    40  String      toLowerCase()
                    - converts all chars in 'this' String to lower case using the
                    rules of the default Locale
                    
    41  String      toLowerCase(Locale locale)
                    - converts all chars in 'this' String to lower case
                    using the given locale. 
                    
    42  String      toString()
                    - returns this object as itself. Kind of dumb
                    - (Is it any wonder that the Sr. no is '42')
                    
    43  String      toUpperCase()
                    - converts all chars in 'this' String to upper case using the
                    rules of the default locale
                    
    44  String      toUpperCase(Locale locale)
                    - conerts all chars in 'this' String to upper case using the
                    rules of the given locale
                    
    45  String      trim()
                    - returns a copy of 'this' String w/ leading & trailing white
                    space omitted
                    
    46  static String
                    valueOf(primitive data type x) 
                    - returns the String representation of the passed data type
                    argument
                    
    
                
                
                
    