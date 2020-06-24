import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) {

        try{
            //Take filename from the user.
            System.out.println("--------------------- Image Compression ------------------------");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the image name in the Assets folder: \t");
//
//
//            String arr[] = new String[]{"image1.jpg","image2.jpg","image3.jpg","image4.jpg","image5.jpg","image6.jpg","image7.jpg","image8.jpg","image9.jpg","image10.jpg","image11.jpg","image12.jpg","image13.jpg","image14.jpg","image15.jpg","image16.jpg",
//            "img1.jpg","img2.png","img3.png","img4.png","img5.png","img6.png","img7.png"};
                String file = reader.readLine();
                String[] fileNameSplit = file.split("\\.");

                //We convert everything to jpg because we can achieve maximum compression.
                String fileName = fileNameSplit[0] + ".jpg";
                String extension = fileNameSplit[1];

                //Making the input and the output image paths.
                String inputPath = "Assets/Original/" + file;
                String intermediatePath = "Assets/IntermediateResize/" + file;
                String outputPath = "Assets/Compressed/" + fileName;

                //Making objects for resize, uploading and compression.
                ImageResize imageResize = new ImageResize();
                Upload upload = new Upload();
                Compression compression = new Compression();

                //Resizing the image.
                imageResize.resize(inputPath, intermediatePath);

                //Calling appropriate compression method for the file type.
                String fileNameInAws = fileName;
                System.out.println("Processing Image");
                if (extension.equals("png")) {
                    compression.compressPng(intermediatePath, outputPath, extension);
                } else {
                    compression.compressOthers(intermediatePath, outputPath, extension);
                }
                System.out.println("Processing Done, Image Saved!");
                System.out.println("------------------------------------------------");
//            Calling function to upload image to the cloud.
            String publicUrl = upload.uploadImageToS3(fileNameInAws);
            System.out.println(publicUrl);
            }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
