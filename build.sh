#!/bin/bash

mkdir Build_Output

for folder in tut_*; do
    cd "$folder"
    for texFile in *.tex; do
        echo $texFile
        # build
        xelatex -output-directory="../Build_Output" "$texFile"
    done

    cd ..
done

# Clean everything else
cd Build_Output
# clean files
find . -maxdepth 1 ! -name '*.out' -o ! -name '*.tex' -type f -exec rm -f {} +
