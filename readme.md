# Spring-boot-palindrome

It is a Spring boot application to check whether a word is a palindrome.
It provides several endpoints: 
   checkIfPalindrome: 	POST http://localhost:9305/spring-boot-palindrome/api/palindromes
   getAllPalindrome: 	GET http://localhost:9305/spring-boot-palindrome/api/palindromes
   getPalindromeById: 	GET http://localhost:9305/spring-boot-palindrome/api/palindromes/<palindromeId>
   deleteAllPalindrome: DELETE http://localhost:9305/spring-boot-palindrome/api/palindromes
   deletePalindromeById:DELETE http://localhost:9305/spring-boot-palindrome/api/palindromes/<palindromeId>
   

## Getting Started

Clone code: 
```
git clone https://github.com/mbolori/spring-boot-palindrome.git
``` 

### Prerequisites

You need maven installed on local.
In order to build the project: 
```
mvn clean package
```

### Running program

In order to run jar:
```
java -jar spring-boot-palindrome-1.0.0-SNAPSHOT.jar --spring.config.location=/<path-to-configuration/application.yml  
```

## Configuration

It does not need an external configuration file. Inner configuration file should fit most of the usages: Use od OPTIMUM strategy.

## Authors

* **Manuel Baeta** - *Initial work* 

