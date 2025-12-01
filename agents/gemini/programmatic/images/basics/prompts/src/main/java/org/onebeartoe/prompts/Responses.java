
package org.onebeartoe.prompts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
//import java.io.IO;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *

 */
public class Responses 
{       
    /**
     * @param response
     * @return
     * @deprecated - use allBinaryParts()
     */
    @Deprecated
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
    
    /**
     * 
     * @param response
     * @param outpathPathName
     * @return
     * @throws IOException
     * @deprecated - use saveAllBinaryParts()
     */
    @Deprecated
    public static Path saveFirstBinaryPart(GenerateContentResponse response, String outpathPathName) throws IOException 
    {
        byte [] data = Responses.firstBinaryPart(response);
        
        var formattedDate = formattedDate();
        
        Path outPath = Paths.get(formattedDate + "-" + outpathPathName);
        
        IO.println("Outputting image to this path: \n" + outPath);
        
        Files.write(outPath, data);
        
        return outPath;
    }    

    public static List<Path> saveAllBinaryParts(GenerateContentResponse response, String outputPathName) throws IOException
    {
        List<Part> binaryParts = Responses.allBinaryParts(response, outputPathName);
        
        var partCount = binaryParts.size();
        
        System.out.println("partCount = " + partCount);
        
        List<Path> outPaths = new ArrayList();
        
        var formattedDate = formattedDate();
        
        char iterationChar = 'a';
        
        for(Part part : binaryParts)
        {
            var outPath = formattedOutPath(outputPathName, formattedDate, iterationChar);
            
            byte[] data = part.inlineData().get().data().get();
            
            Files.write(outPath, data);
            
            outPaths.add(outPath);
            
            iterationChar++;
            
            if(iterationChar == 'a')
            {
                var errorMessage = "The file name ahas looped back to 'a'; name overlfow reached.";
                
                throw new UnsupportedOperationException(errorMessage);
            }
        }               
        
        return outPaths;
    }

    private static List<Part> allBinaryParts(GenerateContentResponse response, String outputPathName) 
    {
        List<Part> binaryParts = new ArrayList();
        
        var allParts = response.parts();
        
        System.out.println("allParts count = " + allParts.size() );
        
        for (Part part : Objects.requireNonNull(allParts)) 
        {
            if (part.inlineData().isPresent()) 
            {
                var blob = part.inlineData().get();
            
                if (blob.data().isPresent()) 
                {
                    binaryParts.add(part);                                       
                }
            }
        }

        return binaryParts;
    }

    private static Path formattedOutPath(String outputPathName, String formattedDate, char iterationChar)
    {
        var lastIndex = outputPathName.lastIndexOf('.');                                    
        
        var pathMinusExtension = outputPathName.substring(0, lastIndex);
        
        var endIndex = outputPathName.length();
        
        String extension = outputPathName.substring(lastIndex, endIndex);
        
        var p = pathMinusExtension + "-" + formattedDate + "-" + iterationChar + extension;
        
        return Path.of(p);
    }
}
