from datetime import datetime, timedelta
from functools import wraps
from flask import Flask, request, jsonify, make_response, session, render_template, flash
from  werkzeug.security import generate_password_hash, check_password_hash
import  uuid
import jwt


app = Flask(__name__)
app.config['SECRET_KEY'] = 'temporary_key'
app.config['JWT_TOKEN_SECRET'] = 'FQmC0z/YOUKgwdzuOhN2/QGwX/5mZhqU1djv2t4jZN0=' ##b64encode(os.urandom(32)).decode('utf-8')
userStoredData = {}

def token_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        token = None
        if 'Authorization' in request.headers:
            token = request.headers['Authorization']
        # return 401 if token is not passed
        if not token:
            return jsonify({'message': 'Token is missing !!'}), 401

        try:
            # decoding the payload to fetch the stored details
            data = jwt.decode(token, app.config['JWT_TOKEN_SECRET'], algorithms="HS256")
            if not (data["email"] in userStoredData.keys() and data["uuid"]==userStoredData.get(data["email"])[0]):
                return  make_response(jsonify({"message":"User doesn't exist"}),401)
        except:
            return jsonify({
                'message': 'Token is invalid !!'
            }), 401
        # returns the current logged in users contex to the routes
        return f(*args, **kwargs)

    return decorated

def roles(f):
    def wrapper():
        if session and "role" in session.keys() and session["role"]=="flag":
            return f()
        else:
            return  make_response("You dont have the role/permission to view the flag")

    return wrapper

@app.route("/")
def hello():
    return  render_template("register.html")


@app.route('/register', methods=['POST','GET'])
def register():
    if request.method =="GET":
        return render_template("register.html")

    # creates a dictionary of the form data
    req = request.form
    email, password = req["email"], req["password"]
    name = req["userName"]

    if email in userStoredData.keys():
        flash("User already exists. Please Log in")
        return render_template("register.html")
    else:
        userStoredData[email]=[str(uuid.uuid4()),name,generate_password_hash(password)]
        flash("Successfully Registered, Login here")
        return render_template("login.html")


@app.route("/login", methods=["POST","GET"])
def login():
    if request.method =="GET":
        return render_template("login.html")

    req = request.form
    email, password = req["email"], req["password"]
    if email in userStoredData.keys():
        if check_password_hash(userStoredData.get(email)[2],password):
            token = jwt.encode({
               "uuid": userStoredData.get(email)[0],
                "email": email,
                "exp": datetime.utcnow() + timedelta(minutes = 10)
            }, app.config["JWT_TOKEN_SECRET"], "HS256")
            session["user"]= userStoredData.get(email)[1]
            response = make_response(render_template("home.html"))
            response.headers["Authorization"] = token
            return  response,200
    else:
        flash("User or Password doesn't exist")
        return render_template("login.html")

@app.route("/flag",methods=["GET"])
def flag():

        return render_template("error.html"),200

@app.route("/flag",methods=["POST"])
@token_required
@roles
def flag():
    if request.method == "GET":
        return render_template("error.html"),200
    else:
        file = open("flag.txt","r")
        winjaFlag = file.readlines()
        file.close()
        return make_response(jsonify({"flag":winjaFlag}),200)

@app.errorhandler(404)
def notFound(e):
    return render_template("error.html"),404
