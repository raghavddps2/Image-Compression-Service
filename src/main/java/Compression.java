import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

public class Compression {

    public void compressPng(String inputPath,String outputPath,String extension) throws IOException {

        //Reading the input image.
        File input = new File(inputPath);
        BufferedImage inputImage = ImageIO.read(input);

        //If the image if of type png, we convert that to jpg.
        if (extension.equals("png")) {
            //Converting to jpg.
            BufferedImage newBufferedImage = new BufferedImage(inputImage.getWidth(),
                    inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(inputImage, 0, 0, Color.WHITE, null);
            inputImage = newBufferedImage;
        }
        //Then we call compress method.
        compress(outputPath,inputImage);
        return;
    }

    public void compressOthers(String inputPath,String outputPath,String extension) throws IOException{
        //Reading the file.
        File input = new File(inputPath);
        BufferedImage inputImage = ImageIO.read(input);

        //Calling the compression method.
        compress(outputPath,inputImage);
        return;
    }

    //Private.
    private void compress(String outputPath,BufferedImage inputImage) throws IOException {

        //Getting the OutputStream for the output file.
        File output = new File(outputPath);
        OutputStream os = new FileOutputStream(output);

        //Only using Jpg writer, as we will just use jpg format.
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = (ImageWriter) writers.next();

        //Creating the ImageOutputStream.
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        //Setting compression parameters.
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.5f);

        //Writing the image back.
        writer.write(null,new IIOImage(inputImage,null,null),param);
        return;

    }
}
