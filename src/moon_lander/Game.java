package moon_lander;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {

    /**
     * The space rocket with which player will have to land.
     */
    private PlayerRocket playerRocket;

    /**
     * Items which player will have to get.
     */
    private Items items;
    
    /**
     * Landing area on which rocket will have to land.
     */
    private LandingArea landingArea;

    /**
     * Meteor which player will have to avoid.
     */
    private Meteor meteor;

    /**
     * Game background image.
     */
    private BufferedImage backgroundImg;
    
    /**
     * Red border of the frame. It is used when player crash the rocket.
     */
    private BufferedImage redBorderImg;

    private final int LIFE_NUM = 1;

    private int life = LIFE_NUM;

    private final long TIME_LIMIT = 5;

    private long time = TIME_LIMIT;

    Audio itemSound;

    Audio lossLifeSound;




    

    public Game()
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Sets variables and objects for the game.
                Initialize();
                // Load game files (images, sounds, ...)
                LoadContent();
                
                Framework.gameState = Framework.GameState.PLAYING;
            }
        };
        threadForInitGame.start();
    }
    
    
   /**
     * Set variables and objects for the game.
     */
    private void Initialize()
    {
        playerRocket = new PlayerRocket();
        landingArea  = new LandingArea();
        items = new Items();
        meteor = new Meteor();
        itemSound = new Audio("resources/sounds/item_sound.wav", true);
        lossLifeSound = new Audio("resources/sounds/loss_life.wav", true);
    }
    
    /**
     * Load game files - images, sounds, ...
     */
    private void LoadContent()
    {
        try
        {
            URL backgroundImgUrl = this.getClass().getResource("/resources/images/background.jpg");
            backgroundImg = ImageIO.read(backgroundImgUrl);
            
            URL redBorderImgUrl = this.getClass().getResource("/resources/images/red_border.png");
            redBorderImg = ImageIO.read(redBorderImgUrl);
        }
        catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Restart game - reset some variables.
     */
    public void RestartGame()
    {
        playerRocket.ResetPlayer();
        items.ResetItems();
        landingArea.ResetLandingArea();
        meteor.ResetMeteors();

        life = LIFE_NUM;
        time = TIME_LIMIT;
    }
    
    
    /**
     * Update game logic.
     * 
     * @param gameTime gameTime of the game.
     * @param mousePosition current mouse position.
     */
    public void UpdateGame(long gameTime, Point mousePosition)
    {
        // Move the rocket
        playerRocket.Update();
        meteor.Update(playerRocket.x, playerRocket.y);
        
        // Checks where the player rocket is. Is it still in the space or is it landed or crashed?
        // First we check bottom y coordinate of the rocket if is it near the landing area.
        if(playerRocket.y + playerRocket.rocketImgHeight - 10 > landingArea.y)
        {
            // Here we check if the rocket is over landing area.
            if((playerRocket.x > landingArea.x) &&
                    (playerRocket.x < landingArea.x + landingArea.landingAreaImgWidth - playerRocket.rocketImgWidth))
            {
                // Here we check if the rocket speed isn't too high.
                if(playerRocket.speedY <= playerRocket.topLandingSpeed)
                {
                    playerRocket.landed = true;
                }
                else
                    playerRocket.crashed = true;
            }
            else
                playerRocket.crashed = true;
                
            Framework.gameState = Framework.GameState.GAMEOVER;
        }

        if (((items.flagX - (items.flagImgWidth / 2) <= (playerRocket.x + playerRocket.realRocketImgWidth / 2) ) &&
                ((playerRocket.x - playerRocket.realRocketImgWidth / 2) <= items.flagX + (items.flagImgWidth / 2))) &&
                ((items.flagY - (items.flagImgHeight / 2) <= (playerRocket.y + playerRocket.realRocketImgHeight / 2) ) &&
                        ((playerRocket.y - playerRocket.realRocketImgHeight / 2) <= items.flagY + (items.flagImgHeight / 2))))
        {
            if (!items.flagGotten)
            {
                if (itemSound.audioPlayingTrue()) { itemSound.start(); }
                items.flagGotten = true;
                landingArea.flag = true;
            }

        }

        for (int i=0; i<Items.ITEM_NUM; i++)
        {
            if (((items.heartX[i] - (items.heartImgWidth / 2) <= (playerRocket.x + playerRocket.realRocketImgWidth / 2) ) &&
                    ((playerRocket.x - playerRocket.realRocketImgWidth / 2) <= items.heartX[i] + (items.heartImgWidth / 2))) &&
                    ((items.heartY[i] - (items.heartImgHeight / 2) <= (playerRocket.y + playerRocket.realRocketImgHeight / 2) ) &&
                            ((playerRocket.y - playerRocket.realRocketImgHeight / 2) <= items.heartY[i] + (items.heartImgHeight / 2))))
            {
                if (!items.heartGotten[i])
                {
                    if (itemSound.audioPlayingTrue()) { itemSound.start(); }
                    items.heartGotten[i] = true;
                    life += 1;
                }

            }

            if (((items.watchX[i] - (items.watchImgWidth / 2) <= (playerRocket.x + playerRocket.realRocketImgWidth / 2) ) &&
                    ((playerRocket.x - playerRocket.realRocketImgWidth / 2) <= items.watchX[i] + (items.watchImgWidth / 2))) &&
                    ((items.watchY[i] - (items.watchImgHeight / 2) <= (playerRocket.y + playerRocket.realRocketImgHeight / 2) ) &&
                            ((playerRocket.y - playerRocket.realRocketImgHeight / 2) <= items.watchY[i] + (items.watchImgHeight / 2))))
            {
                if (!items.watchGotten[i])
                {
                    if (itemSound.audioPlayingTrue()) { itemSound.start(); }
                    items.watchGotten[i] = true;
                    time += 5;
                }
            }
        }

        if(gameTime / Framework.secInNanosec >= time){
            if(life > 0){
                life -= 1;
                time += TIME_LIMIT;
            }

        }

        if(life == 0){
            Framework.gameState = Framework.GameState.GAMEOVER;
        }

        if ((meteor.x - meteor.meteorImgWidth / 2) <= (playerRocket.x + playerRocket.realRocketImgWidth / 2) &&
                (playerRocket.x - playerRocket.realRocketImgWidth / 2) <= (meteor.x + meteor.meteorImgWidth / 2) &&
                (meteor.y - meteor.meteorImgHeight / 2) <= (playerRocket.y + playerRocket.realRocketImgHeight / 2) &&
                ((playerRocket.y - playerRocket.rocketImgHeight / 2) <= meteor.y + meteor.meteorImgHeight / 2))
        {
            Framework.gameState = Framework.GameState.GAMEOVER;
        }

    }
    
    /**
     * Draw the game to the screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition current mouse position.
     */
    public void Draw(Graphics2D g2d, Point mousePosition, long gameTime)
    {
        g2d.drawImage(backgroundImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        
        landingArea.Draw(g2d);
        
        playerRocket.Draw(g2d);

        items.Draw(g2d);

        meteor.Draw(g2d);


        g2d.drawString("Life: " + life, 5, 30);

        g2d.drawString("Time limit: " + (time - gameTime / Framework.secInNanosec), 5, 45);
    }


    /**
     * Draw the game over screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition Current mouse position.
     * @param gameTime Game time in nanoseconds.
     */
    public void DrawGameOver(Graphics2D g2d, Point mousePosition, long gameTime)
    {
        Draw(g2d, mousePosition, gameTime);
        
        g2d.drawString("Press space or enter to restart.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 70);
        
        if(playerRocket.landed)
        {
            g2d.drawString("You have successfully landed!", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3);
            g2d.drawString("You have landed in " + gameTime / Framework.secInNanosec + " seconds.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 20);
        }
        else
        {
            g2d.setColor(Color.red);
            g2d.drawImage(redBorderImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
            if(playerRocket.crashed){
                g2d.drawString("You have crashed the rocket!", Framework.frameWidth / 2 - 95, Framework.frameHeight / 3);
            }
            else{
                g2d.drawString("GAME OVER!", Framework.frameWidth / 2 - 50, Framework.frameHeight / 3);
            }

        }
    }
}
