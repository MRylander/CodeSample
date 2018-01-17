# Code Sample 

## To run all tests and compile the jar 

    # Create Executable Jar
    gradlew check jar
    
## To run command line app    
    
The command line app is run by executing the jar with the `local` mode option, followed by the desired output format, and a list of file paths that correspond to the data input files. 

    java -jar build/libs/CodeSample_GR-0.1-SNAPSHOT.jar [modeOption] [outputFormat] [inputFilePaths]...

Valid output formats:
* `option1` - sorted by gender (females before males) then by last name ascending.
* `option2` - sorted by birth date, ascending.
* `option3` – sorted by last name, descending.

Example: This example would parse the contents of 'testData-pipes1.txt' located in the testData directory of the project repo and then output to the terminal the records sorted via option2.

    # From the root of the project repo
    # Compile Executable Jar
    gradlew jar
    
    # Run the app
    java -jar build/libs/CodeSample_GR-0.1-SNAPSHOT.jar local option2 testData/testData-pipes1.txt
    
## To run the REST app   

The REST app is run by executing the jar with the `server` mode option

    java -jar build/libs/CodeSample_GR-0.1-SNAPSHOT.jar [modeOption] 
    
Example:

    # From the root of the project repo
    # Compile Executable Jar
    gradlew jar
    
    # Run the app
    java -jar build/libs/CodeSample_GR-0.1-SNAPSHOT.jar server
    
    # You should then be able to access the REST endpoints at localhost:8080
    
### REST Endpoints

* POST /records - Post a single data line in any of the 3 formats supported by your existing code
* GET /records/gender - returns records sorted by gender
* GET /records/birthdate - returns records sorted by birthdate
* GET /records/name - returns records sorted by name

## Assumptions
* Java 8 is setup and available.
* Code will be evaluated by an experience software engineer on linux or mac. 
* The natural ordering for gender is alphabetic (females before males).
* LastName, FirstName, Gender, and FavoriteColor can be any alpha numeric string that does not contain whitespace.
* Data sets will not be so large that pagination would be required.
* The output of the command line app should be to standard out
* The input files will have one record per line.
* The input records are valid and well formatted.
* The order of the fields on the records will always be: LastName, FirstName, Gender, FavoriteColor, DateOfBirth 
* DateOfBirth is in the form M/D/YYYY within the input records
* "Sorted by name" means that the records with be sorted lexicographically by lastName and then sorted by firstName. 
