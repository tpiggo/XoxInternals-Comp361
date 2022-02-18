# Xox

Vanilla java code of a simple implementation of Tic Tac Toe. Not user interface, only controller and model code.

![version](https://img.shields.io/badge/version-1.3-brightgreen)
![coverage](https://img.shields.io/badge/coverage-93%25-brightgreen)
![building](https://img.shields.io/badge/build-passing-brightgreen)

## About

This code imitates server side core functionality of Xox (Tic Tac Toe). It is not a complete implementation of an operational game, but it provides entry points to imitate server data interactions.  
The following scenario / functionality is covered:

 * Multi / parallel game management
 * Creation / Reset of games
   * Custom player names
   * Custom player colours
 * Retrieval of current board state
 * Retrieval of current game state
   * Winner / longest line by player
   * Game over
 * Retrieval of all available actions for player
 * Selection and application of a valid action by player
 
## Documentation

 * For a full description of available functionality, read the [JavaDoc](https://kartoffelquadrat.github.io/XoxInternals/index.html)
 * The relevant functionality is exposed in [```XoxManagerImpl.java```](https://kartoffelquadrat.github.io/XoxInternals/eu/kartoffelquadrat/xoxinternals/controller/XoxManagerImpl.html).

## Usage

On execution, the game will simply print a listing of an intial test setup.

 1. Get the sources:  
```git clone https://github.com/kartoffelquadrat/XoxInternals.git```

 2. Compile the project with maven. This provides you with a self contained ```jar``` in the ```target```folder:  
```mvn clean install```
 
 3. Run the jar:  
 ```java -jar target/XoxInternals.jar```

## Contact / Pull Requests

 * Author: Maximilian Schiedermeier ![email](email.png)
 * Github: Kartoffelquadrat
 * Webpage: https://www.cs.mcgill.ca/~mschie3
 * License: [MIT](https://opensource.org/licenses/MIT)

