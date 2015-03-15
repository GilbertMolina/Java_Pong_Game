package com.gilberth.game.state;

import com.gilberth.game.main.GameMain;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Gilberth
 */
public abstract class State {
    
    public abstract void init();
    
    public abstract void update();
    
    public abstract void render(Graphics g);
    
    public abstract void onClick(MouseEvent e);
    
    public abstract void onKeyPress(KeyEvent e);
    
    public abstract void onKeyRelease(KeyEvent e);
    
    public void setCurrentState(State newState){
        GameMain.sGame.setCurrentState(newState);
    }
    
}
