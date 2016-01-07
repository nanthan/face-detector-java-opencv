import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by benvo_000 on 7/1/2559.
 */
public class MyFrame  {
    private JFrame jframe;
    private MyPanel panel;

    public MyFrame() {
        jframe = new JFrame();
        jframe.getContentPane().setLayout(new FlowLayout());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new MyPanel();
        jframe.getContentPane().add(panel);
        jframe.setVisible(true);
    }

    public void render(Mat img){
        Image image = toBufferedImage(img);
        panel.setImage(image);
        panel.repaint();
        jframe.pack();
    }

    public static Image toBufferedImage(Mat m){
        // Code from http://stackoverflow.com/questions/15670933/opencv-java-load-image-to-gui

        // Check if image is grayscale or color
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        // Transfer bytes from Mat to BufferedImage
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }
}
