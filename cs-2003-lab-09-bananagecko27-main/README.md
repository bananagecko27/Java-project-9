[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-718a45dd9cf7e7f842a935f5ebbe5719a5e09af4491e668f4dbf3b35d5cca122.svg)](https://classroom.github.com/online_ide?assignment_repo_id=12551753&assignment_repo_type=AssignmentRepo)
# CS-2003-Lab-9

## Overview
The ninth lab is intended to introduce you to the some of the most common hashing schemes.

You’ve been provided with the following files: AbstractHashTable.java, BasicHashTableInterface.java, DoubleHash.java, country-capitals.csv, LinearProbe.java, Main.java, Primes.java, and QuadraticProbe.java. **AbstractHashTable.java** is a partial implementation of the HashTable ADT defined by **BasicHashTableInterface.java**. **country-capitals.csv** is a collection of countries and their capitals. **DoubleHash.java** is an implementation of the double hash algorithm. **LinearProbe.java** is an implementation of the linear probing algorithm. **Main.java** is a driver class to test the relative performance load of each hashing algorithm under a variety of conditions. **Primes.java** is a library to generator primes. **QuadraticProbe.java** is an implementation of the quadratic probing algorithm.

Use **TUGrader.java** to ensure that all unit tests in the provided test file(s) pass.

## Assignment
Implement the linear probing, quadratic probing, and double hashing algorithms in **LinearProbe.java**, **QuadraticProbe.java**, and **DoubleHash.java**, respectively. In addition, implement the rehash method in **AbstractHashTable.java**.

After implementing each algorithm, run **Main.java**. Put together a short report documenting the relative performance of each algorithm (linear probing, quadratic probing, and double hashing). The performance of each algorithm should be documented using plots of performance in nanoseconds (ns) against the load factor of the hashing algorithm from 0 to 1. You must at least include load factors 0.25, 0.5, and 0.65.

## Submission
The assignment is due by the end-of-day Friday of the following week. **Your report should be submitted to Harvey.**

*To submit your work, please review the **Commits** section below.*

## Grading Rubric
- [ ] (20pts) Can store entries using linear probing
- [ ] (20pts) Can store entries using quadratic probing
- [ ] (20pts) Can store entries using double hashing
- [ ] (15pts) Can rehash tables
- [ ] (25pts) Complete report

## Compiling and Testing Code
Your IDE should provide tools to compile your code. If you're unfamiliar with that process, you can research it online or ask. Most developers compile their code from command line using a shell script, such as a **Makefile** or build script (**build.sh**). I've provided build scripts for you in both *Powershell* and *Bash*. Refer to the following directions on how to use these scripts based on the terminal that you're using. If you're on Windows, please use Windows Subsystem for Linux (WSL), Git Bash, or Powershell, not Command Prompt.

### Windows Users (Powershell)
- To compile your code: `./build.ps1`
- To compile and run your code: `./build.ps1 run` (forwards clargs to program)
- To compile and test your code: `./build.ps1 test` (forwards clargs to TUGrader)
- To format your code: `./build.ps1 fmt`
- To sync your code: `./build.ps1 sync`
- To submit your code: `./build.ps1 submit`
- To remove class files: `./build.ps1 clean`

### Windows Users (WSL, Git Bash), Mac and Linux Users
- To compile your code: `./build.sh`
- To compile and run your code: `./build.sh run` (forwards clargs to program)
- To compile and test your code: `./build.sh test` (forwards clargs to TUGrader)
- To format your code: `./build.sh fmt`
- To sync your code: `./build.sh sync`
- To submit your code: `./build.sh submit`
- To remove class files: `./build.sh clean`

These scripts use the following commands. Note that Windows users need to replace the colon with a semicolon in the Java classpath.
- To compile a Java file: `javac -d target -cp lib/*:src <filepath>.java`
- To execute a Java file: `java -cp lib/*:target <package-path>.<filename>`
- To format a Java file: `java -jar lib/google-java-format.jar --replace --skip-javadoc-formatting <filepath>.java`
- To remove class files: `rm -r target/*`

## Code Style
All code should follow the [Google Java style guidelines](https://google.github.io/styleguide/javaguide.html). If you find anything in the code that does not follow the style guidelines, feel free to fix it, but you are not required to do so. Only your handwritten code will be evaluated for its style. You do not need to follow the style guidelines to the letter but egregious deviations from the style guidelines will be penalized. A submission that passes all test cases but does not use an appropriate style will not receive an A for the assignment.

For those using an IDE, such as Eclipse or VS Code, the IDE should provide a formatting tool. I've included the XML specification of the Google Java Style Guidelines at `.vscode/java-google-style.xml`. You can configure your IDE to use the provided XML as its formatting rules to format your code to the Google Java Style Guidelines, which are the industry standard.

If you're working from command-line, [google-java-format](https://github.com/google/google-java-format) is an open-source formatting tool that you can use to format your files. You can use the following commands to format your code depending on your terminal.
- `./build.ps1 fmt`
- `./build.sh fmt`

## Commits
Commits should be made incrementally. Many commits are always better than few, and commits can always be squashed together later if there are too many. You should try to make a commit every time you've made tangible progress in the development of your code.

Every commit should have a commit message. The standard format for commit messages is to start with a verb in present-tense followed by a concise message (typically less than 50 characters) that summarizes the change that the commit is introducing to the repo. For example, "Updates README", "Implements Array", "Passes testGet".

Popular IDEs, such as Eclipse and VS Code, provide integrated Git tools. If you're on Windows, you can install Git Bash or Windows Subsystem for Linux (WSL). If you're on Mac or Linux, you already have git installed.

If you've just installed git, it will need to be configured. The easy way to configure git is from a terminal. Use the following commands.
- `git config --global user.name "<github-username-goes-here>"`
- `git config --global user.email "<github-email-goes-here>"`
- `git config --global pull.rebase true` (optional)
- `git config --global fetch.prune true` (optional)
- `git config --global diff.colorMoved zebra` (optional)

To sync changes made from another device, use the following command.
- `git fetch origin main`
- `git pull origin main`

To push commits from command line, use the following commands.
- `git add -A`
- `git commit -m "<your message goes here>"`
- `git push origin main`

You can also sync all changes and submit with the following commands depending on your terminal.
- `./build.ps1 submit`
- `./build.sh submit`
