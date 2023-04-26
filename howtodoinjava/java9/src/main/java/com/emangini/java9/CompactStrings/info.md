# Compact Strings Improvement

- Before Java 9
    - Strings internally represented by char[]
        - each char = 2 bytes
    - Oracle noticed from heap dumps that most of the strings in use can be 
    represented only using LATIN-1 CHARACTERS SET
        - Latin-1 char can be stored in a single byte
        - 50% reduction per char data type storage. 
        
- Java 9
    - JDK developers changed String class internal storage to byte[]
    - result is a HUGE reduction in heap memory usage. 
    
## DISABLING FEATURE
- By default, CompactStrings is enabled. 
    - Java strings containing single-byte characters are internally represented and
    stored as single-byte-per-character Strings using 
        - ISO-8859-1/Latin-1 encoding. 
        - 50% reduction in space.
    - Java strings containing at least ONE multibyte character
        - the ENTIRE string is represented and stored as 2 bytes per char using UTF16
        
- Disabling this feature forces use of UTF-16 across the board. 
    - this  can be accomplished by adding the following java parameter
        - -XX:-CompactStrings
        
- USE CASES FOR DISABLING
    - overwhelming use of multibyte character strings
    
        
