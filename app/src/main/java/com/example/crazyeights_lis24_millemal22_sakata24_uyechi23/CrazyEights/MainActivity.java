package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.CrazyEights;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
 * This project is for SP22 CS301 - Object-Oriented Programming,
 * simulating a game of Crazy Eights.
 *
 * @author Selena Li
 * @author Maliyah Miller
 * @author Jake Uyechi
 * @author Tyler Sakata
 *
 * @version 15 March 2022
 */
public class MainActivity extends AppCompatActivity {

    /*
    External Citation:
        Date:       14 March 2022
        Problem:    Wanted to make TextView scrollable
        Resource:   https://stackoverflow.com/questions/1748977/making-textview-scrollable-on-android
        Solution:   Added android:scrollbars = "vertical" in xml file and currText.setMovementMethod(new ScrollingMovementMethod());
     */

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
                currText.setMovementMethod(new ScrollingMovementMethod());
                currText.setText("");

                // new instance of the Game State is created (firstInstance)
                // default constructor requires an array of player names
                String[] playerNames = new String[4];
                playerNames[0] = "Tyler";
                playerNames[1] = "Maliyah";
                playerNames[2] = "Selena";
                playerNames[3] = "Jake";
                CrazyEightsHumanPlayer player1 = new CrazyEightsHumanPlayer(playerNames[0]);
                CrazyEightsGameState firstInstance =
                        new CrazyEightsGameState(playerNames, 1);
                CrazyEightsGameState firstCopy =
                        new CrazyEightsGameState(firstInstance, player1);


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
                 *      if(canMove) firstInstance.playLastCard(); // play the last card if possible
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
                currText.append("GAME START: Starting game...\n\n");

                // print out current game state
                currText.append("INITIAL GAME STATE: \n");
                currText.append(firstInstance.toString() + "\n");

                // SEED 1: Selena starts, plays 7 of Hearts
                currText.append("---------------------Turn 1:---------------------\n");
                currText.append(firstInstance.getPlayerTurn() + " played a 7 of Hearts\n");
                currText.append("-------------------------------------------------\n\n");
                firstInstance.movePlay(1, firstInstance.getPlayerTurn());
                currText.append(firstInstance.toString());

                // SEED 1: Jake goes next, draws card(s)
                currText.append("---------------------Turn 2:---------------------\n");
                currText.append(firstInstance.getPlayerTurn() + " draws cards.\n");
                currText.append(firstInstance.getPlayerTurn() + " played an Ace of Hearts\n");
                currText.append("-------------------------------------------------\n");
                firstInstance.moveDraw(firstInstance.getPlayerTurn());
                currText.append(firstInstance.toString());

                // SEED 1: Tyler goes next, plays Ace of Spades
                currText.append("---------------------Turn 3:---------------------\n");
                currText.append(firstInstance.getPlayerTurn() + " plays an Ace of Spades\n");
                currText.append("-------------------------------------------------\n\n");
                firstInstance.movePlay(2, firstInstance.getPlayerTurn());
                currText.append(firstInstance.toString());

                // SEED 1: Maliyah goes next, plays 8 of Hearts
                // New Suit: Diamonds
                currText.append("---------------------Turn 4:---------------------\n");
                currText.append(firstInstance.getPlayerTurn() + " plays a 8 of Hearts\n");
                currText.append("Declared suit: Diamonds\n");
                currText.append("-------------------------------------------------\n\n");
                firstInstance.movePlay(2, firstInstance.getPlayerTurn());
                currText.append(firstInstance.toString());

                // making second instance same seed, same names.
                CrazyEightsGameState secondInstance =
                        new CrazyEightsGameState(playerNames, 1);
                // making a copy. should be same as first copy
                CrazyEightsGameState secondCopy =
                        new CrazyEightsGameState(secondInstance, player1);

                // print the two copies
                currText.append("-------------------------------------------------\n");
                currText.append("COPY CONSTRUCTOR TESTS\n");
                currText.append("-------------------------------------------------\n\n");
                currText.append("First Copy: \n");
                currText.append(firstCopy.toString() + "\n");
                currText.append("-------------------------------------------------\n\n");
                currText.append("Second Copy: \n");
                currText.append(secondCopy.toString() + "\n");
                // check if the two copies are identical
                if(firstCopy.toString().equals(secondCopy.toString())) {
                    currText.append("First copy is identical to second.\n");
                } else {
                    currText.append("First copy is not identical to second.\n");
                }
            }
        };
        // attach on click listener to runTest button
        runTest.setOnClickListener(listener);
    }

}