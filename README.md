# Simple Hadoop Example


Minimal Hadoop example project with Apache Hadoop Core.



## Requirements

* Maven 2 or 3
* JDK 6 or 7
* *nix operating system because auf [HADOOP-7682](https://issues.apache.org/jira/browse/HADOOP-7682)



## Usage

    mvn clean test

Or you can simply run the main method in your preferred IDE.

The result will be in the file *target/result/part-r-00000*.


## Use Case

Count how many words in a a given text file can be formed with one, two, or three lines on the keyboard (excluding vowels).

Strategy:

 * Split words on non-alphabetic characters.
 * Match how many rows are used ignoring vowels.
 * Add one entry to the keybord-row identified.
 * Finally, count all the ones.