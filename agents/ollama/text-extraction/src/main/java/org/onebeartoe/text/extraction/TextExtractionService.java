
package org.onebeartoe.text.extraction;



import java.nio.file.Path;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.Options;
import io.github.ollama4j.utils.OptionsBuilder;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import static org.onebeartoe.prompts.Prompts.Imaging.OpticalCharacterRecognition.EXTRACT_TEXT_FROM_IMAGE;

/**
 *
 */
public class TextExtractionService 
{
    public void textFromImage(Path inputImage)
    {
        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        
        try 
        {
            // Check if server is running
            System.out.println("Reachable: " + ollamaAPI.ping());
  
            ollamaAPI.setRequestTimeoutSeconds(900); 

            var prompt = EXTRACT_TEXT_FROM_IMAGE;
//            ollamaAPI.

            var flyerA = new File("../../gemini/text-extraction/Car-Show-Flyer-A.jpg");
            
            if( !flyerA.exists() )
            {
                throw new IllegalArgumentException("input image does not exist: " + flyerA.getPath() );
            }
            
            var inputFiles = List.of(flyerA);

                    Options options = new OptionsBuilder().build();

            
            // Sync Generation
            OllamaResult result = ollamaAPI.generateWithImageFiles("llama3.2-vision:11b", 
                                                        prompt,
                                                        inputFiles,
                                                        options );
//            String response = ollamaAPI.generate("llama3", "Why is the sky blue?", new OptionsBuilder().build());
            System.out.println(result);
            
            result.notifyAll();
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }        
    }
}



