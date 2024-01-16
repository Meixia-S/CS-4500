Pair: stoic-armadillos \
Commit: [6478a93a96e64ada11b78a1b096c1714356dd186](https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/tree/6478a93a96e64ada11b78a1b096c1714356dd186) \
Self-eval: https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/c0808175b77b8050234a9395cd72220516ab8bda/9/self-9.md \
Score: 158/210 \
Grader: Vish Jeyaraman

#### [158/210pts] Program Inspection
1. [20/20pts] Helpful and accurate self-eval. 
2. [74/80pts] Proxy Player.
   - [10/10pts] accept *name* calls and return the name sent by the client.
   - [10/10pts] accept *setup* calls, turn them into JSON, get result ("void") in JSON.
   - [10/10pts] accept *take-turn* calls, turn them into JSON, receive a JSON ACTION, return it as an internal value.
   - [10/10pts] accept *new-tiles* calls, turn them into JSON, get result ("void") in JSON.
   - [10/10pts] accept *win* calls, turn them into JSON, get result ("void") in JSON
   - [5/15pts] constructor receive handles for sending/receiving over streams (to mock TCP).
      - Missing handles for streams 
   - [9/15pts] unit tests.
     - Missing. Partial credit for honesty. 
3. [54/70pts] Proxy Referee 
   - [10/10pts] call player *setup* when receive corresponding JSON expression, write the resut as JSON to the output stream.
   - [10/10pts] call player *take-turn* when receive corresponding JSON expression, write the resut as JSON to the output stream.
   - [10/10pts] call player *new-tiles* when receive corresponding JSON expression, write the resut as JSON to the output stream.
   - [10/10pts] call player *win* when receive corresponding JSON expression, write the resut as JSON to the output stream.
   - [5/15pts] constructor receive (1) a player and (2) handles for sending/receiving over streams.
     - Missing handles for sending/receiving over streams 
   - [9/15pts] unit tests.
     - Missing. Partial credit for honesty. 
4. [10/40pts] Client-Server
   - [0/20pts] client wait or shut down gracefully when it cannot connect to the server.
     - Graceful shutdown for the client not implemented, would crash 
   - [10/20pts] abstract over the “wait for two periods” property of the server.
     - "two periods" should not be hard-wired into the code.

