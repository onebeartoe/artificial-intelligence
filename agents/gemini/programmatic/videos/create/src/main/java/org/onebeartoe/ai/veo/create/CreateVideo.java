
package org.onebeartoe.ai.veo.create;

import com.google.genai.Client;
import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.GenerateVideosResponse;
import com.google.genai.types.GenerateVideosSource;
import com.google.genai.types.GenerateVideosSource.Builder;
import java.util.List;
import org.onebeartoe.prompts.GenAIModels;
import org.onebeartoe.prompts.Responses;
import static org.onebeartoe.prompts.Responses.formattedDate;

/**
 *
 */
public abstract class CreateVideo 
{
    public static final String modelName = GenAIModels.VEO.getId();
    
    public String fromText() throws InterruptedException
    {
        Client client = GenAIModels.initializeVertexClient();
        
        var operation = client.models.generateVideos(
            modelName,
            videosSource(),
            GenerateVideosConfig.builder()
                .aspectRatio("16:9")
                .resolution("720p")
                .generateAudio(true)
                    
//TODO: do it!                    
//TODO: do it!  .numberOfVideos(3);
        
                .build()
        );        
        
        System.out.println("Requesting video");
        
        while (!operation.done().orElse(false)) 
        {
            Thread.sleep(1000);
            
            operation = client.operations.getVideosOperation(operation, null);
        }
        
        System.out.println("Video response recieved");

var formattedDate = formattedDate();

char i = 'a';
        
       
        var outputPath = "video.mp4";

var formatedName = Responses.formattedOutPath(outputPath, formattedDate, i)
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
        
return null;
    }
    
    public abstract GenerateVideosSource videosSource();
}
