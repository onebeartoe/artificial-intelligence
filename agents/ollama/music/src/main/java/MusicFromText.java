


import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.Options;
import io.github.ollama4j.utils.OptionsBuilder;
import java.util.HashMap;
import static org.onebeartoe.prompts.Prompts.Music.FUNKY_LITTLE_BEAT;

/**
 *
 */
public class MusicFromText 
{
    public static void main(String[] args) 
    {
        var app = new MusicFromText();
        
        var lyrics = """
                        
                     """ + FUNKY_LITTLE_BEAT;
        
        app.musicFromText(lyrics);
    }

    private void musicFromText(String lyrics) 
    {
        String host = "http://localhost:11434/";

        OllamaAPI ollamaAPI = new OllamaAPI(host);
        
        try 
        {
            // Check if server is running
            System.out.println("Reachable: " + ollamaAPI.ping());
  
            ollamaAPI.setRequestTimeoutSeconds(900); 

            var prompt = """
                         Create an MP3 music file with the following characteristics 
                         """ +
                            FUNKY_LITTLE_BEAT +
                    
                        """
                         Save the music file to music-1.mp3
                        """;

            Options options = new OptionsBuilder().build();

//            var modelName = "ace-step";  // Ollma does not have this model, yet
            var modelName = "llamusic/llamusic";  // kinda worked but the python script did not work
            
            // Sync Generation
            OllamaResult result = ollamaAPI.generate(modelName,
                                                        prompt,
                                                        new HashMap() );

            System.out.println(result);
            
            result.notifyAll();
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }        
    }
}
