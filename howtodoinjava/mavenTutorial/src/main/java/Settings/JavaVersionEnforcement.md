# Java Versions
- enforced in pom.xml


    <build>
        <plugins>
            <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-enforcer-plugin</artifactId>
            <version>3.0.0-M2</version>
            <executions>
                <execution>
                <id>enforce-versions</id>
                <goals>
                    <goal>enforce</goal>
                </goals>
                <configuration>
                    <rules>
                        <requireMavenVersion>
                            <version>2.0.6</version>
                        </requireMavenVersion>
                        <requireJavaVersion>
                            <version>1.5</version>
                        </requireJavaVersion>
                        <requireOs>
                            <family>unix</family>
                        </requireOs>
                    </rules>
                </configuration>
                </execution>
            </executions>
            </plugin>
        </plugins>
    </build>
