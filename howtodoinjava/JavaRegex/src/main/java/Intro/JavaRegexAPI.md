# Java Regex API

## PATTERN
a regular expression specified as a string
- must FIRST be compiled into an instance of this class
- resulting pattern can be used to create a Matcher object that can match arbitrary
char sequences against the REGEX.


    Pattern p = Pattern.compile("abc");
    Matcher m = p.matcher("abcabcabcd");
    boolean b = m.matches();    // true!

- instances of this class are IMMUTABLE
- safe for use w/ multiple concurrent threads

### METHODS
Predicate asPredicate()
- creates Java8 Predicate which can be used to match a string

static Pattern compile(String regex)
- used to compile a given regex into a pattern

static Pattern compile(String regex, int flags)
- same as above, but w/ given glags

int flags()
- returns pattern's match flags

Matcher matcher(CharSequence input)
- used to create a matcher that will match the given input against the pattern.

static boolean matches(String regex, CharSequence input)
- used to compile given regex and tries to match given input against it

String pattern()
- return regex from which pattern was compiled

static String quote(String s)
- return a literal pattern String for the specified String

String[] split(CharSequence input)
- used to split given input sequence around matches of this pattern

String[] split(CharSequence input, int limit)
- same as above, but creates up to limit substrings

Stream splitAsStream(CharSequence input)
- creates a stream from given input sequence around matches of this pattern.
    
## MATCHER
- class provides methods that perform match operations
- defines methods for replacing sub-sequences w/ new strings (whose contents can be 
computed from the match result)
- NOT THREAD SAFE

### METHODS
boolean find()
- used for searching multiple occurrences of regex in text

boolean find(int start)
- same as above, starting from given index.

int start()
- gets start index of match being found using find()

int end() 
- gets end index of match being found using find()

int groupCount()
- gets total number of matched subsequences

String group()
- used to find matched subsequence

boolean matches()
- used to test whether the regex matches the pattern or noot

boolean lookingAt()
- tries to match input sequence starting at beginning of the region, against the pattern.

String quoteReplacement(String s)
- returns literal replacement String for specified String

Matcher reset()
- resets this matcher

MatchResult toMatchResult()
- returns match state of this matcher as a MatchResult


## MatchResult (interface)
- this is the result of a match operations
- contains query methods used to determine the results of a match against a regex. 

## PatternSyntaxException
- UNCHECKED exception thrown to indicate a syntax error in a regex pattern