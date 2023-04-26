# EnumSet
specialized impl of Set to use w/ enum types. 
- all elems in set must come from a SINGLE enum type
- not synchronized
- no NULL elements allowed

    public abstract class EnumSet<E extends Enum<E>>
        extends AbstractSet<E>
        implements Cloneable, Serializable {
        
    }
    

# EnumMap
specialized impl of Map to use w/ enum types
- all elems in set must come from a SINGLE enum type
- not synchronized
- no NULL elements allowed

    
    public class EnumMap<K extends Enum<K,V>
        extends AbstractMap<K,V>
        implements Serializable, Cloneable
