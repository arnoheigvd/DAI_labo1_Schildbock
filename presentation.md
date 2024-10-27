---
marp: true
title: "Practical work's presentation"
author: "David Schildböck"
theme: gaia
paginate: true
header: "HEIG-VD | DAI | Lab01"
footer: "28.10.2024"
backgroundColor: "#add8e6"
---

<!-- _Slide 1: Title Slide_ -->
# Practical work's presentation
## CryptoTool &rarr; application with JAVA/Maven/Picocli/GitHub

Author: **David Schildböck & Arno Tribolet**  
Date: October 2024

GitHub repo &rarr; 
**https://github.com/arnoheigvd/DAI_labo1_Schildbock_Tribolet**

---

<!-- _Slide 2: Introduction_ -->
# Plan/Introduction  
- The workflow GitHub
- The CLI application - Caesar cypher and AES algorithm
- Conclusion / Questions

\
*It is a capital mistake to theorize before one has data. Yet, every misstep brings us closer to the truth.* 
by **Sherlock Holmes**

---

<!-- _Slide 3: Main Content - GitHub_ -->
# Workflow GitHub
- Issue, New branch, Work on local, Commit and push, Pull Request (Validation by the other team-mate), Merge
<style>
  img[alt~='center'] {
    display: block;
    margin-left: auto;
    margin-right: auto;
  }
</style>
![center](assets/img1.svg)

---

<!-- _Slide 4: Main Content - CLI Application_ -->
# CLI Application
- **JAVA** - the language used
- **Maven Wrapper** - a tool used to create a java archive (**JAR**) 
- **Picocli** - a library used to create a CLI(Command Line Interface) application

<br>
<div style="display: flex; justify-content: center; gap: 20px;">
    <img src="assets/java.png" alt="Java" style="width: 150px; height: auto;">
    <img src="assets/maven.png" alt="Maven" style="width: 150px; height: auto;">
    <img src="assets/picocli.jpg" alt="Maven" style="width: 250px; height: auto;">
</div>

---

<!-- _Slide 5: Main Content - Caesar cipher_ -->
# Caesar cipher
- Take a **shift key**(k)
- Take the **character**(c) and change it to **c+k**
- Example &rarr; c=A, k=1 &rarr; **A+1 = B**

![center](assets/jules-cesar.jpg)
<p style="text-align: center; font-size: 0.5em;">Julius Caesar, namesake of the Caesar cipher</p>

---

<!-- _Slide 6: Main Content - AES algorithm_ -->
# AES(Advanced Encryption Standard) algorithm
- Widely used symmetric encryption algorithm
- Implemented the ECB(Electronic Codebook) mode 
    - Each plaintext block is encrypted independently
- **Pros** &rarr; Simple and fast, suitable for encrypting individual blocks of data.
- **Cons** &rarr; Identical plaintext blocks produce identical ciphertext blocks, **leading** to pattern leakage and weaker security.

---

<!-- _Slide 7: Conclusion_ -->
# Conclusion  
- Learned a lot
- Fun
- **Questions ??**

<style>
  img[alt~='right'] {
    float: right;
    max-width: 200px;
  }
  .bottom-left-text {
    position: absolute;
    bottom: 60px;
    left: 30px;
    font-size: 0.7em;
    text-align: left;
  }
</style>
![bg right fit](assets/question.gif)

<div class="bottom-left-text">&rarr; Read our <strong>README.md</strong> for more information !</div>

---

<!-- _Ending_ -->

