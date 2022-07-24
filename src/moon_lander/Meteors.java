package moon_lander;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * The meteor which player will have to avoid
 *
 * @author https://github.com/dvpaa/Java-2022-Summer
 */

public class Meteors {

    /**
     * We use this to generate a random number for starting coordinates of the meteor.
     */
    private Random random;


    /**
     * Images of the meteor in air.
     */
    private BufferedImage meteor;


    /**
     * Width of meteor.
     */
    public int meteorWidth;

    /**
     * Height of meteor.
     */
    public int meteorHeight;

    /**
     * Accelerating speed of the meteor.
     */
    private int speedAccelerating;

    /**
     * How fast and to which direction meteor is moving on x coordinate?
     */
    private int speedX;

    /**
     * How fast and to which direction meteor is moving on y coordinate?
     */
    public int speedY;


    public Meteors()
    {

        Initialize();
        LoadContent();

    }

    private void Initialize()
    {
        random = new Random();

        speedAccelerating = 10;

        ResetMeteors();
    }


    private void LoadContent()
    {

//        try
//        {
//            URL meteorImgUrl = this.getClass().getResource("resources/images/meteor_100.png");
//            meteorImg = ImageIO.read(meteorImgUrl);
//
//            meteorImgWidth = meteorImg.getWidth();
//            meteorImgHeight = meteorImg.getHeight();
//
//
//        }
//        catch (IOException ex) {
//            Logger.getLogger(Meteors.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void ResetMeteors()
    {



    }

    public void Draw(Graphics2D g2d)
    {

    }
}

