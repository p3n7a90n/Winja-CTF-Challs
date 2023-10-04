from http.server import HTTPServer, BaseHTTPRequestHandler, SimpleHTTPRequestHandler
import os
import redis


PORT = 5000
DIRECTORY = "/var/www/"
os.chdir(DIRECTORY)

redisPort = 6379
redisHost = "redis"

class SimpleHTTPRequestHandlerLocalImpl(SimpleHTTPRequestHandler):
    def __init__(self, *args, **kwargs):
            super().__init__(*args, **kwargs)
    def list_directory(self, path):
        #print(path)
        return None

    def do_GET(self):
    	remoteIp = self.headers['X-Real-IP']
    	if remoteIp:
	    	print(remoteIp)
	    	if r.exists(remoteIp):
	    		if int(r.get(remoteIp))>=5: # five or more than requests in 30 seconds
	    			return None
	    		else:
	    			r.incr(remoteIp)
	    			SimpleHTTPRequestHandler.do_GET(self)
	    	else:
	    		r.setex(remoteIp,30,1) #key, ttl, value
	    		SimpleHTTPRequestHandler.do_GET(self)	    		
    	else:
    		return None



def run(server_class=HTTPServer, handler_class=SimpleHTTPRequestHandlerLocalImpl, addr="", port=PORT):
    server_address = (addr, port)
    httpd = server_class(server_address, handler_class)

    print("Starting httpd server on addr and port",addr,port)
    httpd.serve_forever()

r=redis.Redis(host=redisHost, port=redisPort)

if __name__ == "__main__":
    run(addr="0.0.0.0", port=PORT)
