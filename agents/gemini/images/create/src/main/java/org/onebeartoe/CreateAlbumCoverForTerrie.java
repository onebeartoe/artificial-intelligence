
package org.onebeartoe;

import com.google.genai.types.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.onebeartoe.prompts.Prompts;
import static org.onebeartoe.prompts.Prompts.Music.ALBUM_COVER_WITH_lYRICS;

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
        
        var c = Prompts.Music.class;
        
        var unformattedText = ALBUM_COVER_WITH_lYRICS;
        
        var promptText = String.format(unformattedText, lyricsText);
        
        var part = Part.fromText(promptText);          
        
        var parts = new ArrayList<Part>();
        
        parts.add(part);
        
        return parts;
  }
}
