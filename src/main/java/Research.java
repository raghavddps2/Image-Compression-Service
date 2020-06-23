import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Research {

    static ArrayList<Double> fileSize = new ArrayList<Double>();
    static ArrayList<Double> compressionQuality = new ArrayList<Double>();
    static StringBuffer fileSizeStr = new StringBuffer("[");
    static StringBuffer compressionQualityStr = new StringBuffer("[");
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
//        compress(outputPath,inputImage);
        return;
    }

    public void compressOthers(String inputPath,String outputPath,String extension) throws IOException{
        //Reading the file.
        File input = new File(inputPath);
        double fileSizeInMB = (float)input.length() /(1024.0*1024.0);
        System.out.println("File Size is: "+fileSizeInMB+"MB");
        BufferedImage inputImage = ImageIO.read(input);

        //Calling the compression method.
        for(double i=1.0;i>=0.0;i-=0.01) {
            compress(outputPath, inputImage, i);
        }
        fileSizeStr.append("]");
        compressionQualityStr.append("]");

        System.out.println(fileSizeStr);
        System.out.println(compressionQualityStr);
        return;
    }

    //Private.
    private void compress(String outputPath,BufferedImage inputImage,double i) throws IOException {

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
        param.setCompressionQuality((float)i);
        //Writing the image back.
        writer.write(null,new IIOImage(inputImage,null,null),param);
        double fileSizeInMB = (float)output.length() /(1024.0*1024.0);
        System.out.println("Quality Factor & Compressed File Size: "+i + " "+fileSizeInMB);
        fileSize.add(fileSizeInMB);
        compressionQuality.add(i);
        fileSizeStr.append(fileSizeInMB+",");
        compressionQualityStr.append(i+",");
        return;

    }
}
