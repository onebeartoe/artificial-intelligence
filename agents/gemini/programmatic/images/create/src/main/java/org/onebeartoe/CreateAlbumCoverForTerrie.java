
package org.onebeartoe;

import com.google.genai.types.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CreateAlbumCoverForTerrie extends BananaCreate
{
    public static void main(String[] args) throws Exception 
    {        
        var app = new CreateAlbumCoverForTerrie();
        
        app.fromText();
    }

    @Override
    public List<Part> parts() throws IOException 
    {
        var lyricsPath = Path.of("imagine.lyrics");
                        
        var lyricsText = Files.readString(lyricsPath);
                       
//TODO: move this to Prompts.java
        var unformattedText = """
                         Create an image from these lyrics from the song 
                         'Imagine' from John Lennon.
                              
                              Limit the the text to two lines.
                              
                              Use a extra super large font size for the text.
                              
                              Use 4:3 for the image apect ratio.
                              
                              Here are the lyrics: %s"
                         """;
        
        var promptText = String.format(unformattedText, lyricsText);
        
        var part = Part.fromText(promptText);          
        
        var parts = new ArrayList<Part>();
        
        parts.add(part);
        
        return parts;
  }
} 