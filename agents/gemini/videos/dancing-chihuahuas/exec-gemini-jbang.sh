
#echo 'hi from Gemini with JBanger'

export MY_VAR=$(jbang GeminiJBang.java option1 option2)

#echo $MY_VAR

jbang run \
    --class-path ~/Versioning/owner/github/artificial-intelligence/agents/gemini/programmatic/images/prompts/target/prompts-1.0.jar \
    GeminiJBang.java option 1