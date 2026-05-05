package edu.kings;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private BufferedImage image;
	private URL imageUrl;
	
	public ImagePanel(String resourcePath) {
	    try {
	        imageUrl = getClass().getResource(resourcePath);
	        //System.out.println("Loading: " + resourcePath + " -> " + imageUrl);
	        if (imageUrl == null) {
	            throw new IOException("Image not found: " + resourcePath);
	        }
	        image = ImageIO.read(imageUrl);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // Maintain aspect ratio
            double imgAspect = (double) image.getWidth(null) / image.getHeight(null);
            int drawWidth = panelWidth;
            int drawHeight = (int) (panelWidth / imgAspect);

            if (drawHeight > panelHeight) {
                drawHeight = panelHeight;
                drawWidth = (int) (panelHeight * imgAspect);
            }

            // Scale factor (optional)
            double scaleFactor = 0.85; // 50% of max fit size
            drawWidth = (int) (drawWidth * scaleFactor);
            drawHeight = (int) (drawHeight * scaleFactor);

            // Center the smaller image
            int x = (panelWidth - drawWidth) / 2;
            int y = (panelHeight - drawHeight) / 2;

            g.drawImage(image, x, y, drawWidth, drawHeight, this);
        }
    }
    
    public void setImage(String resourcePath) {
    	try {
            URL url = getClass().getResource(resourcePath);
            if (url == null) {
                throw new IOException("Image not found: " + resourcePath);
            }
            image = ImageIO.read(url);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}