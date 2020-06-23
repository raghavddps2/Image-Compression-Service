import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

public class Upload {
    public String uploadImageToS3(String fileName){

        System.out.println("------------------------------------------------");
        System.out.println("Uploading Started");
        //AWS Credentials.
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA4E4FLPEYDV6IAUGP", "FcqElrK8Kq9GyglnG+ABNzHnhlve9lx5Nc7Jutsp");
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("ap-south-1").withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        //Specifying bucket information.
        String bucketName = "mybucket19081999";
        String folderName = "Campaign_Images";
        String fileNameInS3 = fileName;
        String fileNameInLocalPC = "Assets/Compressed/"+fileName;

        //Uploading file.
        PutObjectRequest request = new PutObjectRequest(bucketName, folderName + "/" + fileNameInS3, new File(fileNameInLocalPC));
        s3Client.putObject(request);

        //Returning the url.
        String publicUrl = "https://mybucket19081999.s3.ap-south-1.amazonaws.com/"+folderName+"/"+fileNameInS3;
        System.out.println("Uploading Completed");
        System.out.println("------------------------------------------------");
        System.out.println("The public url for the compressed image is: ");
        return publicUrl;

    }
}
