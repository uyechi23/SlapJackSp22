package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23;

import androidx.annotation.NonNull;

public class Card {

    // instance variables of card
    // suit - the suit of the card
    // face - the face value of the card
    // value - the point value of the card (used to calculate post-game scores)
    public String suit;
    public String face;
    public int value;

    public Card(String chars){
        // this should be a String with two characters
        String[] toks = chars.split("");

        // first character is the suit
        switch(toks[0]){
            case "S": this.suit = "Spades"; break;
            case "H": this.suit = "Hearts"; break;
            case "D": this.suit = "Diamonds"; break;
            case "C": this.suit = "Clubs"; break;
        }

        // second character is the face
        switch(toks[1]){
            case "A": this.face = "Ace"; break;
            case "K": this.face = "King"; break;
            case "Q": this.face = "Queen"; break;
            case "J": this.face = "Jack"; break;
            case "0": this.face = "Ten"; break;
            case "9": this.face = "Nine"; break;
            case "8": this.face = "Eight"; break;
            case "7": this.face = "Seven"; break;
            case "6": this.face = "Six"; break;
            case "5": this.face = "Five"; break;
            case "4": this.face = "Four"; break;
            case "3": this.face = "Three"; break;
            case "2": this.face = "Two"; break;
        }

        // calculate value based on face
        setValue(this.face);
    }

    public Card(String face, String suit) {
        // assign cards
        this.suit = suit;
        this.face = face;

        // calculate card value (an integer representing the face value of the card)
        setValue(face);
    }

    // getter methods
    public int getValue() {
        return this.value;
    }
    public String getFace() {
        return this.face;
    }
    public String getSuit() {
        return this.suit;
    }

    // setter method for value
    public void setValue(String face){
        switch (face) {
            case "Ace": this.value = 1; break;
            case "King": // same as queen
            case "Queen": // same as jack
            case "Jack": // same as 10
            case "Ten": this.value = 10; break;
            case "Nine": this.value = 9; break;
            case "Eight": this.value = 8; break;
            case "Seven": this.value = 7; break;
            case "Six": this.value = 6; break;
            case "Five": this.value = 5; break;
            case "Four": this.value = 4; break;
            case "Three": this.value = 3; break;
            case "Two": this.value = 2; break;
        }
    }

    // compare methods - suit and face
    public boolean matchSuit(Card compare) {
        return (compare.suit.equals(this.suit));
    }
    public boolean matchFace(Card compare) {
        return (compare.face.equals(this.face));
    }

    // isValid method - checks if this instance is a valid card play for another card
    public boolean isValid(Card compare){
        if(compare.getFace().equals("Eight")){
            return matchSuit(compare);
        }else{
            return matchSuit(compare) || matchFace(compare);
        }
    }

    // toString card method
    @NonNull
    public String toString() {
        return "" + this.face + " of " + this.suit;
    }
}
