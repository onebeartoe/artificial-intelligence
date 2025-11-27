
package org.onebeartoe.combine;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.http.client.ResponseHandler;
import org.onebeartoe.prompts.GenAIModels;
import org.onebeartoe.prompts.Prompts;
import org.onebeartoe.prompts.Responses;

/**
 *  This class make several calls to one client and generates an image of a sportsball 
 *  player with a pigeon head.
 */
public class BananaNextPigeon 
{
    public static final String modelName = GenAIModels.GEMINI_2_5_FLASH_IMAGE.getId();
//            var modelName = "gemini-2.5-flash-image";
    
    public static void main(String[] args) throws IOException 
    {
        var app = new BananaNextPigeon();
        
        app.nextPigeon();
    }

    public void nextPigeon() throws IOException
    {
        var client = initializeClient();
        
//        var nextOpponent = nextOpponent(client);
        var nextOpponent = "Phoenix Suns";
        
//        var bestPlayer = "Devin Booker";
        var bestPlayer = bestPlayer(client, nextOpponent);

        var playerImage = playerImage(client, nextOpponent, bestPlayer);
        
        nextPigeon(client, playerImage);
    }

    private Client initializeClient() 
    {
        var apiKey = "GEMINI_API_KEY";

        var apiKeyValue = System.getenv(apiKey);
                            
        Client client = new Client.Builder()
                                .apiKey(apiKeyValue)
                                .build()      ;                                  

        return client;
    }

    private String nextOpponent(Client client) 
    {        
        var promptText = """
                What team is next on the NBA San Antonio Spurs schedule.  
                Provide just the city name and mascot name.
                                """;                
        
        var content = Content.fromParts(Part.fromText(promptText) );
        
        var config = GenerateContentConfig.builder()
                                            .responseModalities("TEXT")
                                            .build();
        
        GenerateContentResponse response = client.models.generateContent(modelName, content, config);
        
        return response.text();
    }

    private String bestPlayer(Client client, String nextOpponent) 
    {
        var promptText = String.format(Prompts.SportsBall.BEST_PLAYER, nextOpponent);        
        
        var content = Content.fromParts(Part.fromText(promptText) );
        
        var config = GenerateContentConfig.builder()
                                            .responseModalities("TEXT")
                                            .build();
        
        GenerateContentResponse response = client.models.generateContent(modelName, content, config);
        
        return response.text();        
    }

    private void nextPigeon(Client client, Path playerImage) throws IOException 
    {            
        var pigeonContent = Content.fromParts(
                    Part.fromBytes(Files.readAllBytes(playerImage), "image/png"),
                    Part.fromBytes(Files.readAllBytes(Path.of("/home/luke/Workspace/owner/next-pigeon/DO-NOT-COMMIT-pigion-head.png")), "image/png"),
                    Part.fromText("""
                            Add this pigeon head over the basketball player's head,
                            and make the pigeon head about the same size as the player's head.
                            """)
                    );
            
        var response = client.models.generateContent(modelName,
                                            pigeonContent,
                                            GenerateContentConfig.builder()
                                                .responseModalities("TEXT", "IMAGE")
                                                .build());
        
        var outputPathName = "next-pigeon.png";

        Responses.saveFirstBinaryPart(response, outputPathName);
    }
    
    private Path playerImage(Client client, String nextOpponent, String bestPlayer) throws IOException
    {
        /*
        Sample descriptive image prompt:
            An impressionist oil painting
            of the port of La Rochelle
            with its towers and sailing ships.        
        */
        
        var promptText = String.format("""                                      
                        A current photo 
                        of %s on the %s
                        with the basketball and correct team uniform.
                                """, bestPlayer, nextOpponent);
        
        var content = Content.fromParts(Part.fromText(promptText) );
        
        var config = GenerateContentConfig.builder()
                                            .responseModalities("TEXT", "IMAGE")
                                            .build();
        
        GenerateContentResponse response = client.models.generateContent(modelName, content, config);        
        
        var outfileName = bestPlayer + ".png";
        
        Path outpath = Responses.saveFirstBinaryPart(response, outfileName);
        
        return outpath;
    }
}
