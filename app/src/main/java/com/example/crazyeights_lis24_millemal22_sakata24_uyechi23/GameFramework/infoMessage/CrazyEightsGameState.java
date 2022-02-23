package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.infoMessage;

import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.Card;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.Deck;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.CrazyEightsComputerPlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.CrazyEightsHumanPlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.GameComputerPlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.GameHumanPlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.GamePlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.ProxyPlayer;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

/**
 * CrazyEightsGameState
 * Creates the decks, each player's hand, and verifies each game action
 *
 * @author Selena Li
 * @author Maliyah Miller
 * @author Jake Uyechi
 * @author Tyler Sakata
 *
 * @version 24 February 2022
 */

public class CrazyEightsGameState extends GameState {
    /*
        Instance vars
     */
    private String playerTurn; // name of player whose turn it is
    private Hashtable<String, Deck> playerHands; // all players hands
    private Deck drawPile; // cards to be drawn from
    private Deck discardPile; // cards that were discarded
    private String currentSuit; // top card current suit
    private String currentFace; // top card current face
//    private Card c = new com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.Card(); // Card object

    /* TODO constructor */
    public CrazyEightsGameState(String initTurn, String[] players) {
        // set the current player
        this.playerTurn = initTurn;

        // create a hashtable of player hands
        // given an array of players, iterate through each and make an entry in the hashtable
        this.playerHands = new Hashtable<>();
        for(String player : players){
            this.playerHands.put(player, new Deck());
        }

        // create the draw pile as a new deck, add 52 cards, and shuffle
        this.drawPile = new Deck();
        this.drawPile.add52();
        this.drawPile.shuffle();

        // create an empty discard pile and add a card to it
        this.discardPile = new Deck();
        this.discardPile.add(this.drawPile.removeTopCard());

        // set current suit and face to be empty
        currentSuit = this.getDiscardPile().peekTopCard().getSuit();
        currentFace = this.getDiscardPile().peekTopCard().getFace();
    }

    /* TODO copy constructor: makes a censored copy for players */


    /* copy constructor: makes a censored copy for players */
    public CrazyEightsGameState(CrazyEightsGameState origState, GamePlayer p) {

        String playerName;
        // copies the name of the current player
        this.playerTurn = origState.getPlayerTurn();
        // copies the draw pile and turns it all face down
        this.drawPile = new Deck(origState.getDrawPile());
        this.drawPile.turnFaceDown();
        // copies the discard pile
        this.discardPile.addDeck(origState.getDiscardPile());
        // sets the currentSuit and currentFace to the top card
        this.currentFace = origState.getDiscardPile().peekTopCard().getFace();
        this.currentSuit = origState.getDiscardPile().peekTopCard().getSuit();
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
    public String getCurrentFace() { return this.currentFace; }
    public String getCurrentSuit() { return this.currentSuit; }

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

    // nullify all player's hands except one
    public void turnHandOverExcept(String noFlipPlayer) {
        // retrieve key set (all players in Strings)
        Set<String> keySet = this.playerHands.keySet();

        // for each key (player), turn their hands face-down unless it's the player
        for(String key : keySet){
            if(!key.equals(noFlipPlayer) && this.playerHands.get(key) != null){
                this.playerHands.get(key).turnFaceDown();
            }
        }
    }

    /* TODO */
    @Override
    public String toString() {
        String s = "";

        // prints hand of all players
        s += "These are the following hands of all players:\n " + playerHands.toString();

        // prints who's turn it is
        s += "It is " + playerTurn + "'s turn!\n";

        // prints played card (now is top of the deck)
        s += playerTurn + " played a " + getDiscardPile().peekTopCard() + ".\n";

        // prints the cards in the draw pile
        s += "Cards in Draw Pile: " + getDrawPile().toString();

        // prints selected suit after 8 card is played
        if (this.discardPile.peekTopCard().face.equals("Eight")) {
            s += "Most recent card was an eight - new suit is " + currentSuit;
        }

        return s;
    }

    // draw a card from the deck to the current player's hand
    public boolean drawCard() {
        // checks if the draw pile is empty
        if(drawPile.isEmpty()){
            // if it is, return false without doing anything
            return false;
        }else{
            // if it isn't, get the reference to the current player's
            // hand and add the top card of the draw pile to it
            playerHands.get(playerTurn).add(drawPile.removeTopCard());
            return true;
        }
    }

    // play a specific, indexed card from the current player's hand
    public boolean playCard(int index) {
        // check if the player's hand is empty
        if(playerHands.get(playerTurn) == null) return false;

        // check if the index given to the method is valid
        if(index < 0 || index >= playerHands.get(playerTurn).size()) return false;

        // remove the specified card in the player's hand and add it to the top of the discard pile
        discardPile.add(playerHands.get(playerTurn).removeSpecific(index));
        setSuit(this.discardPile.peekTopCard().getSuit());
        setFace(this.discardPile.peekTopCard().getFace());
        return true;
    }

    // set the current suit if the card played was an eight
    public boolean setSuitDueToEight(String newSuit) {
        // if the top card is not equal to eight, then do nothing and return false
        if(!discardPile.peekTopCard().face.equals("Eight")) return false;

        // if the top card is an eight, set the suit to the new suit
        setSuit(newSuit);
        return true;
    }

    // set the current suit and face
    public void setSuit(String suit){ this.currentSuit = suit; }
    public void setFace(String face){ this.currentFace = face; }

    /*
     * Other methods to implement (possibly):
     * - startGame
     * - exitGame
     * - restartGame
     * - undo
     * - pass
     */

}