<h5>The game state will be represented as a class with the following fields:</h5>
Keeps track of Tiles on the board (defined in Common/Map.java)<br>
<code>
Map                         
</code>
Players in the game; List is ordered in the order they take turns.<br>
<code>
List&lt;Player>                 
</code>
Player who has the current turn<br>
<code>
Player                       
</code>
The Tiles each player has<br>
<code>
HashMap<Player, List&lt;Tile>>  
</code>
Number of points each player has<br>
<code>
HashMap<Player, Integer>     
</code>
Remaining tiles that the referee can hand to players.<br>
<code>
List&lt;Tile>     
</code>
The coordinations of Tiles a Player has placed in their current turn.<br>
<code>
List&lt;Posn>           
</code>
An image representing the current game board's Tiles<br>
<code>
BufferedImage       
</code>
<h5>
It will have methods that the Referee can call in order to progress the game:
</h5>


Returns the player whose turn is next. <br>
<code>
Player getNextPlayer()
</code>

Can the given Player place the tile at the given position according to the
matching rules and in the same row or col as the other Tiles placed?<br>
<code>
boolean isValidMove(Player, Tile, Posn)
</code>

Calculates how many points a Player scored for their last turn.<br>
<code>
int getLastTurnScore(Player)
</code>

Returns all the Player's scores.<br>
<code>
Map<Player, int> getCurrentScores()
</code>

Replaces as many tiles as the Player placed or as many are left.<br>
<code>
void replacePlayerTiles(Player)
</code>

Checks if the game is over. <br>
<code>
boolean isGameOver()
</code>

Gets the Tiles a Player currently has.<br>
<code>
List<Tiles> getPlayerTiles(Player)
</code>

Starts a new game with the given Players and the Tile on the board.<br>
<code>
void initiateGame(List<Players>, Tile)
</code>

Places the given Tile at the position on the board. This method is called if isValidMove()
is true.<br>
<code>
void updateGameBoard(Tile, Posn)
</code>

Returns a copy of the current map for Players and Observers to see.<br>
<code>
HashMap<Tile, Posn> getMap()
</code>

Eliminates a Player from this game and returns their tiles back to the pile.<br>
<code>
void removePlayer(Player)
</code>

exchanges all player's tiles for new ones<br>
<code>
void exchangeTiles(Player, List<Player>)
</code>

Ends a Player's turn.<br>
<code>
void endPlayerTurn(Player)
</code>

Produces an image of the current board<br>
<code>
Image getBoardImage()
</code>

Update our BufferedImage with the given Tile<br>
<code>
void updateBoardImage(Tile, Posn)
</code>

