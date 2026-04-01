
package org.onebeartoe.combine;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.onebeartoe.prompts.GenAIModels;
import static org.onebeartoe.prompts.Prompts.Imaging.Combine.TAYTAY_CHANGE_DRESS_COLOR;
import static org.onebeartoe.prompts.Prompts.SportsBall.SWAP_PLAYER_HEAD;
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
            var modelName = GenAIModels.GEMINI_2_5_FLASH_IMAGE.getId();

//TODO: give Taytay her own main class            
            var taylorContent = Content.fromParts(
                    Part.fromBytes(Files.readAllBytes(Path.of("decor.png")), "image/png"),
                    Part.fromBytes(Files.readAllBytes(Path.of("taylor.png")), "image/png"),
                    Part.fromBytes(Files.readAllBytes(Path.of("red-dress.png")), "image/png"),
                    Part.fromText(TAYTAY_CHANGE_DRESS_COLOR));

            var playerPath = "../../../../cli/imagen/imagen-imagen-4.0-fast-generate-001-20251119-033016-0.png";
            
            var pigeonContent = Content.fromParts(
                                            Part.fromBytes(Files.readAllBytes(Path.of(playerPath)), "image/png"),
                                            Part.fromBytes(Files.readAllBytes(Path.of("pigeon-head.jpg")), "image/jpeg"),
                                            Part.fromText(SWAP_PLAYER_HEAD)
                                            );
            
            var response = client.models.generateContent(modelName,
                                            pigeonContent,
//                                            taylorContent,
                                            GenerateContentConfig.builder()
                                                .responseModalities("TEXT", "IMAGE")
                                                .build());
        
            var outputPathName = "red-taylor-decor.png";

            Responses.saveFirstBinaryPart(response, outputPathName);
        }
    }
    
}
