package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players;

import android.view.View;

import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.Card;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.GameMainActivity;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.infoMessage.GameInfo;

public class CrazyEightsHumanPlayer extends GameHumanPlayer{
    /**
     * instance variables
     */
    private Card[] myHand; // my hand
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
}
