package edu.example.observerExample;

import edu.example.observerExample.observers.Developer;
import edu.example.observerExample.observers.Journalist;
import edu.example.observerExample.observers.Player;
import edu.example.observerExample.subject.Release;

public class ObserverPatternExample {
    public static void main(String[] args) {
        Release smbGame = new Release("Super Mario Bros.", "Super Mario Bros. is a platform game. In the game, Mario must race through the Mushroom Kingdom and save Princess Toadstool (later Princess Peach) from Bowser. Mario jumps, runs, and walks across each level. The worlds are full of enemies and platforms, and open holes.\n", "Super Mario Bros. was designed by Shigeru Miyamoto and Takashi Tezuka of the Nintendo Creative Department, and largely programmed by Toshihiko Nakago of SRD Company, Ltd.\n");
        Release ssbmGame = new Release("Super Smash Bros Melee", "Super Smash Bros. Melee[a] is a 2001 crossover fighting video game developed by HAL Laboratory and published by Nintendo for the GameCube.\n", "Super Smash Bros. Melee was developed by HAL Laboratory, with Masahiro Sakurai as the head of production. Mario creator Shigeru Miyamoto served as co-producer. The game was one of the first games released on the GameCube and highlighted the advancement in graphics over the Nintendo 64.\n");
        Release ssbuGame = new Release("Super Smash Bros Ultimate", "Super Smash Bros. Ultimate is a platform fighter for up to eight players in which characters from Nintendo games and other third-party franchises must try to knock each other out of an arena.\n", "Super Smash Bros. Ultimate was developed by Bandai Namco Studios and Sora Ltd., the same studios that developed Super Smash Bros. for Nintendo 3DS and Wii U, for the Nintendo Switch, with series creator Masahiro Sakurai returning as game director.\n");

        smbGame.addObserver(new Journalist("IGN"));
        smbGame.addObserver(new Developer("MShigeru Miyamoto"));
        smbGame.addObserver(new Player("Mario Enjoyer"));

        ssbuGame.addObserver(new Journalist("IGN"));
        ssbuGame.addObserver(new Developer("Masahiro Sakurai"));
        ssbuGame.addObserver(new Player("Hungrybox"));

        ssbmGame.addObserver(new Journalist("IGN"));
        ssbmGame.addObserver(new Developer("Masahiro Sakurai"));
        ssbmGame.addObserver(new Player("Hungrybox"));

        smbGame.makeAchievement("Complete game with zero deaths");
        ssbmGame.makeAchievement("First tournament");
        ssbuGame.makeAchievement("EVO 2019 largest tournament");

    }
}
