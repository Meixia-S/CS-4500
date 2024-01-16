<pre>          
            TO:      Dr. Matthias
            FROM:    Jenny and Meixia
            DATE:    19 October 2023
            SUBJECT: The Referee State

The referee needs to "contact" the game state everytime the referee gets the user input. While it may not edit the map or the 
current player's score it will need to go to the next player and start a new round (if applicable). The two largest changes in 
the interface from the first time we designed it are: 
                  1) the functionality to keep track of each players turn (which three option they chose) 
                     to be able to determine if the game should be over
                  2) the functionality to keep track of each phase of the game state inorder to start the 
                     game at any given moment (that has already been played)

The referee-state class fields' are:
<table>     <tr>
                            <th>Name</th>
                            <th>Data Type</th>
                            <th>Definition</th>
            </tr>
            <tr>
                            <th>map</th>
                            <th>Map</th>
                            <th>The current state of the map</th>
            </tr>
            <tr>
                            <th>pile</th>
                            <th>List&ltTile></th>
                            <th>The pile of referee's Tiles to draw from</th>
            </tr>
            <tr>
                            <th>players</th>
                            <th>List&ltPlayerState></th>
                            <th>The players’ states ordered in which they take turns</th>
            </tr>
            <tr>
                            <th>activePlayer</th>
                            <th>PlayerState</th>
                            <th>The active player's (the player whose turn it is) index in this.players</th>
            </tr>
            <tr>
                            <th>currentTurnAction</th>
                            <th>HashMap<Posn, Tile></th>
                            <th>The coordinates of Tile the active Player has placed in their current turn</th>
            </tr>
             <tr>
                            <th>alreadyPointedRow</th>
                            <th>List&ltPosn></th>
                            <th>The coordinates we have already pointed on the row</th>
           </tr> 
            <tr>
                            <th>alreadyPointedCol</th>
                            <th>List&ltPosn></th>
                            <th> The coordinates we have already pointed on the column</th>
           </tr>
            <tr>
                            <th>savedgameStates</th>
                            <th>List&ltGameState></th>
                            <th> An array that holds the saved gamestates that are used to resume the game at a given point</th>
           </tr> 
            <tr>
                            <th>playerMoves</th>
                            <th>List&ltMove></th>
                            <th>An array that holds the moves players make during their turn (Place, Exchange, Pass)</th>
           </tr> </table>
               Wish List for the Referee State Class
             _________________________________________________

 Public_Info              getPublicInfo()                    - Public information that the referee shows the active player
 HashMap&ltPosn, Tile>      getCurrentMap()                    - Returns a copy of the current map of this game state.
 List&ltTile>               getAvailableTiles()                - Returns a copy of the active Player's tiles of this game state.
 HashMap&ltString, Integer> getPlayersScores()                 - Returns the all the Players' scores.
 HashMap&ltString, Integer> getPlayersRemainingTiles()         - Returns the number of Tiles each Player has
 int                      getRefereeRemainingTiles()         - Returns the number of remaining tiles of the referee's pile that 
                                                               the player can draw in this game state
 void                     placeTile(Tile tile, Posn posn)    - Places the given Tile on this map at the given location for the 
                                                               active Player
 boolean                  isValidMove(Tile tile,  Posn posn) - The given tile must share a side with at least one tile on the map 
                                                               that it extends, shape or color matching rules, and in the same 
                                                               row or col as the previous Tiles placed during this turn.
 void                     exchangeTiles()                    - Lets the active player exchange all of their tiles for new ones.
 void                     endTurn()                          - Ends this active player's turn and replaces their tiles.
 boolean                  isInSameRowOrCol(Posn posn)        - Checks to see that the tiles places are within the same row 
 void                     renderGameState()                  - Renders the current game state as an image using the 
                                                               Game_State_GUI class
 int                      turnScore()                        - Gets the score a player got by their placements in one turn
</pre>
### The Interaction Protocol Between the Referee and the Game-State Component 
<pre>
1) The referee set-ups the game state, order players and distribute the tiles to 
the players
2) The referee starts the game map with one referee's tile
3) Referee keep tracks of the order of players in this game state
Referee let the active player play their turn.
4) After requesting move from player the referee: 
    1. Validate: validates player' move
        If it is not valid the referee can kick player out of game and reorder the players 
    2. Place: if the move if valid, the referee places their tile on this game map
    3. Exchange: referee takes all 6 tiles from player and replaces them with 6 randomly selected tiles from the pile
        If the player requests an exchange when the pile is less then 6, this is considered a invalid move
    4. Pass: the referee simply moves on to the next player without any modifications to the player
    5. Score: determines the score for the move and add it to the players running score
5) the referee updates the game state after player's move
6) As long as the round is not over, the current state of the game is saved and 
the next player may start their turn
7) If the round is over, the referee checks if the game is over
    If it is, jump to step 9
8) 4 - 6 are repeated every round until the game is over 
9) When the game is over the referee announces the winner and the rankings of players
10) Game ends, all game state and player data is reset
</pre>
