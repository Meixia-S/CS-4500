To: CEO<br>
From: Surprising-Tigers <br>
CC: Teaching Assistants<br>
Date: 10/5/23 <br>
SUBJECT: Player Interface Design

We finished our second milestone, and are now designing the Player Interface. This interface will 
allow Players to inform the Referee what move they desire, as well as request information about
the game's state.

At any point of the game, a Player can request and receive the following information from the Referee:<br>
// the location of tiles on the current map<br>
Map<Posn, Tile> getCurrentMap()<br>

// the order in which players take turn<br>
List<PlayerName> getPlayersOrder()<br>

// all the Players' scores<br>
Map<PlayerName, int> getPlayersScores()<br>

// gets this Player's available tiles<br>
List<Tile< getPlayerTiles()<br>

// the current score of this player<br>
int getScore()<br>

// how many tiles everyone currently has<br>
Map<PlayerTile, int> getPlayersNumberOfTiles()<br>

// how many tiles left in the pile<br>
int getRefereePileTotalTiles()<br>

When it is their turn, our Player Interface says the Player can make one of three kinds of moves:<br>
// Let this player end or pass their turn.<br>
void endTurn()<br>

// Let this player exchange all of its tiles for new ones drawn from the referee's collection.<br>
void exchangeTiles()<br>

// Let this player place their tile if their move satisfies the rules of the Q game.<br>
void placeTile(Posn, Tile)<br>

Feel free to reach out to us if you have any question.

