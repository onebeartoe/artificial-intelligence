
package org.onebeartoe.combine;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.onebeartoe.prompts.Responses;

/**
 *
 */
public class BananaCombine 
{

    public static void main(String[] args) throws IOException 
    {
                var apiKey = "GEMINI_API_KEY";
//        var apiKey = "GOOGLE_API_KEY";
        
        try (
//Client client = new Client.Builder()
//    .project(System.getenv("GOOGLE_CLOUD_PROJECT_ID"))
//    .location(System.getenv("GOOGLE_CLOUD_LOCATION"))
//    .vertexAI(true)
//    .build()
                
                
//!!                
Client client = new Client.Builder()
.apiKey(System.getenv(apiKey))
.build()
                
                
                
                ) 
        {

//!!!!!!!!!
//TODO:!                    
// make this a static or enum                    
            var modelName = "gemini-2.5-flash-image";

        

            var response = client.models.generateContent(modelName,
                Content.fromParts(
                    Part.fromBytes(Files.readAllBytes(Path.of("decor.png")), "image/png"),
                    Part.fromBytes(Files.readAllBytes(Path.of("taylor.png")), "image/png"),
                    Part.fromBytes(Files.readAllBytes(Path.of("red-dress.png")), "image/png"),
                    Part.fromText("""
                        Add this person to the exterior decor,
                        and make her wear the red dress.
                        """)
                ),
                GenerateContentConfig.builder()
                    .responseModalities("TEXT", "IMAGE")
                    .build());

        
            var outputPathName = "red-taylor-decor.png";

            Responses.saveFirstBinaryPart(response, outputPathName);
        }
    }
    
}
