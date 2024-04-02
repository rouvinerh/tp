# Developer Guide

## Table of Contents

* [Acknowledgements](#acknowledgements)
* [Introduction](#introduction)
* [Design](#design)
* [Implementation](#implementation)
* [Appendix: Requirements](#appendix-requirements)
* [Appendix: Manual Testing](#appendix-manual-testing)
* [Frequently Asked Questions](#frequently-asked-questions)

## Acknowledgements

Our team has referenced [Address Book (Level-3)](https://github.com/se-edu/addressbook-level3) 
referenced for their [User Guide (UG)](https://se-education.org/addressbook-level3/UserGuide.html)
and [Developer Guide (DG)](https://se-education.org/addressbook-level3/DeveloperGuide.html) to better structure our own Developer Guide.

- The `java.util.Scanner` class from the Java Standard Library is used for reading user input.
- The `JUnit 5` testing framework is used for writing and running unit tests.
- {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries
-- include links to the original source as well}

## Introduction

The purpose of this guide is to provide an explanation for all the functions and internal workings in PulsePilot. This enables any technical readers to get a detailed understanding of the application's implementation, making it easier for them to contribute to the project or adapt it according to their preferences. 

###### [Back to table of contents](#table-of-contents)

## Design

* [Overview of Components](#overview-of-components)
* [UI](#ui)
  * [Handler](#handler)
  * [Output](#output)
* [Workout](#workout)
    * [WorkoutList](#workout-list)
    * [Gym](#gym)
        * [GymStation](#gym-station)
        * [GymSet](#gym-set)
    * [Run](#run)
* [Health](#health)
    * [HealthList](#health-list)
    * [Bmi](#bmi)
    * [Period](#period)
    * [Appointment](#appointment)
* [Utility](#utility)
  * [Parser](#parser)
  * [Validation](#validation)
  * [CustomExceptions](#custom-exceptions)
  * [Filters](#filters)
* [Storage](#storage)
  * [LogFile](#log-file)
  * [DataFile](#data-file)
* [PulsePilot](#pulsepilot)
* [Constants](#constants)

### Overview of Components

The application follows an Object-Oriented Design approach, with separate classes for handling different components
of the application, such as user input, output, exercise logging, and health data management.

{Include UML Diagram here}

`Main` is responsible for the initialising, processing of user input and termination of the bot. It creates a `PulsePilot` instance.

The application can be further broken down into the following packages:

- `Ui`: The user interface of PulsePilot.
- `Storage`: Contains the data storage components for PulsePilot.
- `Health`: Stores health-related information.
- `Workout`: Stores workout-related information.
- `Utility`: Contains utility functions, such as input parsing and validation.
- `Constants`: Contains all constants used in PulsePilot.

###### [Back to table of contents](#table-of-contents)

### UI

{Insert class diagram}

#### Handler

The main entry point of the application is the `Handler` class, which contains the `processInput` method.
This method is responsible for parsing user input, validating it, and delegating the appropriate actions to other classes based on the command provided.

###### [Back to table of contents](#table-of-contents)

#### Output

The `Output` class is responsible for printing messages, prompts, and information to the console.

###### [Back to table of contents](#table-of-contents)

### Workout

{Insert class diagram}

The `Run` and `Gym` classes represent different types of exercises that the user can record.

###### [Back to table of contents](#table-of-contents)

#### Workout List

###### [Back to table of contents](#table-of-contents)

#### Gym

###### [Back to table of contents](#table-of-contents)

##### Gym Station

###### [Back to table of contents](#table-of-contents)

##### Gym Set

###### [Back to table of contents](#table-of-contents)

#### Run

###### [Back to table of contents](#table-of-contents)

### Health

The Health component consists of `Health`, `HealthList`, `Bmi`, `Period`, and `Appointment`.

{Insert class diagram}

1. `Health` class stores date.
2. `HealthList`class stores separate lists for different `Health` objects using ArrayList.
   `HealthList`includes methods to add, delete, view history of the various `Health`lists.
3. `Bmi`class stores bmi attributes (i.e. height, weight, date, bmi value and bmi category).
4. `Period`class stores period attributes (i.e. start date of period, end date of period, period length
   and cycle length).
5. `Appointment`class stores appointment attributes (i.e. date, time, appointment description). Primarily, `Appointment`
   has all necessary getter methods to access the attributes.

###### [Back to table of contents](#table-of-contents)

#### Health List

###### [Back to table of contents](#table-of-contents)

#### BMI

###### [Back to table of contents](#table-of-contents)

#### Period

###### [Back to table of contents](#table-of-contents)

#### Appointment

###### [Back to table of contents](#table-of-contents)

### Utility

{Insert class diagram}

###### [Back to table of contents](#table-of-contents)

#### Parser

###### [Back to table of contents](#table-of-contents)

#### Validation

###### [Back to table of contents](#table-of-contents)

#### Custom Exceptions

###### [Back to table of contents](#table-of-contents)

#### Filters

###### [Back to table of contents](#table-of-contents)

### Storage

###### [Back to table of contents](#table-of-contents)

#### Log File

###### [Back to table of contents](#table-of-contents)

#### Data File

###### [Back to table of contents](#table-of-contents)

### PulsePilot

###### [Back to table of contents](#table-of-contents)

### Constants

###### [Back to table of contents](#table-of-contents)

## Implementation

* [Workout](#workout)
  * [Add Run](#add-run)
  * [Add Gym](#add-gym)
* [Health](#health)
  * [Add Period](#add-period)
  * [Add BMI](#add-bmi)
  * [Add Appointment](#add-appointment)
  * [Make Period Prediction](#make-period-prediction)
* [View History](#view-history)
* [View Latest](#view-latest)
* [Delete Item](#delete-item)
* [Storage of Data](#storage-of-data)

### Workout

#### Add Run

###### [Back to table of contents](#table-of-contents)

#### Add Gym

###### [Back to table of contents](#table-of-contents)

### Health

#### Add Period

###### [Back to table of contents](#table-of-contents)

### Add BMI

###### [Back to table of contents](#table-of-contents)

### Add Appointment

###### [Back to table of contents](#table-of-contents)

### Make Period Prediction

###### [Back to table of contents](#table-of-contents)

### View History

###### [Back to table of contents](#table-of-contents)

### View Latest

###### [Back to table of contents](#table-of-contents)

### Delete Item

###### [Back to table of contents](#table-of-contents)

### Storage of Data

###### [Back to table of contents](#table-of-contents)

## Appendix: Requirements

* [Product Scope](#product-scope)
  * [Target User Profile](#target-user-profile)
  * [Value Proposition](#value-proposition)
* [User Stores](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)

### Product scope

###### [Back to table of contents](#table-of-contents)

#### Target user profile

Outpatients who need to monitor their health activity and health parameters.

#### Value proposition

PulsePilot is a health monitoring application designed to bridge the gap between medical professionals and patients during outpatient recovery. 

PulsePilot offers outpatients the capability to input and track a range of
health activities, encompassing both aerobic and anaerobic exercises, alongside crucial health parameters such as
BMI and menstrual cycles. 

Simultaneously, PulsePilot facilitates access to this vital data for various
healthcare professionals, ensuring comprehensive and seamless support in guiding outpatient recovery processes.

###### [Back to table of contents](#table-of-contents)

### User Stories

| Version | As a ...              | I want to ...       | So that I can ...                         |
|---------|-----------------------|---------------------|-------------------------------------------|
| 1.0     | gym enthusiast        | enter my gym stats  | track my gym sessions                     |
| 1.0     | runner                | see my running pace | see my relative speed for each run        |
| 1.0     | runner                | log my runs         | track my running progress over time       |
| 1.0     | health conscious user | calculate my BMI | track change in my weight over time       |
| 1.0     | female user           | track my menstrual cycle | monitor any deviations from my normal menstrual cycle |
| 2.0     | runner                | see my latest run | quickly view my most recent run details   |
| 2.0     | gym enthusiast        | see my latest gym session | quickly view my most recent gym session   | 
| 2.0     | gym enthusiast        | enter varying weights for sets | accurately track my progress and strength gains | 
| 2.0     | female user           | predict my next period start date | plan ahead and better manage my health | 
| 2.0     | injured user          | track my medical appointments | remember the appointments I have |

###### [Back to table of contents](#table-of-contents)

### Non-Functional Requirements

- **Usability**: The application should have a user-friendly command-line interface with
  clear instructions and prompts for user input.
- **Reliability**: The application should handle invalid or incomplete user input gracefully,
  providing appropriate error messages and prompting the user for correct input.
- **Maintainability**: The codebase should follow best practices for Object-Oriented Programming,
  including proper separation of concerns, modularization, and code documentation.
- **Testability**: The application should have comprehensive unit tests to
  ensure correct functionality and enable easier maintenance and future enhancements.

{Give non-functional requirements}

###### [Back to table of contents](#table-of-contents)

### Glossary
- **Run**: An exercise activity involving running or jogging, typically characterized by distance, duration, and date.
-  **Gym**: An exercise activity involving various strength training exercises or 
workouts performed at a gym or fitness center.
- **BMI (Body Mass Index)**: A measure of body fat based on height and weight, 
used to assess overall health and fitness.
- **Menstrual Period**: A recurring physiological event in females, characterized by the start and end dates.
- **Medical Appointment**: An arrangement with a doctor, physiotherapist, or healthcare professional, 
to meet at a certain time and place.


###### [Back to table of contents](#table-of-contents)

## Appendix: Manual Testing

* [Adding a Run](#adding-a-run)
* [Loading Sample Data](#loading-sample-data)

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

###### [Back to table of contents](#table-of-contents)

### Adding a Run

#### Expected Input: 

<code style="color: red;">
WORKOUT /e:run /d:<distance> /t:<time> /date:<date>
</code>

- Replace `<distance>` with the distance covered to 2 decimal place  (e.g. if the distance is 5.123 km, enter 5.12).
- Replace `<time>` with the duration of the run in the format `HH:MM:SS` (e.g. if you ran for an hour and 5 minutes,
  enter 01:05:00).
- Replace `<date>` with the date of the run in the format `DD-MM-YYYY` (e.g. if the date is 24/07/2024, enter
  24-07-2024).

###### [Back to table of contents](#table-of-contents)

#### Sequence Diagram for Adding a New Run Exercise
wip
<!--![Sequence Diagram for Adding a New Run Exercise]()-->

###### [Back to table of contents](#table-of-contents)

#### General Workflow of Adding a New Run Exercise
1. User input is passed to `handleExercise()` of the `handler` class.
2. `handleExercise()` will call `checkTypeOfExercise()` to validate the input and determine the type of exercise.
3. If the exercise type is `run`, `checkTypeofExercise()` will return `WorkoutConstant.RUN`
4. Upon receiving `WorkoutConstant.RUN`, `handlerExercise()` will call `Run.getRun()` to extract out
all the run details. These details are stored in `runDetails: String[]`.
5. `handlerExercise()` will then call `Run.addRun` to create a `newRun` object
   - If there is a date provided, it will add the date to the `newRun` object.
   - Else it will add `NA` to the date parameter. 
   - It will also add the `distance`, `time`, and `pace` to the `newRun` object.
   - Lastly, it will add the `newRun` object to the `runList` and `workoutList` in the `WorkoutList` class.
6. The newly created `newRun` object is parsed into `Output.printAddRun()` and printed out to the user

###### [Back to table of contents](#table-of-contents)

[Back to table of contents](#Developer-Guide)

### Loading Sample Data

The application does not currently support loading sample data. However, you can manually test different scenarios 
by entering commands and providing input through the command-line interface.

For example, to test logging a run exercise, you can enter the following command:

<code style="color: red;">
WORKOUT /e:run /d:10.3 /t:00:40:10 /date:15-03-2024
</code>


This command will create a new run exercise with a distance of 10.3 units, a duration of 40 minutes and 10 seconds,
and a date of March 15, 2024.

Similarly, you can test logging gym workouts, recording BMI and menstrual period information, tracking medical
appointment, viewing the exercise history, and accessing the latest run details by entering the appropriate commands.

###### [Back to table of contents](#table-of-contents)

## Frequently Asked Questions

1. **Q: How do I set up the development environment for the project?**  
   A: You can set up the development environment by first cloning the repository to your local system. Then, load the project into your chosen IDE (we recommend IntelliJ IDEA).

###### [Back to table of contents](#table-of-contents)
