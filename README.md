# **CryptoTool**

## Prelude
CryptoTool command-line interface (CLI) tool for cryptographic operations, allowing users to encrypt and decrypt the content of files using multiple encryption algorithms.

> [!NOTE] 
> You can find examples of input/output files for all the algorithms in the ![assets](assets/) folder.

## Introduction
Do you want to know how to execute **CryptoTool** correctly, or would you like to learn more about it ? You can continue reading this document.
The [first section](#how-to-run) is about running the app in the terminal without the need for an IDE or Maven. Next, you will read about the [Caesar cipher algorithm](#caesar-cipher), and finally, you will delve into the [AES-ECB implementation](#aes-ecb).

## How to run?

You can clone this repository with either `ssh` or `http`, but we recommend using `ssh` as it is more secure:

```sh
$ git clone git@github.com:arnoheigvd/DAI_labo1_Schildbock_Tribolet.git
```
We expect that you already have a JRE installed in your system.

Using the Maven wrapper **mvnw** you can then run:

```sh
$ ./mvnw package
```
This will create the jar (Java archive) that you will use to launch our application.

To finally run **CryptoTool**, simply execute:
```sh
$ java -jar target/lab01-crypto-1.0-SNAPSHOT.jar
```
And you're done.

> [!CAUTION]
> Be careful to use the correct JAR file; do not use the one labeled `original`, as it does not contain the dependencies used in our project !

If you see the following output then you've finished the installation and you can proceed to another section.

```sh
Missing required options: '--input=<filename_input>', '--algorithm=<algorithm>', '--key=<key>'
Usage: lab01-crypto-1.0-SNAPSHOT.jar [-chV] -a=<algorithm> -i=<filename_input>
                                     -k=<key> [-o=<filename_output>] [COMMAND]
A CLI to encrypt/decrypt files using different algorithms.
  -a, --algorithm=<algorithm>
                    The algorithm to use (possible values: CODE_CESAR, AES_ECB).
  -c, --color       Add color to the output terminal.
  -h, --help        Show this help message and exit.
  -i, --input=<filename_input>
                    The input file to use.
  -k, --key=<key>   The key to use for the algorithm.
  -o, --output=<filename_output>
                    The output file to use, default value is output.txt.
  -V, --version     Print version information and exit.
Commands:
  decode  Decode the content of a file.
  encode  Encode the content of a file.
```

> [!TIP]
> We suggest that you keep the default value of the `-c` option, but if you want, you can change it to `false` and it will print the output in the terminal wihtout colors !

> [!IMPORTANT]
> There is a default value to the `-o` option (output.txt), meaning that if you forget to specify an output file it will default to that one (located in the root directory of the project, might not be the same as input).
---
## Caesar Cipher

The **Caesar cipher** is a basic encryption technique used to encode messages.

> [!NOTE]
> This alogorithm is named after Julius Caesar, who reportedly used it for secure communication.

> [!IMPORTANT]
> This cipher is no longer considered secure, but since it might still be required in some cases, we decided to implement it.

### How it works
1. Choose a Shift value -> This is a number that determines how many positions each letter will be shifted.
2. Encrypting a message -> To encrypt a message, replace each letter with the letter that is a certain number of positions down the alphabet.
3. Decrypting a Message -> To decrypt the message, you shift the letters back by the same number.

### Examples
#### Encryption

Let's encode the file `input.txt` (from the assets) to an `output.txt` file.
```sh
$ java -jar target/lab01-crypto-1.0-SNAPSHOT.jar -a=CODE_CESAR -k=10 -i=assets/code_ceasar/input.txt -o=assets/code_ceasar/output.txt encode
```

If successful, the output will be:
```sh
Encoding from assets/code_ceasar/input.txt to assets/code_ceasar/output.txt using CODE_CESAR algorithm.
Execution time in 89 [ms]
```

> [!NOTE]
> Execution time may vary; this is normal.

#### Decryption

Let's decode the file `output.txt` (from the assets) to a `result.txt` file so that we can compare it with the `input.txt` from [before](#encryption).

```sh
$ java -jar target/lab01-crypto-1.0-SNAPSHOT.jar -a=CODE_CESAR -k=10 -i=assets/code_ceasar/output.txt -o=assets/code_ceasar/result.txt decode
```

If successful, the output will be :
```sh
Decoding from assets/code_ceasar/output.txt to assets/code_ceasar/result.txt using CODE_CESAR algorithm.
Execution time in 94 [ms]
```

> [!NOTE]
> Execution time may vary; this is normal.

### Result
`HelloW0rd!!` to `RovvyG0bvn!!`

> [!NOTE]
> In our implementation, special characters and numbers are not encrypted.
---
## AES-ECB
**AES-ECB (Advanced Encryption Standard - Electronic Codebook)** is a symmetric encryption algorithm used to securely encode data.

### Key features
- Symmetric Encryption -> AES-ECB uses the same key for both encryption and decryption. This means both the sender and receiver must securely share the key beforehand.
- Block Cipher -> AES encrypts data in fixed-size blocks. For AES, the block size is always 128 bits (16 bytes).
- Electronic Codebook (ECB) Mode -> In ECB mode, each block of plaintext is encrypted independently with the same key.
  
> [!WARNING]
> The user should be aware of:
> - When the same plaintext block is encrypted repeatedly, it will generate identical ciphertext blocks, which can expose patterns in the data.
> - The user-provided key is converted into a 256-bit key using the SHA-256 algorithm **without salt**, which leaves the encryption susceptible to brute force or dictionary attacks.
> - Our aplication doesn't implement any authentication, meaning the integrity of the encrypted data cannot be guaranteed or verified.

### How it works
1. The user provides the files he needs to encrypt/decrypt and the shared key.
2. The user's key is transformed in a 256 bits key and in a SecretKeySpec to be used by the Java Cipher class.
3. The input file is read by 128-bits blocks and encrypted with AES, then directly writed in the output file.
4. If the total data size isn't a multiple of 16 (bytes -> 128 bits), a padding is added using PKCS5Padding.
5. The files are closed, the input file's datas are untouched and the output file is the encrypted version.

### Examples
#### Encryption

Let's encode the file `input.txt` (from the assets) to an `output.txt` file.
```sh
java -jar target/lab01-crypto-1.0-SNAPSHOT.jar -a=AES_ECB -k=10 -i=assets/aes_ecb/input.txt -o=assets/aes_ecb/output.txt encode
```

If successful, the output will be :
```sh
Encoding from assets/aes_ecb/input.txt to assets/aes_ecb/output.txt using AES_ECB algorithm.
Execution time in 125 [ms]
```

> [!NOTE]
> Execution time may vary; this is normal.

#### Decryption

Let's decode the file `output.txt` (from the assets) to a `result.txt` file so that we can compare it with the `input.txt` from [before](#encryption).

```sh
java -jar target/lab01-crypto-1.0-SNAPSHOT.jar -a=AES_ECB -k=10 -i=assets/aes_ecb/output.txt -o=assets/aes_ecb/result.txt decode
```

If successful, the output will be :
```sh
Decoding from assets/aes_ecb/output.txt to assets/aes_ecb/result.txt using AES_ECB algorithm.
Execution time in 123 [ms]
```

> [!NOTE]
> Execution time may vary; this is normal.

### Result
`HelloW0rld!!` to `U=oNA½¨ÌÃ[úû¨`

> [!WARNING]
> The output is in binary format, meaning it is not human-readable, so the characters displayed may vary depending on how the file is interpreted !

---
## Conclusion
We hope that **CryptoTool** will assist users with various purposes, such as CTF challenges, student assignments, and more.

If you have other algorithms you would like to implement, feel free to create an issue. Our team will review your proposal and merge it if we find it suitable.

> [!IMPORTANT]
> Have fun !
