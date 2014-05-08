# Simple Hadoop Example


Minimal Hadoop example project with Apache Hadoop Core.



## Requirements

* Maven 2 or 3
* JDK 7+
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


## License
    Copyright (c) 2013, Philipp Krenn
    All rights reserved.
   
    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions are met:
        * Redistributions of source code must retain the above copyright
          notice, this list of conditions and the following disclaimer.
        * Redistributions in binary form must reproduce the above copyright
          notice, this list of conditions and the following disclaimer in the
          documentation and/or other materials provided with the distribution.
        * Neither the name of the authors nor the names of its contributors
          may be used to endorse or promote products derived from this
          software without specific prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS "AS IS" AND ANY
    EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
    WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
    DISCLAIMED. IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY DIRECT,
    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
    (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
    LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
    ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
    SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.