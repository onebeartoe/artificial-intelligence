package org.onebeartoe;

import java.io.IOException;
import java.nio.file.Path;

public class ComicStyle 
{
    public static void main(String[] args) throws IOException 
    {
        // This prompt is from Laforge's article on comic strip styled image editing
        //      https://github.com/glaforge/comic-trip-agent/blob/main/src/main/resources/comic-prompt.md
        var comicPrompt = """
                    Turn this photography into a pop-art comic panel, 
                          with thick black outlines, colors drops, splashes 
                          and wide strokes or geometrical shapes. 
                          Use halftone textures for non-primary colored areas, 
                          and a vintage muted color palette.
                           """;

        var app = new BananaEdit();
        
        var inputPath = Path.of("/home/luke/Distribute/group/chuey/riverbend-second-house-20260407.jpg");
        
        var outputPath = app.edit(inputPath, comicPrompt);
        
        System.out.println("Comic style image saved to: " + outputPath);
    }
}
