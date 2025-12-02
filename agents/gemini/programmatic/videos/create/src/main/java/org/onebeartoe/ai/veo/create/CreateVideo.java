
package org.onebeartoe.ai.veo.create;

import com.google.genai.Client;
import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.GenerateVideosResponse;
import com.google.genai.types.GenerateVideosSource;
import com.google.genai.types.GenerateVideosSource.Builder;
import java.util.List;
import org.onebeartoe.prompts.GenAIModels;

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
        
        var outputPath = "video.mp4";
        
        operation.response()
            .flatMap(GenerateVideosResponse::generatedVideos)
            .stream()
            .flatMap(List::stream)
//!!! catch them all!!!!                 
            .findFirst()
            .ifPresent(video -> client.files.download(video, outputPath, null));
        
        System.out.println("video output: " + outputPath);
        
return null;
    }
    
    public abstract GenerateVideosSource videosSource();
}
