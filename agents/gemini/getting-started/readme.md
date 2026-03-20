
# Prerequisites

One agent offered by Google is Gemini CLI.

Here are some steps/commands to get started on the command line:

Configure the Authentication for Google Cloud (and Application Default Credentials (ADC) )
sudo apt-get install apt-transport-https ca-certificates gnupg curl

sudo snap install google-cloud-cli 

sudo snap install google-cloud-cli --classic

gcloud

// This next opens a browser for authentication.
gcloud auth application-default login

// you should see the Gemini user interface now
gemini


# API Authentication

Using Gemini with CLI or in a programmatic way (Java/Python), requires setting up API keys for your environment.

## See the [configuration directory](../configuration/readme.md) for sample configuration files

# Verify the Installation

Here is an example prompt to issue to verify Gemini is set up correctly for the local environment:

```
gemini "what is the weather like in Bandera, TX?"
```

The terminal should print a weather forecast.

TODO: fix JBang link
See the 
[JBang with Gemini CLI](../prompts/readme.md) 
for details on piping data from a script to the gemini command.








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

TODO
!!!!!!][][][    Multimodal AI 

        "Look at this UI mockup @mockup.png and produce a JavaFX GUI for it.



## [Up](../readme.md)
