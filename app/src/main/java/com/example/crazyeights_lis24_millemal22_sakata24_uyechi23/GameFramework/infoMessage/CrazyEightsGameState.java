package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.infoMessage;

import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.Card;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.CrazyEightsLocalGame;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.CrazyEightsHumanPlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.GamePlayer;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class CrazyEightsGameState extends GameState {
    /*
        Instance var
     */
    private String playerTurn; // name of player whose turn it is
    private Hashtable<String, ArrayList<Card>> playerHands; // all players hands
    private ArrayList<Card> drawPile; // cards to be drawn from
    private ArrayList<Card> discardPile; // cards that were discarded


    /* copy constructor: makes a censored copy for players */
    public CrazyEightsGameState(CrazyEightsGameState origState) {

        // copies the name of whoevers turn it is
        this.playerTurn = origState.getPlayerTurn();
        // removes information to be censored to player here
        this.playerHands = null;
        this.drawPile = null;
        // deep copy of the discard array list
        ArrayList<Card> discardCopy = new ArrayList<Card>();
        discardCopy.addAll(origState.getDiscardPile());
        this.discardPile = discardCopy;
    }

    public String getPlayerTurn() {
        return this.playerTurn;
    }

    public ArrayList<Card> getDiscardPile() {
        return this.discardPile;
    }
}
