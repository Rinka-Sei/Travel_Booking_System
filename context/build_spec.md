# Build & Compilation Specification (`build_spec.md`)

This document dictates the strict compilation parameters and dependency management rules for any LLM or AI Agent generating the Maven `pom.xml` file for any project item.

---

## 1. Java Version Constraints

- **Compiler Source/Target:** Java 21.
- **Maven Compiler Plugin:** Must explicitly enforce version 21 in `pom.xml`.
- **Encoding:** UTF-8.

## 2. Dependency Constraints

- **Rule:** Zero external libraries.
- **Allowed Runtime Dependencies:** ONLY `com.mysql:mysql-connector-j` (version 8.3.0 or higher).
- **Allowed Test Dependencies:** ONLY `org.junit.jupiter:junit-jupiter` (version 5.10.0 or higher) for unit tests. Do NOT include Mockito, AssertJ, or other testing libraries.

## 3. Required `pom.xml` Template

Any Agent or LLM must generate a `pom.xml` adhering to this exact baseline structure:

    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>xyz.groupN</groupId>
        <artifactId>{preferred project name}</artifactId>
        <version>1.0-SNAPSHOT</version>
        <packaging>jar</packaging>

        <properties>
            <maven.compiler.source>21</maven.compiler.source>
            <maven.compiler.target>21</maven.compiler.target>
            <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        </properties>

        <dependencies>
            <!-- MySQL JDBC Connector (Only allowed external library) -->
            <dependency>
                <groupId>com.mysql</groupId>
                <artifactId>mysql-connector-j</artifactId>
                <version>8.3.0</version>
            </dependency>

            <!-- JUnit 5 (Only allowed testing library) -->
            <dependency>
                <groupId>org.junit.jupiter</groupId>
                <artifactId>junit-jupiter</artifactId>
                <version>5.10.0</version>
                <scope>test</scope>
            </dependency>
        </dependencies>

        <build>
            <plugins>
                <!-- Compiler Plugin -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.11.0</version>
                    <configuration>
                        <source>21</source>
                        <target>21</target>
                    </configuration>
                </plugin>
                <!-- Surefire Plugin for JUnit 5 -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>3.1.2</version>
                </plugin>
                <!-- Exec Plugin for launching the app -->
                <plugin>
                    <groupId>org.codehaus.mojo</groupId>
                    <artifactId>exec-maven-plugin</artifactId>
                    <version>3.1.0</version>
                    <configuration>
                        <mainClass>Main</mainClass>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </project>