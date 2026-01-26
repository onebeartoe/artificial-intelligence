
package org.onebeartoe.ai.veo.create;

import com.google.genai.Client;

import com.google.genai.types.GenerateVideosOperation;

import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.Image;
import com.google.genai.types.GenerateVideosResponse;
import com.google.genai.types.GenerateVideosSource;

import java.io.IOException;
import java.util.List;
import static org.onebeartoe.prompts.Prompts.Imaging.TapeRecoderUnwoundText.TAPE_RECODER_UNWOUND_TEXT;
import org.onebeartoe.prompts.Responses;
import static org.onebeartoe.prompts.Responses.formattedDate;

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
//                .durationSeconds(20)
                
                
//TODO: do it!                    
//TODO: do it!  .numberOfVideos(3);                
                
                .build();

        // Call the generateVideos method. Note that the Java SDK handles the input types.
        // It accepts the model ID, prompt, a list of input contents (images), and config.
        GenerateVideosOperation operation = client.models.generateVideos(
                
//TODO!!!!!!!1is this right????veo-3.1-generate-preview
//TODO!!!!!!!1is this right????
//TODO!!!!!!!1is this right????
//TODO!!!!!!!1is this right????                
                "veo-3.1-generate-preview", // Use a suitable Veo model
//TODO: this is an update version, add it GenAIModels.java!!!!

                
//                prompt,
//                image,
                
                textPrompt,
                promptImage,
                
                
                config
        );

        System.out.println("Waiting for video generation to complete...");

        // Poll the operation status until the video is ready
        while ( ! operation.done().orElse(false) ) 
        {
            Thread.sleep(10_000); // Wait for 10 seconds
            
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

//    @Override
//    public GenerateVideosSource videosSource() 
//    {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
