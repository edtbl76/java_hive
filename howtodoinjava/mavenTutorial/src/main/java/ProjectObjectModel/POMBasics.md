# Super POM
- POM files maintain a parent-child relationship. 
- any POM file you create for your project will finally be extending the super pom file content. 

# Minimal POM Config

    <project>
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.example</groupId>
        <artifactId>MavenExample</artifactId>
        <version>1.0.0</version>
    </project>
    
- project root
- model Version (should be set to 4.0.0)
- group Id
- artifactId
- version

## Effective POM file
- this is the result of merging child pom files, their parent pom files, and then the super pom file. 
- this can be displayed on the CLI 

    mvn help:effective-pom