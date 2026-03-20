import os
import re
import sys

def find_md_files(root_dir):
    md_files = []
    for root, dirs, files in os.walk(root_dir):
        if '.git' in dirs:
            dirs.remove('.git')
        for file in files:
            if file.endswith('.md'):
                md_files.append(os.path.join(root, file))
    return md_files

def extract_links(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Simple regex for markdown links: [text](link)
    # Also handles links with titles: [text](link "title")
    links = re.findall(r'\[.*?\]\((.*?)\)', content)
    return links

def is_external(link):
    return link.startswith(('http://', 'https://', 'mailto:', 'tel:', 'ftp:'))

def verify_links(repo_root):
    md_files = find_md_files(repo_root)
    broken_links_report = {}
    total_broken = 0

    for md_file in md_files:
        links = extract_links(md_file)
        file_broken_links = []
        
        for link in links:
            # Clean link (remove anchor and query params)
            link_path = link.split('#')[0].split('?')[0].strip()
            
            if not link_path or is_external(link_path):
                continue
            
            # Resolve path
            if link_path.startswith('/'):
                target_path = os.path.join(repo_root, link_path.lstrip('/'))
            else:
                target_path = os.path.join(os.path.dirname(md_file), link_path)
            
            # Normalize and check existence
            target_path = os.path.normpath(target_path)
            
            if not os.path.exists(target_path):
                file_broken_links.append(link)
        
        if file_broken_links:
            rel_file_path = os.path.relpath(md_file, repo_root)
            broken_links_report[rel_file_path] = file_broken_links
            total_broken += len(file_broken_links)
            
    return broken_links_report, total_broken

if __name__ == "__main__":
    repo_root = os.getcwd()
    report, total = verify_links(repo_root)
    
    if not report:
        print("No broken internal links found.")
    else:
        print(f"Total broken links: {total}")
        for file_path, broken_links in report.items():
            print(f"\nFile: {file_path}")
            print(f"Broken links count: {len(broken_links)}")
            for link in broken_links:
                print(f"  - {link}")
