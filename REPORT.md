# 2023F CS2910 Project Report
**Group Members**
1. Laura Nweneka (0345887)
2. Ridwan Kolapo (0348715)

# Summary of Game
_

The game we chose to build is called Diamant, which involves 2-8 players. Diamant follows the players into a mine, where they
either recover jewels and relics worth 5 and 1 point respectively, or they could be stumped by different traps. After each play 
of cards, the players have the choice to stay in the mine, or leave to their camps. If they leave, then they can keep their treasure. 
If they don't leave, then they keep playing. But 2 of the same traps appear in the game, then the round is over and any player still 
in the game loses all the treasure that they collected in that round. The player with the highest points after 5 rounds wins.

https://www.ultraboardgames.com/diamant/game-rules.php




# Experiment Report
## Player Strategies
_For each of 3 strategies you implemented, name the strategy, and then description of it (100 words each)_
1. exitOnFirstPlay: This is a strategy where a player leaves after the first card has been drawn. It uses a function called
endGame which changes their in-game status to false. After each play,
the player randomly decides to stay in the game or leave. If a player decides to leave and only one card has been 
drawn so far in the round, no matter what card it is, then this method is called, which in turn calls the endgame method, 
signalling that they are no longer in that round. They take whatever rubies they have and add it to their chest and wait for the 
round to end.

2. exitOnFirstTreasure: This is a strategy where a player leaves after the first treasure card has been drawn. This uses
   a function called exitGame, which changes the players in-game status to false. The player
   randomly decides to stay in the game or leave after every draw of card. If a player decides to leave and only one treasure card has been
   drawn so far in the round, then this method is called, which also calls the endGame function, signalling that
   they are no longer in that round, and are safe in camp. After the strategy is called then they add whatever rubies they have to their chest
   and wait for the round to be over. 
3. exitOnFirstTrap: This is a strategy where a player leaves after the first trap in the game has been drawn.
   This uses a function called exitGame, which changes the players in-game status to false.
   The player randomly decides to stay in the game or leave after each drw of card. If a player decides to leave 
   and only one trap has been drawn so far in the round, then this method is called and changes their in-game status to false, signalling that
   they are no longer in that round, and are safe in camp. The player can then add however many rubies that they have collected so far and 
   add it to their chest and wait for the end of the round
4. exitWithRelic: This is a strategy where a player leaves with a relic. The player randomly decides to stay in the 
   game or leave after each draw of card. If they chose to leave and a relic has been played, and they are the only player leaving, then this 
   method is called and changes their in-game status to false. They take the relic. If the number of relics left in the game is greater than 2,
   then the player takes 1 diamond for each relic they collected. But if there is only 1 or 2 relics left in the game, then they take
   2 diamonds for each relic they collect. They also add their rubies to their chest and wait in camp for the end of the round.


## Procedure


The game allows for 2 - 8 players, however for the sake of this experiment, we chose to take simulations from 2 players.
We wanted to see if we can determine which strategy is best. So we ran the game 60 times and recorded the findings. We 
recorded the number of each of the 4 simulations that each player took in each game as well as the final points. The goal
was to find a trend. Does any strategy result in the highest number of points? Before running the experiment our hypothesis
was that leaving after the first Treasure card has been played would result in greater points. 

## Results
_A presentation on the results of your simulation of the strategies in table(s) or appropriate graphic(s) 
with a short summary. (250 words)_


**Example Table:**

| Simulations | Player | Leaving with Relic | Leaving after First Treasure | Leaving after First Trap | Leaving after First Draw | Points |
|-------------|--------|--------------------|------------------------------|--------------------------|-------------------------|--------|
| 0           | 1      | 0                  | 1                            | 0                        | 1                       | 38     |
|             | 2      | 0                  | 2                            | 0                        | 0                       | 52     |
| 1           | 1      | 1                  | 3                            | 1                        | 0                       | 33     |
|             | 2      | 4                  | 3                            | 1                        | 0                       | 45     |
| 2           | 1      | 0                  | 1                            | 0                        | 0                       | 64     |
|             | 2      | 0                  | 2                            | 3                        | 0                       | 16     |
| 3           | 1      | 0                  | 0                            | 1                        | 1                       | 10     |
|             | 2      | 2                  | 0                            | 1                        | 2                       | 40     |
| 4           | 1      | 3                  | 0                            | 1                        | 0                       | 71     |
|             | 2      | 0                  | 1                            | 2                        | 0                       | 36     |
| 5           | 1      | 2                  | 0                            | 2                        | 1                       | 30     |
|             | 2      | 1                  | 2                            | 1                        | 1                       | 73     |
| 6           | 1      | 0                  | 0                            | 1                        | 2                       | 39     |
|             | 2      | 3                  | 3                            | 0                        | 1                       | 41     |
| 7           | 1      | 0                  | 1                            | 0                        | 2                       | 37     |
|             | 2      | 4                  | 0                            | 2                        | 1                       | 32     |
| 8           | 1      | 2                  | 0                            | 2                        | 0                       | 48     |
|             | 2      | 0                  | 0                            | 0                        | 0                       | 13     |
| 9           | 1      | 0                  | 0                            | 0                        | 0                       | 7      |
|             | 2      | 0                  | 0                            | 2                        | 0                       | 7      |
| 10          | 1      | 2                  | 2                            | 0                        | 1                       | 36     |
|             | 2      | 0                  | 0                            | 1                        | 1                       | 47     |
| 11          | 1      | 1                  | 1                            | 2                        | 2                       | 22     |
|             | 2      | 3                  | 0                            | 4                        | 0                       | 41     |
| 12          | 1      | 2                  | 0                            | 1                        | 0                       | 30     |
|             | 2      | 0                  | 3                            | 2                        | 0                       | 27     |
| 13          | 1      | 1                  | 4                            | 1                        | 0                       | 30     |
|             | 2      | 0                  | 1                            | 0                        | 1                       | 14     |
| 14          | 1      | 2                  | 1                            | 0                        | 4                       | 60     |
|             | 2      | 1                  | 4                            | 0                        | 1                       | 26     |
| 15          | 1      | 0                  | 1                            | 0                        | 2                       | 18     |
|             | 2      | 3                  | 2                            | 0                        | 2                       | 80     |
| 16          | 1      | 1                  | 1                            | 0                        | 1                       | 30     |
|             | 2      | 2                  | 4                            | 0                        | 1                       | 24     |
| 17          | 1      | 4                  | 5                            | 0                        | 0                       | 67     |
|             | 2      | 1                  | 0                            | 2                        | 1                       | 49     |
| 18          | 1      | 3                  | 0                            | 2                        | 2                       | 12     |
|             | 2      | 1                  | 0                            | 0                        | 1                       | 14     |
| 19          | 1      | 0                  | 0                            | 0                        | 0                       | 56     |
|             | 2      | 2                  | 0                            | 0                        | 0                       | 28     |
| 20          | 1      | 1                  | 1                            | 0                        | 1                       | 28     |
|             | 2      | 1                  | 0                            | 0                        | 2                       | 34     |

## Analysis
_Based on my experiment, it was very obvious that players that left with the most relics, tended to have the most points. However 
our initial hypothesis wasn't wrong as well. Players that left with after the first treasure card also had more points. Of course there are
exception to this trend. And this is also for a 2- player simulation. Determining the better strategy for more than 3 players would require 
simulations and a better way to analyse the result than a table, like a visual graph._

# Reflection

### What generative AI did you use, and what tasks did you use it for?
We used ChatGPT, after writing all the main classes by ourselves,
to see if there were any aspects of the main code that could be improved. For instance,
identifying redundant methods. We also used ChatGPT to understand how to write
assertions for testing.

### How did you learn about the tools used by your group (delete ones that don't apply)?
_ChatGPT is an AI the most students use and misuse for their academics, which made us hesitant
to use it in the first place. But, we realized that we could use it as an assistant, rather than
using it to build the entire project._

### Reflecting on your experience:
_Write a short reflection on your use of generative AI in this project, including if you did not use it. 
You may use the prompts as headings if you wish. (500 words)_  

_We really wanted to challenge ourselves and test our knowledge 
with this project. So we decided to do all the main codes without the help of AI. Instead of using ChatGPT when we got stuck,
we used the class slides and Perusall. For example, we knew that the game needed to have a deck of cards, which is also a linked
list, so we read up on the Linked list and Stack sections from Perusall, and learnt how to build a stack. We made this stack, which 
became the deck. Of course, after doing all that, we realized that we could have easily imported stack from java utilities, but we
decided to leave it in the code anyway._

_Making the main classes, essentially constructing the entire game, definitely helped with critical thinking, as well as patience. 
There were so many aspects of this game that we didn't even plan for in the beginning, a level of complexity that we didn't exactly 
plan for in the beginning. Having to account for every action and decision was definitely a tedious task. We took a lot more notes
than we thought we would, because it helped us keep track of what we had done, what we needed to do and any bugs we found. Writing 
the different processes down also helped it to not feel like there was too much going on at once, and its because we could track down
where we were._

_We did use ChatGPT to help understand how to write test codes for the classes we made, because it's not something that was extensively
covered in class, and we didn't really know how to do it. When writing the tests, we went back and forth
between ChatGPT and tests for the labs we've done. We wanted to use ChatGPT as a guide or an assistant, so that we don't misuse it
and also so that we actually gain more knowledge from this whole process._

_In as much as we did the majority of the work by ourselves, using AI gave us a level of assurance, that our code was actually good and we
actually knew what we were doing._

_When we started, we thought that each of us could do certain tasks by ourselves and then merge at the end, so we made branches. But we quickly realized
that it was more beneficial for us to work together on the same computer, just so that we could bounce ideas off each other and highlight
errors to each other that maybe we couldn't se for ourselves. So we mostly worked on the main code on Laura's system, and the rest on Ridwan's
system._



# Bonus Consideration:
If you have aspects of your project you would like considered for the available bonus.