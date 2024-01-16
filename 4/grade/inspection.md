Pair: ambidextrous-mice \
Commit: [aa765dcc117f7e9a9209c852471d94c6f72d4a6f](https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/tree/aa765dcc117f7e9a9209c852471d94c6f72d4a6f) \
Self-eval: https://github.khoury.northeastern.edu/CS4500-F23/ambidextrous-mice/blob/7bfced7a00e32aa2b63405bb66802ce45b796c0e/4/self-4.md \
Score: 30/70 \
Grader: Derek Leung

inspection: [20/60] points

- [0/20] points, for helpful self-eval

[25/40] points, distributed as follows, at 60% scale because no self eval => [12/40]:

signatures and purpose statements of the following methods/functions

- [5/5] points for rendering the referee state

- [5/5] points for scoring a placement

unit tests for ’scoring a placement’:

- [0/5] points for for at least one should cover the bonus for a "Q"

- [0/5] points for for at least one should cover the case when there is no bonus

factoring of ’scoring a placement’. The function consists of four counting tasks:

- [0/5] points for ’length of placement’

- [5/5] points for ’bonus for finishing’

- [5/5] points for ’segments extended along the line (row, column) of placements’

- [5/5] points for ’segments extended orthogonal to the line (row, column) of placements’

design: [5/10] points

- [5/10] points, a referee must call the following methods in order: ‘take turn‘ then ‘accept tiles‘. The second call must be optional.
