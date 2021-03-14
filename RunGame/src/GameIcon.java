import java.awt.*;

abstract class GameIcon {

    private int x;
    private int y;
    private int iconWidth;
    private int iconHeight;

    public int speed = 15;

    int frameWidth;
    int frameHeight;

    GameIcon(int frameWidth, int frameHeight){
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }

    void setIconWidth(int iconWidth) {
        this.iconWidth = iconWidth;
    }
    void setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
    }

    public int getIconHeight() {
        return iconHeight;
    }

    public int getIconWidth() {
        return iconWidth;
    }

    void setX(int x) {
        this.x= x;
    }

    void setY(int y) {
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    abstract Image getImage();

    void move() {
            setX(getX() - speed);
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),getIconWidth(),getIconHeight());
    }


}
