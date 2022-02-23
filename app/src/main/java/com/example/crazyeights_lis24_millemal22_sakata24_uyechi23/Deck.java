package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Deck implements Serializable {

    // initialize an ArrayList with a capacity of 52
    // the last element in this ArrayList is the top of the deck
    public final ArrayList<Card> cards = new ArrayList<>();

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

    // add a standard 52-card set of cards to the Deck
    public void add52(){
        for(char s : "SHDC".toCharArray()){
            for(char f : "AKQJ098765432".toCharArray()){
                Card newCard = new Card("" + s + f);
                cards.add(newCard);
            }
        }
    }

    // shuffle the deck of cards
    public void shuffle() { synchronized(this.cards){ Collections.shuffle(this.cards); } }

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

    // view top card of deck
    public Card peakTopCard(){
        synchronized (this.cards) {
            if (this.cards.isEmpty()) return null;
            return this.cards.get(this.cards.size() - 1);
        }
    }

    // turn all cards in the deck face-down
    // this will be used when making copies of the GameState to send to players,
    // since players will not need to know what cards are in the deck.
    // Does not modify the size of the deck, but prevents players from seeing what's in it
    public void turnFaceDown(){
        synchronized(this.cards){
            for(int i = 0; i < cards.size(); i++){
                cards.set(i, null);
            }
        }
    }

    // append another deck to this one
    public void addDeck(Deck d){ this.cards.addAll(d.cards); }

    // toString method
    public String toString(){

        String ret = "";

        synchronized (this.cards){
            for(Card c : this.cards){
                ret += c.toString() + "\n";
            }
        }

        return ret;
    }


}
