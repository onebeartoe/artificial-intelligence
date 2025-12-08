package org.onebeartoe;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Part;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.onebeartoe.prompts.GenAIModels;
import org.onebeartoe.prompts.Responses;

/**
 * This had creates images from text prompts.
 */
public abstract class BananaCreate 
{
    public static final String modelName = GenAIModels.GEMINI_2_5_FLASH_IMAGE.getId();
    
    public List<Path> fromText() throws IOException
    {
        Client client = GenAIModels.initializeClient();
        
        List<Part> parts = parts();                 
        
        Part [] type = new Part[0];
        
        var content = Content.fromParts(parts.toArray(type));
        
        var response = client.models.generateContent(modelName,
                                                     content,    
                                            GenerateContentConfig.builder()
                                                    .responseModalities("TEXT", "IMAGE")
                                                    .build()
                        );

        var outputPathName = "oil.png";

        List<Path> outpaths = Responses.saveAllBinaryParts(response, outputPathName);
                       
        return outpaths;
    }
    
    public abstract List<Part> parts() throws IOException;
}
