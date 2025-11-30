
package org.onebeartoe.combine;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        var client = GenAIModels.initializeClient();
        
        var nextOpponent = nextOpponent(client);
//        var nextOpponent = "Phoenix Suns";
        
//        var bestPlayer = "Devin Booker";
        var bestPlayer = bestPlayer(client, nextOpponent);

        var playerImage = playerImage(client, nextOpponent, bestPlayer);
        
        nextPigeon(client, playerImage);
    }

    private String nextOpponent(Client client) 
    {        
        var promptText = Prompts.SportsBall.NEXT_OPPONENT;                
        
        var content = Content.fromParts(Part.fromText(promptText) );
        
        var config = GenerateContentConfig.builder()
                                            .responseModalities("TEXT")
                                            .build();
        
        GenerateContentResponse response = client.models.generateContent(modelName, content, config);
        
        var nextOpponent = response.text();

        System.out.println("nextOpponent = " + nextOpponent);
        
        return nextOpponent;
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
                    Part.fromText(Prompts.SportsBall.NEXT_PIGEON)
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
        var promptText = String.format(Prompts.SportsBall.PLAYER_IMAGE, bestPlayer, nextOpponent);
        
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
