package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.CrazyEights;

import androidx.annotation.NonNull;

import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.infoMessage.GameState;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.GameComputerPlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.GamePlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.ProxyPlayer;

import java.util.Hashtable;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * CrazyEightsGameState
 *
 * Creates the decks, each player's hand, and verifies functionality of each game action
 *
 * @author Selena Li
 * @author Maliyah Miller
 * @author Jake Uyechi
 * @author Tyler Sakata
 *
 * @version 24 February 2022
 */

public class CrazyEightsGameState extends GameState {
    /* Instance variables */
    private String playerTurn; // name of player whose turn it is
    private Hashtable<String, Deck> playerHands; // all players hands
    private Deck drawPile; // cards to be drawn from
    private Deck discardPile; // cards that were discarded
    private String currentSuit; // top card current suit
    private String currentFace; // top card current face

    /**
     * CrazyEightsGameState constructor
     *
     * Initializes the instance variables for start of game
     *
     * @param players - a String array of player names
     */
    public CrazyEightsGameState(String[] players) {
        Random rand = new Random();
        this.playerTurn = players[rand.nextInt(players.length)];

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

    /**
     * Copy Constructor
     *
     * Makes a censored copy for players
     *
     * @param origState
     * @param p
     */
    public CrazyEightsGameState(CrazyEightsGameState origState, GamePlayer p) {

        String playerName; // TODO: set playerName to be the player name of the GamePlayer
        // copies the name of the current player
        this.playerTurn = origState.getPlayerTurn();
        // copies the draw pile and turns it all face down
        this.drawPile = new Deck(origState.getDrawPile());
        turnDrawPileFaceDown();
        // copies the discard pile
        this.discardPile.addDeck(origState.getDiscardPile());
        // sets the currentSuit and currentFace to the top card
        this.currentFace = origState.getDiscardPile().peekTopCard().getFace();
        this.currentSuit = origState.getDiscardPile().peekTopCard().getSuit();
        this.discardPile = new Deck(origState.getDiscardPile());

        if(p instanceof ProxyPlayer) {
            ProxyPlayer proxyPlayer = (ProxyPlayer) p;
            // TODO: find a way to get a proxy player's name
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

        // censor all but GamePlayer p
        turnHandsOverExcept(playerName);
    }


    /**
     * Setter methods:
     *
     * setSuit(): Returns the current suit as a String (@param suit)
     * setFace(): Returns the current face as a String (@param face)
     *
     * @return void
     */
    public void setSuit(String suit){ this.currentSuit = suit; }
    public void setFace(String face){ this.currentFace = face; }

    /**
     * Getter methods:
     *
     * getDrawPile(): Gets the draw pile (@return Deck)
     * getDiscardPile(): Gets the discard pile (@return Deck)
     * getPlayerHands(): Gets the hands of the player (@return Hashtable)
     * getPlayerTurn(): Gets who the current player is (@return String)
     * getCurrentFace(): Gets the current card's face (@return String)
     * getCurrentSuit(): Gets the current card's suit (@return String)
     */

    public Deck getDrawPile() { return this.drawPile; }
    public Deck getDiscardPile() { return this.discardPile; }
    public Hashtable<String, Deck> getPlayerHands() { return this.playerHands; }
    public String getPlayerTurn() { return this.playerTurn; }
    public String getCurrentFace() { return this.currentFace; }
    public String getCurrentSuit() { return this.currentSuit; }


    /**
     * turnDrawPileFaceDown
     *
     * Nullifies the cards in the deck
     * Turns them face-down so data of card is unknown
     *
     * @return void
     */
    public void turnDrawPileFaceDown() {
        if(!this.drawPile.isEmpty()) {
            // remove the top card in the deck
            // c will be null if the deck is empty
            Card c = this.drawPile.removeTopCard();

            // turn all of the cards in the deck face-down
            // if there are no cards this method call does nothing
            this.drawPile.turnFaceDown();

            // if the draw pile was not empty at the start
            // (i.e., Card c is not null),
            // add the top card back to the top of the deck
            this.drawPile.add(c);
        }
    }


    /**
     * turnHandOverExcept()
     *
     * Nullifies all player's hands except one
     *
     * @param noFlipPlayer
     *
     * @return void
     */
    public void turnHandsOverExcept(String noFlipPlayer) {
        // retrieve key set (all players in Strings)
        Set<String> keySet = this.playerHands.keySet();

        // for each key (player), turn their hands face-down unless it's the player
        for(String key : keySet){
            if(!key.equals(noFlipPlayer) && this.playerHands.get(key) != null){
                Objects.requireNonNull(this.playerHands.get(key)).turnFaceDown();
            }
        }
    }

    /**
     * toString()
     *
     * Describes state of game as a string
     *
     * @return String
     */
    @NonNull
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

    /**
     * drawCard()
     *
     * Draws a card from the deck to the current player's hand
     *
     * @return boolean
     */
    public boolean drawCard() {
        // checks if the draw pile is empty
        if(drawPile.isEmpty()){
            // if it is, return false without doing anything
            return false;
        }else{
            // if it isn't, get the reference to the current player's
            // hand and add the top card of the draw pile to it
            Objects.requireNonNull(playerHands.get(playerTurn)).add(drawPile.removeTopCard());
            return true;
        }
    }

    /**
     * playCard()
     *
     * Plays a specific, indexed card from the current player's hand
     *
     * @param index
     *
     * @return boolean
     */
    public boolean playCard(int index) {
        // check if the player's hand is empty
        if(playerHands.get(playerTurn) == null) return false;

        // check if the index given to the method is valid
        if(index < 0 || index >= Objects.requireNonNull(playerHands.get(playerTurn)).size()) return false;

        // remove the specified card in the player's hand and add it to the top of the discard pile
        discardPile.add(Objects.requireNonNull(playerHands.get(playerTurn)).removeSpecific(index));
        setSuit(this.discardPile.peekTopCard().getSuit());
        setFace(this.discardPile.peekTopCard().getFace());
        return true;
    }

    /**
     * setSuitDueToEight()
     *
     * Sets the current suit if the card played was an eight
     *
     * @param newSuit
     *
     * @return boolean
     */
    public boolean setSuitDueToEight(String newSuit) {
        // if the top card is not equal to eight, then do nothing and return false
        if(!discardPile.peekTopCard().face.equals("Eight")) return false;

        // if the top card is an eight, set the suit to the new suit
        setSuit(newSuit);
        return true;
    }

    /*
     * TODO: Other methods to implement (possibly):
     * - startGame
     * - exitGame
     * - restartGame
     * - undo
     * - pass
     */

}