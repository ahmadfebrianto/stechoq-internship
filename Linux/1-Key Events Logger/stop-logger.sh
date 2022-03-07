ps -fC "xinput" | grep -v UID | awk -F ' ' '{print $2}' | xargs kill -9 
