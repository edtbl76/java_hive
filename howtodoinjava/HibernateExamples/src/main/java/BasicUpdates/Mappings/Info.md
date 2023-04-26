# Mappings and their associations
Entities contain refs to other entities:
- directly as embedded property/field
- indirectly via collection (array, set, list)

Associations are represented using FK Relationships in underlying tables. 
- Foreign Keys rely on identifiers used by participating tables. 
- UNIDIRECTIONAL associations is when only one member of the pair of entities contains 
a reference to the other. 
- BIDIRECTIONAL associations is when the references are mutual.

## Managing Associations
- only ONE end of the relationship results in any updates to FK
    - 1 and only 1 member of the relationship is referred to a "managing the relationship"
        - the "non-manager" of the two is annotated as "mappedBy"
    - NEVER EVER EVER EVER EVER EVER EVER EVER make both ends of association.
    

## MAPPINGS TABLE

| Association Type | Options/Usage | 
| --- | --- |
| One-to-One | Either end can be made the owner, but ONE (AND ONLY ONE) of them SHOULD be. Failure to specify an owner results in a circular dependency |
| One-to-Many | Many end must be made the owner of the association |
| Many-to-One | This is the same as the one to many relationship viewed from the other side of the tracks. The many end must be made owner of the association |
| Many-to-Many | Either end of the association can be made the owner |


## Annotations v. Mappings

CONS OF ANNOTATIONS
- external XML files allow the schema to vary independently from the code. 

PROS of ANNOTATIONS
- far more intuitive than XML files. 
- EXECUTABLE SPECIFICATION
    - i.e. the annotations are part of the source code, side-by-side w/ the associations
    they are related to. 
- fewer files to manage
- less verbose
- Hibernate uses/supports JPA 2 Persistence Annotations
    - i.e. entities can be deployed to env using other ORM tools that support JPA 2
-  since annotations are compiled w/ the code, they are less likely to be subject to stale files.
