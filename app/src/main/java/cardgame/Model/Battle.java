package cardgame.model;

import java.util.List;
import java.util.ArrayList;

import cardgame.model.cardContainer.*;
import cardgame.patterns.observer.*;

public class Battle implements Subject {
    Character playerCharacter;
    Hand playerHand;
    Deck playerDeck;

    List<Enemy> enemies;
    List<Observer> observers;

    public Battle(Character playerCharacter, Hand playerHand, Deck playerDeck, List<Enemy> enemies) {
        this.playerCharacter = playerCharacter;
        this.playerHand = playerHand;
        this.playerDeck = playerDeck;
        this.enemies = enemies;

        this.observers = new ArrayList<Observer>();
    }

    public Battle() {
        this.playerCharacter = null;
        this.playerHand = new Hand();
        this.playerDeck = new Deck();
        this.enemies = null;

        this.observers = new ArrayList<Observer>();
    }

    public Character getPlayerCharacter() {
        return playerCharacter;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public Deck getPlayerDeck() {
        return playerDeck;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setPlayerCharacter(Character playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public void setPlayerDeck(Deck playerDeck) {
        this.playerDeck = playerDeck;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        this.enemies.remove(enemy);
    }

    public void drawCard(int quant) {
        for (int i = 0; i < quant; i++) {
            this.playerHand.addCard(this.playerDeck.draw());
        }
    }

    public void shuffleHand() {
        int size = this.playerHand.getSize();
        // Move all cards from hand to deck
        playerHand.getCards().forEach(card -> {
            playerDeck.addCard(card);
        });
        playerHand.getCards().clear();

        // Shuffle deck
        playerDeck.shuffle();

        // Draw cards from deck to hand
        for (int i = 0; i < size; i++) {
            playerHand.addCard(playerDeck.draw());
        }
    }

    public void discardCard(int index) {
        this.playerHand.getCards().remove(index);
    }

    public void attackEnemy(int index, int damage, Element element) {
        Enemy enemy = this.enemies.get(index);

        int modifier = 0;
        if (enemy.isCountered(element)) {
            modifier = 1;
        } else {
            modifier = -1;
        }

        enemy.setHp(enemy.getHp() - damage - modifier);
    }

    public void attackPlayer(int damage, Element element) {
        int modifier = 0;

        if (playerCharacter.isCountered(element))
            modifier = 1;
        else
            modifier = -1;

        this.playerCharacter.setHealth(this.playerCharacter.getHealth() - damage - modifier);
    }

    public void heal(int heal, Element element) {
        int modifier = 0;

        if (playerCharacter.isCountered(element))
            modifier = 1;
        else
            modifier = -1;

        this.playerCharacter.setHealth(this.playerCharacter.getHealth() + heal - modifier);
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }
}
