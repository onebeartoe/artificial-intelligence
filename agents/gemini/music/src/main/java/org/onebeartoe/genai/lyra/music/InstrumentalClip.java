
package org.onebeartoe.genai.lyra.music;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
public class InstrumentalClip 
{
    public static void main(String[] args) throws IOException 
    {
        var app = new InstrumentalClip();
        
        Path clipPath = app.generateClip();
        
        System.out.println("clipPath = " + clipPath);
    }
    
    /**
     * 
     * @return the Path of the output file
     */
    public Path generateClip() throws IOException
    {
//TODO: add this to the models enumeeration        
        var modelName = "lyria-3-clip-preview";

        var outpath = Paths.get("clip.mp3");
        
        try (Client client = new Client()) 
        {
            GenerateContentConfig config = GenerateContentConfig.builder()
                                                    .responseModalities("TEXT", "AUDIO")
                                                    .build();
            
            var prompt = """
                            A bright chiptune melody in C Major, retro 8-bit 
                            video game style. Instrumental only, no vocals.
                         """;
            
            var response = client.models.generateContent(modelName,
                                                        prompt, 
                                                        config);
            
            for (Part part : response.parts()) 
            {
                if (part.text().isPresent()) 
                {
                    System.out.println(part.text().get());
                } 
                else if (part.inlineData().isPresent()) 
                {
                    var blob = part.inlineData().get();
                    
                    if (blob.data().isPresent()) 
                    {                        
                        Files.write(outpath, blob.data().get());
                        
                        System.out.println("Audio saved to clip.mp3");
                    }
                }
            }
        }
        
        return outpath;
    }
}
