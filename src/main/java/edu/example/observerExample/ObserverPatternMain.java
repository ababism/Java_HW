package edu.example.observerExample;

import edu.example.observerExample.observers.Developer;
import edu.example.observerExample.observers.Journalist;
import edu.example.observerExample.observers.Player;
import edu.example.observerExample.subject.Release;

public class ObserverPatternMain {
    public static void main(String[] args) {
        Release dmcGame = new Release("Devil May Cry 5", "The game takes place five years after Devil May Cry 4 and follows a trio of warriors with demonic powers, the returning Dante, Nero, and a new protagonist named V, as they attempt to stop the Demon King Urizen from destroying the human world.\n", "In 2013, Hideaki Itsuno, director of the Devil May Cry games since the second game, showed an interest in continuing the original Devil May Cry series by developing a fifth installment.[27] It was originally thought the series might go on hiatus or end for good if Devil May Cry 4: Special Edition was not a commercial success.\n");
        Release ssbmGame = new Release("Super Smash Bros Melee", "Super Smash Bros. Melee[a] is a 2001 crossover fighting video game developed by HAL Laboratory and published by Nintendo for the GameCube.\n", "Super Smash Bros. Melee was developed by HAL Laboratory, with Masahiro Sakurai as the head of production. Mario creator Shigeru Miyamoto served as co-producer. The game was one of the first games released on the GameCube and highlighted the advancement in graphics over the Nintendo 64.\n");
        Release ssbuGame = new Release("Super Smash Bros Ultimate", "Super Smash Bros. Ultimate is a platform fighter for up to eight players in which characters from Nintendo games and other third-party franchises must try to knock each other out of an arena.\n", "Super Smash Bros. Ultimate was developed by Bandai Namco Studios and Sora Ltd., the same studios that developed Super Smash Bros. for Nintendo 3DS and Wii U, for the Nintendo Switch, with series creator Masahiro Sakurai returning as game director.\n");

        dmcGame.addObserver(new Journalist("IGN"));
        dmcGame.addObserver(new Developer("Hideaki Itsuno"));
        dmcGame.addObserver(new Player("Dante"));

        ssbuGame.addObserver(new Journalist("IGN"));
        ssbuGame.addObserver(new Developer("Masahiro Sakurai"));
        ssbuGame.addObserver(new Player("Hungrybox"));

        ssbmGame.addObserver(new Journalist("IGN"));
        ssbmGame.addObserver(new Developer("Masahiro Sakurai"));
        ssbmGame.addObserver(new Player("Hungrybox"));

        dmcGame.makeAchievement("Everyone got motivated");
        ssbmGame.makeAchievement("First tournament");
        ssbuGame.makeAchievement("EVO 2019 largest tournament");

    }
}
