# Regular Expressions

## Metacharacters

| metacharacter | name | what it does | 
| --- | --- | --- |
| ^ | caret | represents start of line |
| $ | dollar sign | represents end of line |
| [] | character class | represents explicit set of chars |
| [^ ] | caret in a character class | negation | 
| [ - ] | dash in a character class | range of characters |
| . | dot/period | this matches any character (not in a charclass) |
| (can't show) | PIPE character | combines multiple expressions into a single expression that matches ANY of the individual ones |
| 


## Character classes
[] 
- anything in brackets is matched EXPLICITLY
    - ex [Aa] matches the capital and lowercase a
    - ex [Aa]ss matches Ass and ass
    
## Range of characters
" - "
- a dash represents a range of chars
    EX: [A-Za-z0-9] represents capitals, lowercase and numbers 
    - note order of the ranges don't matter. 


