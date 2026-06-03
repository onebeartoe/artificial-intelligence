# exit the script on the first error/exception (non-zero exit code)
set -e

echo 'hi from Gemini with JBanger'

export PROJECT_DIRECTORY=$(pwd)
echo "project directory: $PROJECT_DIRECTORY"

export PROMPT_DIRECTORY=~/Versioning/owner/github/artificial-intelligence/agents/gemini/programmatic/images/prompts/


# update dependencies
cd $PROMPT_DIRECTORY

mvn package

# move back 
cd $PROJECT_DIRECTORY

export GEMINI_PROMPT=$(jbang run \
    --class-path ~/Versioning/owner/github/artificial-intelligence/agents/gemini/programmatic/images/prompts/target/prompts-1.0.jar \
    GeminiJBang.java option 1)

gemini "$GEMINI_PROMPT"

