The commit we tagged for your submission is c2dd15511935b1028dfc2de93e8a6fa9cca575a1.
**If you use GitHub permalinks, they must refer to this commit or your self-eval will be rejected.**
Navigate to the URL below to create permalinks and check that the commit hash in the final permalink URL is correct:

https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/tree/c2dd15511935b1028dfc2de93e8a6fa9cca575a1

## Self-Evaluation Form for Milestone 5

Indicate below each bullet which piece of your code takes care of each task:

1. a data definition (inc. interpretation) for the result of a strategy
We have a strategy interface and AStrategy abstract class
This code returns the position which tile is placed and where when player applies a the strategy. Player will exchange or pass if place is not possible.
https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/c2dd15511935b1028dfc2de93e8a6fa9cca575a1/Q/src/Player/strategy.java#L27
https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/c2dd15511935b1028dfc2de93e8a6fa9cca575a1/Q/src/Player/DagStrategy.java#L25-L30
https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/c2dd15511935b1028dfc2de93e8a6fa9cca575a1/Q/src/Player/LdasgStrategy.java#L71-L75
2. the `dag` strategy 
https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/c2dd15511935b1028dfc2de93e8a6fa9cca575a1/Q/src/Player/DagStrategy.java#L16-L31
3. the `ldasg` strategy 
https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/c2dd15511935b1028dfc2de93e8a6fa9cca575a1/Q/src/Player/DagStrategy.java#L16-L31

4. a data definition (inc. interpretation) for the result of a strategy iterator
https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/c2dd15511935b1028dfc2de93e8a6fa9cca575a1/Q/src/Player/Player_Input.java#L8-L43
https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/c2dd15511935b1028dfc2de93e8a6fa9cca575a1/Q/src/Player/AStrategy.java#L34-L55

 

5. unit tests for the `dag` strategy
   - one for a 'pass' decision
   - one for a 'replace all tiles' decision
   - one for a 'place this tile there' decision

6. unit tests for the `ldaag` strategy
   - one for a 'pass' decision
   - one for a 'replace all tiles' decision
   - one for a 'place this tile there' decision

7. unit tests for the strategy iteration functionality 
   - one for a 'pass' decision
   - one for a 'replace all tiles' decision
   - one for a _sequence of_ 'place this tile there' decision
We don't have tests

8. how does your design abstract the common strategy iteration functionality 
https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/c2dd15511935b1028dfc2de93e8a6fa9cca575a1/Q/src/Player/AStrategy.java#L34-L55

9. does your design abstract the common search through the sorted tiles?
   (for a bonus)
<pre>
  Yes this can be seen in the AStragey class where most methods are abstarcted in both strategy classes:
  determinePlaceableTiles(), getPlaceablePosns, and getSamllestTiles().
</pre>
https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/c2dd15511935b1028dfc2de93e8a6fa9cca575a1/Q/src/Player/AStrategy.java#L75-L123
   
The ideal feedback for each of these points is a GitHub perma-link to
the range of lines in a specific file or a collection of files.

A lesser alternative is to specify paths to files and, if files are
longer than a laptop screen, positions within files are appropriate
responses.

You may wish to add a sentence that explains how you think the
specified code snippets answer the request.

If you did *not* realize these pieces of functionality, say so.


