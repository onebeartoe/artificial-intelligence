
package org.onebeartoe.combine;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
//import java.io.IO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.onebeartoe.combine.BananaNextPigeon.modelName;
import org.onebeartoe.prompts.GenAIModels;
import org.onebeartoe.prompts.Prompts;
import org.onebeartoe.prompts.Responses;

/**
 *
 */
public class BottleLabel 
{
    public static void main(String[] args) throws IOException 
    {
        var app = new BottleLabel();
        
        app.generateLabel();
    }

    private void generateLabel() throws IOException 
    {
        Client client = GenAIModels.initializeClient();
            
        var promptText = String.format(Prompts.Marketing.PRODUCT_LABEL);
        
        var betoPath = Path.of("beto-2023-08_n.jpg");
        
        var betoBytes = Files.readAllBytes(betoPath);
        
        var content = Content.fromParts(Part.fromBytes(betoBytes, "image/jpeg"),
                                        Part.fromText(promptText) );
       
        var config = GenerateContentConfig.builder()
                                          .responseModalities("TEXT", "IMAGE")
                                          .build();
        
        GenerateContentResponse response = client.models.generateContent(modelName, content, config);
        
        var outfileName = "bottle-label.png";
        
        Path outpath = Responses.saveFirstBinaryPart(response, outfileName);
        
        IO.println("outpath: " + outpath);
    }
}
