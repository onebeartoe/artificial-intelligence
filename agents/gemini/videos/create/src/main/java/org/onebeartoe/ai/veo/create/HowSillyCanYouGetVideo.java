//TODO: make this a main class or jbang!
package org.onebeartoe.ai.veo.create;

import com.google.genai.types.GenerateVideosSource;
import com.google.genai.types.Image;
import org.onebeartoe.prompts.Prompts;

/**
 *
 */
public class RobotChickenVideoFromTextAndImage extends CreateVideo
{
//TODO: move this to Prompts.java    
            String prompt = """
                        use the robot chicken as a 
                            model for a cute futuristic pet
                                    waling around nice park.
                                            add flying cars in the         city skyline. 
                                                    The video should have a cinematic feel.
                                                                                           """;
        
        // Define the input images as Content parts        
        Image image = Image.fromFile("robot-chicken-b.png", "image/png");

    public static void main(String[] args) throws InterruptedException 
    {
        System.out.println("Hello from RobotChickenVideoFromTextAndImage!");
        
        var app = new RobotChickenVideoFromTextAndImage();
        
        app.fromText();        
    }        
        
    @Override
    public GenerateVideosSource videosSource() 
    {
        var promptText = Prompts.Imaging.FunkyElectricChickenLeg.LITERAL;
        
        return GenerateVideosSource.builder()
                .prompt(promptText)                
                .image(image)
                .build();
    }        
}
