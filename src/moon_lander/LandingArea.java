package moon_lander;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Landing area where rocket will land.
 * 
 * @author www.gametutorial.net
 */

public class LandingArea {
    
    /**
     * X coordinate of the landing area.
     */
    public int x;
    /**
     * Y coordinate of the landing area.
     */
    public int y;
    
    /**
     * Image of landing area.
     */
    private BufferedImage landingAreaImg;
    
    /**
     * Width of landing area.
     */
    public int landingAreaImgWidth;

    public boolean flag;

    private Random random;
    
    
    public LandingArea()
    {
        Initialize();
        LoadContent();
    }
    
    
    private void Initialize()
    {
        random = new Random();

        ResetLandingArea();
    }
    
    private void LoadContent()
    {
        try
        {
            URL landingAreaImgUrl = this.getClass().getResource("/resources/images/landing_area.png");
            landingAreaImg = ImageIO.read(landingAreaImgUrl);
            landingAreaImgWidth = landingAreaImg.getWidth();
        }
        catch (IOException ex) {
            Logger.getLogger(LandingArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ResetLandingArea()
    {
        flag = false;

        // X coordinate of the landing area
        x = (int)(Framework.frameWidth * random.nextFloat(0f, 1f));
        // Y coordinate of the landing area is at 86% frame height.
        y = (int)(Framework.frameHeight * 0.88);
    }
    
    
    public void Draw(Graphics2D g2d)
    {
        if (flag)
        {
            g2d.drawImage(landingAreaImg, x, y, null);
        }
    }
    
}
