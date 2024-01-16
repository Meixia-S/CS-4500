To: CEOs <br>
From: Surprising-Tigers <br>
CC: <br>
Date: 9/14/23 <br>
SUBJECT: Plans For Q Game's First 3 Sprints

<h3>Sprint 1 </h3>
Our first sprint's goal is to figure out the communication layer the Q 
game. We will complete the following tasks:
1. Creating a Server component capable of sending and receiving json 
messages over tcp. 
2. Define the player-referee interface which will specify the format and 
type of messages the Player and Referee class send and receive.
3. Create the Referee component based on the player-referee interface.
By the end of this sprint, our Referee will be able to read json messages that follow
our player-referee interface.

<h3>Sprint 2 </h3>
The goal in Sprint 2 is to implement the Q game's rules and 
the Player component. 
1. We will add the Qwirkle game's rule to the Referee. The referee will initiate the 
game with players, a map, and game pieces and store the board's state. It will also 
enforce where tiles can be placed, how moves will be scored, and when the game is over. 
2. Create the Player class based on the player-referee interface. 
The player wil be able to play a turn: pass, exchange, or place tiles.
3. Create a graphic representation of the game's current state.

<h3>Sprint 3 </h3>
Our third sprint's goal is to integrate the Player, Referee, and Server components.
1. We will use the three components to run a game on the Server that includes a Referee 
waiting for Players to join, Players sending moves to the Sever, the Referee sending 
updates to Players, and the game ending. 
2. Add an Observer class. An observer can view the current game board and player action.
3. Implement logical-sign-up
4. Let the Referee boot players if they cheat. 