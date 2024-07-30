const { app, BrowserWindow, globalShortcut } = require('electron')

const createWindow = () => {
  const win = new BrowserWindow({
    width: 1000,
    height: 800,
    webPreferences: {
        sandbox:false,
        devTools:false,
        contextIsolation: false,
        nodeIntegration: false
    },
    fullscreen: true,
  })

win.menuBarVisible=false;
win.loadFile("index.html")

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

app.whenReady().then(() => {
    createWindow()
  
    app.on('activate', () => {
      if (BrowserWindow.getAllWindows().length === 0) {
        createWindow()
      }
    })

    app.on('ready', () => {
      // Register a shortcut listener for Ctrl + Shift + I
      globalShortcut.register('Control+Shift+I', () => {
          // When the user presses Ctrl + Shift + I, this function will get called
          // You can modify this function to do other things, but if you just want
          // to disable the shortcut, you can just return false
          return false;
      });
  });

  })

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') app.quit()
  })