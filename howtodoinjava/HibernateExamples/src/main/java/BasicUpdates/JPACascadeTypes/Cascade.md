# JPA Cascade Types
A Cascade type "binds" persistence features from an owning entity to a managed entity
in a Hibernate association (cardinality relationship)

NOTE: Examples are in EmployeeEntity3 and AccountEntity3

CascadeType.PERSIST
- save() or persist() operations cascade to related entities

CascadeType.MERGE
- related entities are merged when owning entity is merged

CascadeType.REFRESH
- related entities are refreshed when owning entity is refreshed.

CascadeType.REMOVE
- removes all related entities association w/ this setting when owning entity is deleted

CascadeType.DETACH
- detaches all related entities if a "manual detach" occurs

CascadeType.ALL
- does All Of The Above.

## ORPHAN REMOVAL
- Cascade Type that removes an owned object from the DB when it's removed from its owning
relationship. 