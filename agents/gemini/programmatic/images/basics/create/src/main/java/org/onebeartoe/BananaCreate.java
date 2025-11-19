package org.onebeartoe;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class BananaCreate 
{
    public static void main(String[] args) 
    {
        var apiKey = "GEMINI_API_KEY";
//        var apiKey = "GOOGLE_API_KEY";






var oilPrompt =                 """
        An impressionist oil painting
        of the port of La Rochelle
        with its towers and sailing ships.
        """;


var funkyPrompt = """
        funky electric chicken leg 
        """;

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

            
            
for (Part part : Objects.requireNonNull(response.parts())) {
    if (part.inlineData().isPresent()) {
        var blob = part.inlineData().get();
        if (blob.data().isPresent()) {
            try {
                Files.write(Paths.get("oil.png"), blob.data().get());
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}            
        }
    }
}
