# HAS-A relationship

    -   determines whether or not a class HAS-A certain 'thing'
    -   the purpose of this relationship (and determining it) is for
    reducing duplication of code (as well as bugs!) 
    
    EX:
    
        public class Vehicle() {}
        public class Speed() {}
        
        public class Van() extends Vehicle() {
            private Speed sp;
        }