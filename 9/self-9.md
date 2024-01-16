The commit we tagged for your submission is 6478a93a96e64ada11b78a1b096c1714356dd186.
**If you use GitHub permalinks, they must refer to this commit or your self-eval will be rejected.**
Navigate to the URL below to create permalinks and check that the commit hash in the final permalink URL is correct:

https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/tree/6478a93a96e64ada11b78a1b096c1714356dd186

## Self-Evaluation Form for Milestone 9

Indicate below each bullet which file/unit takes care of each task.

For `Q/Server/player`,

- explain how it implements the exact same interface as `Q/Player/player`
    https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/6478a93a96e64ada11b78a1b096c1714356dd186/Q/Server/player.java#L26-L30
    * implements the IPlayer interface which contains the 5 methods listed in the Player API. It takes in our data representations
      and concevters them into JSON STDIN.
- explain how it receives the TCP connection that enables it to communicate with a client
    * https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/6478a93a96e64ada11b78a1b096c1714356dd186/Q/Server/server.java#L91-L118
    * In the server class, the signUp methods waits for players to connect and accepts their connection.
- point to unit tests that check whether it writes (proper) JSON to a mock output device
    * none

For `Q/Client/referee`,

- explain how it implements the same interface as `Q/Referee/referee`
    * We chose not to have our referees implement an interface. Since the server will only ever use a Q/Referee/referee and a client would only ever use a Q/Client/referee, we did not feel that it was necessary to create an interface that both classes implement. Though both classes essentially run a looping method that encapsulates the playing of a complete game of Q, the requirement that the runGame method on Q/Referee/referee return the winners and misbehaved players also did not make much sense in the context of the Q/Client/referee.
- explain how it receives the TCP connection that enables it to communicate with a server
    * https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/6478a93a96e64ada11b78a1b096c1714356dd186/Q/Client/referee.java#L44-L47
    * The startConnection method on the client referee takes as arguments the ip adress and port number of the server, and creates a socket on the specified port number of the server's address.
- point to unit tests that check whether it reads (possibly broken) JSON from a mock input device
    * none

For `Q/Client/client`, explain what happens when the client is started _before_ the server is up and running:

- does it wait until the server is up (best solution)
    * We treated out Client/client class as simply an instance of our Player interface (albeit one with a smarter strategy than DAG or LDASG). As such, it does not deal with connecting to the server. We would need an additional script to handle creating a Q/Client/referee (with an associated IPLayer) and waiting until the server is running.
    * https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/6478a93a96e64ada11b78a1b096c1714356dd186/Q/Client/client.java#L12-L46
- does it shut down gracefully (acceptable now, but switch to the first option for 10)
    * See above

For `Q/Server/server`, explain how the code implements the two waiting periods. <br>
    1. In the server's startGame method, runSignUpPeriod is called:
       https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/6478a93a96e64ada11b78a1b096c1714356dd186/Q/Server/server.java#L36-L61
    2. In the runSignUpPeriod method, we wait 20 seconds for the signUp method to be executed. In line 86 a second call to this method is made if 
       there are less then 2 players signed up at the end of the first 20 second wait period. Another 20 seconds is given if 0 - 1 players join.
       https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/6478a93a96e64ada11b78a1b096c1714356dd186/Q/Server/server.java#L76-L89
    3. It does this by making a call to our utils class that handles our wait period a bit more neatly by using generics to create a "template"
       that waits for the given amount in seconds and the method to execute once the input is given (method name runMethodWithTimeout). 
       https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/6478a93a96e64ada11b78a1b096c1714356dd186/Q/Common/Utils.java#L14-L31
    4. In the call to runMethodWithTimeout, the method signUp is called. This is how we wait 3 seconds after a player has connected to the game 
       server to enter their name.
       https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/6478a93a96e64ada11b78a1b096c1714356dd186/Q/Server/server.java#L91-L118
    5. If 0 - 1 players are connected once the 40 seconds are up (or if the thread manipulation produces an error) the server will shut down gracefully
       https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/6478a93a96e64ada11b78a1b096c1714356dd186/Q/Server/server.java#L43-L61

The ideal feedback for each of these three points is a GitHub
perma-link to the range of lines in a specific file or a collection of
files.

A lesser alternative is to specify paths to files and, if files are
longer than a laptop screen, positions within files are appropriate
responses.

You may wish to add a sentence that explains how you think the
specified code snippets answer the request.

If you did *not* realize these pieces of functionality, say so.

