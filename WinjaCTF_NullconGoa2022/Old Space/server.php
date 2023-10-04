<?php

function encrypt($value, string $passphrase)
    {
        $salt = openssl_random_pseudo_bytes(8);
        $salted = '';
        $dx = '';
        while (strlen($salted) < 48) {
            $dx = md5($dx . $passphrase . $salt, true);
            $salted .= $dx;
        }
        $key = substr($salted, 0, 32);
        $iv = substr($salted, 32, 16);
        $encrypted_data = openssl_encrypt($value, 'aes-256-cbc', $key, true, $iv);
        $data = ["ct" => base64_encode($encrypted_data), "iv" => bin2hex($iv), "s" => bin2hex($salt)];
        return json_encode($data);
    }


function decrypt($json,$passphrase){

    $salt = hex2bin($json["s"]);
        $iv = hex2bin($json["iv"]);
        $ct = base64_decode($json["ct"]);
        $concatedPassphrase = $passphrase . $salt;
        $md5 = [];
        $md5[0] = md5($concatedPassphrase, true);
        $result = $md5[0];
        for ($i = 1; $i < 3; $i++) {
            $md5[$i] = md5($md5[$i - 1] . $concatedPassphrase, true);
            $result .= $md5[$i];
        }
        $key = substr($result, 0, 32);
        $data = openssl_decrypt($ct, 'aes-256-cbc', $key, true, $iv);
        return $data;
}

$error_code=0;
if (strtoupper($_SERVER['REQUEST_METHOD']) !== 'POST') {
    throw new Exception('Only POST requests are allowed');
  }

// Make sure Content-Type is application/json 
$content_type = isset($_SERVER['CONTENT_TYPE']) ? $_SERVER['CONTENT_TYPE'] : '';

if (strcmp($content_type, 'application/json') !== 0) {
  throw new Exception('Content-Type must be application/json');
}

// Read the input stream
$body = file_get_contents("php://input");
 
// Decode the JSON object
$object = json_decode($body, true);
 
// Throw an exception if decoding failed
if (!is_array($object)) {
  throw new Exception('Failed to decode JSON object');
}

// decode the request, check the content and then send the response

$key = "topSecret";
$cipher_algo = "aes-256-cbc";

$decrypted_text = decrypt($object,$key);
if($decrypted_text){

    $decrypted_array=explode('&',$decrypted_text);
    if(count($decrypted_array)!==2)
    {
        $error_code = -1;
        $response = "Invalid data";
    }

    if(!$error_code){

    $extracted_username =  $decrypted_array[0];
    $extracted_password = $decrypted_array[1];

    if($extracted_username ==="1qazxsw2" && $extracted_password === "1qazxsw2"){

        $file = fopen("/flag.txt","r");
        $flag = fread($file,filesize("/flag.txt"));
        fclose($file);

        $response = $flag;
    }
    else{
        $error_code = -1;
        $response = "Wrong Username/Password combination";
    }
}

    $response = json_encode(array("error_code"=>$error_code,"response"=>$response));

    header("Content-Type: application/json");

    echo encrypt($response,$key);

}

?>

