#!/bin/sh
for folder in tut_*; do
    cd "$folder"
    # clean files
    find . -maxdepth 1 ! -name '*.tex' -type f -exec rm -f {} +

    cd ..
done

