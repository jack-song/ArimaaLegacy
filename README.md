Arimaa
======

Currently in development as a learning project; feedback, pointers, and advice greatly appreciated

[arimaa]: http://arimaa.com/arimaa/
[nathan]: http://thenoviceoof.com/blog/projects/arimaa-icon-set/


#### Goals and Approach
{:.bar-heading}
Build an Android App to play the board game published at [arimaa.com][arimaa]. This app was developed with the purpose of learning the Android framework in mind. I initially aimed to just have a pleasant experience for 2 human players. This included modular graphics, full mechanics/rules implementation, and the automatic saving/loading of one game at a time. This is the first program I'm writing for public release, so I'm trying especially to research and follow conventions.

#### Mechanics of Interest:

- Players are allowed to set up their pieces in any starting orientation
- Players are allowed 4 moves of their choice, in any combination, on their turn
- Players are allowed to move their opponent's pieces, depending on a hierarchy of power

#### Long Term Goals:

- Adding predictive behaviour to allow making multiple moves at a time
- Adding AI to play with
- Saving and loading multiple game states by name
- Multiplayer?

#### Planned refactors:
- Exporting utility methods from the massive GameEngine class to a game rules oriented class
- Adjusting the scopes of fields and methods
- reducing amount of switch-cases and other convoluted code pieces
- Implementing exceptions for handling errors
- Defining and using constants properly
- Exporting fields from the massive GameEngine class to a proper model class
- implementing an observer pattern on the model class to reduce onion code layers

#### Code Design
{:.bar-heading}
Being a relatively large and expanding program, I tried to spend time making good design choices. This is an ongoing process - I'm learning how to refactor and redesign as I go and new requirements come up. I've already had to do a complete code rewrite when I realized my original design(lack of a design) was very bad. I'm currently trying vanilla MVC. All display-related code is kept in the View class, while the game engine itself only has logical functions. I am currently working on seperating out logic that does not rely heavily on the game state into a static utilities class. I'm also working on separating the model itself from the logic and using proper observer classes. 

#### UI Design
{:.bar-heading}
To let a user dive straight into a game, the default screen is the game board, which either immediately starts a new game or loads a previous one. The game visuals would be important as a user would be looking at the game board for extended amounts of time. A board or piece set in real life is something a user goes out to select and buy, so there should options for their appearance in the app.

I especially wanted to provide an alternative to the default piece set, which I personally found to be unclear and distracting. The set designed by [Nathan Hwang][nathan] allows a player to judge the positions with much greater ease, speed, and accuracy.

This software is being provided with a written authorization from Arimaa.com and in compliance with "Section 3
of the Arimaa Public License". Authorization #131207. Any rights granted by the end user license of this software
apply only to this software and do not extend to the Arimaa game. The end user is responsible to ensure that any
derivative work based on this software complies with the Arimaa Public License and obtain any authorization or
license by contacting Arimaa.com. The Arimaa name is a trademark of Arimaa.com. The Arimaa game is patented.
The Arimaa game rules, the Arimaa board design and the Arimaa piece design are copyright protected. The Arimaa
Public License allows cost free use of the Arimaa game for non-commercial use.
