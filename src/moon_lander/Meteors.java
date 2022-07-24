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
     * number of meteors.
     */
    private final int METEOR_NUM = 6;

    /**
     * X coordinates of the meteor.
     */
    public int[] meteorItemX;

    /**
     * Y coordinates of the meteor.
     */
    public int[] meteorItemY;

    /**
     * Images of the meteor in air.
     */
    private BufferedImage meteorImg;

    /**
     * Images of the meteor item in air.
     */
    private BufferedImage[] meteorItemImg;

    /**
     * Width of meteor.
     */
    public int meteorImgWidth;

    /**
     * Height of meteor.
     */
    public int meteorImgHeight;

    /**
     * Width of meteor item.
     */
    public int meteorItemImgWidth;

    /**
     * Height of meteor.
     */
    public int meteorItemImgHeight;

    /**
     * Is item gotten?
     */
    public boolean[] itemGotten;

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

        meteorItemX = new int[METEOR_NUM];
        meteorItemY = new int[METEOR_NUM];


        ResetMeteors();
    }


    private void LoadContent()
    {

        meteorItemImg = new BufferedImage[METEOR_NUM];


        try
        {
//            URL meteorImgUrl = this.getClass().getResource("resources/images/meteor_100.png");
//            meteorImg = ImageIO.read(meteorImgUrl);
//
//            meteorImgWidth = meteorImg.getWidth();
//            meteorImgHeight = meteorImg.getHeight();

            for (int i=0; i<METEOR_NUM; i++)
            {
                URL itemImgUrl = this.getClass().getResource("/resources/images/meteor_40.png");
                meteorItemImg[i] = ImageIO.read(itemImgUrl);
            }
            meteorItemImgWidth = meteorItemImg[0].getWidth();
            meteorItemImgHeight = meteorItemImg[0].getHeight();
        }
        catch (IOException ex) {
            Logger.getLogger(Meteors.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ResetMeteors()
    {
        itemGotten = new boolean[METEOR_NUM];
        Arrays.fill(itemGotten, false);

        for (int i=0; i<METEOR_NUM; i++)
        {
            meteorItemX[i] = random.nextInt(Framework.frameWidth - meteorItemImgWidth);
            meteorItemY[i] = 15 + random.nextInt(Framework.frameHeight - meteorItemImgHeight - 60);
        }
    }

    public void Draw(Graphics2D g2d)
    {
        for (int i=0; i<METEOR_NUM; i++)
        {
            // If the meteor item is gotten?
            if (!itemGotten[i])
            {
                g2d.drawImage(meteorItemImg[i], meteorItemX [i], meteorItemY[i], null);
            }
        }
    }
}

