from flask import Flask, render_template, request, jsonify, send_from_directory
import docker, random, string, requests, os, json

app = Flask(__name__,
            static_url_path='/assets', 
            static_folder='assets',
            template_folder='templates')
client = docker.DockerClient(base_url='unix://var/run/docker.sock')
start = 45200
next_port = start
max_ports = 300
ctfd_token = f"Bearer {os.environ['CTFD_TOKEN']}"
ctfd_url = "https://ctf.winja.org/api/v1"
images_ports = {
    "electronapp1":"5910",
    "electronapp2":"5910",
    "wackyserver":"8000",
}

# Function to generate a random password
def generate_password(length=8):
    characters = string.ascii_letters + string.digits
    return ''.join(random.choice(characters) for _ in range(length))

# Home page
@app.route('/', methods=['GET', 'POST'])
def home():
    global next_port
    error = ""
    team_name = port = password = None
    if request.method == 'POST':
        team_name = request.form['team_name']
        leader_email = request.form['leader']
        app_image = request.form['app']
        teams = json.load(open("./data/teams.json"))
        try:            
            if app_image not in list(images_ports.keys()):
                raise Exception("Invalid App")
            
            if len(list(teams.keys())) >= max_ports:
                raise Exception("Instances are full contact System Admininstrator for help.")
            
            page = total_pages = 1
            team_list = []
            while page <= total_pages:
                response = requests.get(f"{ctfd_url}/teams?page={page}",headers={"Authorization":ctfd_token,"Content-type":"application/json"})
                data = response.json()
                total_pages = data['meta']['pagination']['pages']
                team_list += data['data']
                page += 1

            if team_name not in [t['name'] for t in team_list]:
                raise Exception("Invalid Team Name")
            
            team_index = [t['name'] for t in team_list].index(team_name)
            team = team_list[team_index]
            response = requests.get(f"{ctfd_url}/users/{team['captain_id']}",headers={"Authorization":ctfd_token,"Content-type":"application/json"})
            if response.json()['data']['email'] != leader_email:
                raise Exception("Invalid Leader Email")
                
            key = f"{team_name}-{app_image}"
            if key in list(teams.keys()):
                try:
                    port = teams[key]['port']
                    password = teams[key]['password']
                    client.containers.get(f"{app_image}-{team_name}-{port}")
                except:
                    port = password = None
            
            if not port or not password:
                port = next_port
                teams[key]={
                    "port":port,
                }
                if app_image != "wackyserver":
                    password = generate_password()
                    teams[key]["password"] = password
                else:
                    password = ""
                
                # Create a Docker container with CAP_SYS_ADMIN capability
                container = client.containers.run(
                    app_image,  # Replace with your Docker image name
                    detach=True,
                    name=f"{app_image}-{team_name}-{port}",
                    ports={
                        f'{images_ports[app_image]}/tcp': port
                    },
                    environment={'PASSWD': password},
                    cap_add=['SYS_ADMIN'],  # Add CAP_SYS_ADMIN capability
                    auto_remove=True  # Automatically remove the container when it stops
                )
                json.dump(teams,open("./data/teams.json","w"),indent=2)
                next_port = (next_port-start+1)%max_ports + start
                
        except Exception as e:
            error = str(e)

    return render_template('index.html', images=list(images_ports.keys()), error=error, team_name=team_name, port=port, password=password)

if __name__ == '__main__':
    app.run(debug=True)
