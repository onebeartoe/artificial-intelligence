//TODO: make this a main class or jbang!
package org.onebeartoe.ai.veo.create;

import com.google.genai.types.Image;

/**
 *
 */
public class RobotChickenVideoFromTextAndImage 
{
            String prompt = """
                        use the robot chicken as a 
                            model for a cute futuristic pet
                                    waling around nice park.
                                            add flying cars in the         city skyline. 
                                                    The video should have a cinematic feel.
                                                                                           """;
        
        // Define the input images as Content parts        
        Image image = Image.fromFile("robot-chicken-b.png", "image/png");
    
}
