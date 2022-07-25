package moon_lander;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * The meteor which player will have to avoid
 *
 * @author https://github.com/dvpaa/Java-2022-Summer
 */

public class Meteor {

    /**
     * Images of the meteor in air.
     */
    private BufferedImage meteorImg;


    /**
     * Width of meteor.
     */
    public int meteorImgWidth;

    /**
     * Height of meteor.
     */
    public int meteorImgHeight;

    /**
     * How fast and to which direction meteor is moving on x coordinate?
     */
    private int speedX;

    /**
     * X coordinate of the meteor.
     */
    public int x;

    /**
     * X coordinate of the meteor.
     */
    public int y;

    /**
     * Direction of the meteor.
     */
    private int dir;


    public Meteor()
    {

        Initialize();
        LoadContent();

    }

    private void Initialize()
    {
        ResetMeteors();
    }


    private void LoadContent()
    {

        try
        {
            URL meteorImgUrl = this.getClass().getResource("/resources/images/meteor_100.png");
            meteorImg = ImageIO.read(meteorImgUrl);
            meteorImgWidth = meteorImg.getWidth();
            meteorImgHeight = meteorImg.getHeight();
        }
        catch (IOException ex) {
            Logger.getLogger(Meteor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ResetMeteors()
    {
        dir = 1;

        x = meteorImgWidth / 2;

        y = (int)(Framework.frameHeight * 0.60);

        speedX = 30;
    }

    public void Update()
    {
        if (x <= meteorImgWidth / 2)
        {
            dir = 1;
        }
        else if (x >= Framework.frameWidth - (meteorImgWidth / 2)) {
            dir = 2;
        }

        if (dir == 1)
        {
            x += speedX;
        }
        else
        {
            x -= speedX;
        }
    }

    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(meteorImg, x, y, null);
    }
}

