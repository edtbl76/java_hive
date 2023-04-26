# Comparable
interface used to sort array/list of objects based on their NATURAL ORDER
- natural ordering of elements is imposed by implementing it's compareTo() method. 

    
    public interface Comparable<T> {
        public int comopareTo(T o);
    }
    
## USING
Strings implement compareTo() lexicographically

Wrappers implement compareTo() based on the numeric values

User Defined objects require the compareTo method to be overridden. 

    class Employee implements Comparable<Employee> {
    
        private Long id;
        private String name;
        
        @Override
        public int compareTo(Employee o) {
            return this.getId().compareTo(o.getId();
        }
    }
    
## SORTING
Collections.sort() for List of Objects

Arrays.sort() for Array of Objects

Collections.reverseOrder() for reversing natural order. 