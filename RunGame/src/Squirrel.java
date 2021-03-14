import javax.swing.*;
import java.awt.*;

public class Squirrel extends GameIcon {

    private Image squirrel;

    Squirrel(int frameWidth, int frameHeight) {
        super(frameWidth, frameHeight);
        squirrel = new ImageIcon("images\\squirrel2.png").getImage();

        setIconWidth(squirrel.getWidth(null));
        setIconHeight(squirrel.getHeight(null));

        setX(frameWidth);
        setY(380 - getIconHeight());
    }

    public Image getImage() {
        return squirrel;
    }
}
