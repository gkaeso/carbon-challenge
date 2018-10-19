Author: gmarcel
Date: 04/12/2016
Source: https://github.com/gmarcel-dev/carbon-challenge

This README file explains how to use the Treasure Hunt application.


----- REQUIREMENTS -----

The application requires Java 7 or a higher version.
It only uses the standard library.

----- PURPOSE -----

The treasure application emulates a simple board, the treasure map,
on which adventurers can move and pick up treasures. The board can
be composed of Lands with or without treasures, and mountains. 
Mountains as well as the map borders cannot be crossed. Adventurers
move on the map and pick up treasures if they find one.

----- COMPILE -----

If you know how to compile programs from command lines, you can easily
compile the application. For Maven users, the following should do:
> cd path_to_carbon-challenge_folder
> mvn package

----- RUN -----

To run the application, first compile it.
Then, the following should do: 
> java -cp target/carbon-challenge-1.0-SNAPSHOT.jar gmarcel.game.TreasureHuntApp arg0 arg1 arg2

Where :
- arg0 is the path to a text file containing the treasure map configuration.
- arg1 is the path to a text file containing the adventurers configuration.
- arg2 is the path to a text file in which will be saves all the adventurers' moves.

NOTE: the saved file will be OVERWRITTEN if it already exists.


----- CONFIGURE THE GAME -----

Examples of treasure map and adventurers text files can be found in 
src/main/resources/gmarcel/game/


The treasure map text file should be written as followed:

C width height
T x-y N
M x-y

Where:
- C defines the map; T defines a treasure; M defines a mountain
- width and height define the size (number of cells) of the board
- x-y defines the position of a treasure (or a mountain)
- N defines the number of treasures on the board cell

NOTE: the treasure map configuration file should always begin with "C width height".



The adventurers text file should be written as followed:

Name x-y Z S

Where:
- Name defines the name of an adventurer
- x-y defines the initial position of an adventurer on the map
- Z defines the initial orientation of an adventurer; values can be (N,S,E,W)
- S defines the sequence of an adventurer's moves. Adventurers can do the 
following actions: A to move forward, D to turn right, G to turn left. Turning
left or right only changes the adventurer's orientation; not their position. 
The move sequence can be as long as wanted.

NOTE: several adventurers can be on the map at the same time. Adventurers 
cannot overlay others at the same position.


The output text file gives the list of the adventurers' name, position and 
number of treasures picked up for each turn in the game.
