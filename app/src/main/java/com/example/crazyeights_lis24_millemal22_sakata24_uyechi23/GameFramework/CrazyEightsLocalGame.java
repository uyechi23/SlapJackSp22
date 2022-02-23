package com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework;

import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.actionMessage.GameAction;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.infoMessage.CrazyEightsGameState;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.GameComputerPlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.GameHumanPlayer;
import com.example.crazyeights_lis24_millemal22_sakata24_uyechi23.GameFramework.players.GamePlayer;

public class CrazyEightsLocalGame extends LocalGame{

    // game state
    CrazyEightsGameState state;

    public CrazyEightsLocalGame() {

    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        // disregard method if the state is null
        if(state == null){ return; }

        // make a copy of the game state and nullify any
        // data the player shouldn't have access to
        CrazyEightsGameState newState = new CrazyEightsGameState(state, p);

        newState.turnDrawPileFaceDown();

    }

    @Override
    protected boolean canMove(int playerIdx) {
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}
