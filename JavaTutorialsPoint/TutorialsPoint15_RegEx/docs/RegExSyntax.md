# Regular Expression Syntax

    ^               Beginning of the Line
    $               End of the Line
    .               Matches and single character except newline
                    (using 'm' option allows it ot match the newline as well) 
    [...]           Matches any single character in brackets
    [^...]          Matches any single character NOT in brackets
    \A              Beginning of the entire string
    \z              End of the entire string
    \Z              End of the entire string except allowable final line terminator
    re*             Matches 0 or more occurences of the preceding expression
                    (i.e. optional)
    re+             Matches 1 or more occurences of the preceding expression
                    (i.e. required at least once)
    re?             Matches 0 or 1 occurence of the preceding expression
    re{ n}          Matches exactly n # of occurences of the preceding expression
    re{ n,}         Matches n or more occurences of the preceding expression
    re{ n,m}        Matches at least n, and at most m occurences of preceding
                    expression
    a| b            Matches either a or b
    (re)            Groups regular expressions and remembers the matches text
    (?: re)         Groups regular expressions w/o remebering the matchex text
    (?> re)         Matches the independent pattern w/o backtracking
    \w              Matches the word characters
    \W              Matches ther nonword characters
    \s              Matches the whitespace 
                    (equivalent to [\t\n\r\f])
    \S              Matches the nonwhitespace
    \d              Matches the digits 
                    (equivalent to [0-9])
    \D              Matches the nondigits
    \A              Matches the beginning of the string
    \Z              Matches the end of the string (if a newline exists, it matches
                    just before the newline) 
    \z              Matches the end of the string
    \G              Matches the point where the last match finished
    \n              Back-reference to capture group number "n"
    \b              Matches the word boundaries when outside the brackets
                    Matches backspace (0x08) when inside the brackets
    \B              Matches nonword boundaries
    \n              Matches newline
    \t              Matches tab
    \f              Matches form feed
    \r              Matches carriage return
    \Q              Escape (quote) all characters up to \E
    \E              Ends quoting that was started w/ \Q
    
    