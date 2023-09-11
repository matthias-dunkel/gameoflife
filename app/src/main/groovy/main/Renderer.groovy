package main

import javax.swing.*
import java.awt.*
import java.awt.event.*

class BoardDrawer extends JPanel implements ActionListener {
    private Board board;
    private Timer timer;
    private int scale;
    
    BoardDrawer(Board board, int scale) {
        this.board = board;
        this.scale = scale;

        timer = new Timer(500, this)
        timer.start();
    }
  
    void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK)
        for(int r = 0; r < this.board.size; r++){
            for(int c = 0; c < this.board.size; c++){
                if(this.board.getAt(r, c)){
                    g.fillRect(c * scale, r * scale, scale, scale)
                }
            }
        }
    }

    void actionPerformed(ActionEvent e) {
        this.board.tick()
        repaint();
    }
}

class Renderer extends JFrame {
    int width;
    int height;
    BoardDrawer drawer;
    Board board;
    Renderer(Board board) {
        this.drawer = drawer
        this.board  = board
        this.width  = this.board.size * 10
        this.height = this.board.size * 10
    }

    void setUpGui() {
        setTitle("Game of Life")
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        setSize(this.width, this.height);
        add(new BoardDrawer(this.board, 10));
        setVisible(true);
    }
}