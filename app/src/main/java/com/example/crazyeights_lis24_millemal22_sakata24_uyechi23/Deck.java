package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Deck implements Serializable {

    // initialize an ArrayList with a capacity of 52
    // the last element in this ArrayList is the top of the deck
    public final ArrayList<Card> cards = new ArrayList<>(52);

    public Deck(){
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

    // retrieve the top card
    public Card removeTop(){
        synchronized (this.cards) {
            if (this.cards.isEmpty()) return null;
            return this.cards.remove(this.cards.size() - 1);
        }
    }

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
