1 docker compose
2 mvn quarkus:dev -Dquarkus.profile=database

This is important because the db calls will be cached by Flyway, and they
won't be persisted to psql unless you use the database profile.

With the db profile, you can create data sources in IJ or log in to the psql node
and see the changes.
