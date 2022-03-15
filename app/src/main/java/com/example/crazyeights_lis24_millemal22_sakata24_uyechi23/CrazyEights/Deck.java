package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.CrazyEights;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Deck
 *
 * A Deck object simply contains an ArrayList of cards and has methods to shuffle
 * and turn over cards (set to null objects), with other methods to help with the
 * manipulation of the cards within the "cards" ArrayList.
 *
 * @author Selena Li
 * @author Maliyah Miller
 * @author Jake Uyechi
 * @author Tyler Sakata
 *
 * @version 14 March 2022
 */
public class Deck implements Serializable {

    // initialize an ArrayList with a capacity of 52
    // the last element in this ArrayList is the top of the deck
    public ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        ArrayList<Card> cards = new ArrayList<>();
        //TODO: add cards to the deck
    }

    // deep copy ctor //
    public Deck(Deck orig) {
        for (Card c : orig.cards) {
            Card cardCopy = new Card(c);
            this.cards.add(cardCopy);
        }
    }

    // getter method for cards
    public ArrayList<Card> getCards() { return this.cards; }

    // add a standard 52-card set of cards to the Deck
    public void add52(){
        int count = 0;
        for(char s : "SHDC".toCharArray()){
            for(char f : "AKQJ098765432".toCharArray()){
                Card newCard = new Card("" + s + f);
                cards.add(newCard);
                count++;
            }
        }
        Log.d("test", "Number of cards: " + count);
    }

    // shuffle the deck of cards
    public void shuffle() { synchronized(this.cards){ Collections.shuffle(this.cards); } }

    // shuffle the deck of cards based on a seed
    public void shuffleSeed(int seed){
        synchronized(this.cards){
            Collections.shuffle(this.cards, new Random(seed));
        }
    }

    // add a card to the top of the deck
    public void add(Card c){ synchronized(this.cards){ this.cards.add(c); } }

    // get the number of cards in the deck
    public int size(){ return this.cards.size(); }

    // determine if the deck is empty
    public boolean isEmpty(){ return this.size() <= 0; }

    // retrieve the top card
    public Card removeTopCard(){
        synchronized (this.cards) {
            if (this.cards.isEmpty()) return null;
            return this.cards.remove(this.cards.size() - 1);
        }
    }

    // retrieve the bottom card
    public Card removeBottomCard(){
        synchronized (this.cards){
            if(this.cards.isEmpty()) return null;
            return this.cards.remove(0);
        }
    }

    // retrieve a specific card
    // this will be used when a player plays a card from their hand
    public Card removeSpecific(int index){
        synchronized (this.cards){
            if(this.cards.isEmpty()) return null;
            return this.cards.remove(index);
        }
    }

    // empty the entire deck
    public void emptyDeck(){
        synchronized (this.cards){
            this.cards.removeAll(this.cards);
        }
    }

    // view top card of deck
    public Card peekTopCard(){
        synchronized (this.cards) {
            if (this.cards.isEmpty()) return null;
            return this.cards.get(this.cards.size() - 1);
        }
    }

    // find most suits
    public String findMostSuits(){
        // spades - 0
        // hearts - 1
        // diamonds - 2
        // clubs - 3
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        int[] counts = {0, 0, 0, 0};

        // categorize cards into suits and keep counts, iterating through all cards in deck
        for(Card c : this.cards){
            switch (c.getSuit()) {
                case "Spades": counts[0] = counts[0] + 1; break;
                case "Hearts": counts[1] = counts[1] + 1; break;
                case "Diamonds": counts[2] = counts[2] + 1; break;
                case "Clubs": counts[3] = counts[3] + 1; break;
            }
        }

        // find the max by looping through int array
        String ret = "Spades";
        int max = counts[0];
        for(int i = 0; i < counts.length; i++){
            if(counts[i] > max){
                ret = suits[i];
                max = counts[i];
            }
        }

        return ret;
    }

    // turn all cards in the deck face-down
    // this will be used when making copies of the GameState to send to players,
    // since players will not need to know what cards are in the deck.
    // Does not modify the size of the deck, but prevents players from seeing what's in it
    public void turnFaceDown(){
        synchronized(this.cards) {
            for (int i = 0; i < cards.size(); i++) {
                cards.set(i, null);
            }
        }
    }

    // append another deck to this one
    public void addDeck(Deck d){ this.cards.addAll(d.cards); }

    // toString method
    @NonNull
    public String toString(){

        // indicate which card is on the "top";
        // only matters for discard and draw piles
        String ret = "TOP: ";

        // iterate backwards, since the top of the deck is treated as the last index
        synchronized (this.cards){
            for(int i = this.cards.size() - 1; i >= 0; i--){
                if(this.cards.get(i) != null) ret += "[" + i + "] - " + this.cards.get(i).toString() + "\n";
            }
        }

        // return string
        return ret;
    }

    public void setDeck(Deck deck) {
        this.cards = deck.getCards();
    }
}
