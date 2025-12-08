
package org.onebeartoe;

import com.google.genai.types.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.onebeartoe.prompts.Prompts.Imaging.TapeRecoderUnwoundText.TAPE_RECODER_UNWOUND_TEXT;

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
