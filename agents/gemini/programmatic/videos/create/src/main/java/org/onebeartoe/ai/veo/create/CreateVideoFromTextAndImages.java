
package org.onebeartoe.ai.veo.create;

import com.google.genai.Client;

import com.google.genai.types.GenerateVideosOperation;

import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.Image;
import com.google.genai.types.GenerateVideosResponse;

import java.io.IOException;
import java.util.List;
import org.onebeartoe.prompts.Responses;
import static org.onebeartoe.prompts.Responses.formattedDate;

//!!!!!!!!!!TODO: Remove the plural on Images
public class CreateVideoFromTextAndImages 
{

    public static void main(String[] args) throws InterruptedException, IOException {
        // Initialize the client. The client will automatically use Application Default Credentials.
        // Ensure GOOGLE_CLOUD_PROJECT and GOOGLE_CLOUD_LOCATION environment variables are set.
        Client client = Client.builder().vertexAI(true).build();

        String prompt = """
                        use the robot chicken as a 
                            model for a cute futuristic pet
                                    waling around nice park.
                                            add flying cars in the         city skyline. 
                                                    The video should have a cinematic feel.
                                                                                           """;
        
        // Define the input images as Content parts
        Image image = Image.fromFile("robot-chicken-b.png", "image/png");



        // Configure video generation parameters
        GenerateVideosConfig config = GenerateVideosConfig.builder()
                .aspectRatio("16:9") // Example aspect ratio
                .build();

        // Call the generateVideos method. Note that the Java SDK handles the input types.
        // It accepts the model ID, prompt, a list of input contents (images), and config.
        GenerateVideosOperation operation = client.models.generateVideos(
                
//TODO!!!!!!!1is this right????
//TODO!!!!!!!1is this right????
//TODO!!!!!!!1is this right????
//TODO!!!!!!!1is this right????                
                "veo-3.1-generate-preview", // Use a suitable Veo model
                prompt,
                image,
//                imageContents,
                config
        );

        System.out.println("Waiting for video generation to complete...");

        // Poll the operation status until the video is ready
        while ( ! operation.done().orElse(false) ) 
        {
            Thread.sleep(10000); // Wait for 10 seconds
            
            System.out.print(".");
            
            operation = client.operations.getVideosOperation(operation, null);
        }
        
        System.out.println("Video response recieved");

        var outputName = "video.mp4";
        
        var formattedDate = formattedDate();
        
        char i = 'a';
        
        var formatedName = Responses.formattedOutPath(outputName, formattedDate, i)
                                        .toString();
                
        
        
        operation.response()
            .flatMap(GenerateVideosResponse::generatedVideos)
            .stream()
            .flatMap(List::stream)
//TODO:!!!!
//TODO:!!! catch them all!!!!                 
//TODO:!!!!                
            .findFirst()
            .ifPresent(video -> client.files.download(video, formatedName, null));
        
        System.out.println("video output: " + formatedName);        
    }
}
