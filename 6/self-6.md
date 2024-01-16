The commit we tagged for your submission is 075ae1a854177bf47dbb4d46126eb7ffd5d4c06f.
**If you use GitHub permalinks, they must refer to this commit or your self-eval will be rejected.**
Navigate to the URL below to create permalinks and check that the commit hash in the final permalink URL is correct:

https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/tree/075ae1a854177bf47dbb4d46126eb7ffd5d4c06f

## Self-Evaluation Form for Milestone 6

Indicate below each bullet which piece of your code takes care of each task:

1. the five pieces of player functionality
    * name
    https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Player/player.java#L82-L87
    
    * setup
    https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Player/player.java#L89-L98
    
    * taketurn
    https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Player/player.java#L100-L111
    
    * newtiles
    https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Player/player.java#L113-L125
    
    * win
    https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Player/player.java#L127-L132

2. `setting up players` functionality in the referee component 
    * https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Referee/referee.java#L61-L73

3. `running a game` functionality in the referee component
    * https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Referee/referee.java#L39-L59

4. `managing a round` functionality in the referee component
    (This must be factored out to discover the end-of-game condition.)
    * https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Referee/referee.java#L39-L59
    * Line 51 to 53, this tracks the moves each player plays during one round

5. `managing an individual turn` functionality in the referee component
    * https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Referee/referee.java#L75-L163

6. `informing survivors of the outcome` functionality in the referee component
    * Lines 56 to 58
    * https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Referee/referee.java#L39-L59
    * https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Referee/referee.java#L239-L281

7. unit tests for the `referee`:

   - five distinct unit tests for the overall `referee` functionality (link is red but we wrote tests before the deadline)
        * https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Tests/TestRefereeAndPlayer.java#L81-L202
   - unit tests for the above pieces of functionality 
   
8. the explanation of what is considered ill-behaved player and how the referee deals with it.
    * takes too long to make a move
    https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Referee/referee.java#L75-L92
    
    * makes an illegal move (line 137)
    https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Referee/referee.java#L122-L144
    
    * trys to exchange when there are not enough to do so (line 154)
    https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/98c8229edfddec472195fb3d35441bb46d51441c/Q/Referee/referee.java#L146-L156

The ideal feedback for each of these points is a GitHub perma-link to
the range of lines in a specific file or a collection of files.

A lesser alternative is to specify paths to files and, if files are
longer than a laptop screen, positions within files are appropriate
responses.

You may wish to add a sentence that explains how you think the
specified code snippets answer the request.

If you did *not* realize these pieces of functionality, say so.


