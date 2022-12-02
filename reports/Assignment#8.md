# Assignment #8 - TVVS

- Filipe Cerqueira - 202204408
- Avito Silva - 202204407
- Deivid Desposito - 201902431

## White Box Testing - Dataflow Testing

## Functions Selected

## stripString(Str, int length)

**1)stripString (Str, int length): StringUtils.java**
Selected for its simplicity to start performing Dataflow testing.

### Functions Purpouse
**1)stripString (Str, int length): StringUtils.java**
The purpouse of this function is to make large text smaller. To do it, if the text has more than x characters ,where x is the provided length, the program returns a string with the first x characters and three dots at the end.

### Dataflow Testing
**1)stripString (Str, int length): StringUtils.java**
*1.1)Code*
![](https://i.imgur.com/6gmpG3U.jpg)

*1.2)Code Adapted*
![](https://i.imgur.com/K3rWFJF.jpg)

*1.3)Data Flow Control Graph*
![](https://i.imgur.com/P69PQDr.jpg)

*1.4)Tables for each variable*
![](https://i.imgur.com/mR4TM9g.jpg)
![](https://i.imgur.com/Jqz885O.jpg)
![](https://i.imgur.com/PAhvQhT.jpg)

*1.5)Paths*
A) Variable Text:
All-Def:
![](https://i.imgur.com/YKht1Pj.jpg)

All-Cuses:
![](https://i.imgur.com/DbtEzW4.jpg)

All-Puses:
![](https://i.imgur.com/rEOrSqE.jpg)

All-Uses:
![](https://i.imgur.com/8kRUVGE.jpg)


B) Variable Length:
All-Def:
![](https://i.imgur.com/1eLAfu2.jpg)

All-Cuses:
![](https://i.imgur.com/ieZ2L8L.jpg)

All-Puses:
![](https://i.imgur.com/UXvEJgi.jpg)

All-Uses:
![](https://i.imgur.com/Lszb2uJ.jpg)


C) Variable Result:
All-Def:
![](https://i.imgur.com/o3MRum3.jpg)

All-Cuses:
![](https://i.imgur.com/O5AySl9.jpg)

All-Puses:
![](https://i.imgur.com/vNIJT4h.jpg)

All-Uses:
![](https://i.imgur.com/iHrFnPw.jpg)



### Unit Tests Generated

**1)stripString (Str, int length): StringUtils.java**
As all-uses includes def-uses , c-uses and p-uses, we developed the tests for all uses and that covered the others.

For the variable TEXT the steps 1 and 2 are covered on the paths of all the cases, is imposible not to start on those 2 steps, which remove test with id 1.
Also path from test 2 and 5 have the same steps so we removed test with id 2 and end up with 4 test:
id 3<1,2,3,4>, 4<1,2,3,8>, 5<1,2,3,4,5> and 6<1,2,3,4,8>.
but id 5 includes id 3 steps so we end with 3 tests:
id 4<1,2,3,8>, 5<1,2,3,4,5> and 6<1,2,3,4,8>.

For the variable LENGTH we have 3 tests but path for test 1 and 2 is the same so we ened with 2 test:
id 2<1,2,3,4,5> and 3<1,2,3,4,8>.
Which are the same as test with id 5 and 6 from variable TEXT so we removed the 2 test from variable LENGTH

For the variable RESULT we have 2 test:
id 1<2,3,8> and 3<5,8>.
but id 1 is included on test 4 from variable TEXT so we removed it.

To sumarize we end up with 4 test cases:
a)<1,2,3,8> (obtained from TEXT var, id 4)
b)<1,2,3,4,5> (obtained from TEXT var, id 5)
c)<1,2,3,4,8> (obtained from TEXT var, id 6)
d)<5,8> (obtained from RESULT var, id 3)

From that list we can see that b and d cover the same paths as after step 5 the only possible step executed is 8 and reaching step 5 is only possible after 1,2,3,4 so they force to follow the path <1,2,3,4,5,8>
Leting us with 3 tests:
a)<1,2,3,8>
b+d)<1,2,3,4,5,8>
c)<1,2,3,4,8>

And for those we created the following tests:
a)nullTextToTrim:
For this we used the method testDataFlowStripStringProvidingLengthNullText providing a null text and we expected to receive a null string.

b+d)notNullTextWithLengthHigherThanTheProvided:
For this we used the method testDataFlowStripStringProvidingLengthNotNullText providing a text with more than 80 characters and the length 80 and we expected to receive the first 80 characters followed by "..."

c)notNullTextWithLengthLowerThanTheProvided:
For this we used the method testDataFlowStripStringProvidingLengthNotNullText providing a text with less than 80 characters and we expected to receive the same string

![](https://i.imgur.com/hxK4XQS.jpg)

On the past we have similar tests but they used the stripString(String text) function, for this data flow assignment we have to use the stripString function that also receives the length as parameter.

### Test Outcome
**1)stripString (Str, int length): StringUtils.java**
The result of the test was what we expected, all of them passed.
![](https://i.imgur.com/XxhZPXZ.jpg)


---
---

## getSha256Hash(char[] text, int iteration): CrypUtils.java

### Function purpouse
This method calculates a SHA256 hash of the sended input. A number of iterations for the process can be defined and it will influence on the number of times that the text is digested. Digest means a secure way that take arbitrary-sized data and output a fixed-length of hash value.

### Flowchart
![](https://i.imgur.com/QEVRFBl.png)



---


### Table Summary

### Variable: **text**
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| 1       | 1   | 4   | <1,2,3,4> |

### Variable: **iteration**
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| 1       | 1   | (7,T)   | <1,2,3,4,5,6,7,8> |
| 2       | 1   | (7,F)   | <1,2,3,4,5,6,7,11> |

### Variable: **md**
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| 1       | 2   | 3   | <2,3> |
| 2       | 2   | 5   | <2,3,4,5> |
| 3       | 2   | 8   | <2,3,4,5,6,7,8> |
| 4       | 2   | 9   | <2,3,4,5,6,7,8,9> |

### Variable: **bytes**
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| 1       | 4   | 5   | <4,5> |

### Variable: **digest**
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| 1       | 5   | 9   | <5,6,7,8,9> |
| 2       | 5   | 11   | <5,6,7,8,9,10,11> |
| 3       | 5   | 11   | <5,6,7,11> |
| 4       | 9   | 9   | <9,10,7,8,9> |
| 5       | 9   | 9   | <9,9> |
| 6       | 9   | 11   | <9,10,7,11> |


### Variable: **i**
| pair id | def | use   | path          |
| ------- | --- | ----- | ------------- |
| 1       | 6   | (7,F) | <6,7,11>      |
| 2       | 6   | (7,T) | <6,7,8>       |
| 3       | 6   | 10    | <6,7,8,9,10>  |
| 4       | 10  | (7,F) | <10,7,11>     |
| 5       | 10  | (7,T) | <10,7,8>      |
| 6       | 10  | 10    | <10,10>       |
| 7       | 10  | 10    | <10,7,8,9,10> |

---

### All Paths

### Variable: **text**

#### All Defs

| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**       | **1**  |**4** | **<1,2,3,4>** |

#### All C-uses

| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**       | **1**   | **4**   | **<1,2,3,4>** |

#### All P-uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| 1       | 1   | 4   | <1,2,3,4> |

#### All Uses

| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**   | **1**   | **4**   | **<1,2,3,4>** |

---

### Variable: **iteration**

#### All Defs
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**       | **1**   | **(7,T)**   | **<1,2,3,4,5,6,7,8>** |
| 2       | 1   | (7,F)   | <1,2,3,4,5,6,7,11> |

#### All C-uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| 1       | 1   | (7,T)   | <1,2,3,4,5,6,7,8> |
| 2       | 1   | (7,F)   | <1,2,3,4,5,6,7,11> |


#### All P-uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**       | **1**   | **(7,T)**   | **<1,2,3,4,5,6,7,8>** |
| **2**       | **1**   | **(7,F)**   | **<1,2,3,4,5,6,7,11>** |

#### All Uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**       | **1**   | **(7,T)**   | **<1,2,3,4,5,6,7,8>** |
| **2**       | **1**   | **(7,F)**   | **<1,2,3,4,5,6,7,11>** |

---

### Variable: **md**

#### All Defs
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**   | **2** | **3**   | **<2,3>** |
| 2       | 2   | 5   | <2,3,4,5> |
| 3       | 2   | 8   | <2,3,4,5,6,7,8> |
| 4       | 2   | 9   | <2,3,4,5,6,7,8,9> |


#### All C-uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**   | **2**   | **3**   | **<2,3>** |
| **2**   | **2**   | **5**   | **<2,3,4,5>** |
| **3**   | **2** | **8** | **<2,3,4,5,6,7,8>** |
| **4**   | **2** | **9** | **<2,3,4,5,6,7,8,9>**|


#### All P-uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| 1       | 2   | 3   | <2,3> |
| 2       | 2   | 5   | <2,3,4,5> |
| 3       | 2   | 8   | <2,3,4,5,6,7,8> |
| 4       | 2   | 9   | <2,3,4,5,6,7,8,9> |


#### All Uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**   | **2**   | **3**   | **<2,3>** |
| **2**   | **2**   | **5**   | **<2,3,4,5>** |
| **3**   | **2** | **8** | **<2,3,4,5,6,7,8>** |
| **4**   | **2** | **9** | **<2,3,4,5,6,7,8,9>**|


---

### Variable: **bytes**

#### All Defs
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**       | **4**   | **5**   | **<4,5>** |

#### All C-uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**       | **4**   | **5**   | **<4,5>** |

#### All P-uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| 1       | 4   | 5   | <4,5> |

#### All Uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**       | **4**   | **5**   | **<4,5>** |


---

### Variable: **digest**

#### All Defs
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**   | **5** | **9** | **<5,6,7,8,9>** |
| 2       | 5   | 11   | <5,6,7,8,9,10,11> |
| 3       | 5   | 11   | <5,6,7,11> |
| **4**   | **9** | **9** | **<9,10,7,8,9>** |
| 5       | 9   | 9   | <9,9> |
| 6       | 9   | 11   | <9,10,7,11> |

#### All C-uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**   | **5** | **9**   | **<5,6,7,8,9>** |
| **2**   | **5** | **11**  | **<5,6,7,8,9,10,11>** |
| **3**   | **5** | **11**  | **<5,6,7,11>** |
| **4**   | **9** | **9**   | **<9,10,7,8,9>** |
| **5**   | **9** | **9**   | **<9,9>** |
| **6**   | **9** | **11**  | **<9,10,7,11>** |


#### All P-uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| 1       | 5   | 9   | <5,6,7,8,9> |
| 2       | 5   | 11   | <5,6,7,8,9,10,11> |
| 3       | 5   | 11   | <5,6,7,11> |
| 4       | 9   | 9   | <9,10,7,8,9> |
| 5       | 9   | 9   | <9,9> |
| 6       | 9   | 11   | <9,10,7,11> |

#### All Uses
| pair id | def | use | path |
| ------- | --- | --- | ---- |
| **1**   | **5** | **9**   | **<5,6,7,8,9>** |
| **2**   | **5** | **11**  | **<5,6,7,8,9,10,11>** |
| **3**   | **5** | **11**  | **<5,6,7,11>** |
| **4**   | **9** | **9**   | **<9,10,7,8,9>** |
| **5**   | **9** | **9**   | **<9,9>** |
| **6**   | **9** | **11**  | **<9,10,7,11>** |

---

### Variable: **i**

#### All Defs
| pair id | def | use   | path          |
| ------- | --- | ----- | ------------- |
| **1**   | **6**   | **(7,F)** | **<6,7,11>**      |
| 2       | 6   | (7,T) | <6,7,8>       |
| 3       | 6   | 10    | <6,7,8,9,10>  |
| **4**   | **10**  | **(7,F)** | **<10,7,11>**     |
| 5       | 10  | (7,T) | <10,7,8>      |
| 6       | 10  | 10    | <10,10>       |
| 7       | 10  | 10    | <10,7,8,9,10> |


#### All C-uses
| pair id | def | use   | path          |
| ------- | --- | ----- | ------------- |
| 1       | 6   | (7,F) | <6,7,11>      |
| 2       | 6   | (7,T) | <6,7,8>       |
| **3**   | **6** | **10** | **<6,7,8,9,10>**  |
| 4       | 10  | (7,F) | <10,7,11>     |
| 5       | 10  | (7,T) | <10,7,8>      |
| **6**   | **10** | **10** | **<10,10>** |
| 7       | 10  | 10    | <10,7,8,9,10> |

#### All P-uses
| pair id | def | use   | path          |
| ------- | --- | ----- | ------------- |
| **1**   | **6**   | **(7,F)** | **<6,7,11>**      |
| 2       | 6   | (7,T) | <6,7,8>       |
| 3       | 6   | 10    | <6,7,8,9,10>  |
| **4**   | **10**  | **(7,F)** | **<10,7,11>**     |
| 5       | 10  | (7,T) | <10,7,8>      |
| 6       | 10  | 10    | <10,10>       |
| 7       | 10  | 10    | <10,7,8,9,10> |

#### All Uses
| pair id | def | use   | path          |
| ------- | --- | ----- | ------------- |
| **1**   | **6**   | **(7,F)** | **<6,7,11>**      |
| 2       | 6   | (7,T) | <6,7,8>       |
| **3**   | **6**   | **10**    | **<6,7,8,9,10>**  |
| **4**   | **10**  | **(7,F)** | **<10,7,11>**     |
| 5       | 10  | (7,T) | <10,7,8>      |
| **6**   | **10**  | **10**    | **<10,10>**       |
| 7       | 10  | 10    | <10,7,8,9,10> |

---


### Unit Tests
Tests we had after this assignment:
![](https://i.imgur.com/Cz1YUmY.png)

Variable: **text**
For this variable there is only one path, which is covered by all dataflow tests of the others variables so there is no special need to fill this path.
**path - pair id 1 = <1,2,3,4>**

Variable: **iteration**
For this variable there is two paths which any of them cant replace each other. So we are left with:
**path - pair id 1 = <1,2,3,4,5,6,7,8>
path - pair id 2 = <1,2,3,4,5,6,7,11>**

Variable: **md**
For this variable there are 4 paths which all complement each other in a way that the last pair id path covers all the lines covered in the last paths of this variable. So we are left with one path:
**path - pair id 4 = <2,3,4,5,6,7,8,9>**

Variable: **bytes**
For this variable there is only one path which is:
**path - pair id 1 = <4,5>**

Variable: **digest**
For this variable there are 6 paths, although there are many there is one that covers all of the lines of the others, which we will focus on:
**path - pair id 2 = <5,6,7,8,9,10,11>**

Variable: **i**
For this variable there are 7 paths and never one path covers all lines, with this said, we need to have at least two paths to cover all lines. There are many options, but for us it would make sense that the first def of the variable were made more early, so with that in mind we choose:
**path - pair id 1 = <6,7,11>
path - pair id 3 = <6,7,8,9,10>**

**Sumarize:**
We have:

| Variable | Pair Id | Path |
| -------- | ------- | -------- |
| text     | 1       | <1,2,3,4>   |
| iteration| 1       | <1,2,3,4,5,6,7,8>   |
| **iteration**| **2**| **<1,2,3,4,5,6,7,11>**   |
| md       | 4       | <2,3,4,5,6,7,8,9>   |
| bytes    | 1       | <4,5>   |
| **digest**| **2** | **<5,6,7,8,9,10,11>**   |
| i        | 1       | <6,7,11>   |
| i        | 3       | <6,7,8,9,10>   |

* From our flowchart we can analyse that the path <1,2,3,4,5> is always covered besides what tests we go, so we can assume that for granted.
* From this table there are two paths that can cover the last line: **iteration - 2** and **digest - 2**.
* We can also notice that they cover diferent lines, while **digest - 2** covers "<8,9,10>" the other one doesn't.

With this said we came to two possible tests:
1. Is by covering <1,2,3,4,5> like the code demands and following the rest of the **digest - 2** by covering <5,6,7,8,9,10,11>

Even tho we can cover all lines with the first path, from looking to the source code and using the jacoco analysis from last week, we understand that for 100% coverage when there is a if statement, there is always the need to have true and false result and since inside a for there is a if statement we need to have a path that also fills the need.
![](https://i.imgur.com/7lMIdjS.png)

2. By this we used the **iteration - 2** path that follows <1,2,3,4,5,6,7,11>.

**Summary table of the coverage criteria:**
| Variable | Pair Id | Path |
| -------- | ------- | -------- |
| **iteration**| **2**| **<1,2,3,4,5,6,7,11>**   |
| **digest**| **2** | **<5,6,7,8,9,10,11>**   |

### Tests Outcome

In our first tests we only called this method that would send 0 as parameter which would lead to a missing branch.
![](https://i.imgur.com/TRI43C7.png)
For use to cover the inside of the loop referenced by the first coverage criteria path (<5,6,7,8,9,10,11>.
![](https://i.imgur.com/I4znBaN.png)
We needed to use call another public method that would iterate the loop 1000 times.
![](https://i.imgur.com/0aUn5A5.png)

**Results of the tests:**
1. Test
![](https://i.imgur.com/2GFcB96.png)

**Coverage Criteria:** variable: digest | pair id:2
**Path Covered:** <5,6,7,8,9,10,11>
We used the method **getPKCS5Sha256Hash** to iterate 1000 times and by this intering the "for" to cover all the path of this test. In order to test if the values equal to each other, we replicated the original function and made it also iterate 1000 times. As expected the tests passed.

2. Test
![](https://i.imgur.com/3NY0sOo.png)

**Coverage Criteria:** variable: iteration | pair id:2
**Path Covered:** <1,2,3,4,5,6,7,11>
This test were already made before, but basically what it did was sending diferent char[] text values with his sha256 value from an online convertor and then, by converting the result of the original test method to Hex, we compare both of the values and expect them to be equal. As expected the tests passed.

**Summary:**

| Variable | Pair Id | Pairs Covered     | Test |
| -------- | ------- | ----------------- | --------- |
| digest   | 2       | <5,6,7,8,9,10,11> | testGetSha256HashAssignment8 |
| iteration| 2       | <1,2,3,4,5,6,7,11>| testGetSha256Hash            |


---
---

## nonValidXmlCharacters(final String in): StringUtils.java
### Function Purpouse
The purpose of this function is to strip or replace non unicode characters with "?"

### Data flow analysis graph
![](https://i.imgur.com/F1IuYFW.png)

#### Tables for each variable
in
![](https://i.imgur.com/nhxd2xS.png)


out
![](https://i.imgur.com/KxKD51C.png)

current
![](https://i.imgur.com/Met53RF.png)

i
![](https://i.imgur.com/S64m4CO.png)


##### Analysis

For variable i

All defs
![](https://i.imgur.com/x3Sr4ja.png)


All c-uses
![](https://i.imgur.com/LbEN7DG.png)

All p-uses
![](https://i.imgur.com/r9E6pdj.png)

All uses
![](https://i.imgur.com/R65Jcrw.png)


For variable out

All defs
![](https://i.imgur.com/o1QUy6p.png)

All c-uses
![](https://i.imgur.com/zMAVTaW.png)

All p-uses
For this variable there are no predicate uses (p-uses)

All uses
![](https://i.imgur.com/StnSwvi.png)


For variable current

All defs
![](https://i.imgur.com/AeQBWgI.png)

All c-uses
![](https://i.imgur.com/gxwdOEK.png)

All p-uses
![](https://i.imgur.com/Uc55jOC.png)

All uses
![](https://i.imgur.com/IJ5D1yB.png)

For variable i

All defs
![](https://i.imgur.com/Gkvvfs3.png)

All c-uses
![](https://i.imgur.com/MyOcAnY.png)

All p-uses
![](https://i.imgur.com/Vzt5uXZ.png)

All uses
![](https://i.imgur.com/ooNcTmO.png)

Number of tests analysis
1) In the first analysis we can see in green the tests we need to generate for each variable to cover the paths.
![](https://i.imgur.com/fMEMy0h.png)

2) In the second analysis we can reduce the number of tests from 9 to just 4.
![](https://i.imgur.com/fAleAer.png)

Generated and their outcome
The test for the variable in on the **pair id 2(variable in)** was already tested. On this test it was used an empty string where the result was an empty string and in with the null string where the result was also null.
The outcome: Both tests passed has expected. This test represents the test number 5 in the figure below.
![](https://i.imgur.com/vtP6mHc.png)

**For the variable out in the pair id1 and 3(variable out):**
If take a close look to the dataflow graph The **pair id 1** is also tested here because the only way of reaching to point 7 is by validating a the all string and then generate is output(7) so combine this test with the pair id 3 the string must contain only valid xml characters
![](https://i.imgur.com/hrZ6svq.png)


**For the variable out in the pair id4(variable out):**
To test this path we need to use a string containig non valid xml characters so it should loop through the all string and after verfying all the string it go 7 which means it will also satisfy the pair id 1 that should replace the characters that are invalid with '?' like in image below on test #3
![](https://i.imgur.com/1WfLYaY.png)


