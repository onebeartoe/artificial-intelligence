
package org.onebeartoe.ai.veo.create;

import com.google.genai.Client;

import com.google.genai.types.GenerateVideosOperation;

import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.Image;
import com.google.genai.types.GenerateVideosResponse;
import java.io.IO;

import java.io.IOException;
import java.util.List;
import static org.onebeartoe.prompts.GenAIModels.VEO_FAST_3_1_PREVIEW;
import org.onebeartoe.prompts.Responses;
import static org.onebeartoe.prompts.Responses.formattedDate;

//TODO:
//TODO:
//TODO: REfactor this to be more parameterized for all the other 
//TODO:     main() class clients.
//TODO:
//TODO:

//!!!!!!!!!!TODO: Remove the plural on Images
public class CreateVideoFromTextAndImages //extends CreateVideo
{

    public static void main(String[] args) throws InterruptedException, IOException 
    {                     
        if(args.length != 2)
        {
            var errorMessage = 
                     """
                     At least two arguments are needed to invoke this app. 
                     The first is either the input file for text prompt or a 
                     known final String prompt name.  The second arguement is the path to 
                     the image file.
                      """;
                    
            System.err.println(errorMessage);
            
            System.exit(-3);
        }
        
        var textPrompt = args[0];
        
        var promptImagePath = args[1];
        
        var app = new CreateVideoFromTextAndImages();
        
        app.createVideoFromTextAndImage(textPrompt, promptImagePath);
    }
    
    public void createVideoFromTextAndImage(String textPrompt, String promptImagePath) throws InterruptedException
    {
        
        
//TODO: !!!!correctly configure the content tuype        
        Image promptImage = Image.fromFile(promptImagePath, "image/jpeg");
        
//TODO: are the environment variables set correctly?        
// Initialize the client. The client will automatically use Application Default Credentials.
// Ensure GOOGLE_CLOUD_PROJECT and GOOGLE_CLOUD_LOCATION environment variables are set.
        Client client = Client.builder().vertexAI(true).build();


 
        // Configure video generation parameters
        GenerateVideosConfig config = GenerateVideosConfig.builder()
                .aspectRatio("16:9") // Example aspect ratio
                .resolution("720p")
                .generateAudio(true)
                
//TODO: do it!                
//                .durationSeconds(16)
//TODO: it does not generate anythign. 
//TODO: is there a way to see any error message?                
                
//TODO: do it!                    
//TODO: do it!  .numberOfVideos(3);                
                
                .build();

        var modelName = VEO_FAST_3_1_PREVIEW.getModelName();
        
        // Call the generateVideos method. Note that the Java SDK handles the input types.
        // It accepts the model ID, prompt, a list of input contents (images), and config.
        GenerateVideosOperation operation = client.models.generateVideos(
                modelName,                
                textPrompt,
                promptImage,
                config
        );

        IO.println("Waiting for video generation to complete...");

        // Poll the operation status until the video is ready
        while ( ! operation.done().orElse(false) ) 
        {
            Thread.sleep(10_000); // Wait for 10 seconds
            
            IO.print(".");
            
            operation = client.operations.getVideosOperation(operation, null);
        }
        
        IO.println("Video response recieved");
        
        

        var outputName = "video.mp4";
        
        var formattedDate = formattedDate();
        
        char i = 'a';
        
        var formatedName = Responses.formattedOutPath(outputName, formattedDate, i)
                                        .toString();
                
        var testResponse = operation.response()
                .toString();
        
        IO.println("testResponse = " + testResponse);
        
        operation.response()
            .flatMap(GenerateVideosResponse::generatedVideos)
            .stream()
            .flatMap(List::stream)
//TODO:!!!!
//TODO:!!! catch them all!!!!    
//TODO:!!! not just the first one!!!!!!!
//TODO:!!!!                
            .findFirst()
            .ifPresent(video -> client.files.download(video, formatedName, null));
        
        IO.println("video output: " + formatedName);        
    }
}
