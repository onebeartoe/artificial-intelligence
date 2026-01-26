package org.onebeartoe.prompts;

import com.google.genai.Client;

public enum GenAIModels 
{
    GEMINI_2_5_PRO("gemini-2.5-pro", "Gemini 2.5 Pro", "Advanced reasoning model, large context window."),
    
    GEMINI_2_5_FLASH_IMAGE("gemini-2.5-flash-image", "Gemini 2.5 Flash Image", "Price-performance balance, low-latency, high-volume tasks."),
    
    IMAGEN("imagen", "Imagen", "Generates studio-grade images from text prompts."),

    VEO("veo-3.0-generate-001", "Veo", "Generates videos from text prompts and images."),
    
    VEO_FAST("veo-3.0-fast-generate-001", "Veo Fast", "Fastly generates videos from text prompts and images."),

    VEO_FAST_3_1_PREVIEW("veo-3.1-generate-preview", "Veo Fast", "Preview version of Veo 3.1"),
    
    GEMMA("gemma", "Gemma", "Open models for efficient execution on low-resource devices.");

    private final String id;
    private final String modelName;
    private final String description;

    GenAIModels(String id, String modelName, String description) 
    {
        this.id = id;
        this.modelName = modelName;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getModelName() {
        return modelName;
    }

    public String getDescription() {
        return description;
    }

    public static Client initializeClient() 
    {
        var apiKey = "GEMINI_API_KEY";

        var apiKeyValue = System.getenv(apiKey);
                            
        Client client = new Client.Builder()
                                .apiKey(apiKeyValue)
                                .build();        
        return client;
    }    
    
    public static Client initializeVertexClient()
    {
        return new Client.Builder()
            .project(System.getenv("GOOGLE_CLOUD_PROJECT_ID"))
            .location(System.getenv("GOOGLE_CLOUD_LOCATION"))
            .vertexAI(true)
            .build();
    }
    
    @Override
    public String toString() 
    {
        return modelName;
    }
}
