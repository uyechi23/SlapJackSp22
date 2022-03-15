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
                CrazyEightsGameState firstInstance = new CrazyEightsGameState(playerNames, 1);

                // implementation methods:
                /*
                 * PLAY CARD: GameState.playCard(index);
                 *      The index of the card to play should be printed out in Logcat
                 *
                 * DRAW CARD: GameState.drawCard();
                 *      This will find the current player and add a card to their hand
                 *      In the actual game, the player will tap the deck multiple times
                 *      until they have a card they can play. To test GameState, use a
                 *      while loop to imitate this. Use with below code snippet:
                 *
                 *  boolean canMove = false; // have a boolean if the player can move
                 *      // while the player can't move and there are cards in the draw pile
                 *      while(!canMove && firstInstance.getDrawPile().size() > 0) {
                 *          firstInstance.drawCard();
                 *          canMove = firstInstance.checkIfValid();
                 *      }
                 *      if(canMove) firstInstance.playLastCard(); // if the player can move, play the last card
                 *
                 * CHECK IF THE SUIT NEEDS TO BE CHANGED: GameState.checkToChangeSuit();
                 *      This detects if the most recent card was an 8, and the suit needs
                 *      to be changed. This decision is done automatically (based on the
                 *      most common suit in the player's hand).
                 *
                 * NEXT PLAYER: GameState.nextPlayer();
                 *      Moves to next player
                 *
                 *
                 */

                // indicate game start
                Log.d("GAME START", "Starting game...");

                // print out current game state
                Log.d("Game State", firstInstance.toString());

                // SEED 1: Selena starts, plays 7 of Hearts
                Log.d("ACTION", "Selena plays 7 of Hearts");
                firstInstance.playCard(1); // play the card
                firstInstance.checkToChangeSuit(); // check if the suit needs to be changed
                firstInstance.nextPlayer(); // move to next player
                Log.d("Game State", firstInstance.toString());

                // SEED 1: Jake goes next, draws card(s)
                Log.d("ACTION", "Jake draws a card.");
                boolean canMove = false; // have a boolean if the player can move
                // while the player can't move and there are cards in the draw pile
                while(!canMove && firstInstance.getDrawPile().size() > 0) {
                    firstInstance.drawCard();
                    canMove = firstInstance.checkIfValid();
                }
                if(canMove) firstInstance.playLastCard(); // if the player can move, play the last card
                firstInstance.checkToChangeSuit(); // check if the suit needs to be changed
                firstInstance.nextPlayer(); // move to next player
                Log.d("Game State", firstInstance.toString());

                // SEED 1: Tyler goes next, plays Ace of Spades
                Log.d("ACTION", "Tyler plays Ace of Spades");
                firstInstance.playCard(2); // play the card
                firstInstance.checkToChangeSuit(); // check if the suit needs to be changed
                firstInstance.nextPlayer();
                Log.d("Game State", firstInstance.toString());

                // SEED 1: Maliyah goes next, plays 8 of Hearts
                // New Suit: Diamonds
                Log.d("ACTION", "Maliyah plays 8 of Hearts.");
                Log.d("ACTION", "Declared suit: Diamonds:");
                firstInstance.playCard(2); // play the card
                firstInstance.checkToChangeSuit(); // check if the suit needs to be changed
                firstInstance.nextPlayer(); // move to next player
                Log.d("Game State", firstInstance.toString());

            }
        };
        // attack on click listener to runtest button
        runTest.setOnClickListener(listener);
    }

}