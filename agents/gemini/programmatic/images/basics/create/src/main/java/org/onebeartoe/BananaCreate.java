package org.onebeartoe;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.onebeartoe.prompts.Prompts;
import org.onebeartoe.prompts.Responses;

public class BananaCreate 
{
    public static void main(String[] args) throws IOException, Exception 
    {
        var apiKey = "GEMINI_API_KEY";
//        var apiKey = "GOOGLE_API_KEY";


// TODO: remove this demo code
var somePrompt = Prompts.bestPlayer;

// TODO: move these to the Prompts class
var oilPrompt =                 """
        An impressionist oil painting
        of the port of La Rochelle
        with its towers and sailing ships.
        """;


var funkyPrompt = """
        funky electric chicken leg 
        """;

        try (
Client client = new Client.Builder()
.apiKey(System.getenv(apiKey))
.build()
) 
        {
            // artificial intelligence
            
            
            
            
            var response = client.models.generateContent(
//!!!!!!!!!
//TODO:!                    
// make this a static or enum                    
        "gemini-2.5-flash-image",  // ehte demo has '-preivew' at the end of teh model but is inot avaialbe any more
//        "gemini-2.5-flash-image-preview",
//!!!!TODO above                    

        
        funkyPrompt
                    
                    
                ,    
// only needed for Vertex???                    
//                    ,
    GenerateContentConfig.builder()
        .responseModalities("TEXT", "IMAGE")
        .build()
            );

            var outputPathName = "oil.png";
            
            List<Path> outpaths = Responses.saveAllBinaryParts(response, outputPathName);
//            Responses.saveFirstBinaryPart(response, outputPathName);
        }
    }
}
