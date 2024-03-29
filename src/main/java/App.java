import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) {

        try{

            //Take filename from the user.
            System.out.println("--------------------- Image Compression ------------------------");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the image name in the Assets folder: \t");
            String file = reader.readLine();
            String[] fileNameSplit = file.split("\\.");

            //We convert everything to jpg because we can achieve maximum compression.
            String fileName = fileNameSplit[0] +".jpg";
            String extension = fileNameSplit[1];

            //Making the input and the output image paths.
            String inputPath = "Assets/Original/"+file;
            String outputPath = "Assets/Compressed/"+fileName;

            //Making objects for uploading and compression.
            Upload upload = new Upload();
            Compression compression = new Compression();

            //Calling appropriate compression method for the file type.
            String fileNameInAws = fileName;
            System.out.println("Processing Image");
            if(extension.equals("png")){
                compression.compressPng(inputPath,outputPath,extension);
            }
            else{
                compression.compressOthers(inputPath,outputPath,extension);
            }
            System.out.println("Processing Done, Image Saved!");
            System.out.println("------------------------------------------------");
            //Calling function to upload image to the cloud.
            String publicUrl = upload.uploadImageToS3(fileNameInAws);
            //Public Url.
            System.out.println(publicUrl);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
