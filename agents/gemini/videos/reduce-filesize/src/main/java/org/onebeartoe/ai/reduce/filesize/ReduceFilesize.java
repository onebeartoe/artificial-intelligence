///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 25+

//DEPS org.onebeartoe:prompts:1.0

package org.onebeartoe.ai.reduce.filesize;

import org.onebeartoe.prompts.Prompts;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import static org.onebeartoe.prompts.GenAIModels.GEMINI_2_5_FLASH_IMAGE;
import static org.onebeartoe.prompts.GenAIModels.GEMINI_2_5_PRO;

/**
 *
 */
public class ReduceFilesize 
{

    public static void main(String[] args) 
    {
        System.out.println("Hello World!");
        
//        var extenstions = new String[]{".mp4"};
        var extenstions = new String[]{".mp4", "mpeg"};
        
        var prompt = Prompts.Multimedia.reduceFilesizeButNotQuality(extenstions);
        
        String apiKey = System.getenv("GEMINI_API_KEY");
        if (apiKey == null) 
        {
            System.err.println("Please set the GEMINI_API_KEY environment variable");
            System.exit(1);
        }

//        var originalModelName = "gemini-1.5-flash";   gave error
        var modelName = GEMINI_2_5_FLASH_IMAGE.getModelName();  // gave error
//        var modelName = GEMINI_2_5_PRO.getModelName(); gave error
//TODO: add this to GenAIModels.java
//        var modelName = "gemini-2.5-flash"; // works!!!!
        
        ChatLanguageModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .allowCodeExecution(true)
                .includeCodeExecutionOutput(true)
                .logRequestsAndResponses(true)        
                .build();

        String response = model.generate(prompt);
        
        System.out.println(response);    
    }
}
