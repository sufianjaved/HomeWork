# HomeWork

**API Testing Framework with Cucumber**

This Framework is designed for API Testing of Webservices using Java with RestAssured and Serenity utilizing Maven as dependency management tool. 

**Technology Stack**

* Java
* Serenity
* Maven
* RestAssured

**Prerequisites**

* Java 1.8 - Java Dev Kit
* Maven - Dependency Manager
* Any IDE

**Project Structure**

constant: This package contains Constant Class

**Installation**

Open the project in an IDEA of your choice. Run the following command in Terminal and build the project. It will automatically download all the required dependencies.

`$ mvn clean install`

If the build is successful. All the required dependencies are installed successfully. But if the build fails, make sure to to resolve all the issues in order to execute tests successfully. Make sure that config.properties path in Property Reader class is set according to your Operating System Environment.

**Execute Tests**

Run the following command in Terminal to execute tests.

`$ mvn clean verify`

**Test Report**

You can find the Serenity HTML reports in the following directory of the Project.
*  \target\site\serenity\index.html
* \target\site\serenity\serenity-summary.html

Under the site-reports directory, open ‘index.html’ and serenity-summary.html file to view reports.
