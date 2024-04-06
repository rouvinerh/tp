# User Guide

## Introduction

Hello! Thank you for choosing to use PulsePilot!

Our team appreciates that you are giving our health application a try, and we hope that it helps you track your recovery better and bounce back from whatever injury you are facing.

This document is the **User Guide** of PulsePilot, which will help you, an injured person to learn how to install and use our product.

Once again, we hope our application helps you, and thank you for choosing PulsePilot!

## Table of Contents

* [How to use this guide](#how-to-use-this-guide)
  * [Glossary Usage](#glossary-usage)
  * [Blocks](#blocks)
* [What is PulsePilot](#what-is-pulsepilot)
* [Origin of PulsePilot](#origin-of-pulsepilot)
* [Command Line Interface](#command-line-interface)
* [Installation of Java and PulsePilot](#installation-of-java-and-pulsepilot)
* [PulsePilot Commands](#pulsepilot-commands)
* [Command Summary](#command-summary)
* [Frequently Asked Questions](#frequently-asked-questions)
* [Glossary](#glossary)

## How to use this guide

Before we delve into the content of what is PulsePilot and how to use it, we want to let you know and get familiar with the styling used in this guide, so that this guide can help you better!

### Glossary Usage

This guide may have difficult to understand technical jargon or terms used. Fret not! We understand that seeing such terms can be daunting to someone not familiar with it.

As such, our team has created a **Glossary** for you to refer to when reading this guide! It serves as a mini-dictionary, for you to read a definition and quickly go back to wherever you were.

Technical terms are marked in **italicised blue** [*like this*]()! When you click on it, it brings you to the glossary. Each term in the glossary also contains a 'Go back' button, and clicking on that returns you to where you originally were. This is to create a seamless reading experience for you!

Give it a try with the term: [*Object-Oriented Programming*](#glossary).

The blue phrases known are also known as [*hyperlinks*](#glossary). 

Non-italicised hyperlinks [like this]() are used to bring you to different parts of the document. 

For example, **the one below to bring you back to the table of contents on top!**

###### [Back to table of contents](#table-of-contents)

### Blocks

There are 3 different kinds of **blocks**, denoted using different **icons** to bring things to your attention.

> 💡 This is an **information** block. It is used to highlight additional details that you might be interested in!

> ⚠️ This is a **warning** block. It is used to highlight information about certain errors that you might encounter.

> ❗This is an **important** block. It contains information that you **MUST READ**! Take note of these!

If you need more, add more.

###### [Back to table of contents](#table-of-contents)

## What is PulsePilot

Include Description of the application

###### [Back to table of contents](#table-of-contents)

## Origin of PulsePilot

Explain why we created the application, to include:

- Who we are (athletes)
- Lack of recovery stuff from our presentation

I think no need to explain the NGEMR and Synapxe things here.

###### [Back to table of contents](#table-of-contents)

## Command Line Interface

Explain what this is for both OS 

Images are included like this 

![img.png](img_2101/windows_cmd.png)

> ❗This is an **example image** to show how images are included, and will be removed in future iterations.

###### [Back to table of contents](#table-of-contents)

### Windows

###### [Back to table of contents](#table-of-contents)

### MacOS

###### [Back to table of contents](#table-of-contents)

## Installation of Java and PulsePilot

Explain that our application can run anywhere as long as Java is installed. Can show how to install from our GitHub.

###### [Back to table of contents](#table-of-contents)

## PulsePilot Commands

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

###### [Back to table of contents](#table-of-contents)

#### Add Run

###### [Back to table of contents](#table-of-contents)

#### Add Gym

###### [Back to table of contents](#table-of-contents)

### Health

###### [Back to table of contents](#table-of-contents)

#### Add Period

###### [Back to table of contents](#table-of-contents)

#### Add BMI

###### [Back to table of contents](#table-of-contents)

#### Add Appointment

###### [Back to table of contents](#table-of-contents)

#### Make Period Prediction

###### [Back to table of contents](#table-of-contents)

### View History

###### [Back to table of contents](#table-of-contents)

### View Latest

###### [Back to table of contents](#table-of-contents)

### Delete Item

###### [Back to table of contents](#table-of-contents)

### Storage of Data

###### [Back to table of contents](#table-of-contents)

## Command Summary

| Action       | Format, Examples                                                                                                                       |
|--------------|----------------------------------------------------------------------------------------------------------------------------------------|
| Print help   | `help`                                                                                                                                 |
| Add new run  | `workout /e:run /d:DISTANCE /t:TIME [/date:DATE]`<br> Example: `workout /e:run /d:5.24 /t:25:23 /date:19-03-2024`   </br>              |
| Add gym      | `workout /e:gym /n:NUMBER_OF_STATIONS [/date:DATE]`<br> Example: `workout /e:gym /n:4`</br>                                            |
| Track BMI    | `health /h:bmi /height:HEIGHT /weight:WEIGHT /date:DATE`<br> Example: `health /h:bmi /height:1.70 /weight:75.42 /date:19-03-2024`</br> |
| Track Period | `health /h:period /start:START_DATE /end:END_DATE`<br> Example: `health /h:period /start:09-03-2022 /end:16-03-2022` </br>             |
| View history | `history /item:TYPE`<br> Example: `history /item:run` </br>                                                                            |
| View latest  | `latest /item:TYPE`<br>  Example: `latest /item:bmi`  </br>                                                                            |
| Exit bot     | `exit`                                                                                                                                 |


###### [Back to table of contents](#table-of-contents)

## Frequently Asked Questions

###### [Back to table of contents](#table-of-contents)

## Glossary

| Term                           | Definition                                                                                                                                                            |
|--------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Object-Oriented Programming    | A programming paradigm aiming used to guide the the analysis and structure of solutions by programmers in a specific way. <br> [*Go Back*](#glossary-usage) </br>     |
| Hyperlinks                     | A reference to data that a user can follow by clicking on, which can point to an entire document or specific part of document. <br>[*Go Back*](#glossary-usage) </br> |



###### [Back to table of contents](#table-of-contents)
