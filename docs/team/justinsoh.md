# JustinSoh - Project Portfolio Page

## Overview

**PulsePilot** is a desktop application designed for **efficiently tracking health-related information** through a **Command Line Interface (CLI)**. For users who can type quickly, the CLI allows for faster data entry compared to traditional Graphical User Interface (GUI) applications on phones or computers.

### Summary of Contributions

The code I have written is documented [here](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=JustinSoh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23).

Below is the breakdown of what I have done.

#### Features Added

- Implemented the `WorkoutList` class, to help track and manage newly created `Run` and `Gym` objects.

- Implemented the `toString()` method for both `run` and `gym` objects, allowing for easy integration with the `Output` class for clear display. 

- Implemented all outputs method relating to `gym`, `run`, and `workouts` within the `Output` class. 

- Designed the `GymSet` and `GymStation` classes with [@rouvinerh](https://github.com/rouvinerh) to enable multiple instances of `GymStation` within a particular `Gym` instance. 


#### Enhancements Implemented

- Implemented the `Parser.parseGymStationInput` method, which interactively prompts users for their gym station details. This enhancement simplifies the process of adding new gym workouts by reducing the length of the required command.

- Implemented the `Gym.toFileString()` and `Parser.parseGymFileInput()` allowing [@L5-Z](https://github.com/L5-Z) to easily integrate the saving/loading of Gym classes.

- Refactored Static Methods into Non-Static Methods where Instances are used instead to adopt a more Object-Oriented approach. 

#### Documentation Written


##### User Guide

- Wrote UG portion for `Gym` and `Show Latest`

##### Developer Guide

- Wrote DG portion for `Gym` and `Latest` portion 
- Worked with [@rouvinerh](https://github.com/rouvinerh) to create sequence diagrams for Run, Gym, Latest, History, and Handler.

#### Other Contributions

##### Project Management

- Interfaced between [@L5_Z](https://github.com/L5-Z) and [@rouvinerh](https://github.com/rouvinerh) to integrate the `Handler` and `Storage` class with the `Run` and `Gym` classes. 