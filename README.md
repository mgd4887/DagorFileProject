# DagorFileProject

---
##About

This is a java program designed to read the block files of the [Dagor game engine](https://en.wikipedia.org/wiki/Gaijin_Entertainment#Dagor_Engine)

Compatibility has been tested with:

* War Thunder
* Enlisted*
* Cuisine Royal

*Game in development, only tested with the latest alpha version I could get hold of (april 2018).

and should work with any game that has the same format for their block files.

---

##How to use

###Creating dagor object from a blockFile
```java
DagorFile dagorfile = new DagorFileNative("<fileName>");
```

###Creating dagor object from a string
```java
DagorObject dagorObject = new DagorObjectNative("<DataString>");
```
