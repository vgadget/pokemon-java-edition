package utilities.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import javax.swing.ImageIcon;

/**
 *
 * @author Adrian Vazquez
 */
public class ImageUtil {

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    
    public static double getProportion(BufferedImage img) {

        return img.getWidth() / img.getHeight();

    }

    public static double getProportion(Dimension d) {

        return d.getWidth() / d.getHeight();

    }

    public static BufferedImage resizeProportional(BufferedImage img, double percent) {

        return resize(img, (int) (img.getWidth() * percent), (int) (img.getHeight() * percent));

    }

    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static BufferedImage cropImage(BufferedImage src, int width, int height) {
        BufferedImage dest = src.getSubimage(0, 0, width, height);
        return dest;
    }

    public static BufferedImage changeColor(BufferedImage img, RGB oldPixelValue, RGB newPixelValue) throws Exception {

        BufferedImage image = deepCopy(img);
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster imageRaster = image.getRaster();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int[] pixel = imageRaster.getPixel(x, y, (int[]) null);

                if (RGB.getRGB(pixel[0], pixel[1], pixel[2]).equals(oldPixelValue)) {
                    pixel[0] = newPixelValue.getRed();
                    pixel[1] = newPixelValue.getGreen();
                    pixel[2] = newPixelValue.getBlue();
                    imageRaster.setPixel(x, y, pixel);
                }
            }
        }
        return image;
    }

    public static BufferedImage changeEveryColor(BufferedImage img, RGB newPixelValue) throws Exception {

        BufferedImage image = deepCopy(img);
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster imageRaster = image.getRaster();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int[] pixel = imageRaster.getPixel(x, y, (int[]) null);

                pixel[0] = newPixelValue.getRed();
                pixel[1] = newPixelValue.getGreen();
                pixel[2] = newPixelValue.getBlue();
                imageRaster.setPixel(x, y, pixel);

            }
        }
        return image;
    }

    public static BufferedImage overlayImages(BufferedImage background, BufferedImage foreground) {

        BufferedImage image = deepCopy(background);

        Graphics2D g2d = image.createGraphics();

        //g2d.drawImage(background, 0, 0, background.getWidth(), background.getHeight(), null);
        g2d.drawImage(foreground, 0, 0, foreground.getWidth(), foreground.getHeight(), null);
        g2d.dispose();

        return image;
    }

    public static BufferedImage setBrightness(BufferedImage img, int amount) throws Exception {
        BufferedImage image = deepCopy(img);
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster imageRaster = image.getRaster();

        RGB pixelValue;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int[] pixel = imageRaster.getPixel(x, y, (int[]) null);

                pixelValue = RGB.getRGB(img, x, y);

                int red = pixelValue.getRed() + amount;
                int green = pixelValue.getGreen() + amount;
                int blue = pixelValue.getBlue() + amount;

                if (red < 0) {
                    red = 0;
                } else if (red > 255) {
                    red = 255;
                }

                if (green < 0) {
                    green = 0;
                } else if (green > 255) {
                    green = 255;
                }

                if (blue < 0) {
                    blue = 0;
                } else if (blue > 255) {
                    blue = 255;
                }

                pixel[0] = red;
                pixel[1] = green;
                pixel[2] = blue;

                imageRaster.setPixel(x, y, pixel);

            }
        }
        return image;
    }

    public static BufferedImage addText(BufferedImage img, String text, Font font, Color c) {

        BufferedImage newImage = deepCopy(img);

        Graphics2D g = (Graphics2D) newImage.createGraphics();
        g.setPaint(c);
        g.setFont(font);

        FontMetrics fm = g.getFontMetrics();

        int fixedX, fixedY;

        fixedX = (int) ((newImage.getWidth() - fm.stringWidth(text)) / 2f);
        fixedY = (int) ((newImage.getHeight() + fm.getHeight() / 2f) / 2f);

        g.drawString(text, fixedX, fixedY);
        g.dispose();

        return newImage;
    }

    public static BufferedImage addText(BufferedImage img, String text, int x, int y, Font font, Color c) {

        BufferedImage newImage = deepCopy(img);

        Graphics2D g = (Graphics2D) newImage.createGraphics();
        g.setPaint(c);
        g.setFont(font);

        g.drawString(text, x, y);
        g.dispose();

        return newImage;
    }
}
