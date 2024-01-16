<pre>
TO:      Dr. Matthias
FROM:    Aiden and Meixia
DATE:    3 December 2023
SUBJECT: Changes to Make Program Configurable  
</pre>

### What We Needed To Change:
<pre>
    1. Added a new Q/Server/server constructor
        1.1 Created and implemented waitingRoundNum, connectTimeOutTime,and signUpTimeOutTime fields
        1.2 Created and implemented a record class called RefereeConfig that contains the game state, the time a player has to 
            made their move, and the determiner if the game should create a GUI for an observer(s)
            
    2. Added a new Q/Referee/Referee constructor 
        2.1 Created and implemented a timeOutTime field
        
    3. Added a new Q/Common/Game_State constructor 
        3.1 Created a record class called ScoringConfig that holds the bonus values for completing a Q and placing all tiles in 
            one's inventory
</pre>
