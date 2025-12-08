
package org.onebeartoe.prompts;

/**
 *
 */
public class MimeTypeExtensions 
{

    public static String extensionFor(String outputPathName) throws Exception 
    {
        var mimeType = outputPathName.toLowerCase();
        
        return switch(mimeType)
        {
            case String s when s.equals("image/png") -> ".png";
                
            default -> throw new Exception("mime-type: " + mimeType + " not supported yet");
        };
    }

//    public static String extensionOf(String outputPathName) throws Exception 
//    {
//        throw new Ex
//    }    
}
