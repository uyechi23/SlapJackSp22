package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players;

import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.infoMessage.GameInfo;

public class CrazyEightsComputerPlayer extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public CrazyEightsComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {

    }

    public String getName() {
        return null;
    }
}
