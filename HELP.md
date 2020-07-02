Trigram Problem [CodeKata](http://codekata.com/kata/kata14-tom-swift-under-the-milkwood/)
---
The texts consist of sentences and also sentences consist of words. Human beings can understand linguistic structures and their meanings easily, but machines are not successful enough on natural language comprehension yet. So, we try to teach some languages to machines like we do for an elementary school kid. This is the main concept; words are basic, meaningful elements with the ability to represent a different meaning when they are in a sentence. By this point, we keep in mind that sometimes word groups provide more benefits than only one word when explaining the meaning. 
Here is our sentence "I read a book about the history of America."
The machine wants to get the meaning of the sentence by separating it into small pieces. How should it do that? 
1. It can regard words one by one. This is unigram; each word is a gram.
"I", "read", "a", "book", "about", "the", "history", "of", "America"
2. It can regard words two at a time. This is bigram (digram); each two adjacent words create a bigram.
"I read", "read a", "a book", "book about", "about the", "the history", "history of", "of America"
3. It can regard words three at a time. This is trigram; each three adjacent words create a trigram.
"I read a", "read a book", "a book about", "book about the", "about the history", "the history of", "history of America"

Why would i need Trigram?
---
The idea is to make (statistical) predictions about what is happening in a sentence.
A trigram makes a prediction for the word based on the two words before that.


Solution and Coding
---
My implementation of first coding challenge has following classes and interfaces:

* MyTrigramApp.java
    
        This is the main entry point of application.

* InputDataReader.java
    
        This class is used to read data either from Input stream or File.

* KeyGenerator.java
    
        This class is used to create the key and store the key (two words) and following third word as value into hash map.

* Processor.java

        This class is used to compose trigram text from hash map which store each two words and followed third word. 

* StringJoinerUtils.java

        This is utility class used to join the words.

* StringConstant.java
    
        It contains the static values.

* InputDataException.java
    
        It is custom exception, used to catch while reading the input data either from file or stream.


### Pre-Requisite Software:
    Apache Maven
    JDK 8
    Java development IDE (IntelliJ or Eclipse)

### Reporting with Code Coverage, introduced 80% code coverage rule in POM.xml:
        Run below command to generate reporting:
        Eg: [workspace path]> mvn cobertura:cobertura
        You can find report inside target folder under jaococo-ut folder (index.html).
        
### Command to execute: 
    java -jar MyTrigramApp-<version>.jar <filename>\n

### Case handling
    DOS file ending: Replace \n with space to let KeyGenerator doesn't think a sentence has terminated. 
    Extra spaces: Trim while creating the keys.
    Punctuation: Punctuation symbols are coming as is.
    
### Future enhancement

    Generate random number of sentences upto given number, not all paragraph should have exactly end sentences.
    Way to kill infinite loop.
    Finding keys case  insensitive.
    Deal with inner sentence's punctuation.