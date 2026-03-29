
prompt:  how to best structure a GitHub repository for a scientific peer-reviewed manuscript? 

For a scientific manuscript, the gold standard for repository structure is a Research Compendium.
  This approach ensures that your work is reproducible, transparent, and easy for reviewers to
  navigate.

  Recommended Directory Structure

    1 project-name/
    2 ├── README.md           # Project overview, installation, and reproduction guide
    3 ├── LICENSE             # Open source license (e.g., MIT, CC-BY 4.0)
    4 ├── environment.yml     # Or requirements.txt / Dockerfile for reproducibility
    5 │
    6 ├── data/               # Data storage
    7 │   ├── raw/            # Immutable raw data (never edit these files)
    8 │   └── processed/      # Data cleaned/transformed for analysis
    9 │
   10 ├── src/                # Source code (scripts, functions, modules)
   11 │   ├── processing/     # Scripts to turn raw data into processed data
   12 │   └── analysis/       # Scripts to generate results from processed data
   13 │
   14 ├── notebooks/          # Jupyter/RMarkdown for exploratory analysis & figures
   15 │
   16 ├── results/            # Final outputs
   17 │   ├── figures/        # Plots used in the manuscript
   18 │   └── tables/         # CSV/TeX tables used in the manuscript
   19 │
   20 ├── manuscript/         # The paper itself (LaTeX, Markdown, or PDF)
   21 │   └── references.bib  # Bibliography
   22 │
   1. The "One-Click" Reproducibility Rule: Provide a clear README.md and ideally a master script
      (e.g., run_all.sh or main.py) that executes the entire pipeline from raw data to final
      figures.
   2. Raw Data is Sacred: Never manually edit files in data/raw/. All cleaning must be done via
      scripts in src/processing/ to ensure the "data provenance" is documented.
   3. Environment Management: Use conda (environment.yml), pip (requirements.txt), or a Dockerfile
      to lock the exact versions of the libraries you used.
   4. Versioning Milestones: Use Git Tags to mark specific versions:
       * git tag -a v1.0-submitted -m "Version submitted to Journal X"
       * git tag -a v1.1-revision -m "Revised version after first round of review"
   5. Large Data Handling: If your data is >50MB, do not commit it to Git. Host it on a repository
      like Zenodo or OSF and provide a download script or link in your README.md.

  Recommended Licenses
   * For Code: MIT or Apache 2.0 (standard for open research).
   * For Data/Text: CC-BY 4.0 (requires attribution, allows sharing and adaptation).

  If you'd like, I can help you initialize this structure in your current workspace and generate
  boilerplate files for any of these sections.
