# Assignment #5 - TVVS

- Filipe Cerqueira - 202204408
- Avito Silva - 202204407
- Deivid Desposito - 201902431

## White Box Testing - Structural Testing

For this assignment we started by configuring and runing the JaCoCo library. After that we started doing some code analysis to understand what parts were not tested.

Jacoco has pre-unit test that sets up the necessary configurations for analysing the tests execution and a post-unit-test that writes the results of the analysis into an HTML report in the target/site/Jacoco directory.
In the end of the analysis, an exception was added to the plugin so that jpass.ui and its subpackages are excluded from the analysis.

## Initial Branch and Line coverage

With the tests developed in Assignment 2 and Assignment 3, the branch coverage of the all the packages was 16%, and 34% of line coverage.

So to understand the process of this work we will explain the tests we had till this assignment and the ones we will produce now that we are on white-box testing.

![](https://i.imgur.com/b2PsrEW.png)
![](https://i.imgur.com/75qJkiw.png)
![](https://i.imgur.com/JZpZkb8.png)
![](https://i.imgur.com/EPrymxV.png)
![](https://i.imgur.com/QAwLwHj.png)
![](https://i.imgur.com/JCkZsZS.png)


In the first results we had really small coverage of the project. Some of the classes had already a good coverage but even those had some parts without tests. With this, we started the structural coverage doing new and correcting old tests.

---
## Structural Tests

### jpass.util.Configuration
The configuration module uses a property files that works as a map of par key-values, being the value of different types (integer,boolean,String and array).
There were already one test for this class, but some were added to increase coverage.

- **testConfigurationCreation()**
  - **mehtods covered:** getInstance()

This function assures that when a configuration is built is instance will not be a null object.

- **testConfigurationGetBoolean()**
  - **mehtods covered:** is()

In this test is provided a key and a value (true) and the test ensures that if the property exits another value is returned else it is return the default value.

- **testConfigurationGetInteger()**
  - **mehtods covered:** getInteger()

In this test is providaded a key "default.password.generation.length" that will generate a password default value size. With that value and knowing (white-box) it's value we compare it.

- **testConfigurationGetProperty()**
  - **mehtods covered:**

In this method it's used another default key "clear.clipboard.on.exit.enabled" and compare it to a default string string.

- **testConfigurationGetArray()**
  - **mehtods covered:** getArray()

This method is provided a key and a default and the program will return the value ("entry.details", null) for that key if the property exist the value is returned if not the default value is returned.

- **Description of the JUnit features:**  
  Test anotation was used in all the tests generated for this class.

- **Coverage:**  
  ![](https://i.imgur.com/gg7zIxF.png)

---
### jpass.util.SpringUtilities
There were already one test for this class, but some were added to increase coverage.
It is important to specifty that there where part of the codes that were not possible to reach like in the image bellow.
![](https://i.imgur.com/9hfcXpS.png)


- **shouldNotWorkWithEmptyContainer()**
  - **mehtods covered:** makeGrid()

This test covers the prevention of building of an empty grid that already covered a lot of instructions. So when an empty grid is tried to build an exception is rased and this expects and catches this exception.

- **shouldWorkWithAllPositiveContainer()**
  - **mehtods covered:** makeGrid()

This test coverd the building of a non empty container grid with all positive values, two grids were build to cover some conditions, because this test actually builds a grid it passes true many instructions and branches so it coverd most of the missing instructions and branches.

- **shouldWorkWithEmptyCompactGrid()**
  - **methods coverd:** makeCompactGrid()

In this test it was build a empty compact grid with no elements inside it.
It was created a panel, a layout and finally a the empty compact grid. since it is suposed to be able to create a empty compact grid this function test the creating of the empty grid.

- **shouldWorkWithNonEmptyCompactGrid()**
  - **methods coverd:** makeCompactGrid()

This test creates a grid with a row and a column but the rest of the parameters are zeros so basically it is possiblwe to create a compact grid without X and Y values and pads. So it expected to not work with a ar

- **shouldWorkWithAllPositiveCompactGrid()**
  - **methods coverd:** makeCompactGrid()

In this test it was build a full grid with all the atributes and and element inside it (button). By creating this full compact grid it was possible to cover the instructions necessary to build it e.g setWidht(), setHeight() and also use springLayouts to contraint the X and Y axis.


- **Description of the JUnit features:**

Test used on this class contained a simple @Test annotation from org.junit.jupiter.api.

- **Coverage:**  
  ![](https://i.imgur.com/4fHkwDf.png)

---
### jpass.util.CryptUtils
All the tests for this class were already made in the last assignments.

- **testGetSha256Hash()**
  - **mehtods covered:** getSha256Hash(), newRandomNumberGenerator()

This method aligns the first rows * cols components of parent in a grid. Each component is as big as the maximum preferred width and height of the components. The parent is made just big enough to fit them all. With our initial test we had arround 70% of the instructions covered. Important to notice that some of them were unreachable because it was inside of a catch that would never happen because the logic of the code prevented that to happen. Even tho we didn't got 100% of the line and instructions covered from our prespective to complain to the last analysis made in the last assignments we ended up not changing or creating new tests.

- **Description of the JUnit features:**

It was used **ParameterizedTest** that allows to execute this single test multiple times with different parameters.

- **Coverage:**  
  ![](https://i.imgur.com/rxCtQs5.png)

---
### jpass.util.ClipboardUtils
There were already one test for this class, but some were added to increase coverage.

- **testSetClipboardContent()**
  - **mehtods covered:** setClipboardContent()
    This test covers the built of a clipboard to build this the string needs to have more than 1 character so it is able to store in a location of the device temporarly to be copied later. It was tested empty and null strings, and strings with one and more charcters.


- **Description of the JUnit features:**

**ParameterizedTest** allows to execute this single test multiple times  with different parameters

- **Coverage:**  
  ![](https://i.imgur.com/27fABok.png)


---

---
### jpass.util.StringUtils
This class all already tested during the category partitioning and boundary value analsys so most of the instructions were already coverd.


- **testStripNonValidXmlCharacters()**
  - **mehtods covered:** stripNonValidXmlCharacters()

In this function it was basically coverd the construction of a string with the boundaires of all the valid characters but also the test to cover a string with an invalid character and the invalid character is suposed to be converted to an question mark '?'.
In this functions there is a missing 3 of 18 branches!.
![](https://i.imgur.com/4QsY1ug.png)



- **testStripString()**
  - **mehtods covered:** stripString()

This test was also tested in the boundary value analysis and category partitioning. Basically this test covered the buit of a string with less than 80 characters which the output should be this exact string and the input of a string with more than 80 characters which returned the first 80 characters + '...'.
- **testStripStringNullInput()**
  - **mehtods covered:** stripString()

This functions assures that when an empty string is inserted an exeption is generating because it makes no sense try to strip a null string.

- **Description of the JUnit features:**

**ParameterizedTest** allowed to execute a singe test multiple times wich is very hany to test this function since the there were boundaries between the valid and invalid characters that should be covered.


![](https://i.imgur.com/0fpejwf.png)

---
### jpass.util.DateUtils
There were already one test for this class, but some were added to increase coverage.

- **testCreateFormatter()**
  - **mehtods covered:** DateTimeFormatter(), formatIsoDateTime()

The test made for this package were to send a first input of data formated value/null/empty and a second input of a ISO time type and fill it with the current system data.
The tests covered all methods but didnt cover all of the lines of the "formatIsoDateTime" which had a try catch inside a catch, the first tests made only covered the first try, it never entered the catch.

- **testFormatIsoDateTimeOne()**
  - **mehtods covered:** formatIsoDateTime()

On this test we covered the second "try", the goal was sending date in milliseconds expect the method to return it in the format we also send. Then we compared it to the real date that we discovered using a online tool milliseconds to date.
During the tests we found out that the formatter has a problem with converting milliseconds that represent dates above a certain time because somehow the formatter doesn't handle well dates in in milliseconds with char length above then 10 charachters.

- **testFormatIsoDateTimeTwo()**
  - **mehtods covered:** formatIsoDateTime()

On this test it was covered the last "catch" of the method formateIsoDateTime(). The goal was send a correct date format type and send completely wrong data. Since this is white-box testing we noticed that when it's send input that not represents either correct date format or date in milliseconds the method returns a static date time that is "1969/01/01", with that we compared this value to the one that the method returns and the tests passed sucessfully. We did this, to cover all the lines of the method but also to confirm the logic of the original creator, of course in our reflection this return doesn't make much sense.

- **Description of the JUnit features:**

The JUnit features used in this class tests were **ParameterizedTest**, that can allow the developer send multiple parameters that will run the method from the start for each one. On the first test it was made on black-boxing so we couldn't understand what code coverage it was doing, but the second and third test since we had access to the code we chose the parameters with the implementation in mind and **assertEquals** just to compare two values.

- **Coverage**
  *Note: The only line that it wasn't covered was "public class DateUtils {", Jacolo for some reason consider that the declaration of the class wasn't readed.*
  ![](https://i.imgur.com/1orhL6j.png)


---
### jpass.data.DataModel

For this class all the tests were made for this assigment.
This class represents the model of the data used for the file logic save state and entities.



| TestName | MethodsCovered | Description |
| -------- | -------- | -------- |
| getInstanceTest() | getInstance() | Compares via assertEquals if a empty ArrayList of type <Entry> is the same as the Instance returned of the method. |
| entriesTest() | setEntries(),getEntries() | Sets an entrie and gets it, then compares them. |
| filenameTest() | setFileName(),getFileName() | A filename is passed as an argument and then it is verified if the filename is the one given. |
| modifiedFlagTest() | setModified(), isModified() | True is passed as an argument and then it is verified if the flag has the right value. |
| passwordTest() | setPassword(), getPassword() | A password is passed as an argument and then it is verified if the password is the one given. |
| clearTest() | clear() | Clears all the values from the model. The test is a verification if all the fields are empty. |
| entryByTitleTest() | getEntryByTitle() | The methods sets the entries list with one entry inside and then tries to get it using its title. |
| entryIndexByTitleTest() | getEntryIndexByTitle() | Sets an entry and then gets the index 0 of the entries list and compares it. |
| entryByTitleNullTest() | getEntryByTitle() | The method is called passing a non existing title and then it is verified if null is returned. |


- **Description of the JUnit features:**

The JUnit features used on the tests of this class were **Before** to get the Instance of the DataModel and atribute it to a DataModel variable on the class test. **AssertEquals** to compare two  values. **AssertTrue** to compare if a value is true. **AssertNull** to compare if a value is null.

- **Coverage:**  
  ![](https://i.imgur.com/AzrKXF0.png)


---
### jpass.crypt.Aes256

For this class all the tests were create from the originald developer and had some changes to comply to more code covarage.

- **shouldEntryptAndDecryptATestMessage()**
  - **mehtods covered:** encrypt(), decrypted()

Encrypts one block. The input block lies in inBlock starting at the position inIndex. The inBlock won't be modified by this method. The encrypted block will be stored in outBlock starting at position outIndex. The way this were made was to compare two arrays and then see if the return was true.

- **shouldEntryptAndDecryptRandomData()**
  - **mehtods covered:** encrypt(), decrypted()

This test the encryption and decryption with random data. The way this were made was to compare two arrays and then see if the return was true.

- **Description of the JUnit features:**

**AssertTrue** was the feature of the JUnit used on this class test to read if the array comparasion was true.

- **Coverage:**  
  ![](https://i.imgur.com/QxaHEZ5.png)

---

## Final Branch and Line coverage

With the new tests developed in this Assignment, the branch coverage of the all the packages (exlcuding ui) is ~77%, and 89% of line coverage.

![](https://i.imgur.com/6GDafgs.png)

Besides all the unreachable if statements and catch we covered most of the JPass projects methods. Althought during configuration test we noticed that for example:
![](https://i.imgur.com/QWcbdFL.png)
Here it expects us to cover 4 branches in this if statement, but from what we learned on classes, in branch coverage outcomes of the if should be true or false and, even tho we didn't cover the two outcomes when writing this report we noticed that. So in Jacoco logic we have 25% of branch coverage but we should have 50%. From our prespective the only explanation for this woud be that the Jacoco analysis when exists **"&&"** conditions in if statements it basically "creates" a if for each condition inside the if, which could eventually lead to a four branch analysis.  
Althought this is not a big deal, doing this assignment we ended up noticing some weird aspects of the coverage library. Like this:
![](https://i.imgur.com/JJzvujb.png)
We have all the method and lines of the class tested but for some reason he complains about the declaration of the class DateUtils.

Also it's important to notice that we tried to do carefull analysis of this new tests but as the title of the assigment describes, we ended up focusing more on coverage then the analysis.

So in the end we ended testing most of the main classes and methods of the JPass. We didn't ended up with more then 90% but we were pretty close, fair to notice that at first we were expecting to only cover the initial tests of the assignment 2 and 3, which lead to a misunderstood of the project and delay the development of itself.






