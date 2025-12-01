
package org.onebeartoe.ai.veo.create;

import com.google.genai.types.GenerateVideosSource;
import org.onebeartoe.prompts.Prompts;

/**
 *
 */
public class CreateVideoFromText extends CreateVideo
{
    public static void main(String[] args) throws InterruptedException 
    {
        var app = new CreateVideoFromText();
        
        app.fromText();
    }    
    
    @Override
    public GenerateVideosSource videosSource() 
    {
        var promptText = Prompts.Imaging.FunkyElectricChickenLeg.LITERAL;
        
        return GenerateVideosSource.builder()
                .prompt(promptText)
                .build();
    }
}
