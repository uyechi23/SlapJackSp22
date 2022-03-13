/**
 * CRAZY EIGHTS - CS301 Semester Project (Spring 2022)
 *
 * @author Maliyah Miller
 * @author Selena Li
 * @author Tyler Sakata
 * @author Jake Uyechi
 * <p>
 * This project is for SP22 CS301 - Object-Oriented Programming,
 * simulating a game of Crazy Eights.
 */

package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.CrazyEights;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.R;

/**
 * MainActivity
 *
 * The MainActivity runs onCreate when the app is run and starts the game.
 *
 * @author Selena Li
 * @author Maliyah Miller
 * @author Jake Uyechi
 * @author Tyler Sakata
 *
 * @version 24 February 2022
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c8gamestatetest);

        SurfaceView gui = findViewById(R.id.gameBoard);

        // access the button from the c8gamestatetest file and assign an onClickListener
        Button runTest = findViewById(R.id.RunTestButton);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // clear the current TextView (from previous test)
                TextView currText = findViewById(R.id.EditText);
                currText.setText("");

                // new instance of the Game State is created (firstInstance)
                // default constructor requires an array of player names
                String[] playerNames = new String[4];
                playerNames[0] = "Tyler";
                playerNames[1] = "Maliyah";
                playerNames[2] = "Selena";
                playerNames[3] = "Jake";
                CrazyEightsGameState firstInstance = new CrazyEightsGameState(playerNames, 10);

                // methods in CrazyEightsGameState (* is essential):
                /*
                 * setSuit
                 * setFace
                 * getDrawPile
                 * getDiscardPile
                 * getPlayerHands
                 * getPlayerTurn
                 * getCurrentFace
                 * getCurrentSuit
                 * turnDrawPileFaceDown
                 * turnHandsOverExcept
                 * toString
                 * drawCard*
                 * playCard*
                 * setSuitDueToEight*
                 * nextPlayer
                 * checkIfValid*
                 */

                Log.d("Test", firstInstance.getDrawPile().toString());

                // TODO: Rig the deck somehow.
                // first player (random) draws a card
                firstInstance.drawCard();
                // draws cards until first player can play a card
                while(!firstInstance.checkIfValid()) {
                    firstInstance.drawCard();
                }
                // create instance of the players hand
                Deck newDeck = firstInstance.getPlayerHands().get(firstInstance.getPlayerTurn());
                int loopCounter = 0;
                // loop thru all cards in hand, do other stuff with that loop.
                for (Card c : newDeck.cards) {
                    if(c.value == 8) {
                        firstInstance.playCard(loopCounter);
                    }
                    loopCounter++;
                }

            }
        };
        // attack on click listener to runtest button
        runTest.setOnClickListener(listener);
    }
}