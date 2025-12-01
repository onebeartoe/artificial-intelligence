
package org.onebeartoe;

import com.google.genai.types.Part;
import java.util.ArrayList;
import java.util.List;
import org.onebeartoe.prompts.Prompts;

/**
 *
 */
public class CreateFunkyElectricChickenLeg extends BananaCreate
{
    public static void main(String[] args) throws Exception 
    {        
        var app = new CreateFunkyElectricChickenLeg();
        
        app.fromText();
    }

    @Override
    public List<Part> parts()
    {
        var parts = new ArrayList<Part>();
        
        var promptText = Prompts.Imaging.FunkyElectricChickenLeg.LITERAL;
        
        var part = Part.fromText(promptText);
        
        parts.add(part);
        
        return parts;
    }        
}
