
package org.onebeartoe;

import com.google.genai.types.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.onebeartoe.prompts.Prompts;
import static org.onebeartoe.prompts.Prompts.Imaging.IMPRESSIONIST_OIL_PAINTING;

/**
 *
 */
public class CreateImpressionistOilPainting extends BananaCreate
{
    public static void main(String[] args) throws IOException, Exception 
    {   
        var app = new CreateImpressionistOilPainting();
        
        app.fromText();                        
    }        

    @Override
    public List<Part> parts() 
    {
        var parts = new ArrayList<Part>();
        
        var promptText = IMPRESSIONIST_OIL_PAINTING;
        
        var part = Part.fromText(promptText);
        
        parts.add(part);
        
        return parts;
    }
}
