*Java random generators test*

This is small experiment on how accurate Java produce random booleans.

Generating random booleans with MathRandom
Streak                Chance (Experiment)           Chance (Theory)
------                -------------------           ---------------
row of 1                       .499743E00                     .5E00
row of 2                       .249677E00                    .25E00
row of 3                       .125148E00                   .125E00
row of 4                       .63166E-01                  .625E-01
row of 5                       .31281E-01                 .3125E-01
row of 6                       .15478E-01                .15625E-01
row of 7                         .782E-02                .78125E-02
row of 8                        .7687E-02               .390625E-02

Raw data[499743, 249677, 125148, 63166, 31281, 15478, 7820, 7687]



Generating random booleans with RandomRandom
Streak                Chance (Experiment)           Chance (Theory)
------                -------------------           ---------------
row of 1                       .499836E00                     .5E00
row of 2                       .250029E00                    .25E00
row of 3                       .125158E00                   .125E00
row of 4                       .62402E-01                  .625E-01
row of 5                       .31251E-01                 .3125E-01
row of 6                       .15639E-01                .15625E-01
row of 7                         .786E-02                .78125E-02
row of 8                        .7825E-02               .390625E-02

Raw data[499836, 250029, 125158, 62402, 31251, 15639, 7860, 7825]
