The commit we tagged for your submission is ad45abab09d232f371d1251773cec11ea2ff3a72.
**If you use GitHub permalinks, they must refer to this commit or your self-eval will be rejected.**
Navigate to the URL below to create permalinks and check that the commit hash in the final permalink URL is correct:

https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/tree/ad45abab09d232f371d1251773cec11ea2ff3a72

## Self-Evaluation Form for Milestone 10

Indicate below each bullet which file/unit takes care of each task.

The data representation of configurations clearly needs the following
pieces of functionality. Explain how your chosen data representation.

- implements creation within programs _and_ from JSON specs 
  * The configuration classes we created do not provide internal support for creation from a JSON spec. ScoringConfig.java and RefereeConfig.java are both record classes which provide only the default record constructor for creating these objects within our program. Translation from the JSON specifications into our internal representation of the data within these objects occurs in the xServer script as shown below. We do not have an internal representation for a Server Configuration.
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Common/ScoringConfig.java#L3-L4
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Referee/RefereeConfig.java#L5-L7
  * Creation of the referee cofigeration was by identifying the server configuration and then calling a helper method that parses the 
  Json further and creates the RefereeConfig record object
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Server/xServer.java#L59-L73
  * The creation of the scoring configuration was done in the same way. We pass in the refereeStateConfig JsonObject parse it to get the qbo and fbo values. The we create our scoringConfig record class with the parsed values.
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Server/xServer.java#L94-L106

- enforces that each configuration specifies a fixed set of properties (no more, no less)
  * We do not check that the incoming JSON contains exactly the fixed set of properties the configurations need as we assumed we where only going to be handling valid Json. Our internal representations of the configurations, as record classes with fields corresponding to each of the properties in the corresponding configurations, must specify exactly the fixed set of properties that configuration requires. 

- supports the retrieval of properties 
  * As record classes, RefereeConfig.java and ScoringConfig.java (see above) contain built-in getters to retrieve their internal properties.
  
- sets properties (what happens when the property shouldn't exist?)
  * Referee - RefereeConfig
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Referee/Referee.java#L25-L53
  
  * Game_State - ScoringConfig
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Common/game_state.java#L27-L58
  
  * We do not account for when there is an property shouldn't be there as we assumed the Json would be well formed.

- unit tests for these pieces of functionality
   * No unit tests where written.

Explain how the server, referee, and scoring functionalities are abstracted
over their respective configurations.
  * The referee class contains a constructor that takes in a referee config and the gamestate class (scoring is not separate from the gamestate) contains a constructor that takes in a scoring config. The configuration objects are then broken down into the components they contain, which are stored as fields on the referee and gamestate classes. As we did not implement an internal representation of a server config, the conponents of a server config are passed to the server in its constructor.
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Referee/Referee.java#L36-L53
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Common/game_state.java#L41-L58
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Server/server.java#L50-L58

Does the server touch the referee or scoring configuration, other than
passing it on?
  * No, it does not touch the referee configuration.
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Server/server.java#L50-L58
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Server/server.java#L94

Does the referee touch the scoring configuration, other than passing
it on?
  * No, it does not touch the scoring configuration. Our referee config does not contain the scoring config directly, but instead contains a gamestate with the scoring configuration already inside it, guided by our interpretation of the spec. Our xserver script creates the gamestate contained in the referee config as shown below:
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Server/xServer.java#L59-L92
  * The constructor in the referee class:
  https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/ad45abab09d232f371d1251773cec11ea2ff3a72/Q/Referee/Referee.java#L36-L53



----------------------------------------------------------------------------------------
The ideal feedback for each of these three points is a GitHub
perma-link to the range of lines in a specific file or a collection of
files.

A lesser alternative is to specify paths to files and, if files are
longer than a laptop screen, positions within files are appropriate
responses.

You may wish to add a sentence that explains how you think the
specified code snippets answer the request.

If you did *not* realize these pieces of functionality, say so.

