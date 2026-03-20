
# Model Context Protocol

If you have a paid account with Gemini, then Romin's article is an exciting way to use MCP (Vertex) features of Gemini CLI and programmatically.

https://medium.com/google-cloud/gemini-cli-tutorial-series-part-6-more-mcp-servers-91422cadaa35



    Setup
        Follow the instruction at the top to install the dependency.  

        That first dependency has ‘Install’ instructions of its own to do first; 
            pretty much install the Media MCP binaries

        Intall Go

        Ensure the binary of the MCPs are built and runnable,

            use the ./install.sh script to install

            /opt/go-bin/mcp-imagen-go 

                or where ever the install puts the binaries

            and in ~/.bashrc
            
            export PATH="$PATH:/home/roberto/go/bin"



Once I had my Google Gemini/Vertex credentials, [Romin Irani’s 11 part guide](https://medium.com/google-cloud/gemini-cli-tutorial-series-77da7d494718) was really fun to go through.  This one shows how to integrate with different MCP services, including to to build your own MCP service.




## [Up](../readme.md)
