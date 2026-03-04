## Getting Started and clone from main branch 
> git clone https://github.com/khsheng/data_structure_and_algorithms.git


## Switch to your own branch
1. check your branch
> git branch

    If your are not in your own branch and your branch **not create yet**--> move to 2

    If your are not in your own branch and your branch **already created** --> move to 3


2. Ceate and switch to a new branch
> git checkout -b `your-branch-name`

    Please create a branch name which is consisten with the module name which you tacken
    
    Example: 'git checkout -b your branch name'

3. Switch to a exited branch
> git checkout `your-branch-name`


## Pull code from main to local

Update the code from branch main to your local repository

> git pull origin main

## Add your code into stage area

**!!! Ensure that you pull before you add !!!**

> git add .

You may specify the file that you intent to stage.

Example: `git add src/App.java src/login/login.java`

## Commint your code in local

> git commit -m "`your-commit-message`"

Write your commit message is a good habit when you coroperate with your teameat

You also can write the commit message in editor by using the following command

> git commit

## Push your code to githab

**!!! Ensure that you are in your own branch !!!**

> git push origin `your-branch-name`