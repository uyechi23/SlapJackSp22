package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

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

        // check that chars is non-null
        if(chars == null) return;

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
            default: this.value = 0; break;
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

    /*
    External Citation:
        Date:       21 February 2022
        Problem:    Needed to draw the card on a SurfaceView instead of using ImageView
        Resource:   GameFramework Card.java class includes the methods below
        Solution:   Used code from the Card.java class in the SlapJack GameFramework example
     */

    /**
     * Draws the card on a Graphics object.  The card is drawn as a
     * white card with a black border.  If the card's rank is numerih, the
     * appropriate number of spots is drawn.  Otherwise the appropriate
     * picture (e.g., of a queen) is included in the card's drawing.
     *
     * @param g  the graphics object on which to draw
     * @param where  a rectangle that tells where the card should be drawn
     */
    public void drawOn(Canvas g, RectF where) {
        // create the paint object
        Paint p = new Paint();
        p.setColor(Color.BLACK);

        // get the bitmap for the card
        int firstIndex = -1;
        switch(this.getSuit()){
            case "Clubs": firstIndex = 0; break;
            case "Diamonds": firstIndex = 1; break;
            case "Hearts": firstIndex = 2; break;
            case "Spades": firstIndex = 3; break;
        }

        int secondIndex = -1;
        switch(this.getFace()){
            case "Ace": secondIndex = 0;
            case "Two": secondIndex = 1;
            case "Three": secondIndex = 2;
            case "Four": secondIndex = 3;
            case "Five": secondIndex = 4;
            case "Six": secondIndex = 5;
            case "Seven": secondIndex = 6;
            case "Eight": secondIndex = 7;
            case "Nine": secondIndex = 8;
            case "Ten": secondIndex = 9;
            case "Jack": secondIndex = 10;
            case "Queen": secondIndex = 11;
            case "King": secondIndex = 12;
        }

        Bitmap bitmap = cardImages[firstIndex][secondIndex];

        // create the source rectangle
        Rect r = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());

        // draw the bitmap into the target rectangle
        g.drawBitmap(bitmap, r, where, p);
    }


    // array that contains the android resource indices for the 52 card
    // images
    private static int[][] resIdx = {
            {
                    R.drawable.card_ac, R.drawable.card_2c, R.drawable.card_3c,
                    R.drawable.card_4c, R.drawable.card_5c, R.drawable.card_6c,
                    R.drawable.card_7c, R.drawable.card_8c, R.drawable.card_9c,
                    R.drawable.card_tc, R.drawable.card_jc, R.drawable.card_qc,
                    R.drawable.card_kc,
            },
            {
                    R.drawable.card_ad, R.drawable.card_2d, R.drawable.card_3d,
                    R.drawable.card_4d, R.drawable.card_5d, R.drawable.card_6d,
                    R.drawable.card_7d, R.drawable.card_8d, R.drawable.card_9d,
                    R.drawable.card_td, R.drawable.card_jd, R.drawable.card_qd,
                    R.drawable.card_kd,
            },
            {
                    R.drawable.card_ah, R.drawable.card_2h, R.drawable.card_3h,
                    R.drawable.card_4h, R.drawable.card_5h, R.drawable.card_6h,
                    R.drawable.card_7h, R.drawable.card_8h, R.drawable.card_9h,
                    R.drawable.card_th, R.drawable.card_jh, R.drawable.card_qh,
                    R.drawable.card_kh,
            },
            {
                    R.drawable.card_as, R.drawable.card_2s, R.drawable.card_3s,
                    R.drawable.card_4s, R.drawable.card_5s, R.drawable.card_6s,
                    R.drawable.card_7s, R.drawable.card_8s, R.drawable.card_9s,
                    R.drawable.card_ts, R.drawable.card_js, R.drawable.card_qs,
                    R.drawable.card_ks,
            },
    };

    // the array of card images
    private static Bitmap[][] cardImages = null;

    /**
     * initializes the card images
     *
     * @param activity
     * 		the current activity
     */
    public static void initImages(Activity activity) {
        // if it's already initialized, then ignore
        if (cardImages != null) return;

        // create the outer array
        cardImages = new Bitmap[resIdx.length][];

        // loop through the resource-index array, creating a
        // "parallel" array with the images themselves
        for (int i = 0; i < resIdx.length; i++) {
            // create an inner array
            cardImages[i] = new Bitmap[resIdx[i].length];
            for (int j = 0; j < resIdx[i].length; j++) {
                // create the bitmap from the corresponding image
                // resource, and set the corresponding array element
                cardImages[i][j] =
                        BitmapFactory.decodeResource(
                                activity.getResources(),
                                resIdx[i][j]);
            }
        }
    }
}
