README 
Anu Vijayamohan
CISCO Technical Test
23 Aug 2014 

To Run
---------------------------------------
Unzip the file to a chosen directory
Open command prompt, and cd to the chosen directory.  Run the following command
java -cp target/vijayamohan-technical-test-1.0-SNAPSHOT.jar com.cisco.anutest.SaturdayServer

The application listens on port 4444.

Once connected, a list of supported commands can be shown by typing "help"

Supported commands are
help: show supported commands
pwd: show working path
mkdir: create folder
rmdir: delete folder				
ls: list directory contents
cd: change directory
close: close connection


Requirement - Technical Test
----------------------------------------
The candidate will have to write a simple Java server application that will support "telnet"-like connections. There is no need to implement the client side as well since the classic "telnet" application will be used for testing the server. 

The server must support multiple concurrent connections and it will have to respond to very basic commands like "ls", "cd", "mkdir", "pwd". It also must be portable across platforms (build time and run time). 

The use of the classes that invoke native commands (e.g. Runtime or ProcessBuilder) is not allowed. 

The candidate must also pay attention to code formatting, commenting and unit testing. 

The deliverable will be a project that can be built with Maven 2.


Please Note
----------------------------------------
This application was written using JAVA SE 8 (JDK 1.8.0)
The package was built using Maven 2.2.1