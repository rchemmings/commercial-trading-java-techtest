# Anagram Finder
A simple command line utility for finding anagrams in a specified file

The utility will read a text file containing single words on each line and group words thst are anagrams of each other
together onto a single line.

## Solution
The AnagramCommandLineRunner is wired with a FileProcessor and an AnagramCollector and simply calls the collect method
of the FileProcessor passing in the file to process and the AnagramCollector

The FileProcessor's job is to open the file that is passed in and to read a line at a time until it gets to the end.
Each line is assumed to be a single word) and the word is then passed to the AnagramCollectorfor processing . 
After the end of the file is reached the FileProcessor must call the AnagramCollector to flush out any remaingin data,
in this case this is simply to output the results and clear down the collection of words. Rather than 

The AnagramCollectors job is collect the anagrams together based on the number of letters in each work. It is assumed 
that words will be passed in in ever increasing lengths. Upon construction the AnagramCollector will have a 
WordCollection wired in to it, which will be used to hold the words. The collcet() method adds the word passed in into
the wordCollection, but first determines if the length off words passed in has changed. If the length has changed the 
flush() method is called to output all the words that are in the wordCollection and then clear the wordCollection ready 
for the next group of words.

The WordCollection class is used to store the words passed into it and to group words that are anagrams of each other
together. Grouping is achieved by taking the word passed in and sorting it into alphabetical order, the result is used 
as a key into a hashmap (called anagramMap). Words that are anagrams of each other will then generate the same key. 
The value of each entry in the anagramMap is a List of strings (each string being a word), and all words in the list 
will then be anagrams of each other (could change this to use a set if we were only interested in unique anagrams)


## Improvements
Use an interface in the FileProcessor instead of using the AnagramCollectorto make it more generic.

Although sorting the word for the key is a simple solution, I would investigate other alternatives as sorting can be
quite intensive. The java sort() method uses is a Dual-Pivot Quicksort implementations which has a bigO worst case time 
complexity of O(n^2), the average complexity of the sort is O(n log(n)).


## Assumptions
De-duplication of words is not required.
No validation of each line in the file is require and each line will be a single word


## Software required to run this
* Java 17

## Building and Running the tests
```
./gradlew clean build
```

## Running the program
```
./gradlew bootRun --args="example2.txt" 
```
where example2.txt is the text file that we want to search for anagrams



