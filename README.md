White Elephant
=========
Description
-----------
- This project gives an assignment method to deisgnate a recipient for each participant;
- To make sure the names are legal, we iliminate the names without any characters or with only spaces;
- Considering there might be duplicate names among participants, we add a different suffix to duplicates after sorting the names; there is another way using HashMap
- To randomly assign a recipient for each participant, we start from the last participant, and randomly select a recipient from former ones for him/her.Then we move forward to his former participant. Repeat the procedure. Through this method, we can make sure the probability of each participant is selected is 1/n, where n is the number of participants.

BUILDING
---------
To build the .class file(JDK is required), use:
```sh
make
```

USAGE
-------
- The file cases contains some input samples of the game;
- You can also input another file name to test the code; The participants list is one-line string which names are delimited by ',';
- To run code, use:
```sh
./run.sh cases
```

TESTING
-------
The unit test is written via JUnit.
To run the test case, use:
```sh    
make test
```
