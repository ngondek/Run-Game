import javax.swing.*;
import java.awt.*;

public class TwoFlowers extends GameIcon{


    private Image twoFlowers;

    TwoFlowers(int frameWidth, int frameHeight){
        super(frameWidth, frameHeight);
        twoFlowers = (new ImageIcon("images\\twoFlowers.png").getImage());

        setIconWidth(twoFlowers.getWidth(null)-10);
        setIconHeight(twoFlowers.getHeight(null));

        setX(frameWidth);
        setY(380 - getIconHeight());

    }

    public Image getImage() {
        return twoFlowers;
    }


}
