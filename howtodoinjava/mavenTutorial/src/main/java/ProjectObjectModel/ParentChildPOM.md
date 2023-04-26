# PARENT POM 
RULES
- there is a pom file in project's root directory OR in a given relative path
- the reference from child POM contains the same coordinates as stated in the parent POM file. 

## PARENT EXAMPLE

    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd;
        <modelVersion>4.0.0</modelVersion>
     
        <groupId>com.howtodoinjava.demo</groupId>
        <artifactId>MavenExamples</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <packaging>pom</packaging>
     
        <name>MavenExamples Parent</name>
        <url>http://maven.apache.org</url>
     
        <properties>
            <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
            <junit.version>3.8.1</junit.version>
            <spring.version>4.3.5.RELEASE</spring.version>
        </properties>
     
        <dependencies>
         
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
                <scope>test</scope>
            </dependency>
             
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
                <version>${spring.version}</version>
            </dependency>
             
        </dependencies>
    </project>
    
    
## CHILD EXAMPLE

    <project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
     
        <!--The identifier of the parent POM-->
        <parent>
            <groupId>com.howtodoinjava.demo</groupId>
            <artifactId>MavenExamples</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </parent>
     
        <modelVersion>4.0.0</modelVersion>
        <artifactId>MavenExamples</artifactId>
        <name>MavenExamples Child POM</name>
        <packaging>jar</packaging>
     
        <dependencies>       
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-security</artifactId>
                <version>${spring.version}</version>
            </dependency>
        </dependencies>
     
    </project>