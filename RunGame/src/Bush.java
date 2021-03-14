import javax.swing.*;
import java.awt.*;

public class Bush extends GameIcon  {

    private Image bush;

    Bush(int frameWidth, int frameHeight){
        super(frameWidth, frameHeight);
        bush = (new ImageIcon("images\\tree.png").getImage());

        setIconWidth(bush.getWidth(null)-10);
        setIconHeight(bush.getHeight(null));

        setX(frameWidth);
        setY(380 - getIconHeight());
    }

    public Image getImage() {
        return bush;
    }
}
