# stoic-whales | Group 1
_________________________________________________________________________________________________________________________________________

## Program A - JSON

### Purpose of the Program:

This program has been created to take in one well-formed JSON from STDIN and returns STDOUT as a string. The three main cases are as follows: <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1) Any dictionary keys are not returned  <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2) All non key integers are replaced with the string "number" <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 3) All non key strings are returned without any manipulation <br>
The program can be run on command line with the prompt ./xjson followed by the JSON STDIN. <br>

### Tests:

A single test was created following the n-in.json and n-out.json method specified in the homework assignment directions <br>
(we assigned n to be 0). A n-in.json test can be fed into ./xjson by running ./xjson < Tests/n-in.json <br> <br>
The files can be found in the Tests folder. File path: stoic-whales/A/Tests. 

### Directory Organization:

NOT all files and folders are presented in this display.
The focus is on the location of the code, java jar, json library, and xjson.iml file.

* code:         stoic-whale/A/Other/src/Main.java
* java jar:     stoic-whale/A/Other/A.jar
* json library: stoic-whale/A/Other/lib/jackson-core-2.14.0.jar
* xjson.iml:    stoic-whale/A/Other/xjson.iml
<pre>
                                   stoic-whales
                                        |
         ________________________________________________
           |             |           |                 |                 
        .idea        .vscode         A              ReadMe.md
                                     |
                        _____________________
                          |       |        |         
                        Other   Tests    xjson
                          |       |___________________________________________
             __________________________________________________      _________|_________
                |         |         |          |            |           |           |
             .idea       lib       src       A.jar      xjson.iml    0-in.json   0-out.json
                          |         |
                          |     _____________________
                          |         |          |
                          |       .DS_Store   Main.java
             __________________________________
              |           |
          .DS_Store   jackson-core-2.14.0.jar  
 </pre>         
_________________________________________________________________________________________________________________________________________
## Program B - GUI

### Purpose of the Program:

This program takes in a well-formed and valid JSON STDIN input and outputs an image of all the specified tiles side by side. The image is then saved into the directory with the given file name and then prints the string "Done" once the image has been saved. After that visual que, the user is then able to enter in another well-formed and valid STDIN and another image will be created and saved (unless the same file name is given which then leads to the new image to replace the old one). <br>
The program can be run on command line with the prompt xvfb-run ./xjson followed by the JSON STDIN.

### Tests:

None

### Directory Organization:

NOT all files and folders are presented in this display.
The focus is on the location of the code, java jar, image folder, javaFX library, and pom.xml file.
<em> Due to using maven software with our javaFX GUI we needed to feed in the Jackson library in a pom.xml file </em>

* code:           stoic-whale/B/Other/xgui/src/main/java/com/xgui/Main.java
* code2:          stoic-whale/B/Other/xgui/src/main/java/com/xgui/StartApplication.java
* java jar:       stoic-whale/B/Other/xgui.jar
* image folder:   stoic-whale/B/Other/xgui/images
* javaFX library: stoic-whale/A/Other/lib/...
* pom.xml:        stoic-whale/B/Other/xgui/pom.xml
 <pre>
                          stoic-whales
                               |
  ______________________________________________________________
     |             |         |       |         |            |                 
   .idea        .vscode      A       Q         B         ReadMe.md
                                               |
                            _________________________
                              |        |           |
                            Other   .DS_Store     xgui
                              |
         ___________________________________________________________________
         |              |                               |            |
   .idea/artifacts     lib                            xgui      .DS_Store
                        |                               |
  ______________________|_______________________        |_____________________________________________________       
       |                      |               |             |             |              |          |
 javafx-sdk-20.0.2   javafx-linux-sdk-21    7 more      src/main/java     xgui.jar     images     pom.xml
                                                            |                            |
                                       ________________________________________        30 png
                                          |            |             |
                                        META-INF   com/xgui    moduke-info.java
                                                       |
                                                ___________________________________
                                                    |                 |
                                                Main.java    StartApplication.java
 
</pre>
_________________________________________________________________________________________________________________________________________
## Program 2 - The Map

### Purpose of the Program At This Stage:

The Q-Game program that is heavily based off the well-known Qwirkle board game is currently in its earliest stage. The purpose of the current program is to present a data representation of the game map that supports 3 functionalities described on the assignment description. They are as follows: <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1)	Creating the map with a beginning tile (aka the referee’s tile) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2)	Extend the map with a different tile by placing it beside an already placed tile regardless of the color and shape <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3)	Determining all playable moves based on the game state based on a given tile

### Folder and Files: 
<pre> <table> <tr>
      <th> Folder Name(s) </th>
      <th> Filepath </th>
      <th> Description </th>
    </tr>
    <tr>
      <td> Common </td>
      <td>stoic-whales/Q/Common </td>
      <td>folder that contains all the java files needed for the program to work, however each
file contains only the link to its corresponding file placed in another folder </td>
    </tr>
    <tr>
      <td> Planning </td>
      <td>stoic-whales/Q/Planning </td>
      <td>contains the state.md file </td>
    </tr>
     <tr>
      <td> Q_Game </td>
      <td>stoic-whales/Q/Q_Game </td>
      <td>contains all the java files with the code and along with the nested folders (the files
that are linked to the ones in the Common folder) </td>
    </tr>
     <tr>
      <td> test/java/org/common </td>
      <td>stoic-whales/Q/Q_Game/src/test/java/org/common </td>
      <td>contains all the java test files </td>
    </tr> </table> <table> <tr>
      <th> File Name </th>
      <th> Filepath </th>
      <th> Description </th>
    </tr>
    <tr>
      <td> Board </td>
      <td>stoic-whales/Q/Common/Board.java <b> or </b> 
stoic-whalesQ/Q_Game/src/main/java/org/common/Board.java </td>
      <td>contains the interface for the map </td>
    </tr>
    <tr>
      <td> Map </td>
      <td>stoic-whales/Q/Common/Map.java <b> or </b>  
stoic-whalesQ/Q_Game/src/main/java/org/common/Map.java </td>
      <td>contains the Map class that implements the Board interface </td>
    </tr>
     <tr>
      <td> Rule </td>
      <td>stoic-whales/Q/Common/Rule.java <b> or </b>  
stoic-whalesQ/Q_Game/src/main/java/org/common/Rule.java </td>
      <td>contains the interface for the rules pertaining to the game </td>
    </tr>
    <tr>
      <td> Rule_Extend_A_Side </td>
      <td>stoic-whales/Q/Common/Rule_Extend_A_Side.java <b> or </b>  
stoic-whalesQ/Q_Game/src/main/java/org/common/Rule_Extend_A_Side.java </td>
      <td>contains the Rule_Extend_A_Side class that implements the Rule interface </td>
    </tr>
    <tr>
      <td> Rule_Must_Match_Neighbors </td>
      <td>stoic-whales/Q/Common/Rule_Must_Match_Neighbors.java <b> or </b>  
stoic-whalesQ/Q_Game/src/main/java/org/common/Rule_Must_Match_Neighbors.java </td>
      <td>contains the Rule_Must_Match_Neighbors class that implements the Rule interface </td>
    </tr>
     <tr>
      <td> Tile </td>
      <td>stoic-whales/Q/Common/Tile.java <b> or </b>  
stoic-whalesQ/Q_Game/src/main/java/org/common/Tile.java </td>
      <td>contains the Tile interface for the tile pieces </td>
    </tr>
      <tr>
      <td> Valid_Tile </td>
      <td>stoic-whales/Q/Common/Valid_Tile.java <b> or </b>  
stoic-whalesQ/Q_Game/src/main/java/org/common/Valid_Tile.java </td>
      <td>contains the Valid_Tile class that implements the Tile interface </td>
    </tr>
    <tr>
      <td> Board_Test </td>
      <td>stoic-whales/Q/Q_Game/src/test/java/org/common/Board_Test.java </td>
      <td>contains all the tests that exist within the class 
(ones that support all the required functionalities)</td>
    </tr>  
    <tr>
      <td> Rule_Extends_A_Side_Test </td>
      <td>stoic-whales/Q/Q_Game/src/test/java/org/common/Rule_Extends_A_Side_Test.java </td>
      <td>contains the tests that checks that the methods correctly enforce the rule </td>
    </tr>
     <tr>
      <td> Rule_Must_Match_Neighbors_Test </td>
      <td>stoic-whales/blob/main/Q/Q_Game/src/test/java/org/common/Rule_Must_Match_Neighbors_Test.java </td>
      <td>contains the tests that checks that the methods correctly enforce the rule </td>
    </tr> 
    <tr>
      <td> Valid_Tile_Test </td>
      <td>stoic-whales/blob/main/Q/Q_Game/src/test/java/org/common/Valid_Tile_Test.java </td>
      <td>making sure Tiles are correctly assembled </td>
    </tr> </table> </pre>  

### Tests:

All tests can be run by running ./x_test

FileName: Board_Test <br>
Filepath: stoic-whales/blob/main/Q/Q_Game/src/test/java/org/common/BoardTest.java <br>
Purpose: Testing the methods that are within the Map class <br>

FileName: Rule_Extends_A_Side_Test <br>
Filepath: stoic-whales/blob/main/Q/Q_Game/src/test/java/org/common/Rule_Extends_A_SideTest.java <br>
Purpose: Making sure the rule is followed all formates of the board <br>

FileName: Rule_Must_Match_Neighbors_Test <br>
Filepath: stoic-whales/blob/main/Q/Q_Game/src/test/java/org/common/Rule_Extends_A_SideTest.java <br>
Purpose: Making sure the rule is followed all formates of the board <br>

FileName: Valid_Tile_Test <br>
Filepath: stoic-whales/blob/main/Q/Q_Game/src/test/java/org/common/Valid_Tile_Test.java <br>
Purpose: Making sure Tiles are correctly assembled <br>

### UML Class Diagram:
![diagram](https://media.github.khoury.northeastern.edu/user/15076/files/3770a704-a223-4edd-9e2a-903c7ce25919)
_________________________________________________________________________________________________________________________________________ 
## Program C - TCP

### Purpose of the Program:

This program has been created to take in one well-formed JSON from STDIN sent in by the client and returns STDOUT as a string on the server side. The three main cases are as follows: <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1) Any dictionary keys are not returned  <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2) All non key integers are replaced with the string "number" <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 3) All non key strings are returned without any manipulation <br>
The program can be run on command line with the prompt ./xtcp followed by joining the localhost server and the the JSON STDIN. <br>

### Tests:

Three tests were created following the n-in.json and n-out.json method specified in the homework assignment directions. <br>
A n-in.json test can be fed into ./xtcp by running ./xtcp < Tests/n-in.json <br> <br>
The files can be found in the Tests folder. File path: stoic-whales/C/Tests. 

### Directory Organization:

NOT all files and folders are presented in this display.
The focus is on the location of the code, java jar, json library, and xtcp.iml file.

* code: stoic-whales/C/Other/.xtcp/src/Main.java
* code2: stoic-whales/C/Other/.xtcp/src/Json_Parser_A.java
* code3: stoic-whales/C/Other/.xtcp/src/Server.java
* java jar: stoic-whales/C/Other/.xtcp/xtcp.jar
* Json Library: stoic-whales/C/Other/lib/jackson.core-2.14.0
* xtcp.iml: stoic-whales/C/Other/.xtcp/xtcp.iml

<pre>
                          stoic-whales
                               |
  ______________________________________________________________
     |             |         |      |       |      |        |                 
   .idea        .vscode      A      B       C      Q    ReadMe.md
                                            |
                               _____________________________________
                                  |      |        |        |
                                .idea  Other    Tests  .DS_Store
                                         |        |
              ___________________________|        |________________________________________________________________
                |     |       |        |              |           |          |        |           |          |
               lib   idea  .DS_Store .xtcp        0-in.json 0-out.json  1-in.json 1-out.json  0-in.json 0-out.json
                |                      |
  ______________|_______________       |______________________________________________________________
    |              |                    |      |      |          |         |              |
.DS_Store   jackson.core-2.14.0        idea   src  .DS_Store  xtcp.iml  xtcp.jar  out/production/xtcp        
                                               |
                                     __________|_______________________________________________________
                                        |          |              |               |            |
                                     META-INF  .DS_Store  Json_Parser_A.java   Main.java   Server.java
</pre>
_________________________________________________________________________________________________________________________________________ 
## Program 3 - The State

### Purpose of the Program At This Stage:

The purpose of the current program is to present a data representation of the referee's game state and of the player's information that is given to the player during their turn. Both these representations support the 3 functionalities described on the assignment description. They are as follows: <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1)	Extracting the data to be sent to the player during their turn <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2)	Completing a turn action <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3)	Checking whether a proposed placement by the currently active player satisfies the rules of The Q Game

### Folder and Files: 
<pre> <table> <tr>
        <th> Folder Name(s) </th>
        <th> Filepath </th>
        <th> Description </th>
    </tr>
    <tr>
        <td> Common </td>
        <td>stoic-whales/Q/Common </td>
        <td>folder that contains all the java files needed for the program to work. Each
  file contains only the link to its corresponding file placed in another folder </td>
    </tr>
     <tr>
        <td> Planning </td>
        <td>stoic-whales/Q/Planning </td>
        <td>contains the player_interface.md file and the other planning memos </td>
    </tr>   
        <td> Q_Game </td>
        <td>stoic-whales/Q/Q_Game </td>
        <td>contains all the java files with the code and along with the nested folders 
  (the files that are linked to the ones in the Common folder) </td>
    </tr> </table> <table> <tr>
        <th> File Name </th>
        <th> Filepath </th>
        <th> Description </th>
    </tr>
     <tr>
        <td> Game_State </td>
        <td>stoic-whales/Q/Q_Game/src/test/java/org/common/Q_Game_State.java </td>
        <td>contains the interface that represents the referee's game state </td>
    </tr>
    <tr>
        <td> Players_Game_State </td>
        <td>stoic-whales/Q/Q_Game/src/test/java/org/common/Player_Game_State.java </td>
        <td>the class that allows the referee to send important and relevent 
  information to the player during their turn </td>
    </tr> 
     <tr>
        <td> Q_Game_State </td>
        <td>stoic-whales/Q/Q_Game/src/test/java/org/common/Q_Gamr_State.java </td>
        <td>contains the class that implements the Game_State interface </td>
    </tr>
     <tr>
        <td> Tile </td>
        <td>stoic-whales/Q/Q_Game/src/test/java/org/common/Tile.java </td>
        <td> We got rid of the Tile interface and refactored the Valid_Tile
class to Tile</td>
    </tr> </table> </pre>
 * Player: An empty class that will be used to represent individual players in the future
    
### Tests:
All JSON test specific to this phase of the project can be run using the jar file using ./xmap command.

All tests are located in the 3/Tests folder.

### UML Class Diagram:
![diagram](https://media.github.khoury.northeastern.edu/user/15076/files/bfae8d94-6c0a-45c5-b1d8-8e39087e2359)

# ambidextrous-mice | Group 2
__________________________________________________________________________________________
## Milestone 4 - The Score

### Purpose of the Added Functionality Program At This Stage:
The purpose of the current program is to equip our game-state component with the following two pieces: <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1) functionality for rendering the current state, including its map, graphically <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2) functionality for scoring a placement (assuming the move is legal)

### Folder and Files:
 
 <table> <tr> <th>
                  Folder Name(s)
             </th>
             <th>
                  Filepath
             </th>
             <th>
                  Description
             </th>
       </tr>
         <tr>
             <td>
                  Q
             </td>
             <td>
                  ambidextrous-mice/Q
             </td>
             <td>
                  contains the Common and Planning folder and their repective files
             </td>
       </tr>
         <tr>
             <td>
                  4/Tests
             </td>
             <td>
                  ambidextrous-mice/4/Tests
             </td>
             <td>
                  The folderss that contain the Json test files
             </td>
       </tr>
 </table> <table>
       <tr>
             <th>
                  File Name
             </th>
             <th>
                  Filepath
             </th>
             <th>
                  Description
             </th>
       </tr>
       <tr>
             <td>
                  Game_State.java
             </td>
             <td>
                  ambidextrous-mice/Q/Common/java/src/Game_State.java
             </td>
             <td>
                  the class that represents the game state 
             </td>
       </tr>
         <tr>
             <td>
                  Game_State_GUI.java
             </td>
             <td>
                  ambidextrous-mice/Q/Common/java/src/Game_State_GUI.java
             </td>
             <td>
                  the class that handles the visiual representation of the game state
             </td>
       </tr>
        <tr>
             <td>
                  XLegal.java
             </td>
             <td>
                  ambidextrous-mice/Q/Common/java/src/XLegal.java
             </td>
             <td>
                  class that reads the Json STDIN and returns Json STDOUT (a boolean or JMap)
             </td>
       </tr> 
        <tr>
             <td>
                  Test Description.md
             </td>
             <td>
                  ambidextrous-mice/4/Tests/Test Description.md
             </td>
             <td>
                  a file that explains what is being tested/checked in each test in the Tests folder
             </td>
       </tr> </table>	  	  

### Tests:
All JSON test specific to this phase of the project can be run using the jar file using ./xlegal command.

All tests are located in the 4/Tests folder.

### UML Class Diagram:
![diagram](https://media.github.khoury.northeastern.edu/user/15076/files/e11879f6-a270-4aa7-a098-17cefc8ad56f)
__________________________________________________________________________________________________________________________
## Milestone 5 - The Strategy

### Purpose of the Added Functionality Program At This Stage:
The purpose of the current program is to implement a strategy interface and two concrete variants:<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1) The first one is called dag <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2) The second is called ldasg  <br>
The lexicographic ordering on tiles says that tile p is less than tile q if p’s shape is less than q’s. If p’s shape is 
identical to q’s, p is below q iff p’s color is below q’s. The order of the shapes and colors are: <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Shape: star < 8star < square < circle < clover < diamond <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Color: red < green < blue < yellow > orange < purple <br>
We then equip the strategy component with an iteration functionality. Starting from some game state, the piece of 
functionality applies a given strategy as far as possible to obtain the longest possible series of placements or 
a replacement decision or a pass decision.

### Tests:
All JSON test specific to this phase of the project can be run using the jar file using ./xscore command.

All tests are located in the 5/Tests folder.

### UML Class Diagram:
![diagram](https://media.github.khoury.northeastern.edu/user/15076/files/26245aa9-0edc-4e5e-bc63-ba37f8ecb011)
__________________________________________________________________________________________________________________________
## Milestone 6 - The Game

### Purpose of the Added Functionality Program At This Stage:
The purpose of the current program is to create and implement the referee and players in the program: <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The player should be able to pick a strategy to implement and  <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The referee should be able to communicate with the players and game state <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; until the game is over. Then they announce the winner(s) and end the game. <br>
As the title implies this compelents the basic operations and comonents for the program to conduct a full game.

### Folder and Files:
 
 <table> <tr> <th>
                  Folder Name(s)
             </th>
             <th>
                  Filepath
             </th>
             <th>
                  Description
             </th>
       </tr>
         <tr>
             <td>
                  Referee
             </td>
             <td>
                  ambidextrous-mice/Q/Referee
             </td>
             <td>
                  contains the referee class
             </td>
       </tr>
         <tr>
             <td>
                  6/Tests
             </td>
             <td>
                  ambidextrous-mice/6/Tests
             </td>
             <td>
                  The folderss that contain the Json test files
             </td>
       </tr>
 </table> <table>
       <tr>
             <th>
                  File Name
             </th>
             <th>
                  Filepath
             </th>
             <th>
                  Description
             </th>
       </tr>
       <tr>
             <td>
                  player.java
             </td>
             <td>
                  ambidextrous-mice/Q/Player/player.java
             </td>
             <td>
                  the class that represents the players
             </td>
       </tr>
         <tr>
             <td>
                  referee.java
             </td>
             <td>
                  ambidextrous-mice/Q/Referee/referee.java
             </td>
             <td>
                  the class that represents the referee
             </td>
       </tr>
        <tr>
             <td>
                  XStrategy.java
             </td>
             <td>
                  ambidextrous-mice/Q/Player/XStrategy.java
             </td>
             <td>
                  class that reads the Json STDIN and returns Json STDOUT (1Placement)
             </td>
       </tr> </table>	  	  

### Tests:
All JSON test specific to this phase of the project can be run using the jar file using ./xstrategy command.

All tests are located in the 6/Tests folder.

### UML Class Diagram:
![diagram](https://media.github.khoury.northeastern.edu/user/15076/files/c27542e9-6819-45ee-ab35-cdd215519cbb)
__________________________________________________________________________________________________________________________
## Milestone 7 - The Clean Up

### Purpose of the Added Functionality Program At This Stage:
The purpose of the current program is to create and implement the referee and players in the program: <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The player should be able to pick a strategy to implement and  <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The referee should be able to communicate with the players and game state <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; until the game is over. Then they announce the winner(s) and end the game. <br>
As the title implies this compelents the basic operations and comonents for the program to conduct a full game.

### Folder and Files:
 
 <table> <tr> <th>
                  Folder Name(s)
             </th>
             <th>
                  Filepath
             </th>
             <th>
                  Description
             </th>
       </tr>
         <tr>
             <td>
                  Referee
             </td>
             <td>
                  ambidextrous-mice/Q/Referee
             </td>
             <td>
                  contains the referee class
             </td>
       </tr>
         <tr>
             <td>
                  6/Tests
             </td>
             <td>
                  ambidextrous-mice/6/Tests
             </td>
             <td>
                  The folderss that contain the Json test files
             </td>
       </tr>
 </table> <table>
       <tr>
             <th>
                  File Name
             </th>
             <th>
                  Filepath
             </th>
             <th>
                  Description
             </th>
       </tr>
       <tr>
             <td>
                  player.java
             </td>
             <td>
                  ambidextrous-mice/Q/Player/player.java
             </td>
             <td>
                  the class that represents the players
             </td>
       </tr>
         <tr>
             <td>
                  referee.java
             </td>
             <td>
                  ambidextrous-mice/Q/Referee/referee.java
             </td>
             <td>
                  the class that represents the referee
             </td>
       </tr>
        <tr>
             <td>
                  XStrategy.java
             </td>
             <td>
                  ambidextrous-mice/Q/Player/XStrategy.java
             </td>
             <td>
                  class that reads the Json STDIN and returns Json STDOUT (1Placement)
             </td>
       </tr> </table>	  	  

### Tests:
All JSON test specific to this phase of the project can be run using the jar file using ./xstrategy command.

All tests are located in the 6/Tests folder.

### UML Class Diagram:
![diagram](https://media.github.khoury.northeastern.edu/user/15076/files/c27542e9-6819-45ee-ab35-cdd215519cbb)
_____________________________________________________________________________________________________________________________________________

# stoic-armadillos
_____________________________________________________________________________________________________________________________________________
## Milestone 8 - The Observer

### Purpose of the Added Functionality Program At This Stage:
The purpose of the current program is to create an user interface that represents the game state at every phase of the game if an oberver exists.
Its two main features are: <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. saving the states it receives as PNG files in a directory called Tmp/, naming each image the order in which it was created (ex: 0.png)  <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. displays the states it receives as images in a GUI. This GUI supports the following three interaction capabilities:<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.1 a next functionality shows the next state of the game, if it is available <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.2 a previous functionality shows the previous state of the game, if it is available <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.3 a save functionality saves the current state as an instance of JState in a file of the user’s choice.

### Folder and Files:
<table> <tr>
    <th>Folder Name(s)</th>
    <th>Filepath</th>
    <th>Description</th>
  </tr> <tr>
    <td>7/Tests</td>
    <td>sly-dolphins/7/Tests</td>
    <td>the folder that contains the JSON test files</td>
  </tr> </table> <table> <tr>
  <th>File Name</th>	
  <th>Filepath</th>
  <th>Description</th> 
  </tr> <tr>
    <td>Xgames-with-observer.java</td>
    <td>sly-dolphins/Q/Referee/Xgames-with-observer.java</td>
    <td>this class that reads the Json STDIN and returns Json STDOUT</td> 
  </tr> <tr>
    <td>ObservableGameState</td>
    <td>sly-dolphins/Q/Common/ObservableGameState.java</td>
    <td>this interface represents the functionality for observing/viewing a game state.
        Does not include any functionality for modifying the state</td> 
  </tr> <tr>
    <td>game_state</td>
    <td>sly-dolphins/Q/Common/game_state.java</td>
    <td>this class now implements the ObservableGameState</td> 
  </tr> 
    <td>JSON_Processor</td>
    <td>sly-dolphins/Q/Player/xgames-with-observer.java</td>
    <td>this class contains methods meant for converting JSON to our data 
        representation and vice versa</td> 
  </tr> <tr>
    <td>Observer</td>
    <td>sly-dolphins/Q/Referee/Observer.java</td>
    <td>this class that represents an observer of the game</td> 
  </tr> <tr>
    <td>Observer_GUI</td>
    <td>sly-dolphins/Q/Referee/Observer_GUI.java</td>
    <td>this class represents the oberver and the GUI interface it uses</td> 
  </tr> </table>

### Tests:
All JSON test specific to this phase of the project can be run using the jar file using ./xgames-with-observer command.

All tests are located in the 8/Tests folder.

### UML Class Diagram:
![diagram](https://media.github.khoury.northeastern.edu/user/15076/files/4830a6cb-dce5-4ef0-83ec-bd733ac82f84)
_____________________________________________________________________________________________________________________________________________
## Milestone 9 - Remote

### Purpose of the Added Functionality Program At This Stage:
The purpose of the current program is to create the functionality that allows for third party players join the game server and play
through a game. Once the server is on, players are given 20 seconds to join (with 3 seconds to enter their names) and additional 20
seconds if less then two players join. If less then two players join the server will not start a game and will return the JSON STDIN 
"[[ ], [ ]]". If two or more players join, the game will starts and play out as expected. If players take to long to complete certain
tasks (6 seconds) during the game they will be removed as well. If there are any issues on the server side that disrupts or changes 
objects within the game, the server will terminate the game and shut down the server.

### Folder and Files:
<table> <tr>
    <th>Folder Name(s)</th>
    <th>Filepath</th>
    <th>Description</th>
  </tr> <tr>
    <td>9/Tests</td>
    <td>stoic-armadillos/9/Tests</td>
    <td>the folder that contains the JSON test files</td>
  </tr> <tr>
    <td>Client</td>
    <td>stoic-armadillos/Q/Client</td>
    <td>the folder that contains proxy referee and client IPlayer</td>
  </tr> <tr>
    <td>Server</td>
    <td>stoic-armadillos/Q/Server</td>
    <td>the folder that contains proxy player and host server</td>
  </tr> </table> <table> <tr>
  <th>File Name</th>	
  <th>Filepath</th>
  <th>Description</th> 
  </tr> <tr>
    <td>xbaddies</td>
    <td>stoic-armadillos/Q/Referee/xbaddies.java</td>
    <td>this class that reads the Json STDIN and returns Json STDOUT</td> 
  </tr> <tr>
    <td>DFSStrategy</td>
    <td>stoic-armadillos/Q/Player/DFSStrategy.java</td>
    <td>this class is the smart DFS strategy methods a client player should use</td> 
  </tr> <tr>
    <td>referee</td>
    <td>stoic-armadillos/Q/Client/referee.java</td>
    <td>the class that represents the proxy referee for players who are connecting to the server</td> 
  </tr> <tr>
    <td>client</td>
    <td>stoic-armadillos/Q/Client/client.java</td>
    <td>the class that represents the cilent (aka players) who joins the game server</td> 
  </tr> 
    <td>player</td>
    <td>stoic-armadillos/Q/Server/player.java</td>
    <td>the class that represents the proxy player</td> 
  </tr> <tr>
    <td>server</td>
    <td>stoic-armadillos/Q/Server/server.java</td>
    <td>the class that starts the server and then starts the game once two or more players join the server</td> 
  </tr> </table>

### Tests:
All JSON test specific to this phase of the project can be run using the jar file using ./xbaddies command.

All tests are located in the 9/Tests folder.

### UML Diagram:
![diagram](https://media.github.khoury.northeastern.edu/user/15076/files/fa72430e-ac20-4d15-ada7-a5b8f23da709)

_____________________________________________________________________________________________________________________________________________
## Milestone 10 - Revised/End

### Purpose of the Added Functionality Program At This Stage:
At this point in the process we have finally ready to put everything together. The game is fully functioning and players can join the host
server once it has been started. Players can chose one of the three strategies to implement throughout the game. 

### Folder and Files:
<table> <tr>
    <th>Folder Name(s)</th>
    <th>Filepath</th>
    <th>Description</th>
  </tr> <tr>
    <td>10/Tests</td>
    <td>stoic-armadillos/10/Tests</td>
    <td>the folder that contains the JSON test files</td>
  </tr> <table> <tr>
  <th>File Name</th>	
  <th>Filepath</th>
  <th>Description</th> 
  </tr> <tr>
    <td>xserver</td>
    <td>stoic-armadillos/Q/Server/xserver.java</td>
    <td>this class that reads the Json STDIN and returns Json STDOUT on the Server side</td> 
  </tr> <tr>
    <td>xclient</td>
    <td>stoic-armadillos/Q/Client/xclient.java</td>
    <td>this class that reads the Json STDIN and returns Json STDOUT on the Client side</td> 
  </tr> <tr>
    <td>Utils</td>
    <td>stoic-armadillos/Q/Common/Utils.java</td>
    <td>contains the various static untility methods that are not specific to a certain data type</td> 
  </tr> </table>

### Tests:
All JSON test specific to this phase of the project can be run using the jar file using the following command: <br>
&nbsp;&nbsp;&nbsp;&nbsp;$ ./xserver PortNumber < FileNameForServerConfig & <br>
&nbsp;&nbsp;&nbsp;&nbsp;$ ./xclients PortNumber < FileNameForClientConfig & <br>

All tests are located in the 10/Tests folder.

### UML Diagram:
![diagram](https://media.github.khoury.northeastern.edu/user/15076/files/bb071570-83ac-4858-8896-214507005355)
    
### Sequence Diagram:
![image](https://media.github.khoury.northeastern.edu/user/6070/files/602706e0-2f6e-427d-9e4d-48e37cd02ab0)

