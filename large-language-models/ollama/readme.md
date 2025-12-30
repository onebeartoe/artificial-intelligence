
# Ollama Examples that use LangChain4J

## Markus Eisele Examples

### Dad Joke Generator

[Groan-Worthy Greatness: Build an AI Dad Joke Generator with Java, Quarkus, and Langchain4j](https://www.the-main-thread.com/p/quarkus-langchain4j-dad-joke-generator)

This one was fun.  I had to install the Ollama binary.

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

## [Up](../readme.md)
