<h1 align="center">▶️ Expose Run/Debug 🔌</h1>
<p align="center">
    <a href="https://fr.wikipedia.org/wiki/Kotlin_(langage)"> 
        <img src="https://img.shields.io/badge/Kotlin-%204--2--1?style=for-the-badge&label=language&color=red">
    </a>
  <a href="https://www.jetbrains.com/"> 
        <img src="https://img.shields.io/badge/Jetbrain-%204--2--1?style=for-the-badge&label=language&color=purple">
    </a>
</p>

<p align="center">
        <img src="https://i.ibb.co/0RX3hQ4P/Capture-d-e-cran-2025-04-30-a-02-27-50.png">
</p>

## Overview
This is a [Jetbrain](https://www.jetbrains.com/fr-fr/) plugin that allow you to expose Run/Debug configuration to be executed from ``api call``


## How to use
Just activate the plugin ( by default the server will run on port ``5555``)

but you can change the port under ``Settings -> Expose Run/Debug Plugin Settings``

You have to select between ``/run`` or ``/debug`` and set a ``config`` name as parameter, example:

```

 http://localhost:5555/debug?config=MainKt

```
to launch the MainKt config on debug mode on the Jetbrain IDE where the plugin is running !
