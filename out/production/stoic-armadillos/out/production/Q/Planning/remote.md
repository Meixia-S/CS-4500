<pre>
            TO:      Dr. Matthias
            FROM:    Meixia and Jenny
            DATE:    12 October 2023
            SUBJECT: The Remote-Proxy Protocol      
            
Our remote proxy protocol provides interfaces through which the client players and our referee implementation can feasibly 
interact. This includes a proxy referee responsible for receiving player input in the form of our data representation, 
converting it to a transferable JSON format, and sending it over the TCP connection. On the server-side, we will need a 
proxy player with which our actual referee communicates. The proxy player will be responsible for receiving the JSON over 
the TCP connection from the proxy referee, converting it BACK to our data representation, and feeding it to the referee.
</pre>          
### Remote-Proxy Protocol
<pre>
  1. Allow players to connect to the server
  2. Converting the player's information (birthday and name) to a player_state
  3. Alert the players that the game by asking the first player for their move by giving them the public_info
            1. This information will need to be converted from the internal representation to JSON
  4. Receive the player move and convert it to a player_input data representation
  5. Let the player know their move was valid (if it was) and let them know the new tiles will be distributed to them later
  6. Repeat step 3 and 5 until the game is complete
  7. Let players know if they were a winner and then also display the misbehaved player
  8. End the game by shutting down the server
</pre>  
### Diagram
 <pre>
        Players                    Proxy                       Referee
_______________________________________________________________________________________
|      players join       |                             |                             |Starts server (step 1)
|_________________________|_____________________________|_____________________________|_______________________
|                         |                             |                             |
| birthday & name in JSON |         player_state        |                             |Players input info (step 2)
|-----------------------> |---------------------------> |                             |____________________________
|                         |                             |                             |
|                         |  Convered to JSON to Player |    public_info to player    |Asks players for their move (steps 3 - 5)
|                         | <---------------------------| <---------------------------|__________________________________________
|                         |                             |                             |
|  Move(P, E, P) in JSON  |  Converted to player_input  |                             |
|-----------------------> |---------------------------> |                             |
|                         |                             |                             |
|                         |inform move was valid in JSON|     Edits the game_state    |
|                         | <---------------------------| <---------------------------|
|                         |                             |                             |
|                         | inform game is over in JSON |       game ends             |Game ends (step 6)
|                         | <---------------------------| <---------------------------|___________________
|                         |                             |                             |
|                         |  display this info in JSON  |Determines winners/misbehaved|Inform players who won/misbehaved (step 7)
|                         | <---------------------------| <---------------------------|___________________________________________
|                         |                             |                             |
|   players disconnect    |                             |      Shuts down server      |Shuts down server (step 8)
|_________________________|                             | <---------------------------|___________________________
|                         |                             |                             |
|                         |                             |                             |
</pre>
