package org.onebeartoe;

import java.io.IOException;
import java.nio.file.Path;

public class SanAntonioStormy 
{
    public static void main(String[] args) throws IOException 
    {
        var stormyNightPrompt = """
            Simplify this painting to focus on key elements, turn
            this oil painting into a black and white ink noir comic
            drawing, make the weather rainy and change the time of
            the day to be at night.
            """;        
        
        var app = new BananaEdit();
        
        var inputPath = Path.of("san-antonio-riverwalk.png");
        
        var outputPath = app.edit(inputPath, stormyNightPrompt);
        
        System.out.println("Stormy image saved to: " + outputPath);
    }
}
