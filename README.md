# BEAT THE BUG
A fun little coding exercise that should become a small and enjoyable little game.

## What is the game?
In "Beat the Bug", a group of players try to stand their ground against one giant bug that throws different challenges at them. Every challenge that gets mastered by the players will reduce the bugs strength. However, every player will only be able to loose one challenge and has then to start again from scratch. Those players that beat a challenge however will become stronger, being able to inflict more damage to the bug in the following rounds.

The game itself will be a small web application connected to a backend server and database where player data is handled. Players will be able to see other players and need to work together to beat the bug.

## What is the exercise?
I want to give myself a fun and rewarding challenge in learning and enforcing new technologies, skills and methods I haven't used in a long time or didn't use before at all. Most of all, "Beat the Bug" will be my recurring exercise for the following technologies and clean code techniques.

### Java
The last time I touched Java for something serious has been in university while working part time at my first real developer job. Java 8 was just a new thing out there and nobody really knew how to use all that fancy stuff like lambda expressions or consumers. To this day, I haven't really written anything with Java 8 in mind, let alone Java 12 that has been released earlier that year. I just skimmed through some of the new features. Modules? Optionals? Type Inference? This sure looks really exciting to try out!

### Spring Framework
Most of the time, I don't want to reinvent the wheel and get over with the "boring" stuff that comes with developing and get right into the "fun" stuff. Boring stuff includes writing excessive lines of code or annotation to map application data to a database interface, or writing even more lines of code to just define REST services for your data. It seems that I can now just minimize this time spent and use the Spring framework! Just skimming over the possible features makes me smile 

### Angular
We're up to what version of Angular now? Angular 9? I also missed five version of updates here... My saving grace might be that I already worked intensely with Angular 4, so the new app structure and using Typescript are not unfamiliar to me. The new features also look very cool. Template views generating code? I'll definitely try that! Animation will also play a big part in the game, so good news that *"though Angular's intuitive API"* it should now be very easy to create those.

## Top Down View of the Game
Someone a lot smarter than me once said at a conference something like this:

| You always create your application top down, except the first time.

At that time, this hit a little too close to home. So this *first time*, I'll try to prove that person wrong, failing eventually 😉 but hey, isn't failing the thing that makes trying interesting? So I took a few notes about the ideas I want to incorporate in *Beat the Bug* and have drawn out a small handful of screens that will be at the top most position of the project - **the frontend**.

### The Frontend
The frontend will be split up in four main parts.

![The simple frontend](./resources/images/frontend_mockup.png)

#### The Homescreen
This is the first screen that new players should see. They will be greeted by the games title and can then decide to play a round. When hitting the *Play* button, they will be transferred to the lobby.

#### The Lobby
To not throw a unlimited number of players into one game, the players will be split up into different game lobbies, each representing a single instance of a game. In a lobby, a player can pick his or her player name and the color they want to be displayed in the game. Other players that are already waiting in the lobby will also be displayed. Once all players in a lobby have clicked on the *Ready* button, the game will begin, sending them to the challenge.

#### The Challenge
The challenge is where the players will face off against the bug in a series of escalating challenges. The bug will be displayed prominently as the common obstacle for all players, it's massive healthbar at the top giving the player's a sense of their progress. On the top left, some information about the current challenge will be displayed. On the top right, messages concerning the game will be displayed to all players. At the bottom, the players will be visible. If either the bug is beaten or all players are defeated, all players will be transferred to the scoreboard.

#### The Scoreboard
On the scoreboard, the player's will be able to see their final results of the challenge. If they are up to a new challenge, they can click the *Back* button and land again on the homescreen.

### The Objects
By looking at the different screens of the mockup we can deduce our first batch of *"real world"* objects that we will need and what attributes might be necessary.

#### The Game
A `Game` object will represent one instance of the actual Beat the Bug game. A `Game` therefore needs to be identifiable by a unique id to be separable from other `Game` instances. Furthermore, a `Game`can either be running or finished.

![The Game Class](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.https://github.com/daedal-knickerbockers/code_exercises/resources/diagrams/Game.puml)