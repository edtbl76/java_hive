# Entities Equality and Identity
Hibernate objects each represent both
1. instance of a Java class
1. row(s) in a db table(s)

# NOTES
Requesting the same object again from the same Hibernate session returns 
- the SAME INSTANCE

Requesting the same object from a different Hibernate session returns
- a DIFFERENT INSTANCE of the class. 

NOTE: ALWAYS impl equals() and hashCode() methods in Hibernate entities.
- and only compare them using .equals()!


