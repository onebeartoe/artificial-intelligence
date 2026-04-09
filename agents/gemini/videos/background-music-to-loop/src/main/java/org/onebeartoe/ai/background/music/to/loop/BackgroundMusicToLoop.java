
package org.onebeartoe.ai.background.music.to.loop;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.AudioContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.message.VideoContent;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.output.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import org.onebeartoe.prompts.Prompts;

/**
 *
 */
public class BackgroundMusicToLoop 
{

    public static void main(String[] args) throws URISyntaxException 
    {
        System.out.println("Hello World!");
        
        URI musicPath = new URI("http://192.168.1.80:9292/music/silly-surf-song-1775514478035.mp3");
//        var musicPath = Path.of("http://192.168.1.80:9292/music/silly-surf-song-1775514478035.mp3");
//        var musicPath = Path.of("/home/luke/Versioning/owner/github/artificial-intelligence/agents/gemini/music/silly-surf-song-1775514478035.mp3");
//        var musicPath = Path.of("../../music/silly-surf-song-1775514478035.mp3");
  
        URI videoToLoop = new URI("http://192.168.1.80:9292/videos/create/silly-video-2026-04-07-0623-a.mp4");
//        var videoToLoop = Path.of("http://192.168.1.80:9292/videos/create/silly-video-2026-04-07-0623-a.mp4");
//        var videoToLoop = Path.of("/home/luke/Versioning/owner/github/artificial-intelligence/agents/gemini/videos/create/silly-video-2026-04-07-0623-a.mp4");
//        var videoToLoop = Path.of("../create/silly-video-2026-04-07-0623-a.mp4");
        
        var app = new BackgroundMusicToLoop();
        
        app.addBackgroundMusicToLoop(musicPath, videoToLoop);
    }

    private void addBackgroundMusicToLoop(URI musicPath, URI videoToLoop)
//    private void addBackgroundMusicToLoop(Path musicPath, Path videoToLoop)
    {
//        if( !musicPath.toFile().exists() )
//        {
//            throw new IllegalArgumentException("the music path is invalid: " + musicPath.toAbsolutePath());
//        }
//                
//        if( !videoToLoop.toFile().exists() )
//        {
//            throw new IllegalArgumentException("the video path is invalid: " + videoToLoop.toAbsolutePath());
//        }        
                
        String apiKey = System.getenv("GEMINI_API_KEY");
        
        if (apiKey == null) 
        {
            System.err.println("Please set the GEMINI_API_KEY environment variable");
            System.exit(1);
        }        
        
        //TODO: add this to GenAIModels.java
        var modelName = "gemini-2.5-flash";
        
        ChatLanguageModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .allowCodeExecution(true)
                .includeCodeExecutionOutput(true)
                .logRequestsAndResponses(true)
                .build();
     
        var prompt = Prompts.Video.Editing.addBackgroundMusicToLoopedVideo();
System.out.println("prompt = " + prompt);

        UserMessage multiModalMessage = UserMessage.from(
                TextContent.from(prompt)
                //,                
//                AudioContent.from(musicPath ),
//                VideoContent.from(videoToLoop )
//                AudioContent.from(musicPath.toUri() ),
//                VideoContent.from(videoToLoop.toUri() )
            );        
        
        Response<AiMessage> response = model.generate(multiModalMessage);
        
        var responseText = response.content().text();
        System.out.println("responseText = " + responseText);
        
        var finishReason = response.finishReason();
        System.out.println("finishReason = " + finishReason);
    }
    
    
}
