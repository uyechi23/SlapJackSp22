package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players;

import android.view.View;

import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.Card;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.Deck;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.GameMainActivity;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.infoMessage.GameInfo;

/**
 * CrazyEightsHumanPlayer
 *
 * A player object that extends from GameHumanPlayer. It operates similar to its parent.
 * (for now)
 *
 * @author Selena Li
 * @author Maliyah Miller
 * @author Jake Uyechi
 * @author Tyler Sakata
 *
 * @version 24 February 2022
 */
public class CrazyEightsHumanPlayer extends GameHumanPlayer {
    /**
     * instance variables
     */
    private Deck myHand; // my hand
    /**
     * constructor
     *
     * @param name the name of the player
     */
    public CrazyEightsHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }

    public String getName() {
        return this.name;
    }
}
