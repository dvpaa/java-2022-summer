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
     * We use this to generate a random number for starting x coordinate of the meteor.
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
     * X coordinate of the meteor.
     */
    public int x;

    /**
     * X coordinate of the meteor.
     */
    public int y;

    private int speedX;

    private int speedY;

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
            URL meteorImgUrl = this.getClass().getResource("/resources/images/meteor_40.png");
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
        x = random.nextInt(Framework.frameWidth - meteorImgWidth);

        y = (int)(Framework.frameHeight * 0.90);
    }

    public void Update(int rocketX, int rocketY)
    {
        int dist = (int)(Math.sqrt(Math.pow((x - rocketX), 2) + Math.pow((y - rocketY), 2)));

        if (dist > 200)
        {
            speedX = (int)((rocketX - x) * 0.1);
            speedY = (int)((rocketY - y) * 0.1);
        }

        x += speedX;
        y += speedY;
    }

    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(meteorImg, x, y, null);
    }
}

