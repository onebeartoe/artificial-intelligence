
# Ollama Examples that use LangChain4J

## Markus Eisele Examples

### Dad Joke Generator

[Groan-Worthy Greatness: Build an AI Dad Joke Generator with Java, Quarkus, and Langchain4j](https://www.the-main-thread.com/p/quarkus-langchain4j-dad-joke-generator)

This one was fun.  I had to install the Ollama binary.

I was seeing some Java 25 related execption (IllegalAcceException) sometimes.  So had to add the following to the maven-surefile-plugin:

```
                <configuration>
                    <argLine>--add-opens java.base/java.lang=ALL-UNNAMED</argLine>
                    ......
                </configuration>
```

Not specific to this article but I had to add this to 'application.properties' to change the port number:

quarkus.http.port=9292

It was fun to change the prompt and see quick results:

```
@RegisterAiService
public interface DadJokeService {
    @SystemMessage("You are a super corny dad joke generator."
            + "Your main topics include  cooking. "   // driving, sports, and
            + "Your jokes should be short, cheesy, and guaranteed to make people groan. "
            + "One joke at a time.")
    String getDadJoke();
}
```

An it gave this response:

```
Why did the golfer wear two pairs of pants? In case he got a hole in one!
```


### Captain's Log

[Captain’s Log, Stardate Java: Building a Quarkus-Powered AI Sci-Fi App with Langchain4j and Ollama](https://www.the-main-thread.com/p/quarkus-langchain4j-captains-log-generator)

This was fun. It feautures call 'tools' in the workflow.

It has a nice glowy star crew user interface.

## [Up](../readme.md)


### Document Assistant

[https://www.the-main-thread.com/p/quarkus-langchain4j-ai-document-assistant](Build an AI-Powered Document Assistant with Quarkus and LangChain4j)

This one looked cool and uses retrieval augmented generation (RAG), to learn about a new subject by importing documents.

I got it running up to needing a Posgres database connection and stopped there.


### Dungeon Master

[https://www.the-main-thread.com/p/ai-dungeon-master-quarkus-langchain4j-java](Mastering AI Tool-Calling with Java: Build Your Own Dungeon Master with Quarkus and LangChain4j)

This one looked and sounded cool but I could only bring up the HTML interface and not any responses from the AI Agent.  It gave some timeout error and was not sure where to congiure that.