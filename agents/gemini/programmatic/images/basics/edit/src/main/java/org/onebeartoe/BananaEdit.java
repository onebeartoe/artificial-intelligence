package org.onebeartoe;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class BananaEdit 
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
var modelName = "gemini-2.5-flash-image";  // ehte demo has '-preivew' at the end of teh model but is inot avaialbe any more
//        "gemini-2.5-flash-image-preview",
//!!!!TODO above                    
            
var niceDayPrompt = """
            Simplify this painting to focus on key elements, turn
            this oil painting into a black and white ink noir comic
            drawing, make the weather sunny with no clouds
            and change the time of the day to be at daytime.
            """;

var stormyNightPrompt = """
            Simplify this painting to focus on key elements, turn
            this oil painting into a black and white ink noir comic
            drawing, make the weather rainy and change the time of
            the day to be at night.
            """;

var response = client.models.generateContent(modelName,
    Content.fromParts(
        Part.fromBytes(
            Files.readAllBytes(Path.of("san-antonio-riverwalk.png")), "image/png"),
        Part.fromText(niceDayPrompt)
    ),
    GenerateContentConfig.builder()
        .responseModalities("TEXT", "IMAGE")
        .build());            
            

            
            
for (Part part : Objects.requireNonNull(response.parts())) {
    if (part.inlineData().isPresent()) {
        var blob = part.inlineData().get();
        if (blob.data().isPresent()) {
            try {
                Files.write(Paths.get("oil-2025-11-20.png"), blob.data().get());
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
