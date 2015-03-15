package com.gilberth.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import com.gilberth.game.main.GameMain;
import com.gilberth.game.main.Resources;
import com.gilberth.game.model.Ball;
import com.gilberth.game.model.Paddle;

/**
 *
 * @author Gilberth
 */
public class PlayState extends State{
    
    private Paddle paddleLeft, paddleRight;
    private static final int PADDLE_WIDTH = 15;
    private static final int PADDLE_HEIGHT = 60;
    private int playerScore = 0;
    private Font scoreFont;
    private Ball ball;
    private static final int BALL_DIAMETER = 20;

    @Override
    public void init() {
        paddleLeft = new Paddle(0, (GameMain.GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT);
        //paddleLeft = new Paddle(0, (450 / 2) - (60 / 2), 15, 60);
        paddleRight = new Paddle(GameMain.GAME_WIDTH - PADDLE_WIDTH, (GameMain.GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT);
        //paddleRight = new Paddle((800 - 15), (450 / 2) - (60 / 2), 15, 60);
        scoreFont = new Font("SansSerif", Font.BOLD, 25);
        ball = new Ball(300, 200, BALL_DIAMETER, BALL_DIAMETER);
    }

    @Override
    public void update() {
        paddleLeft.update();
        paddleRight.update();
        ball.update();
        
        if (ballCollision(paddleLeft)) {
            playerScore++;
            ball.onCollideWith(paddleLeft);
            Resources.hit.play();
        }else if (ballCollision(paddleRight)) {
            playerScore++;
            ball.onCollideWith(paddleRight);
            Resources.hit.play();
        }else if (ball.isDead()) {
            playerScore -= 2;
            ball.reset();
        }
    }
    
    @Override
    public void render(Graphics g) {
        //Se dibuja el background de ambos lados
        g.setColor(Resources.darkBlue);
        g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        g.setColor(Resources.darkRed);
        g.fillRect(GameMain.GAME_WIDTH / 2, 0, GameMain.GAME_WIDTH / 2, GameMain.GAME_HEIGHT);
        
        //Se dibuja la linea del medio
        g.drawImage(Resources.line, (GameMain.GAME_WIDTH / 2) - 2, 0, null);

        //Se dibujan las paletas
        g.setColor(Color.white);
        g.fillRect(paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(), paddleLeft.getHeight());
        g.fillRect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());
        
        //Se dibuja la bola
        g.fillRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
        
        //Se dibuja el marcador
        g.setFont(scoreFont);
        g.drawString("" + playerScore, 350, 40);
    }

    @Override
    public void onClick(MouseEvent e) {
        //No implementado.
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            paddleLeft.accelUp();
            paddleRight.accelDown();
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            paddleLeft.accelDown();
            paddleRight.accelUp();
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            paddleLeft.stop();
            paddleRight.stop();
        }
    }
    
    private boolean ballCollision(Paddle p){
        return ball.getRect().intersects(p.getRect());
    }
    
}
