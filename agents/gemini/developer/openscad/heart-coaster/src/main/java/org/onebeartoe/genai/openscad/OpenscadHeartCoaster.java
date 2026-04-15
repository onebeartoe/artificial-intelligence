
package org.onebeartoe.genai.openscad;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Part;
import org.onebeartoe.prompts.GenAIModels;
import org.onebeartoe.prompts.Prompts;

/**
 *
 */
public class OpenscadHeartCoaster 
{
    public static void main(String[] args) 
    {
        var app = new OpenscadHeartCoaster();
        
        var shape = "hart (❤️)";
        
        app.createRepeatingPatternCoaster(shape);
    }

    private void createRepeatingPatternCoaster(String shape) 
    {
        try (Client client = new Client()) 
        {
            GenerateContentConfig config = GenerateContentConfig.builder()
                                                    .responseModalities("TEXT")
                                                    .build();
 
            String prompt = preparePrompt(shape);

            var modelName = GenAIModels.GEMINI_3_1_PRO_PREVIEW.getId();
            
//            client.models.getClass()
            
            var response = client.models.generateContent(modelName,
                                                        prompt, 
                                                        config);
  
            System.out.println("response = " + response);

            System.out.println("Parts:");
            
            for (Part part : response.parts()) 
            {
                if (part.text().isPresent()) 
                {
                    System.out.println(part.text().get());
                } 
            }
        }        
    }    

    private String preparePrompt(String shape) 
    {
        var template = Prompts.Developer.OpenScad.REPEATING_PATTERN_COASTER;
        
        return String.format(template, shape);
    }
}
