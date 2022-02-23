package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.infoMessage;

import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.Card;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.Deck;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.CrazyEightsComputerPlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.CrazyEightsHumanPlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.GameComputerPlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.GameHumanPlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.GamePlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.ProxyPlayer;

import java.util.Enumeration;
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
    public CrazyEightsGameState(CrazyEightsGameState origState, GamePlayer p) {

        String playerName;
        // copies the name of the current player
        this.playerTurn = origState.getPlayerTurn();
        // copies the draw pile and turns it all face down
        this.drawPile = new Deck(origState.getDrawPile());
        this.drawPile.turnFaceDown();
        // copies the discard pile
        this.discardPile = new Deck(origState.getDiscardPile());

        if(p instanceof ProxyPlayer) {
            p = (ProxyPlayer) p;
            //TODO: insert things specific to proxy player???
            return;
        }
        else if(p instanceof GameComputerPlayer) {
            CrazyEightsComputerPlayer computerPlayer = (CrazyEightsComputerPlayer) p;
            playerName = computerPlayer.getName();
        }
        else {
            CrazyEightsHumanPlayer humanPlayer = (CrazyEightsHumanPlayer) p;
            playerName = humanPlayer.getName();
        }
        // copies player hands
        this.playerHands = origState.getPlayerHands();

        // enumeration to loop thru hashtable
        Enumeration<String> enumeration = this.playerHands.keys();

        // loops through all elements of hashtable
        while(enumeration.hasMoreElements()) {

            // key of current element
            String key = enumeration.nextElement();

            // get deck and copy it
            Deck deckCopy = new Deck(this.playerHands.get(key));
            this.playerHands.put(key, deckCopy);

            // skips players hand
            if(key == playerName) {
                continue;
            }

            // facedown all other hands
            this.playerHands.get(key).turnFaceDown();
        }
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
