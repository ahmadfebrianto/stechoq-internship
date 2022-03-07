#!/bin/bash

TERMINAL=xterm
yes | sudo apt install $TERMINAL > /dev/null 2>&1 

DIR_AUTOSTART="$HOME/.config/autostart/"
PATH_XTERM=$(which $TERMINAL)
FILE_NAME="xterm.desktop"
FILE_PATH="$DIR_AUTOSTART$FILE_NAME"

touch "$FILE_PATH"

if  [ ! -d "$DIR_AUTOSTART" ]; then
    mkdir "$DIR_AUTOSTART"
fi

cat > $FILE_PATH << end
[Desktop Entry]
Name=Xterm
Type=Application
Exec="$PATH_XTERM"
Terminal=false
end
