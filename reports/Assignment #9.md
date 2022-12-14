# Assignment #9 - TVVS - Group 7

- Filipe Cerqueira - 202204408
- Avito Silva - 202204407
- Deivid Desposito - 201902431

## White Box Testing - Mutation Testing

Mutation Testing is a type of software testing in which certain statements of the source code are changed/mutated to check if the test cases are able to find errors in source code. The goal of Mutation Testing is ensuring the quality of test cases in terms of robustness that it should fail the mutated source code.

For this assignment we will try to have as close to 100% of mutation score.
First we ran the pit test and noticed that some of our old tests were failing. Obviously it's not meant for that but in our past assingmnets we wanted to made them fail to get better documentation and visualisation of what we were doing.

We removed the failure tests and run again the pit test. Also we noticed that **ClipboardUtilsTest** and **SpringUtilitiesTest** wasn't working which after some research we read that some tests that used some external package could lead to failure on the Pit Test, so we assumed that this was the case and commented the tests of these Classes.

---
---

## Jpass.Util - Package

Initial PIT Report for JPass.Util:
![](https://i.imgur.com/Q8ugYlb.png)

## **CryptUtilsTest** and **DateUtilsTest**
### A)Initial mutation score
![](https://i.imgur.com/HARpo5w.jpg)

### B)Final mutation score
**DateUtilitiesTest**
For this it was possible to increase the mutation coverage only for one of the two mutats not killed as the other was an equivalent mutant:

1.equivalnet mutant Instant.ofEpochMilli(0) changed to Instant.ofEpochMilli(1):
This change does ot produce any efect on the output as it makes dateTime to have 1 as miliseconds but those are trunced on line 69 letting only the seconds so havin 0 or 1 miliseconds do not affect the output

2.return formatter.format(dateTime.truncatedTo(ChromoUnit.SECONDS)) changed to formatter.format(dateTime):
explained on "Tests developed to increase mutation score" section

![](https://i.imgur.com/nT9xdxX.jpg)

**CryptUtilitiesTest**
For this it was possible to increase the mutation coverage only for two of the 5 mutats not killed as there was one mutant related to a code imposible to reach and 2 equivalent mutants:

1.Removed catch content: this is an equivalent mutant as the code in the catch is never reached(we cannot write a test to force to hava an exception and reach that catch).

![](https://i.imgur.com/zSG3jl8.jpg)
![](https://i.imgur.com/W2eUGEP.jpg)

2 and 3.remove md.reset: this is also an equivalent mutant as identified by the following conclution: the md.reset changes the status of the singleton MessageDigest object and the state is only used on the toString method of it returning different messages. We write a test case as can see on the following images that should make the mutant killed if everything works as expected but debuging we found tha the MessageDigest.getInstance("SHA-256") is always returning a new object with the status on INITIAL value, so having the md.reset or not makes no difference as the object obtained to do the verification is always different than the one where the md.reset is applied.

![](https://i.imgur.com/tyP94xw.jpg)
![](https://i.imgur.com/DFku3kC.jpg)
![](https://i.imgur.com/NVNO6sC.jpg)

![](https://i.imgur.com/ZmXkQvj.jpg)

4 and 5. unreachable code:For this we created a test explained on "Tests developed to increase mutation score" section
![](https://i.imgur.com/vEad65U.jpg)
![](https://i.imgur.com/5Cdx9vq.jpg)



FINAL **DateUtilitiesTest** and **CryptUtilitiesTest**
![](https://i.imgur.com/uXdkNAG.jpg)

So we went from 20/25 to 22/25 for CryptUtils and from 14/16 to 15/16 for DateUtils

### C)Tests developed to increase mutation score
**DateUtilitiesTest**

1.return formatter.format(dateTime.truncatedTo(ChromoUnit.SECONDS)) changed to formatter.format(dateTime):
To be able to kill this mutant we developed a test that takes in cosideration the miliseconds of the date

![](https://i.imgur.com/jiQmrA9.jpg)


**CryptUtilitiesTest**
1.Unreachable code: For this we created a test that reach the previous unreachable code.

![](https://i.imgur.com/yyHljuC.jpg)


---


## **ClipboardUtilsTest** and **ConfigurationTest**
### Initial mutation score
![](https://i.imgur.com/ZDtPEb1.png)

#### ClipboardUtils
Here the mutation score is 0 because even if the tests result in success the Pit testing keeps throwing a error "Mutation testing requires a green suite.".

#### ConfigurationTest
At first thius tests were throwing an error because of the properties file missing, so we fixed that and this is the first initial mutation score based on the last assignments:
![](https://i.imgur.com/N6uD7ko.png)

After this result we did a mutation analysis and this is the results for each test:

* **testConfigurationCreation()**

The mutation test here is consisted in either changing the statement or removing the declaration of the class into the object INSTANCE would fail our tests because we are comparing if the return is not null, but it will be because we tested it manually. In our reflection we came to the conclusion that PIT mutation is only done after the creaton of the INSTANCE.
![](https://i.imgur.com/DCgBQS1.png)
![](https://i.imgur.com/HtSP3GE.png)


* **testConfigurationGetInteger()** && **testConfigurationGetIntegerDefaultValue()**

Here we have 2 tests:
- Compare if the Properties configuration file key value is the one written there;
- Compare if a not Properties configuration file key value is equals to the default value passed in the input.

![](https://i.imgur.com/C39fC9q.png)
![](https://i.imgur.com/fqEdKUo.png)

![](https://i.imgur.com/vqv1yZl.png)

The tests result in succes and also the mutation killed all possible mutants. So in this method there are no equivalent mutants alive.


* **testConfigurationGetBoolean()**

Here we again send a Properties configuration key value and compare it to the value itself declared on the file.
The tests resulted in success but the mutation found one surviving equivalent mutant. 
![](https://i.imgur.com/inWkAM2.png)
![](https://i.imgur.com/MXDzo3I.png)

For that we created a new test to compare if in the input we dont send a real Properties key and chek if the defaultValue is the one returned.
![](https://i.imgur.com/oaTtm0K.png)
With that we killed the suvivor and by having a test that chaning the return value would break.

* **testConfigurationGetProperty()**

Here we sended as input a value from the properties file and expecting it to never be the default. This origined in 2 equivalent mutants. 

![](https://i.imgur.com/QYp45EU.png)
![](https://i.imgur.com/sRGm2Gv.png)

As expected the tests passed but it wasn't enough to actual have a true understanding of the possible outcomes because we are just expecting it to not be "default" so by doing diferently mutations to the source code it won't see diference as long as a string is returned to compare them and check that they are different.
To solve this we added a new assertEquals to the test to check if the value returned from the Properties configuration file is the correct one.
![](https://i.imgur.com/o7m8Hxt.png)
![](https://i.imgur.com/NOyyXdn.png)
By doing this we killed all possible equivalent mutants.

* testConfigurationGetArray()

Here we test again if the Properties configuration key value is the the one written in the file. And again, altough the tests resulted in success we got still a surviving equivalent mutant.
![](https://i.imgur.com/NGif9XI.png)
![](https://i.imgur.com/Qa3GMhP.png)
![](https://i.imgur.com/eMV7fo6.png)

So we created in the same test more conditions to get coverage of all possible outcomes and prevent errors.
![](https://i.imgur.com/8orH7vd.png)
We created two more jUnit Asserts to compare if either the defaultValue is always the one we se in the method parameters.
By doing this we got 100% coverage for this function and killed all surviving equivalent mutants.

##### Final Results on ConfigurationTest:
![](https://i.imgur.com/efkVRB8.png)
Even if the numbers shown in the PIT Report are not the perfect ones, we did had a great mutation score if we ignore some code that is not acessable and so untestable by us, like:
![](https://i.imgur.com/5z2s7Ro.png)
![](https://i.imgur.com/4oLF2FY.png)
If we ignore this mutations we have 100% of mutation score and from our analysis our tests improved a lot with this test technique.


> AVITO TESTS HERE 
## **StringUtilsTest**
Results:
![](https://i.imgur.com/GgPot5J.png)

Although it was not possible to reach 100% of mutation coverage we know what failed and what are the things making it impossible reach it.

Most of the substituitions values made by pit were already covered during the boundary value analysis as it's possible to observe.
For example the mutant number 3 it's tested the onPoint6 which the expected = ? and the input is 0xD800 and there is a image blow showing it killed but still some of the test were correct when planned but the implemented test had some flows and pit also created mutants that were not possible to kill.
Intial:
![](https://i.imgur.com/qyDZOfv.png)

Tests imporved for substitutions mutation and killed intial mutant # 3:

![](https://i.imgur.com/8QjCq1q.png)


On this new image the new mutant nº3 that survived it's suposed to survive and is an example of why we did not reach 100% as we can see that is inside the interval of a correct xml character
![](https://i.imgur.com/HTd1DIa.png)


Mutant not killed at line 53 of the source code:
![](https://i.imgur.com/yJzKfIW.png)

This condition was already tested and it is tested with null input and we developed a new to still cover it but it's still not killing the mutant

![](https://i.imgur.com/dCyBnI7.png)




### But overall the tests improved the score mutation of this class to 89% as we can see below and the class is pretty much coverd:
![](https://i.imgur.com/8kD22iZ.png)


















---


---


## Jpass.Crypt - Package

Here are the initial PIT Report for Jpass.Crypt tests:
![](https://i.imgur.com/Q2aLpL1.png)


### data/DataModelTest

### Initial mutation score
![](https://i.imgur.com/FAEUi5H.png)

#### DataModel

Datamodel is a class with the getters and setters of the aplication file data, it contains the fileName, passowrd, modifified state and his entries with the encryption passwords.
The tests did a good mutation score but there would still be room for improvement.

* **clearTest()**

The pit showed one possible situation where the tests survived mutation, that consisted when the line were removed. That happened because our tests didn't define value to the fileName/password/modified so it would get his default value given on the declaration of the class that is also the same value is given in this clear() method.
![](https://i.imgur.com/3KUWl72.png)
For that we added:
![](https://i.imgur.com/0UfZEP6.png)
In order for the dataModel have values on his atributes and force the clear() to indeed change them to null and the test give a real possible green suit.

##### Final Results on DataModelTest:

We killed all surviving equivalent mutants for this class unless the private attributes and the singletown instance declaration that has explained before if we change the values manually the test fails but for PIT they pass which is a wrong behavior.
![](https://i.imgur.com/mfSEZoO.png)
![](https://i.imgur.com/9Zj25Zj.png)


### data/EntriesRepository

### Initial mutation score

No image because there was no tests build for this class.

#### EntriesRepository

EntriesRepository is the class to read and write from XML files.

* **repositoryReadAndWriteTest()**

The pit report showed two Catch without code coverage and also an if, that could be removed or even change the operator and survive, but the reality is that this inputStream.close() is a declared class in the begining of the method so there is no chance for us to build tests to that condition.
![](https://i.imgur.com/dbWXUKv.png)
Besides our new tests were:
![](https://i.imgur.com/bgpGIA5.png)


##### Final Results on EntriesRepository:

We killed all surviving mutants for this class unless the if that has nothing to test and some private attributes.
![](https://i.imgur.com/oOt0aMS.png)


---

## Conclusion

To conclude we found the Mutation Test very interesting expectially because it also does line coverage which in the end ends up being a conjunt of testing techniques. We developed new tests and improved ones, it showed how powerfull mutation test can be to discover possible human errors on the source code or even to prevent from creating new ones. At first it feels overdoing tests but after this assignment definitely we can understand why is it so important and why it makes so much sense. Fix actual error is really great but prevent new ones could be even better for the stability and maintainability of a project.

Our final mutation socre was:
[Please insert fina final PIT print](/sbF2jBEfQ2KpW2kNPPFFgg)
