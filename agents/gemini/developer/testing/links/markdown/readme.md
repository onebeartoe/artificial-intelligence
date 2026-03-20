
# Gemini Agents - Developer - Testing - Links - Markdown

This section has a prompt to demonstrate developer tools to find broken internal links in markdown files.

See the [.prompt file](broken-links.prompt) in this directory.

It was used to create the link_checker.sh 

The first time it created a BASH script to create teh broken link report.  and works pretty well.  It prints a minimal report to standard out, and outputs a details report to a text file.

It was checking external links, but I only wanted to check internal links for this exercise.

So I updated the prompt to ignore external links.  It gave a 
[Python script](verify_links.py)
 this time and also works well.

## [Up](../readme.md)
