
package org.onebeartoe.ai.veo.create;

import com.google.genai.Client;
import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.GenerateVideosResponse;
import com.google.genai.types.GenerateVideosSource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.onebeartoe.prompts.GenAIModels;
import org.onebeartoe.prompts.Responses;
import static org.onebeartoe.prompts.Responses.formattedDate;

//TODO:
//TODO:
//TODO: Remove any redundant code
//TODO: Sonar where are you?
//TODO:
//TODO:

/**
 *
 */
public abstract class CreateVideo 
{
//TODO:!!!!! use VEO_FAST during developemnt!!!!    
//TODO: make this configurable    
//    public static final String modelName = GenAIModels.VEO.getId();
    public static final String modelName = GenAIModels.VEO_FAST.getId();
    
    public String fromText() throws InterruptedException
    {
        Client client = GenAIModels.initializeVertexClient();
        
        var config = GenerateVideosConfig.builder()
                .aspectRatio("16:9")
                .resolution("720p")
                .generateAudio(true)
                    
//TODO: do it!                    
//TODO: do it!  .numberOfVideos(3);
        
                .build();
        
        var operation = client.models.generateVideos(
            modelName,
            videosSource(),
            config
        );        
        
        System.out.println("Waiting for video generation to complete...");
        
        while (!operation.done().orElse(false)) 
        {
            Thread.sleep(10_000); // Wait for 10 seconds
            
            System.out.print(".");
            
            operation = client.operations.getVideosOperation(operation, null);
        }
        
        System.out.println("Video response recieved");

var formattedDate = formattedDate();


        AtomicInteger counter = new AtomicInteger(0);


        
       
        var outputPath = "video.mp4";

        
        operation.response()
            .flatMap(GenerateVideosResponse::generatedVideos)
            .stream()
            .flatMap(List::stream)
//TODO:!!!!
//TODO:!!! catch them all!!!!                 
//TODO:!!!!                
            .forEach(video ->
            {
                char iterationChar = (char) ('a' + (char) counter.getAndIncrement() );
                
                var formatedName = Responses.formattedOutPath(outputPath, 
                                                formattedDate, 
                                                iterationChar)
                                        .toString();
                
                client.files.download(video, formatedName, null);                                       
              
                System.out.println("video output: " + formatedName);
            
                if(iterationChar == 'z')
                {
// this can be a lot more robust
                    var errorMessage = "The file name has reached last the laster character"
                            + "; name overlfow reached.";

                    throw new UnsupportedOperationException(errorMessage);
                }             
                
                System.out.println("come now A!!!!!!");
            });
            
        
        
        
return null;
    }
    
    public abstract GenerateVideosSource videosSource();
}
