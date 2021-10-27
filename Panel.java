import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel {

    private Board board;

    public Panel(int width, int height) {
        setBounds(0,0, width, height);
        setupGame();
        setupKeyListener();
    }

    public void setupGame(){
        board = new Board();
    }

    public void setupKeyListener(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    board.moveUp();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    board.moveDown();
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    board.moveLeft();
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    board.moveRight();
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        board.drawBoard(g2);
    }
}