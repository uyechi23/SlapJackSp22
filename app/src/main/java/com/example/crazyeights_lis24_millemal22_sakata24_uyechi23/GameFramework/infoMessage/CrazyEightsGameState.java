package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.infoMessage;

import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.Card;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.Deck;

import java.util.Hashtable;

public class CrazyEightsGameState extends GameState {
    /*
        Instance vars
     */
    private String playerTurn; // name of player whose turn it is
    private Hashtable<String, Deck> playerHands; // all players hands
    private Deck drawPile; // cards to be drawn from
    private Deck discardPile; // cards that were discarded

    /* copy constructor: makes a censored copy for players */
    public CrazyEightsGameState(CrazyEightsGameState origState) {
        // copies the name of the current player
        this.playerTurn = origState.getPlayerTurn();
        // copies player hands
        this.playerHands = origState.getPlayerHands();
        // copies the draw pile
        this.drawPile = origState.getDrawPile();
        // copies the discard pile
        this.discardPile.addDeck(origState.getDiscardPile());
    }

    // getter methods
    public String getPlayerTurn() { return this.playerTurn; }
    public Hashtable<String, Deck> getPlayerHands() { return this.playerHands; }
    public Deck getDrawPile() { return this.drawPile; }
    public Deck getDiscardPile() { return this.discardPile; }

    // nullify the cards in the deck (turn them face-down so data of card is unknown)
    public void turnDrawPileFaceDown() {
        if(!this.drawPile.isEmpty()) {
            // remove the top card in the deck
            // c will be null if the deck is empty
            Card c = this.drawPile.removeTopCard();

            // turn all of the cards in the deck face-down
            // if there are no cards this method call does nothing
            this.drawPile.turnFaceDown();

            // if the draw pile was not empty at the start (i.e., Card c is not null),
            // add the top card back to the top of the deck
            this.drawPile.add(c);
        }
    }

    // nullify a particular player's hand (a player should only be able to see their own hand)
    public void turnHandOverExcept() {

    }
}
