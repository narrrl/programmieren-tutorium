#!/bin/bash

mkdir Build_Output

for folder in tut_*; do
    cd "$folder"
    for texFile in *.tex; do
        echo $texFile
        xelatex -output-directory="../Build_Output" "$texFile"
        # clean files
        find . -maxdepth 1 ! -name '*.tex' -type f -exec rm -f {} +
    done

    cd ..
done

# Clean everything else
cd Build_Output
mkdir Save_Me
cp *.pdf Save_Me
rm *
mv Save_Me/* .
rm -r Save_Me
