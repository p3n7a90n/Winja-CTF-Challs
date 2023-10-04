
function stringify(cipherParams) {
    // create json object with ciphertext
    var jsonObj = { ct: cipherParams.ciphertext.toString(CryptoJS.enc.Base64) };
    // optionally add iv or salt
    if (cipherParams.iv) {
      jsonObj.iv = cipherParams.iv.toString();
    }
    if (cipherParams.salt) {
      jsonObj.s = cipherParams.salt.toString();
    }

    // stringify json object
    return JSON.stringify(jsonObj);
}


function parse(jsonObj) {

    // extract ciphertext from json object, and create cipher params object
    var cipherParams = CryptoJS.lib.CipherParams.create({
      ciphertext: CryptoJS.enc.Base64.parse(jsonObj.ct)
    });

    // optionally extract iv or salt

    if (jsonObj.iv) {
      cipherParams.iv = CryptoJS.enc.Hex.parse(jsonObj.iv);
    }

    if (jsonObj.s) {
      cipherParams.salt = CryptoJS.enc.Hex.parse(jsonObj.s);
    }

    return cipherParams;
  }

function login(){

    var username = $("#username").val();
    var password = $("#password").val();

    var key = "topSecret";

    var encrypted = CryptoJS.AES.encrypt(username+"&"+password, key);

    $.ajax({
      type: "POST",
      url: "server.php", 
      data: stringify(encrypted),
      dataType: "json",
      contentType: "application/json"
    });

}


