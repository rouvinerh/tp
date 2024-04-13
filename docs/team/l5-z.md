# L5-Z - Project Portfolio Page

## Overview


### Summary of Contributions

> Tasked with _mainly_ UI and Storage

Contributed mostly towards:
- Main files
  - Handler.java
  - DataFile.java

- Test Files
  - HandlerTest.java
  - DataFileTest.java

## Overview

**PulsePilot** is a desktop application designed for **efficiently tracking health-related information** through a **Command Line Interface (CLI)**. For users who can type quickly, the CLI allows for faster data entry compared to traditional Graphical User Interface (GUI) applications on phones or computers.


### Summary of Contributions

The code I have written is documented [here](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=l5_z&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

Below is the breakdown of what I have done.

#### Features Added

- Implemented the first iteration of the Run, Gym and Workout classes, along with helper methods to calculate pace of the run.
  - This allows PulsePilot to track the user's workout information.
- Implemented the Latest and History commands
  - Allows user to view either their latest or all objects added to PulsePilot based on a filter string.

#### Enhancements Implemented

- Added `Validation` and `ValidationTest` classes to handle all input validation from both user and data file. This involved refactoring my teammates' code into one central class for data validation.
  - This allows our team to easily handle all input validation, since methods are abstracted in a separate class.
  - The `Validation` class prevents users from entering malformed input and makes our code more defensive against bugs or unintended use.

- Refactored long methods where applicable, and moved author tags around.

#### Documentation Written

##### User Guide

- Wrote the skeletal structure of the UG for other team members to fill.
- Replaced all expected output with images.
- Wrote UG portion for Run, History and Delete.

##### Developer Guide

- Wrote the skeletal structure of the DG for other team members to fill.
- Worked with [@JustinSoh](https://github.com/JustinSoh) to create sequence diagrams for Run, Gym, Latest, History, and Handler.
- Wrote DG portion for Run, History and Delete.

#### Other Contributions

##### Project Management

- Helped to ensure that the dashboard icons for our team members and our team is green.
- Worked with our TA and [@JustinSoh](https://github.com/JustinSoh) to make sure our sequence diagrams were implemented properly and were not too complicated.