# Java Exam

This is a very short test for your skills in java
It is using maven + spring framework.
In order for you to pass the exams you need ``Mvn Test``
to run without any errors and test failures.

## Part 1
Please do not touch any file in the ``test/part1`` folder ,
only touch ``main/part1``

In order for you to solve this little exam you actually
need to touch only 3 files:
* spring-config.xml
* DefaultTodoService
* DefaultIdNumberValidator


## Part 2
Here things are getting a little bit trickier. You will have
to help me build a simple implementation of a card game called
SET. 

### Game rules
it is described [here](https://en.wikipedia.org/wiki/Set_%28card_game%29)


It is a very simple game (simplified here even more).

it is a card game, in which every card varies in four different
features: shape,number,color,and texture. every feature has 3 different options.

The deck is composed from 81 cards that are all different (3^4)
In the beginning of the game 12 cards are revealed.
The player (here for simplicity there is only one player),
needs to find in the revealed cards  a SET of 3 cards.

**By set I mean**:
for any of the 4 features = all cards are either  identical
(i.e. all have the same shape), or all are completly different (i.e.
none have the same color).

If the set is valid - the score of the player is raised by 1, otherwise
it is decreased by 1.

If the set is valid - then the 3 cards are replaced by
3 new fresh ones rom the deck.

When all the cards are finished (from the deck and then from the reevealed 
cards) - the player is notified that the game is over and his score.

 ### What I ask you to do
 
 I've created some interfaces and classes, and created some unit tests.
that are empty right now (I mean I wrote what I want to test but
did not write the test itself). 
Please help me complete the project by completing the tests and 
writing the code for them to pass. In the end we should be able
to interact with the class called Gameplay, using `start` and
`play` methods. 
 Throughout the code I wrote some thoughts about what should be done.
 You can use this as hints or ignore them if you think it can
 be done better.
 
 **Note - unlike part 1 - here you need to touch the tests sedtion**.
 
 ## Finally

When you are done please commit, push, and let us know
you are done