#Externalizable
- solves the bloat of Serializable interface
- provides methods writeExternal() and readExternal()

Externalizable extends Serializable.

# Comparison Table Externalizable vs Serializable

| Serializable | Externalizable | 
| --- | --- |
| Serializable is a marker interface (contains no methods) | Externalizable contains 2 methods (writeExternal() and readExternal() which implementing classes MUST override |
| Passes responsibility of serialization to JVM and its default algorithm | provides control of serialization logic to developer (logic may be customized) |
| easy to implement, w/ higher cost | more responsibility on the developer, but better performance |
| hard to analyze/modify class structure | easier to support/analyze | 
| default serialization doesn't call any class constructor | a public no-arg constructoor is required |

