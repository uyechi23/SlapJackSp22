package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players;

import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.infoMessage.GameInfo;

/**
 * CrazyEightsComputerPlayer
 *
 *  A player object that extends from GameComputerPlayer. It operates similar to its parent.
 *  (for now)
 *
 * @author Selena Li
 * @author Maliyah Miller
 * @author Jake Uyechi
 * @author Tyler Sakata
 *
 * @version 24 February 2022
 */
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
