
package org.onebeartoe;

import com.google.genai.types.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.onebeartoe.prompts.Prompts.Music.Silly.HOW_SILLY_CAN_YOU_GET_IMAGE_DESCRIPTION;

/**
 *

 */
public class HowSillyCanYouGetImage extends BananaCreate
{    
    public static void main(String[] args) throws IOException, Exception 
    {   
        var app = new HowSillyCanYouGetImage();
        
        app.fromText();                        
    }        

    @Override
    public List<Part> parts() 
    {
        var parts = new ArrayList<Part>();
        
        var promptText = HOW_SILLY_CAN_YOU_GET_IMAGE_DESCRIPTION;
        
        var part = Part.fromText(promptText);
        
        parts.add(part);
        
        return parts;
    }
}
