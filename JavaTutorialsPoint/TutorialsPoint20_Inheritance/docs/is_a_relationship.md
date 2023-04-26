# IS-A RELATIONSHIP

    - this is a way of saying
        - "This object is a type of that object"
        
        EX:
        public class Animal {}
        
        public class Mammal extends Animal {}
        public class Reptile extends Animal {}
        
        public class Dog extends Mammal {}
        
        
            Animal is a superclass of Mammal and Reptile
        
            Mammal and Reptile are subclasses of Animal
            
            Dog is subclass of both Mammal and Animal
            
            Mammal IS-A Animal
            Reptile IS-A Animal
            Dog IS-A Mammal
                Dog IS-A Animal