


Using Gemini with CLI or in a programmatic way (Java/Python), requires setting up API keys for your environment.

## See the [configuration directory](configuration/readme.md) for sample configuration files

# Verify the Installation

Here is an example prompt to issue to verify Gemini is set up correctly for the local environment:

```
gemini "what is the weather like in Bandera, TX?"
```

The terminal should print a weather forecast.


# Setup and Configuration

## [Model Context Protocol](model-context-protocol/readme.md)

## [Audio](audio/readme.md)

## [Imagen](imagen/readme.md)

## [Text Extraction](text-extraction/readme.md)



------------------------------------------

Injecting local (text/media/other) file contents into Prompts


gemini "Summarize the key points from @{path/to/document.txt}" 

gemini hi @some-prompt.text

----------------------------------------------

General CLI Tips from Addy Osmani

https://addyo.substack.com/p/gemini-cli-tips-and-tricks

    text exraction - 'Extract the text from invoice.png and put it into a structured format.'

    $PATH - genimni undertands all fo teh programs on the BASH path

    !<command> - automate commands

    /copy - copy last output to clipboard

    /chat - save and recall

    /memory - include

    /corgi

!!!!!!][][][    Multimodal AI 

        "Look at this UI mockup @mockup.png and produce a JavaFX GUI for it.



## [Up](../readme.md)
