<pre>          
            TO:      Dr. Matthias
            FROM:    Meixia and Jenny
            DATE:    12 October 2023
            SUBJECT: The Player Protocol
 
The consistent reliable communication between the players and the referee is essential to the 
flow of the game. If incorrect commands/permissions are given during a game, its integrity 
is threatened and lead to game ending issues. While we want to ensure there are enough checks 
and steps to confirm information being passed by the two parties we do not want excessive 
redundancy. 

Player Protocol:
1) Player joins the game and both confirms its status (connected) and provides the referee with 
private information (birthday and name)
2) Referee acknowledges the players existence and assigns them with a turn number after all players
have joined the game and provided their respective information
3) Once the game has started, the referee lets the player know the order of players (sorted by age)
and when it is their turn
4) Referee send the active player the public data of the game state (this is a data representation that
involves the current map, the tiles the player has, the number of remaining tiles in the referee's 
pile, other players' score)
5) Player receives the information given by the referee about the current state of the game and is 
given time to decide what they want to do with their turn (Pass, Exchange, Place)
6) Once a player decides they notify the referee with which of the three game options they chose
    7) If they want to pass, the referee will change who's turn it is to play and "lock" all permissions
       to the player who chose to pass.
    8) If the player wish to exchange tiles, the referee will let them know if it's possible (there 
       are enough tiles in the referee's pile to exchange). The referee will hand back the same amount 
       of tiles.
    9) If they want to place tiles they must give the referee the tiles and coordinates. The referee 
       takes them and determines if the move is legal. The referee will place the tile on the player's 
       behalf and update the game state. If not the referee has the power to eliminate the player for 
       their attempts at cheating (breaking the placement rules are seen as cheating).
10) If tiles are placed on the board the referee will calculate the score for their move and add it to
their overall score and notify the player of their actions (lets player know that their points
have been given to them)
11) Steps 3 - 10 will be repeated until the game ends.
12) Once the game has ended the referee will let all players know and for what reason the game has ended
13) Then the referee will announce the players' rankings
14) Ends the game and clears the lobby and player information inorder to host another game
</pre>