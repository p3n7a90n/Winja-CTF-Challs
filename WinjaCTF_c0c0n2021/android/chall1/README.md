## Android Reversing Challenge
## Install the app directly or via adb
```
adb install android-reversing.apk
```

## Solution

1. Decompile the app using jadx or any other decompiler.
2. When the users enters the flag in the app, it gets encrypted with the RSA Stored key and
match with the existing encrypted value.
3. After looking into the decompiled java code, below is the method responsible for decrypting the key.

```
   public String decryptWithRSA(String data, Key privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(2, privateKey);
            return new String(cipher.doFinal(Base64.decode(data, 0)));
        } catch (Exception e) {
            Log.e("Decryption Exception", "Something wrong with the Decryption", e);
            return null;
        }
    }

```

Pass the stored private key and the encrypted flag in the above method to get the plain text.

## Flag

Flag{cyWanLv2d08dETGnEqoR_easy_reversing}