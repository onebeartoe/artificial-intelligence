
package org.onebeartoe.text.extraction;

import java.nio.file.Path;

/**
 *
 */
public class TextExtraction 
{
    public static void main(String[] args) 
    {
        var app = new TextExtraction();
        
        var inputImagePath = "../../gemini/text-extraction/Party-Untitled.png";
        
        app.extractText(inputImagePath);
    }

    private void extractText(String inputImagePath) 
    {
        var service = new TextExtractionService();
        
        var inputPath = Path.of(inputImagePath);
        
        service.textFromImage(inputPath);
    }
}
