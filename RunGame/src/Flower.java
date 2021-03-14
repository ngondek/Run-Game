import javax.swing.*;
import java.awt.*;

public class Flower extends GameIcon{

    private Image flowers;

    Flower(int frameWidth, int frameHeight){
        super(frameWidth, frameHeight);
        flowers = (new ImageIcon("images\\flower.png").getImage());

        setIconWidth(flowers.getWidth(null)-10);
        setIconHeight(flowers.getHeight(null));

        setX(frameWidth);
        setY(380 - getIconHeight());
    }

    public Image getImage() {
        return flowers;
    }

}
