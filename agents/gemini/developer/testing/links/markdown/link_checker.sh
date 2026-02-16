#!/bin/bash

# This script finds all markdown files and checks for broken links.

# Clear the report file
> broken_links_report.txt

# Find all markdown files and store them in an array
mapfile -t markdown_files < <(find . -type f -name "*.md")

# Loop through the array of markdown files
for file in "${markdown_files[@]}"; do
  # Extract links from the file
  # This regex tries to find markdown links like [text](link)
  grep -oE '\[[^]]+\]\(([^)]+)\)' "$file" | while read -r line; do
    link=$(echo "$line" | sed -E 's/\[[^]]+\]\(([^)]+)\)/\1/')
    
    # Skip empty links
    if [ -z "$link" ]; then
      continue
    fi
    
    # Check if it's an HTTP/HTTPS link
    if [[ "$link" == http* ]]; then
      # Use curl to check the link
      if ! curl -s --head --fail --max-time 5 "$link" > /dev/null; then
        echo "$file: $link" >> broken_links_report.txt
      fi
    else
      # It's a local file link
      dir=$(dirname "$file")
      
      # If the link is a fragment, ignore it for now
      if [[ "$link" == \#* ]]; then
        continue
      fi

      # Construct the full path to the linked file
      if [[ "$link" == /* ]]; then
        # Absolute path
        full_path="$link"
      else
        # Relative path
        full_path=$(realpath -m "$dir/$link" 2>/dev/null)
      fi
      
      # Check if the file exists
      if [ ! -e "$full_path" ]; then
        echo "$file: $link" >> broken_links_report.txt
      fi
    fi
  done
done

# Process the report file to generate the summary
if [ ! -s broken_links_report.txt ]; then
  echo "No broken links found."
else
  total_broken_links=$(wc -l < broken_links_report.txt)
  
  echo "--- Broken Link Report ---"
  echo "Found a total of $total_broken_links broken links in the following files:"
  
  declare -A broken_link_counts
  while IFS=: read -r file link; do
    ((broken_link_counts[$file]++))
  done < broken_links_report.txt

  for file in "${!broken_link_counts[@]}"; do
    # a bit of a hack to remove leading whitespace from the filename
    file_trimmed="$(echo -e "${file}" | sed -e 's/^[[:space:]]*//')"
    echo "  - $file_trimmed (${broken_link_counts[$file]} broken links)"
  done

  echo ""
  echo "Details of broken links are in broken_links_report.txt"
fi
