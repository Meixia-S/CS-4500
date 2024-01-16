<pre>          
            TO:      Dr. Matthias
            FROM:    Jenny and Meixia
            DATE:    26 October 2023
            SUBJECT: The Game Observer

The observer is a third party who simply wants to view the different game states throughout an already played out game in a 
form  of a slideshow (one that they have the click a button to go through the ordered panels). While the observer is a new 
component to the current program, the way it interacts with the program is quite minimal. It simply needs to connect to the 
server and access the public states of the pre-made list of game states.

The game-observer class fields' are:
<table>     <tr>
                            <th>Name</th>
                            <th>Data Type</th>
                            <th>Definition</th>
            </tr>
            <tr>
                            <th>map</th>
                            <th>Map</th>
                            <th>A copy of the current state of the map being viewed</th>
            </tr>
            <tr>
                            <th>publicStates</th>
                            <th>List<Public_Info></th>
                            <th>A list of all the game states throughout the game</th>
            </tr>
            <tr>
                            <th>mainFrame</th>
                            <th>JFrame</th>
                            <th>The window the person is viewing the game states on</th>
            </tr>
            <tr>
                            <th>mainPanel</th>
                            <th>JPanel</th>
                            <th>The main panel that the game states are generated on</th>
            </tr>
            <tr>
                            <th>next</th>
                            <th>JButton</th>
                            <th>The button that shows the next game state(s)</th>
            </tr>
             <tr>
                            <th>previous</th>
                            <th>JButton</th>
                            <th>The button that shows the previous game state(s)</th>
           </tr> </table>
              Wish List for the Game Observer Class
         _______________________________________________

List &lt;Public_Info>      retrieveAllPublicInfo()      - Retrieve the public states of all game states that this observer can know 

Public_Info             retrieveAPublicInfo()        - Retrieve the public state of one game state at certain point of the game.
                                                       A single person can see the observer's view of the game.

void                    renderGameStates()           - Renders and represents the game states to the observer.
</pre>
