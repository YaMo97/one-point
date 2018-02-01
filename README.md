# One Point Verificiation System for AICTE
# Repo: one-point

## Description


## Setting Up Development Environment

1. Step 1: Install Tools and Dependencies
    1. Install Eclipse IDE for Java EE Developers
    2. Install Oracle JDK 8 and JRE 
    3. Install and Configure Apache Tomcat 8.5 (https://tomcat.apache.org/download-80.cgi#8.5.27) (https://www.digitalocean.com/community/tutorials/how-to-install-apache-tomcat-8-on-ubuntu-16-04)
    4. Install and configure Git
  
2. Create a working directory and clone project into system

3. Import project into Eclipse

4. Add Java-Addons Library in Configure Build Path

5. Add Tomcat Server to Eclipse IDE
    1. Add Server from New Server -> Choose Apache Tomcat 8.5  (https://www.digitalocean.com/community/tutorials/how-to-install-apache-tomcat-8-on-ubuntu-16-04)
    2. Right-click -> Properties -> Switch Location (This will change [Workspace metadata] to something else) 
    3. Apply and Close
    4. Double-click on Server to open config.
    5. In the Server locations tab , select "Use Tomcat location". (If this option is greyed-out, delete the server and re-add a new server.
    6. Save the configurations and restart the Server.
    7. Test the server by creating a new Dynamic Web Project and adding a servlet.
    (Refer: https://stackoverflow.com/questions/16340711/tomcat-http-status-404 for assistance)

6. 


## Setting up Runtime Environment


### Common Problems and Solutions

###### No Client can be executed - Could not load or find main class

1. Make sure the project path does not have any spaces. If yes, either rename the folders or move to a different path without spaces.


###### Server not starting - ClassNotFoundException

1. Delete all Server not starting - ClassNotFoundException
40
referenced libraries from build path
2. Add all the java addons again to build path
3. Warning will be shown stating dependencies are not exported. Right-click -> Quick Fix and Select Mark associated dependency for export and click Finish. Repeat for all warnings/libraries.
4. Re-run server. Error will be gone.

