
package org.onebeartoe.genai.lyra.music;

import io.github.glaforge.gemini.interactions.GeminiInteractionsClient;
import io.github.glaforge.gemini.interactions.model.Content.AudioContent;
import io.github.glaforge.gemini.interactions.model.Content.ImageContent;
import io.github.glaforge.gemini.interactions.model.Content.TextContent;
import io.github.glaforge.gemini.interactions.model.Interaction;
import io.github.glaforge.gemini.interactions.model.InteractionParams.ModelInteractionParams;


import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import static org.onebeartoe.prompts.Prompts.Music.Silly.HOW_SILLY_CAN_YOU_GET_LYRICS;

/**
 *
 */
public class HowSillyCanYouGetSong 
{
    public static void main(String[] args) 
    {
        GeminiInteractionsClient client = GeminiInteractionsClient.builder()
        .apiKey(System.getenv("GEMINI_API_KEY"))
        .build();

        System.out.println("Running testImageToMusicGeneration");
        try {
            // Downloading a sample image directly from the Generative AI cookbook examples
            byte[] imageBytes = URI.create(
                    "file:///home/luke/Versioning/owner/github/artificial-intelligence/agents/gemini/images/create/surf-folks-2026-04-06-1533-a.png")
                .toURL()
                .openStream()
                .readAllBytes();

            ModelInteractionParams request = ModelInteractionParams.builder()
                .model("models/lyria-3-clip-preview")
                .input(
                    new TextContent(HOW_SILLY_CAN_YOU_GET_LYRICS),
                    new ImageContent(imageBytes, "image/jpeg")
                )
                .responseModalities(
                    Interaction.Modality.AUDIO,
                    Interaction.Modality.TEXT)
                .build();

            Interaction interaction = client.create(request);
            printLyrics(interaction);

            var now = Instant.now().toEpochMilli();
            
            interaction.outputs().stream()
                .filter(output -> output instanceof AudioContent)
                .map(output -> (AudioContent) output)
                .findFirst()
                .ifPresent(audio -> saveAudio(audio, "silly-surf-song-" + now + ".mp3"));
        } catch (IOException e) {
            System.out.println("Failed to download image or generate music: " + e.getMessage());
        }        
    }

    private static void printLyrics(Interaction interaction) {
        interaction.outputs().stream()
            .filter(output -> output instanceof TextContent)
            .map(output -> (TextContent) output)
            .findFirst()
            .ifPresent(textContent -> System.out.println("Lyrics / Structure Generated:\n" + textContent.text()));
    }
    
    private static void saveAudio(AudioContent audio, String filename) {
        assert(audio.data() != null);
        assert(audio.data().length > 0);
        assert("audio".equals( audio.type() ) );
        
        System.out.println("Received audio data of length: " + audio.data().length + " for " + filename);

        try 
        {
            Path targetPath = Paths.get(filename);
//            Path targetPath = Paths.get("target", filename);
//            Files.createDirectories(targetPath.getParent());

            // Lyria audio output natively encoded as MP3 byte stream
            byte[] audioData = audio.data();
            Files.write(targetPath, audioData);

            System.out.println("Saved Lyria generated audio to: " + targetPath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save audio file: " + e.getMessage());
        }
    }
    
    
}
