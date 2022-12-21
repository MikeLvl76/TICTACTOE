# TICTACTOE

A simple Tic-Tac-Toe game using CLI written in Java. The code can be improved and features can be implemented further.

This project is generated with [Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) because it's relevant if you want to include extern dependency.

**In command prompt go to `game` directory and type these commandes to run the project :**

- 1. Compile the project

```sh
mvn compile
```

- 2. Create the .jar

```sh
mvn package
```

- 3. Add .jar to classpath and launch Main.java file

```sh
java -cp target/game-1.0-SNAPSHOT.jar game.src.main.java.tictactoe.Main
```

There are 3 modes :
- Player vs Player
- AI vs AI
- Player vs AI

AI has multiple behaviors but they appear randomly.