
# Configuring Gemini API Keys

The API Keys for Gemini are expected available via environment variables. See the [evn.sh](evn.sh) file for a sample configuration. 

Set up a similar file on the local filesystem and run use the source command on that file, before issuing the gemini command or running your program that calls Gemini services.

```
source evn.sh
```

This ensures your Gemini/Vertex API keys are available at run time.

# Configuring Model Context Protocol (MCP) Services  

The examples under cli/imagen (from Romin Irani) require MCP configuration. 

See the [sample settings.json file](settings.json) for details.



## [Up](../readme.md)
