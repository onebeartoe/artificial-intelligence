package org.onebeartoe;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.onebeartoe.prompts.GenAIModels.GEMINI_2_5_FLASH_IMAGE;
import org.onebeartoe.prompts.Responses;

public class BananaEdit 
{
    private final Client client;
    
    public BananaEdit()
    {
        var apiKey = "GEMINI_API_KEY";
        
        client = new Client.Builder()
                .apiKey(System.getenv(apiKey))
                .build();
    }

    public Path edit(Path inputPath, String prompt) throws IOException 
    {        
        var modelName = GEMINI_2_5_FLASH_IMAGE.getId();

        var mimeType = Files.probeContentType(inputPath);

        var content = Content.fromParts(
                Part.fromBytes(Files.readAllBytes(inputPath), mimeType),
                Part.fromText(prompt)
            );

        var config = GenerateContentConfig.builder()
                                            .responseModalities("TEXT", "IMAGE")
                                            .build();

        var response = client.models.generateContent(modelName,
                                        content,
                                        config);            

        var outputPathName = "edited-" + inputPath.getFileName().toString() + ".png";

        Responses.saveFirstBinaryPart(response, outputPathName);

        return Path.of(outputPathName);        
    }
}
