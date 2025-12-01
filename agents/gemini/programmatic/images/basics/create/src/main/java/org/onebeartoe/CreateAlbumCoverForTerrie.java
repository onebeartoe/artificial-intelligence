
package org.onebeartoe;

import com.google.genai.types.Part;
import java.util.List;

/**
 *
 */
public class CreateAlbumCoverForTerrie extends BananaCreate
{
    public static void main(String[] args) throws Exception 
    {        
        var app = new CreateAlbumCoverForTerrie();
        
        app.fromText();
    }

    @Override
    public List<Part> parts() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
