package com.br.hub.facecomparsion;

import java.io.IOException;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.rekognition.model.*;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;

@Service
public class ComparsionService {
	
   @Autowired
   private RekognitionClient client;	
	
   public float compareTwoPhotos(String photo1Base64, String photo2Base64) throws IOException{
	   float result = 0f;

       try {
           SdkBytes sourceBytes = SdkBytes.fromByteArray(Base64.getDecoder().decode(photo1Base64.substring(photo1Base64.indexOf(',') + 1)));
           SdkBytes targetBytes = SdkBytes.fromByteArray(Base64.getDecoder().decode(photo2Base64.substring(photo2Base64.indexOf(',') + 1)));

           // Create an Image object for the source image.
           Image souImage = Image.builder()
                   .bytes(sourceBytes)
                   .build();

           Image tarImage = Image.builder()
                   .bytes(targetBytes)
                   .build();

           CompareFacesRequest facesRequest = CompareFacesRequest.builder()
                   .sourceImage(souImage)
                   .targetImage(tarImage)
                   .similarityThreshold(10F)
                   .build();

           // Compare the two images.
           CompareFacesResponse compareFacesResult = client.compareFaces(facesRequest);
           System.out.println(compareFacesResult.toString());

           List<CompareFacesMatch> faceDetails = compareFacesResult.faceMatches();

           for (CompareFacesMatch match : faceDetails) {
               ComparedFace face = match.face();
               BoundingBox position = face.boundingBox();
               System.out.println("Face at " + position.left().toString()
                       + " x:" + position.top()
                       + " y: " + position.left()
                       + " matches with " + face.confidence().toString()
                       + "% confidence.");

           }
           List<ComparedFace> uncompared = compareFacesResult.unmatchedFaces();
           System.out.println("There was " + uncompared.size() + " face(s) that did not match");
           System.out.println("Source image rotation: " + compareFacesResult.sourceImageOrientationCorrection());
           System.out.println("target image rotation: " + compareFacesResult.targetImageOrientationCorrection());

           //
           // Separa o MAIOR RESULTADO!
           Optional<CompareFacesMatch> resultMatchFaceMatch = faceDetails.stream().max(Comparator.comparing(a -> a.similarity().floatValue()));
           result = resultMatchFaceMatch.isPresent() ? resultMatchFaceMatch.get().similarity().floatValue() : 0f;

       } catch (InvalidParameterException exc) {
          // throw new InvalidImageException();
   }
       return result;
   }     
}
