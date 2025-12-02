
package org.onebeartoe.ai.veo.create;

import com.google.genai.Client;
//import com.google.genai.client.Client;


import com.google.genai.types.GenerateVideosOperation;


import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.Image;
import com.google.genai.types.Part;
import com.google.genai.types.Video;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CreateVideoFromTextAndImages 
{

    public static void main(String[] args) throws InterruptedException, IOException {
        // Initialize the client. The client will automatically use Application Default Credentials.
        // Ensure GOOGLE_CLOUD_PROJECT and GOOGLE_CLOUD_LOCATION environment variables are set.
        Client client = Client.builder().vertexAI(true).build();

        String prompt = "A futuristic city skyline at sunset, with flying cars. The video should have a cinematic feel.";
        String outputGcsUri = "gs://your-output-bucket/video_output/"; // GCS bucket for output
        String imageOneUri = "gs://your-input-bucket/image1.jpeg"; // GCS URI for image 1
        String imageTwoUri = "gs://your-input-bucket/image2.jpeg"; // GCS URI for image 2

        // Define the input images as Content parts
        List<Content> imageContents = Arrays.asList(
            Content.builder().parts(Arrays.asList(Part.fromImage(Image.builder().gcsUri(imageOneUri).build()))).build(),
            Content.builder().parts(Arrays.asList(Part.fromImage(Image.builder().gcsUri(imageTwoUri).build()))).build()
            // Add a third image content part if needed (up to 3 images supported)
        );

        // Configure video generation parameters
        GenerateVideosConfig config = GenerateVideosConfig.builder()
                .aspectRatio("16:9") // Example aspect ratio
                .outputGcsUri(outputGcsUri)
                .build();

        // Call the generateVideos method. Note that the Java SDK handles the input types.
        // It accepts the model ID, prompt, a list of input contents (images), and config.
        GenerateVideosOperation operation = client.models.generateVideos(
                "veo-3.1-generate-preview", // Use a suitable Veo model
                prompt,
                imageContents,
                config
        );

        System.out.println("Waiting for video generation to complete...");

        // Poll the operation status until the video is ready
        while (!operation.done()) {
            Thread.sleep(10000); // Wait for 10 seconds
            operation = client.operations.get(operation);
        }

        if (operation.response() != null) {
            Video generatedVideo = operation.response().generatedVideos().get(0).video();
            System.out.println("Generated video saved to GCS URI: " + generatedVideo.uri());
            // You can also use client.files.download to get the file locally if needed
        } else if (operation.error() != null) {
            System.err.println("Video generation failed: " + operation.error().message());
        }
    }
}
