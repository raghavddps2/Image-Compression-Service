import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResize {
    public void resize(String inputImagePath,
                              String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }


    public void resize(String inputImagePath,
                              String outputImagePath) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        double imageWidth =  inputImage.getWidth() + 1;
        double imageHeight = (int) inputImage.getHeight() + 1;
        System.out.println(imageHeight+" "+imageWidth);
        double widthPercent = 100000.0/imageWidth;
        double heightPercent = 100000.0/imageHeight;
        double percent = Math.max(widthPercent,heightPercent);
        System.out.println(percent);
        percent = percent/100.0;
        int scaledWidth = (int) (imageWidth * percent);
        int scaledHeight = (int) (imageHeight * percent);
        System.out.println(scaledHeight+" "+scaledWidth);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }

}
