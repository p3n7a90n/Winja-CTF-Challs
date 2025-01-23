from http.server import HTTPServer, BaseHTTPRequestHandler, SimpleHTTPRequestHandler

PORT = 5000
DIRECTORY = "/var/www/"

class SimpleHTTPRequestHandlerLocalImpl(SimpleHTTPRequestHandler):
    def __init__(self, *args, **kwargs):
            super().__init__(*args, directory=DIRECTORY, **kwargs)
    def list_directory(self, path):
        #print(path)
        return None



def run(server_class=HTTPServer, handler_class=SimpleHTTPRequestHandlerLocalImpl, addr="", port=PORT):
    server_address = (addr, port)
    httpd = server_class(server_address, handler_class)

    print("Starting httpd server on addr and port",addr,port)
    httpd.serve_forever()

if __name__ == "__main__":
    run(addr="", port=PORT)