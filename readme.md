[![OSCS Status](https://www.oscs1024.com/platform/badge/.svg?size=small)](https://www.murphysec.com/dr/yOeb26pRLRus7V4HaN)
[![Build Status](https://app.travis-ci.com/GWillS163/AssignmentSubmission_Java_Edition.svg?branch=master)](https://app.travis-ci.com/GWillS163/AssignmentSubmission_Java_Edition)
[![Coveralls](https://img.shields.io/coveralls/xcatliu/pagic.svg)](https://coveralls.io/github/xcatliu/pagic)
## The reconstruction of the assignment submission tool.
before this, there are one tool developed by python using related libs.
but there were some things I encountered that were a hassle. one of the things is GUI when using GUI to develop, the consequential file size will be bigger than 50MB, but there in fact only 9MB before using GUI.

### The Target of Develop Java Edition
1. newer than before! :D
2. more features!

Haha, Let's see what's new seriously!
1. no invasion configured yet, such as no edit user File, no config.txt in user's folder.
## Features of The Newest Version
> that is to use mature technology to resolve current questions I need to be solved.
- Java edition should only get users' inputs and mechanical processes, with no data should be stored in the program.
- all of the data should be stored in the database ( now using MongoDB Atlas), For example, users' data, assignments data, files data, and the new feature is that save the user's computer data so that related account.
- new construction and design of the tool, I will use the philosophy of software engineering to design my tool.
- the large file can be stored in the cloud DB.
- the retrieve message that the user queries all of the statuses of the files can be accomplished.

## Now design
<div align=center >
<img src="readme_md_files/activityDiagram_1.jpg?v=1&type=image" width="400" align="center"/>
</div>

## About File upload Module
the maximum size of MongoDB allowed is 16MB, so we can use Grid FS like a bucket to save the large file.
<div align=center>
<img src="readme_md_files/6219abd0-0267-11ed-8a18-1b2e03e7ecbb.jpeg?v=1&type=image" width="300"/>
</div>

## the description of an older version
maybe this project should be public on GitHub as a repository?
> developed and continuous update by python within 4 months range.
https://github.com/GWillS163/AssignmentSubmission_Python_Edition
<div align=center>
<img src="readme_md_files/b1b79940-0267-11ed-8a18-1b2e03e7ecbb.jpeg?v=1&type=image" width="120"/>
</div>
updated at 2022-7-13 13:02:09

Post at 2022-7-5 14:53:12
