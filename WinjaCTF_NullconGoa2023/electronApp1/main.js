const { app, BrowserWindow, ipcMain } = require('electron')
const INDEX_HOST = process.env.INDEX_HOST || 'http://localhost:8000'
const fs = require('fs')
const path = require('path')

const createWindow = () => {
  const win = new BrowserWindow({
    width: 1000,
    height: 800,
    webPreferences: {
        sandbox:true,
        contextIsolation: true,
        devTools: false,
        nodeIntegration: false,
        preload: path.join(__dirname, 'preload.js'),

}

  })

win.loadURL(INDEX_HOST+'/index')

win.webContents.on('will-navigate', (event, navigationUrl) => {
event.preventDefault();
})
win.webContents.on('will-redirect', (event, navigationUrl) => {
    event.preventDefault();
})

win.webContents.on('devtools-reload-page', (event, navigationUrl) => {
    event.preventDefault();
})
win.webContents.setWindowOpenHandler(() => {
    return { action: "deny" };
});

}

ipcMain.on("session", (event, data) => {
    try {
        const content = fs.readFileSync('/tmp/'+data, 'utf8');
        console.log(content);
        event.reply("reply", content)

      } catch (err) {
        console.error(err);
      }

});

app.whenReady().then(() => {
    createWindow()
  
    app.on('activate', () => {
      if (BrowserWindow.getAllWindows().length === 0) {
        createWindow()
      }
    })
  })

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') app.quit()
  })