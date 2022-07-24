package moon_lander;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.util.Arrays;

/**
 * Items which player will have to get
 *
 * @author https://github.com/dvpaa/Java-2022-Summer
 */


public class Items {

    /**
     * We use this to generate a random number for starting coordinates of the item.
     */
    private Random random;

    /**
     * number of items.
     */
    private final int ITEM_NUM = 3;

    /**
     * number of meteors.
     */
    private final int METEOR_NUM = ITEM_NUM * 2;

    /**
     * X coordinate of the flag.
     */
    public int flagX;

    /**
     * Y coordinate of the flag.
     */
    public int flagY;

    /**
     * X coordinates of the heart.
     */
    public int[] heartX;

    /**
     * Y coordinates of the heart.
     */
    public int[] heartY;

    /**
     * X coordinates of the watch.
     */
    public int[] watchX;

    /**
     * Y coordinates of the watch.
     */
    public int[] watchY;

    /**
     * X coordinates of the meteor.
     */
    public int[] meteorX;

    /**
     * Y coordinates of the meteor.
     */
    public int[] meteorY;

    /**
     * Image of the flag in air.
     */
    private BufferedImage flagImg;

    /**
     * Images of the heart in air.
     */
    private BufferedImage[] heartImg;

    /**
     * Image of the watch in air.
     */
    private BufferedImage[] watchImg;

    /**
     * Images of the meteor in air.
     */
    private BufferedImage[] meteorImg;

    /**
     * Width of flag.
     */
    public int flagImgWidth;

    /**
     * Height of flag.
     */
    public int flagImgHeight;

    /**
     * Width of heart.
     */
    public int heartImgWidth;

    /**
     * Height of heart.
     */
    public int heartImgHeight;

    /**
     * Width of watch.
     */
    public int watchImgWidth;

    /**
     * Height of watch.
     */
    public int watchImgHeight;

    /**
     * Width of meteor.
     */
    public int meteorImgWidth;

    /**
     * Height of meteor.
     */
    public int meteorImgHeight;

    /**
     * Is flag gotten?
     */
    public boolean flagGotten;

    /**
     * Is heart gotten?
     */
    public boolean[] heartGotten;

    /**
     * Is watch gotten?
     */
    public boolean[] watchGotten;

    /**
     * Is item gotten?
     */
    public boolean[] meteorGotten;


    public Items()
    {

        Initialize();
        LoadContent();

    }

    private void Initialize()
    {
        random = new Random();

        heartX = new int[ITEM_NUM];
        heartY = new int[ITEM_NUM];

        watchX = new int[ITEM_NUM];
        watchY = new int[ITEM_NUM];

        meteorX = new int[METEOR_NUM];
        meteorY = new int[METEOR_NUM];

        ResetItems();
    }


    private void LoadContent()
    {
        heartImg = new BufferedImage[ITEM_NUM];

        watchImg = new BufferedImage[ITEM_NUM];

        meteorImg = new BufferedImage[METEOR_NUM];


        try
        {
            URL flagImgUrl = this.getClass().getResource("/resources/images/item_flag.png");
            flagImg = ImageIO.read(flagImgUrl);

            flagImgWidth = flagImg.getWidth();
            flagImgHeight = flagImg.getHeight();

            for (int i=0; i<ITEM_NUM; i++)
            {
                URL heartImgUrl = this.getClass().getResource("/resources/images/item_heart.png");
                heartImg[i] = ImageIO.read(heartImgUrl);

                URL watchIgmUrl = this.getClass().getResource("/resources/images/item_stopwatch.png");
                watchImg[i] = ImageIO.read(watchIgmUrl);
            }

            for (int i=0; i<METEOR_NUM; i++)
            {
                URL meteorImgUrl = this.getClass().getResource("/resources/images/meteor_40.png");
                meteorImg[i] = ImageIO.read(meteorImgUrl);
            }

            heartImgWidth = heartImg[0].getWidth();
            heartImgHeight = heartImg[0].getHeight();

            watchImgWidth = watchImg[0].getWidth();
            watchImgHeight = watchImg[0].getHeight();

            meteorImgWidth = meteorImg[0].getWidth();
            meteorImgHeight = meteorImg[0].getHeight();
        }
        catch (IOException ex) {
            Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ResetItems()
    {
        flagGotten = false;

        heartGotten = new boolean[ITEM_NUM];
        watchGotten = new boolean[ITEM_NUM];
        meteorGotten = new boolean[METEOR_NUM];

        Arrays.fill(heartGotten, false);
        Arrays.fill(watchGotten, false);
        Arrays.fill(meteorGotten, false);

        flagX = random.nextInt(Framework.frameWidth - flagImgWidth);
        flagY = 15 + random.nextInt(Framework.frameHeight - flagImgHeight - 60);

        for (int i=0; i<ITEM_NUM; i++)
        {
            heartX[i] = random.nextInt(Framework.frameWidth - heartImgWidth);
            heartY[i] = 15 + random.nextInt(Framework.frameHeight - heartImgHeight - 60);

            watchX[i] = random.nextInt(Framework.frameWidth - watchImgWidth);
            watchY[i] = 15 + random.nextInt(Framework.frameHeight - watchImgHeight - 60);
        }

        for (int i=0; i<METEOR_NUM; i++)
        {
            meteorX[i] = random.nextInt(Framework.frameWidth - meteorImgWidth);
            meteorY[i] = 15 + random.nextInt(Framework.frameHeight - meteorImgHeight - 60);
        }
    }

    public void Draw(Graphics2D g2d)
    {
        // If the flag is gotten?
        if (!flagGotten)
        {
            g2d.drawImage(flagImg, flagX, flagY, null);
        }

        for (int i=0; i<ITEM_NUM; i++)
        {
            // If the heart is gotten?
            if (!heartGotten[i])
            {
                g2d.drawImage(heartImg[i], heartX[i], heartY[i], null);
            }

            // If the watch is gotten?
            if (!watchGotten[i])
            {
                g2d.drawImage(watchImg[i], watchX[i], watchY[i], null);
            }
        }

        for (int i=0; i<METEOR_NUM; i++)
        {
            // If the meteor item is gotten?
            if (!meteorGotten[i])
            {
                g2d.drawImage(meteorImg[i], meteorX[i], meteorY[i], null);
            }
        }
    }
}
