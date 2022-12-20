package com.tatia.helper.picture;

import java.awt.* ;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.* ;

public class StartPicture extends JFrame {
    public StartPicture() throws IOException {
        super();
        setTitle("Cat");
        setContentPane(new Picture());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    public static class Picture extends JPanel {
        private Image img ;
        public Picture() throws IOException {
            img = ImageIO.read(Objects.requireNonNull(StartPicture.class.getResource("/picture/Cat.jpg")));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 230, 10, this);
        }
    }
    public static void main() throws IOException {
        new StartPicture();
    }
}
