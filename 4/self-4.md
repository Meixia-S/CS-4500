The commit we tagged for your submission is aa765dcc117f7e9a9209c852471d94c6f72d4a6f.
**If you use GitHub permalinks, they must refer to this commit or your self-eval will be rejected.**
Navigate to the URL below to create permalinks and check that the commit hash in the final permalink URL is correct:

https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/tree/aa765dcc117f7e9a9209c852471d94c6f72d4a6f

## Self-Evaluation Form for Milestone 4

Indicate below each bullet which method takes care of each task:

1 'rendering the referee state' 
  void renderGameState

2. 'scoring a placement' 
  int turnScore()

3. The 'scoring a placement' functionality clearly performs four different checks: 
  - 'length of placement'
  -    private HashMap<Posn, Tile> currentTurnAction; 
  - 'bonus for finishing'
  -  https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/aa765dcc117f7e9a9209c852471d94c6f72d4a6f/Q/Common/java/src/Game_State.java#L586-L604
  - 'segments extended along the line (row, column) of placements'
  -   int gettingRowTotalScore(String direction, int row, int col), 438
  -   int gettingColTotalScore(String direction, int row, int col), 463
  - 'segments extended orthogonal to the line (row, column) of placements'
  -  this.alreadyPointedY = new ArrayList<Posn>(); <br>
     this.alreadyPointedX = new ArrayList<Posn>(); <br>
     this.colorOrShapeList = new ArrayList<String>();
  - indicate which of these are factored out into separate
    methods/functions and where.
  - Keep track of which tiles have already been pointed both in a row and col during a players turn. These arrays are added to in line 456 in the gettingRowTotalScore method and in 481 in the gettingColTotalScore method. And a score is added to each time a Posn is added to the arraylists.
   
The ideal feedback for each of these points is a GitHub perma-link to
the range of lines in a specific file or a collection of files.

A lesser alternative is to specify paths to files and, if files are
longer than a laptop screen, positions within files are appropriate
responses.

You may wish to add a sentence that explains how you think the
specified code snippets answer the request.

If you did *not* realize these pieces of functionality, say so.


