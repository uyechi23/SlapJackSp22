package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23;

import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.text.DateTimePatternGenerator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.widget.LinearLayout;

/**
 * @author Jake Uyechi
 * @author Maliyah Miller
 * @author Selena Li
 * @author Tyler Sakata
 */
public class GameBoard extends SurfaceView {

    //Paint colors for the cards
    Paint cardFrontColor = new Paint();
    Paint cardBackColor = new Paint();
    Paint diamondHeartColor = new Paint();
    Paint spadeClubColor = new Paint();
    Paint invalidCardColor = new Paint();

    //Gameboard dimensions
    float boardWidth = 2000.0f; //Need to get from xml file maybe start the game with a popup window then                                                        4
    float boardHeight = 853.0f; //the surface view can be created, and onDraw can be invalidated then the getters might work
    float boardStartY = 0.0f;
    float boardEndY = boardHeight;

    //slot 1 dimensions (current player)
    float slot1StartX = boardWidth/3;           //667
    float slot1StartY = (2 * (boardHeight/3));  //569
    float slot1EndX = slot1StartX * 2;
    float slot1EndY = boardEndY;

    //slot 2 dimensions (left of current)
    float slot2StartX = 0.0f;               //0
    float slot2StartY = (boardHeight/3);    //400
    float slot2EndX = slot1StartX;          //300
    float slot2EndY = slot1StartY;          //600

    //slot 3 dimensions (above current)
    float slot3StartX = slot1StartX;        //300
    float slot3StartY = boardStartY;        //200
    float slot3EndX = slot1EndX;            //600
    float slot3EndY = slot2StartY;          //400


    //slot 4 dimensions (right of current)
    float slot4StartX = slot1EndX;          //600
    float slot4StartY = slot2StartY;        //400
    float slot4EndX = boardWidth;           //900
    float slot4EndY = slot2EndY;            //600

    //Current player Card Dimensions
    float playerCardHeight = (slot1EndY-slot1StartY)/2;     //100
    float playerCardWidth = playerCardHeight/2;

    //Other Player Card Dimensions
    float otherCardHeight = (slot1EndY-slot1StartY)/3;      //67ish
    float otherCardWidth = otherCardHeight/2;

    public GameBoard(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public GameBoard(Context context, AttributeSet attrs){
        super(context, attrs);
        setWillNotDraw(false);

        //Putting the colors in the paint
        setBackgroundColor(0xFF7EA8FB);
        cardFrontColor.setColor(Color.WHITE);
        cardFrontColor.setStyle(Paint.Style.FILL);
        cardBackColor.setColor(0xFFB3E5FF);
        cardBackColor.setStyle(Paint.Style.FILL);
        diamondHeartColor.setColor(Color.RED);
        diamondHeartColor.setStyle(Paint.Style.FILL);
        spadeClubColor.setColor(Color.BLACK);
        spadeClubColor.setStyle(Paint.Style.FILL);
        invalidCardColor.setColor(0x9E9E9E9E);
        invalidCardColor.setStyle(Paint.Style.FILL);

    }

    /**
     * Draws the outline of the cards of whoever's turn it is
     * Needs the amount of cards as a parameter so it can allot
     * the spacing using the slot width
     *
     * How many cards per row, or do we want them stacked on top of each other?
     * cards per row- may need to resize the cards at some point
     * stacked- will have to make it so the one they tap is fully visible (might cover other cards)
     */
    public void drawCurrentPlayerCard(Canvas canvas /* , float amount of cards in hand*/){
        /**
         * float cardStartX = slot1StartX;
         * float cardStartY = (slot1EndY - slot1StartY)/2
         * float add = (slot1EndX - slot1StartX) / amountCards;
         *
         *  //adds each card stacked
         * for (amountCards){
         *      drawRect(cardStartX, cardStartY, cardStartX+playerCardWidth, slot1EndY, cardFrontColor);
         *      cardStartX += add;
         * }
         *
         * //adds each card one after the other
         * for (amountCards){
         *      drawRect(cardStartX, cardStartY, cardStartX+playerCardWidth, slot1EndY, cardFrontColor);
         *      cardStartX += (playerCardWidth + *spacing*);
         * }
         */

    }

    /**
     * draws the back of the other players' cards
     * uses the amount of cards for spacing
     * will need to receive the slot number somehow since the players will be rotating slots
     * maybe have the slot number in the Player object so when the turn is changed it changes
     * the slot number, and then the slot number can be referenced here
     */
    public void drawOtherPlayerCards(Canvas canvas /* , float amount of cards in hand , player *so i can get the slot number* */){
    /**
     * float cardStartX = slot_StartX;
     * float cardStartY = (slot_EndY - slot_StartY)/2;
     * float add = (slot_EndX - slot_StartX) / amountCards
     *
     * //adds each card stacked
     * for(amountCards){
     *      drawRect(cardStartX, cardStartY, cardStartX+playerCardWidth, slot_EndY, cardBackColor);
     *      cardStartX += add;
     * }
     *
     * //adds each card one after the other
     * for (amountCards) {
     *      drawRect(cardStartX, cardStartY, cardStartX + playerCardWidth, slot_EndY, cardBColor);
     *      cardStartX += (playerCardWidth + * spacing *);
     * }
     */

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

      //  drawCurrentPlayerCard(canvas);
        float startY = slot1EndY - ((slot1EndY-slot1StartY) / 2);
        float endX = slot1StartX + playerCardWidth;
        canvas.drawRect(slot1StartX, slot1StartY, slot1EndX, slot1EndY, cardBackColor);
        canvas.drawRect(slot2StartX, slot2StartY, slot2EndX, slot2EndY, cardBackColor);
        canvas.drawRect(slot3StartX, slot3StartY, slot3EndX, slot3EndY, cardBackColor);
        canvas.drawRect(slot4StartX, slot4StartY, slot4EndX, slot4EndY, cardBackColor);
        //canvas.drawRect(slot1StartX, startY, endX, boardEndY, cardFrontColor);                                              //300,100
        //canvas.drawRect(slot1EndX/3, startY, (slot1EndX/3)+playerCardWidth, boardEndY, cardFrontColor);
        //canvas.drawRect(2*(slot1EndX/3), startY, (2*(slot1EndX/3))+playerCardWidth, boardEndY, cardFrontColor);
        String player1 = "Player 1";
        String player2 = "Player 2";
        String player3 = "Player 3";
        String player4 = "Player 4";
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(30.0f);
        canvas.drawText(player1, slot1StartX+275, slot1EndY, textPaint);
        canvas.drawText(player2, slot2StartX+275, slot2EndY, textPaint);
        canvas.drawText(player3, slot3StartX+275, slot3EndY, textPaint);
        canvas.drawText(player4, slot4StartX+275, slot4EndY, textPaint);

    }
}
/