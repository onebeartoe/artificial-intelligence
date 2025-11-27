
package org.onebeartoe.prompts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import java.io.IO;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *

 */
public class Responses 
{       
    public static byte [] firstBinaryPart(GenerateContentResponse response)
    {
        byte [] data = null;
        
        for (Part part : Objects.requireNonNull(response.parts())) 
        {
            if (part.inlineData().isPresent()) 
            {
                var blob = part.inlineData().get();
            
                if (blob.data().isPresent()) 
                {
                    data = blob.data().get();

                    break;
                }
            }
        }

        return data;
    }    
 
    public static String formattedDate()
    {
        LocalDateTime now = LocalDateTime.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"); // HH for 24-hour format
        
        String formattedDateTime = now.format(formatter);        
        
        return formattedDateTime;
    }
    
    public static Path saveFirstBinaryPart(GenerateContentResponse response, String outpathPathName) throws IOException 
    {
        byte [] data = Responses.firstBinaryPart(response);
        
        var formattedDate = formattedDate();
        
        var outPath = Paths.get(formattedDate + "-" + outpathPathName);
        
        IO.println("Outputting image to this path: \n" + outPath);
        
        Files.write(outPath, data);
        
        return outPath;
    }    
}
