import time,subprocess,glob

# Script to delete every files in /var/www dir every 30 seconds

while True:
    files = glob.glob("/var/www/*")
    if files:
        files.insert(0,"rm")
        print("Deleting all the files in /var/www/ dir")
        subprocess.run(files)
    # sleep in seconds
    time.sleep(30)
