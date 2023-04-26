# HashMap Improvements Java8 (JEP180)
- using Balanced Trees instead of LinkedLists can improve performance in 
HashMaps where there are a lot of collisions. 

Once the number of items in a hash BUCKET grows beyond a certain threshold, that particular
bucket will switch from using a LINKEDLIST to a BALANCED TREE.
- in case of HIGH HASH collisions, improves worst case performance
    - O(n) => O(log n)
        
- bins (elements/nodes) of TreeNodes can be traversed, but support faster lookup
when overpopulated. 

