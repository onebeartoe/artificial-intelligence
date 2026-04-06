//TODO: make this a main class or jbang!
package org.onebeartoe.ai.veo.create;

import com.google.genai.types.GenerateVideosSource;
import com.google.genai.types.Image;
import org.onebeartoe.prompts.Prompts;
import static org.onebeartoe.prompts.Prompts.Music.Silly.HOW_SILLY_CAN_YOU_GET_LYRICS;

/**
 *
 */
public class HowSillyCanYouGetVideo extends CreateVideo
{      
    // Define the input images as Content parts        
    Image image = Image.fromFile("/home/luke/Versioning/owner/github/artificial-intelligence/agents/gemini/images/create/surf-folks-2026-04-06-1533-a.png", "image/png");

    public static void main(String[] args) throws InterruptedException 
    {
        System.out.println("Hello from HowSillyCanYouGetVideo!");
        
        var app = new HowSillyCanYouGetVideo();
        
        app.fromText();      
        
        System.out.println("sillines video done");
    }        
        
    @Override
    public GenerateVideosSource videosSource() 
    {
        var promptText = HOW_SILLY_CAN_YOU_GET_LYRICS + 
                """
                Do not show the lyrics on the video.
                use the audio form this file: ../../music/silly-surf-song-2026-04-06-1611.mp3
                """;
        
        return GenerateVideosSource.builder()
                .prompt(promptText)                
                .image(image)
                
                .build();
    }        
}
