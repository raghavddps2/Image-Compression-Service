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
        //chamge this.
        double fileSizeInKB = (float)input.length() /(1024.0);
        System.out.println("File Size is: "+fileSizeInKB+"KB");
        compress(outputPath,inputImage,fileSizeInKB);

        return;
    }

    public void compressOthers(String inputPath,String outputPath,String extension) throws IOException{
        //Reading the file.
        File input = new File(inputPath);
        double fileSizeInKB = (float)input.length() /(1024.0);
        System.out.println("File Size is: "+fileSizeInKB+"KB");
        BufferedImage inputImage = ImageIO.read(input);
        compress(outputPath, inputImage,fileSizeInKB);
    }

    //Private.
    private void compress(String outputPath,BufferedImage inputImage,double fileSizeInKb) throws IOException {

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
//        double compressionQualityFactor = (400)/fileSizeInKb;
//        System.out.println(compressionQualityFactor);
////        if(compressionQualityFactor < 0.05){
////            compressionQualityFactor = 0.05;
////        }
//        System.out.println(compressionQualityFactor);
        double compressionQualityFactor = 0.5;
        param.setCompressionQuality((float)compressionQualityFactor);
            //Writing the image back.
        writer.write(null,new IIOImage(inputImage,null,null),param);
        return;

    }
}
