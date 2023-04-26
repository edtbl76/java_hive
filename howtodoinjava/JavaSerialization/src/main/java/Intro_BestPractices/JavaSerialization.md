# Java Serialization
Enables writing java objects to file system/network for permanent storage or to transfer to other apps.

## Serializable interface
guarantees the serialization of java objects. 

## INCOMPATIBLE CHANGES
these are changes to classes such that the guarantee of interoperability can't be maintained

- Deleting Fields
- Moving Classes Up/Down in hierarchy
- Changing non-static field to static
- Changing non-transient field to transient
- Changing declared type of primitive field
- changing writeObject or readObject method(s) so that they no longer write/read default field data
- changing a Serializable class to Externalizable or vice versa
- changing a class from non-enum type to enum type or vice versa
- removing serializable or externalizable implementation
- adding writeReplace or readResolve method(s) to a class.

## Compatible Changes
- adding fields
- adding classes
- removing classes
- adding writeObject/readObject method(s)
- removing writeObject/readObject methood(s)
- adding Serializable
- changing access to a field.
- changing a static field to non-static
- changing a transient field to non-transient

## serialVersionUID
Universal Version Identifier for a Serializable class
- Deserialization uses this id to ensure that a loaded class corresponds exactly to a serialized
object. 
    - throws InvalidClassException if no match is found.

BEST PRACTICES
- always include this value as a field
- do not change the value in future versions unless you are KNOWINGLY making changes to the class that
will make it incompatible w/ old serialized objects

## readObject/writeObject methods
- deserialization should be treated as any constructor
    - "VALIDATE OBJECT STATE at end of deserialization"
    - readObject() performs this validation
- if constructor makes DEFENSIVE COPIES for mutable obj. fields, so must readObject

## MORE BEST PRACTICES
- denote Serializable fields w/ @serial tag
- .ser extension is conventionally used for files representing serialized objects
- no static/transient fields should undergo default serialization
- Extendable classes should not be Serializable unless ABSOLUTELY necessary
- Inner classes should rarely (if ever) be Serializable
- Container classes should usually follow Hashtable style
    - implement Serializable by storing keys and values as opposed to a large hash table data structure