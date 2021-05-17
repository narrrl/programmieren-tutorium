#!/bin/bash

mkdir Build_Output


for folder in *tut_*; do
    cd "$folder"
    for texFile in *.tex; do
        echo $texFile
        # build
        xelatex "$texFile"
        # twice for bookmarks
        xelatex "$texFile"
        find . -maxdepth 1 -type f -name '*.pdf' -exec mv '{}' ../Build_Output/ \;
    done

    cd ..
done

# Clean everything else
cd Build_Output
# clean files
