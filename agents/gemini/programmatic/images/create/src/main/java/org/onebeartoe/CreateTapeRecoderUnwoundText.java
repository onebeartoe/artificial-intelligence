
package org.onebeartoe;

import com.google.genai.types.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.onebeartoe.prompts.Prompts.Imaging.TapeRecoderUnwoundText.TAPE_RECODER_UNWOUND_TEXT;

//TODO:
//TODO:
//TODO: REfactor this to use just a main class and not extend the class just 
//TODO:     to override the #parts() method.
//TODO:
//TODO:

/**
 *

 */
public class CreateTapeRecoderUnwoundText extends BananaCreate
{    
    public static void main(String[] args) throws IOException, Exception 
    {   
        var app = new CreateTapeRecoderUnwoundText();
        
        app.fromText();                        
    }        

    @Override
    public List<Part> parts() 
    {
        var parts = new ArrayList<Part>();
        
        var promptText = TAPE_RECODER_UNWOUND_TEXT;
        
        var part = Part.fromText(promptText);
        
        parts.add(part);
        
        return parts;
    }
}
