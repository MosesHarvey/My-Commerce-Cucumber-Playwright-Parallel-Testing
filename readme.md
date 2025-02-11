1. Run following command to execute parallel testing
   1)mvn test -DthreadCount=6 -X
   2) Add following to pom.xml file 

                 <configuration>
                    <parallel>methods</parallel>
                    <threadCount>4</threadCount>
                    <useUnlimitedThreads>true</useUnlimitedThreads>
                    <includes>
                        <include>**/*Test.java</include>
                    </includes>
                </configuration>
   3) add junit-platform.properties file under resources folder with following setup:
   # Enable parallel execution
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=concurrent
junit.jupiter.execution.parallel.mode.classes.default=concurrent

# Configure thread count
junit.jupiter.execution.parallel.config.strategy=dynamic
junt.jupiter.execution.parallel.config.dynamic.factor=2

4) Test Runner setup with 

