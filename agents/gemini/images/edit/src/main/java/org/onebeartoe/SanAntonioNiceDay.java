
package org.onebeartoe;

import java.io.IOException;
import java.nio.file.Path;

/**
 *
 */
public class SanAntonioNiceDay 
{
    public static void main(String[] args) throws IOException 
    {
        var niceDayPrompt = """
            Simplify this painting to focus on key elements, turn
            this oil painting into a black and white ink noir comic
            drawing, make the weather sunny with no clouds
            and change the time of the day to be at daytime.
            """;

        var app = new BananaEdit();
        
        var inputPath = Path.of("san-antonio-riverwalk.png");
        
        var outputPath = app.edit(inputPath, niceDayPrompt);
        
        System.out.println("Image saved to: " + outputPath);
    }    
}
