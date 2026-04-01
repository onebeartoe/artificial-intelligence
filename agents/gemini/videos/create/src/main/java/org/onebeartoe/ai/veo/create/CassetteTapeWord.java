//TODO: make this a main class or jbang!
package org.onebeartoe.ai.veo.create;

import com.google.genai.types.GenerateVideosSource;
import com.google.genai.types.Image;
import org.onebeartoe.prompts.Prompts;
import static org.onebeartoe.prompts.Prompts.Imaging.TapeRecoderUnwoundText.TAPE_RECODER_UNWOUND_TEXT;

//TODO:
//TODO:
//TODO: REfactor this to use just a main class 
//TODO:
//TODO:

/**
 *
 */
public class CassetteTapeWord extends CreateVideo
{
    public static void main(String[] args) throws InterruptedException 
    {
        System.out.println("Hello from CassetteTapeWord!");
        
        var app = new CassetteTapeWord();
        
        app.fromText();        
    }

    String tapeTextPrompt = TAPE_RECODER_UNWOUND_TEXT + """
            .       use the image as a model for animating the text as tape printing in cursive.
                            the tape text flow downward from the cassette.
                                animate the word printing from left to right  spelling the word in cursive.
                                                                                           """;
        
        // Define the input images as Content parts        
        Image tapeTextImage = Image.fromFile("/home/luke/Versioning/owner/github/artificial-intelligence/agents/gemini/images/create/tape-text-2025-12-07-0913-a.png", "image/png");

    
    @Override
    public GenerateVideosSource videosSource() 
    {
        var promptText = Prompts.Imaging.FunkyElectricChickenLeg.LITERAL;
        
        return GenerateVideosSource.builder()
                .prompt(promptText)                
                .image(tapeTextImage)
                .build();
    }            
}
