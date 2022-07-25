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
     * We use this to generate a random number for starting coordinates of the meteor.
     */
    private Random random;


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
     * How fast and to which direction meteor is moving on y coordinate?
     */
    public int speedY;

    /**
     * X coordinate of the meteor.
     */
    public int meteorX;

    /**
     * X coordinate of the meteor.
     */
    public int meteorY;

    /**
     * Direction of the meteor.
     */
    private int meteorD;


    public Meteor()
    {

        Initialize();
        LoadContent();

    }

    private void Initialize()
    {
        random = new Random();

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
        meteorD = 1;

        meteorX = meteorImgWidth / 2;

        meteorY = (int)(Framework.frameHeight * 0.60);

        speedX = 30;
    }

    public void Update()
    {
        if (meteorX <= meteorImgWidth / 2)
        {
            meteorD = 1;
        }
        else if (meteorX >= Framework.frameWidth - (meteorImgWidth / 2)) {
            meteorD = 2;
        }

        if (meteorD == 1)
        {
            meteorX += speedX;
        }
        else
        {
            meteorX -= speedX;
        }
    }

    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(meteorImg, meteorX, meteorY, null);
    }
}

