# Complex Streams and Fetch()

## DATABASE STREAMS

| Data | Description |
| --- | --- |
| Magic Header | a sequence of bytes 1, 2, 3, and 4 to identify the file type |
| Version | integer w/ the database stream format version |
| Name | name of the database as a string ending w/ the newline character |
| Key length | integer w/ the number of characters for the next key |
| Key name | sequence of characters for the key name |
| Value length | integer w/ the number of characters for the next value |
| Value | sequence of characters for the value |
| (...) | Remaining { key, value } sequences |

## FETCH()

