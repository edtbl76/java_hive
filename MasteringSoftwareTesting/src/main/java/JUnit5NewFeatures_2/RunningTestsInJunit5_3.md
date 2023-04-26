# Running Tests in JUnit 5

## Jupiter Tests (Maven) 

### pom.xml EXAMPLE

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.jupiter.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

- this includes the **junit-jupiter-api** as a dependency to our project.
- general best practices suggest to use the "latest" version of a library.

#### Build Plugins

    <build>
        <plugins>
            <plugin>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven-surefire-plugin.version}</version>
                <dependencies>
                    <dependency>
                        <groupId>org.junit.platform</groupId>
                        <artifactId>junit-platform-surefire-provider</artifactId>
                        <version>${junit.platform.version}</version>
                    </dependency>
                    <dependency>
                        <groupId>org.junit.jupiter</groupId>
                        <artifactId>junit-jupiter-engine</artifactId>
                        <version>${junit.jupiter.version}</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>

- plugin needs to establish 2 internal dependencies
    - TestLauncher  (**junit-platform-surefire-provider**)
    - TestEngine (**junit-jupiter-engine**)
    

#### Example Test

    
    class MyFirstJUnit5Test {
        
        @Test
        void myFirstTest() {
            String message = "1 + 1 should be equal to 2";
            System.out.println(message);
            assertEquals(2, 1 + 1, message);
        }
    }

![Executing Jupiter Tests with Maven](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ExecJupiterMaven.png)
    

---
## Jupiter Tests (Gradle)

#### build.gradle Dependencies
- Jupiter API (**junit-jupiter-api**)
- Test Engine (**junit-jupiter-engine**)
- Test Launcher (**junit-platform-gradle-plugin**)

  
    buildscript {
        repositories {
            mavenCentral()
        }
        dependencies {
            classpath("org.junit.platform:junit-platform-gradle-plugin:${junitPlatformVersion}")
        }
    }

    repositories {
        mavenCentral()
    }

    apply plugin: 'java'
    apply plugin: 'eclipse'
    apply plugin: 'idea'
    apply plugin: 'org.junit.platform.gradle.plugin'
    
    compileTestJava {
        sourceCompatibility = 1.8
        targetCompatibility = 1.8
        options.compilerArgs += 'parameters'
    }

    dependencies {
        testCompile("org.junit.jupiter:junit-jupiter-api:${junitJupiterVersion}")
        testRuntime("org.junit.jupiter:junit-jupiter-engine:${junitJupiterVersion}")
    }


![Executing Jupiter Tests in Gradle](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ExecJupiterGradle.png)

---
## Legacy Tests (Maven)

### Example

    public class LegacyJUnit4Test {
      
        @Test
        public void myFirstTest() {
            String message = "1 + 1 should be equal to 2";
            System.out.println(message);
            assertEquals(message, 2, 1 + 1);
        }
    }

### Pom.xml

#### JUnit 4 Dependency

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

#### Plugin Dependencies for maven-surefire-plugin
- Test Engine (**junit-vintage-engine**)
- Test Launcher (**junit-platform-surefire-provider**)


    <build>
        <plugins>
            <plugin>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven-surefire-plugin.version}</version>
                <dependencies>
                    <dependency>
                        <groupId>org.junit.platform</groupId>
                        <artifactId>junit-platform-surefire-provider</artifactId>
                        <version>${junit.platform.version}</version>
                    </dependency>
                    <dependency>
                        <groupId>org.junit.jupiter</groupId>
                        <artifactId>junit-vintage-engine</artifactId>
                        <version>${junit.vintage.version}</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>

### Execution
![Executing Vintage Tests w/ Maven](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ExecVintageMaven.png)

---
## Legacy Tests (Gradle)

#### build.gradle Dependencies
- Jupiter API (**junit-jupiter-api**)
- Test Engine (**junit-vintage-engine**)
- Test Launcher (**junit-platform-gradle-plugin**)


    buildscript {
        repositories {
            mavenCentral()
        }
        dependencies {
            classpath("org.junit.platform:junit-platform-gradle-plugin:${junitPlatformVersion}")
        }
    }

    repositories {
        mavenCentral()
    }

    apply plugin: 'java'
    apply plugin: 'eclipse'
    apply plugin: 'idea'
    apply plugin: 'org.junit.platform.gradle.plugin'
    
    compileTestJava {
        sourceCompatibility = 1.8
        targetCompatibility = 1.8
        options.compilerArgs += 'parameters'
    }

    dependencies {
        testCompile("junit:junit:${junitLegacy}")
        testRuntime("org.junit.vintage:junit-vintage-engine:${junitVintageVersion}")
    }

![Executing Vintage Tests w/ Gradle](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ExecVintageMaven.png)
---
## The ConsoleLauncher
- CLI Java app that allows JUnit Platform to be launched from 
the console
    - Vintage/Jupiter tests can be exec'd from the CLI
  

    EX:
      java -jar junit-platform-console-standalone-version.jar <OPTIONS>

---

## Jupiter Tests (JUnit4)
- Vintage component supports running legacy code on JUnit 3/4 
inside JUnit5
- JUnit5 also supports a JUnit4 test runner that allows JUnit5 
tests to be run in IDEs/Build systems that support JUnit 4
    - (but don't support JUnit 5 directly)
  

### Example Test

    
    @RunWith(JUnitPlatform.class) 
    public class JUnit5CompatibleTest {
        
        @Test
        void myTest() {
            String message = "1 + 1 should equal 2";
            System.out.println(message);
            assertEquals(2, 1 + 1, message);
        }
    }

#### Example pom.xml


     <dependencies>
          <dependency>
              <groupId>org.junit.jupiter</groupId>
              <artifactId>junit-jupiter-api</artifactId>
              <version>${junit.jupiter.version}</version>
              <scope>test</scope>
          </dependency>
          <dependency>
              <groupId>org.junit.jupiter</groupId>
              <artifactId>junit-jupiter-engine</artifactId>
              <version>${junit.jupiter.version}</version>
              <scope>test</scope>
          </dependency
          <dependency>
              <groupId>org.junit.platform</groupId>
              <artifactId>junit-platform-runner</artifactId>
              <version>${junit.platform.version}</version>
              <scope>test</scope>
         </dependency
     </dependencies> 
    
#### Example build.gradle

    buildscript {
        repositories {
            mavenCentral()
        }
        dependencies {
            classpath("org.junit.platform:junit-platform-gradle-plugin:${junitPlatformVersion}")
        }
    }

    repositories {
        mavenCentral()
    }

    apply plugin: 'java'
    apply plugin: 'eclipse'
    apply plugin: 'idea'
    apply plugin: 'org.junit.platform.gradle.plugin'
    
    compileTestJava {
        sourceCompatibility = 1.8
        targetCompatibility = 1.8
        options.compilerArgs += 'parameters'
    }

    dependencies {
        testCompile("org.junit.jupiter:junit-jupiter-api:${junitJupiterVersion}")
        testRuntime("org.junit.jupiter:junit-jupiter-engine:${junitJupiterVersion}")
        testCompile("org.junit.platform:junit-platform-runner:${junitPlatformVersion}")
    }
