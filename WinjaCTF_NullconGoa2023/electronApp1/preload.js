const { contextBridge, ipcRenderer } = require('electron');


contextBridge.exposeInMainWorld('api', {
  sessionCheck: (channel, data) =>{ 
    
    let validChannels = ["session"];

    if (validChannels.includes(channel)) {
    ipcRenderer.send(channel,data)
    }

  },

  receive: (channel, func) => {
    let validChannels = ["reply"];
    if (validChannels.includes(channel)) {

        ipcRenderer.on(channel, (event, ...args) => func(...args));
    }
}
})
