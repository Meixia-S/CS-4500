<pre>            TO:      Dr. Matthias
            FROM:    Meixia and Jenny
            DATE:    2 November 2023
            SUBJECT: Ranking Three Game Modifications         
</pre>  

 ### Rating On A Scale (1 - 5)
 <table>
  <tr>
    <th> Change </th>
    <th> Rating </th>
  </tr>
  <tr>
    <td>Changing the bonus points again and allow more players to participate in a game</td>
    <td>2</td>
  </tr>
  <tr>
    <td>Adding wildcard tiles</td>
    <td>4</td>
  </tr>
  <tr>
    <td>Imposing restrictions that enforce the rules of Qwirkle instead of Q</td>
    <td>3</td>
   </tr>
</table>

### Reasoning
    1. Changing the bonus points again and allow more players to participate in a game -
            This will be relatively easy as we simply need to write another helper 
            function that determines if the bonuspoint qualifications are present and 
            add it to the score. This would be another line in the turnScore() - scoring method.
            Adding new players is also easy since our current methods to create, track, and 
            edit the player already exist and do not dependent on the number of players to be 
            between 2 - 4.
    2. Adding wildcard tiles -
            To address this change, we may need to create new implemtations of Tile or 
            add new tiles in class Tile. Then we need to account for new rules we may need 
            to implement when adding new tiles, including methods to check legal move, place a tile,
            and score.
    3. Imposing restrictions that enforce the rules of Qwirkle instead of Q -
            Imposing new rules is challenging as it may require modification of built methods, 
            such as isValidMove method or strategy's methods. Also, we will need to work on the 
            logic to implement new restrictions and integrate it into the codebase.