import javax.swing.*;
import java.awt.*;

public class MainCharacter extends GameIcon  {

    private Image character;

    MainCharacter( int frameWidth, int frameHeight){
        super(frameWidth, frameHeight);
        character = new ImageIcon("images\\grzybek.png").getImage();

        setX(frameWidth/5);
        setY(frameHeight/2);

        setIconWidth(character.getWidth(null));
        setIconHeight(character.getHeight(null));
    }

    public Image getImage(){
        return character;
    }

    void moveUp(){
            setY(getY() -7);
    }
    void moveDown(){
        setY(getY() + 7);
    }

}
