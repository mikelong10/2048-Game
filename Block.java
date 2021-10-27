import java.awt.*;

public class Block {

    private Color color;
    private int value, x, y, size;

    public Block(Color color, int value, int x, int y, int size) {
        this.color = color;
        this.value = value;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(Graphics2D g2){
//        if (value == 1)
//            g2.setColor(Color.WHITE);
//        else if (value == 2)
//            g2.setColor(Color.lightGray);
//        else if (value == 4)
//            g2.setColor(Color.pink);
//        else if (value == 8)
//            g2.setColor(Color.orange);
//        else if (value == 16)
//            g2.setColor(Color.MAGENTA);
//        else if (value == 32)
//            g2.setColor(Color.red);
//        else if (value == 64)
//            g2.setColor(Color.yellow);
//        else if (value == 128)
//            g2.setColor(Color.blue);
//        else if (value == 256)
//            g2.setColor(Color.CYAN);
        g2.drawRect(x+2, y+2, size-4, size-4);
        if (value > 1 && value < 10){
            g2.setFont(new Font("Comic Sans", Font.PLAIN, 100));
            g2.drawString("" + value, x + 70, y + 138);
        } else if (value > 9 && value < 100) {
            g2.setFont(new Font("Comic Sans", Font.PLAIN, 85));
            g2.drawString("" + value, x + 47, y + 130);
        } else if (value > 99 && value < 1000) {
            g2.setFont(new Font("Comic Sans", Font.PLAIN, 70));
            g2.drawString("" + value, x + 32, y + 123);
        } else if (value > 999 && value < 10000) {
            g2.setFont(new Font("Comic Sans", Font.PLAIN, 55));
            g2.drawString("" + value, x + 28, y + 117);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
