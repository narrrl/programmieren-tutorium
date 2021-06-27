#!/bin/sh
printf "Installing texlive-most and fira code font\n"
sleep 3s
if hash apt 2>/dev/null; then
    printf "Found apt manager\n"
    sleep 3s
    sudo apt-get install texlive-full
    fonts_dir="${HOME}/.local/share/fonts"
    if [ ! -d "${fonts_dir}" ]; then
        echo "mkdir -p $fonts_dir"
        mkdir -p "${fonts_dir}"
    else
        echo "Found fonts dir $fonts_dir"
    fi

    for type in Bold Light Medium Regular Retina SemiBold; do
        file_path="${HOME}/.local/share/fonts/FiraCode-${type}.ttf"
        file_url="https://github.com/tonsky/FiraCode/blob/master/distr/ttf/FiraCode-${type}.ttf?raw=true"
        if [ ! -e "${file_path}" ]; then
            echo "wget -O $file_path $file_url"
            wget -O "${file_path}" "${file_url}"
        else
    	echo "Found existing file $file_path"
        fi;
    done

    echo "fc-cache -f"
    fc-cache -f
elif hash paru 2>/dev/null; then
    printf "Found paru aur helper\n"
    sleep 3s
    paru -Syu
    paru -S texlive-most ttf-fira-code
elif hash yay 2>/dev/null; then
    printf "Found yay aur helper\n"
    sleep 3s
    yay -Syu
    yay -S texlive-most ttf-fira-code
elif hash pacman 2>/dev/null; then
    printf "Found pacman manager\n"
    sleep 3s
    sudo pacman -Syu
    sudo pacman -S texlive-most ttf-fira-code
else
    printf "ERROR: Your distro is not supported ... aborting!\n"
    sleep 3s
    exit -1
fi

printf "Installing Metropolis Theme\n"
sleep 3
if [ -d "/tmp/mtheme" ]; then
    sudo rm -rf /tmp/mtheme;
fi
git clone https://github.com/matze/mtheme.git /tmp/mtheme
cd /tmp/mtheme
make sty
sudo make install

printf "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣬⡛⣿⣿⣿⣯⢻\n"
printf "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⢻⣿⣿⢟⣻⣿⣿⣿⣿⣿⣿⣮⡻⣿⣿⣧\n"
printf "⣿⣿⣿⣿⣿⢻⣿⣿⣿⣿⣿⣿⣆⠻⡫⣢⠿⣿⣿⣿⣿⣿⣿⣿⣷⣜⢻⣿\n"
printf "⣿⣿⡏⣿⣿⣨⣝⠿⣿⣿⣿⣿⣿⢕⠸⣛⣩⣥⣄⣩⢝⣛⡿⠿⣿⣿⣆⢝\n"
printf "⣿⣿⢡⣸⣿⣏⣿⣿⣶⣯⣙⠫⢺⣿⣷⡈⣿⣿⣿⣿⡿⠿⢿⣟⣒⣋⣙⠊\n"
printf "⣿⡏⡿⣛⣍⢿⣮⣿⣿⣿⣿⣿⣿⣿⣶⣶⣶⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿\n"
printf "⣿⢱⣾⣿⣿⣿⣝⡮⡻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⠛⣋⣻⣿⣿⣿⣿\n"
printf "⢿⢸⣿⣿⣿⣿⣿⣿⣷⣽⣿⣿⣿⣿⣿⣿⣿⡕⣡⣴⣶⣿⣿⣿⡟⣿⣿⣿\n"
printf "⣦⡸⣿⣿⣿⣿⣿⣿⡛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⣿⣿⣿\n"
printf "⢛⠷⡹⣿⠋⣉⣠⣤⣶⣶⣿⣿⣿⣿⣿⣿⡿⠿⢿⣿⣿⣿⣿⣿⣷⢹⣿⣿\n"
printf "⣷⡝⣿⡞⣿⣿⣿⣿⣿⣿⣿⣿⡟⠋⠁⣠⣤⣤⣦⣽⣿⣿⣿⡿⠋⠘⣿⣿\n"
printf "⣿⣿⡹⣿⡼⣿⣿⣿⣿⣿⣿⣿⣧⡰⣿⣿⣿⣿⣿⣹⡿⠟⠉⡀⠄⠄⢿⣿\n"
printf "⣿⣿⣿⣽⣿⣼⣛⠿⠿⣿⣿⣿⣿⣿⣯⣿⠿⢟⣻⡽⢚⣤⡞⠄⠄⠄⢸⣿\n"
exit 0
