Name   - Deepak Varghese

The programming language used is Java.


Task1 

Posterior probailities

It has one file - compute_a_posteriori.java

It has only one class with name compute_a_posteriori

Code structure - 

The code is structured such that all possible values are defined for the bags and the prior values

The code displays the following information 

Observation sequence Q: ???
Length of Q: ???

After Observation ??? = ???: (This and all remaining lines are repeated for every observation)

P(h1 | Q) = ???
P(h2 | Q) = ???
P(h3 | Q) = ???
P(h4 | Q) = ???
P(h5 | Q) = ???

Probability that the next candy we pick will be C, given Q: ???
Probability that the next candy we pick will be L, given Q: ???

Here it displays the calculation after each iteration.
It checks if next candy is cherry or lime based on an if else statement and performs the corresponding calculations and displays the result.

Compilation instructions 

javac compute_a_posteriori.java
java compute_a_posteriori CLCLCL(input sequence)

References

1) https://github.com/chiraghshah/posterior_probability
2) http://omega.uta.edu/~gopikrishnav/classes/common/4308_5360/slides/06a_bayesian_networks.pdf
3) https://www.mkyong.com/java/java-display-double-in-2-decimal-points/



Task 2 

Bayesian Networks 

It has one file - bnet.java

It has only one class with name bnet 

Functions -

1) generateTT - This function used to find the missing values from input and calls another function generateTable.

2) generateTable - This function is used to genreate the possible truth table combinations for all the missing input values.

3) missing_val - This function helps in finding the missing values and adds to input.

4) computeProbability (String A, String B , String E, String J, String M ) - This function performs the actual probability calculation based on the input true or false values for the events B,E,A,J,M . 

The main function performs all the checks and calculations for input sequences with and without given conditions.

Compilation Instructions 

javac bnet.java
java bnet Jt Af given Bt Ef(input sequence)

References 

1) https://stackoverflow.com/questions/38213413/what-is-the-best-way-to-concatenate-multi-dimensional-arrays-in-java

2) https://stackoverflow.com/questions/24824044/whats-the-logic-to-print-the-truth-table-values-given-the-x-in-2x

3) https://www.javatpoint.com/StringBuilder-class

4) https://www.tutorialgateway.org/multi-dimensional-array-in-java/

5) https://www.geeksforgeeks.org/arraylist-in-java/
