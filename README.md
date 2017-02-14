# Vertex Coloring

[![Build Status](https://travis-ci.org/devshawn/vertex-coloringModule.svg?branch=master)](https://travis-ci.org/devshawn/vertex-coloringModule) [![Coverage Status](https://coveralls.io/repos/github/devshawn/vertex-coloringModule/badge.svg?branch=master)](https://coveralls.io/github/devshawn/vertex-coloringModule?branch=master)

Materials, notes, and code for a directed study on vertex coloringModule and related graph theory.

| List of work |
| --- |
| [Report write up on homework](work/work.pdf) |

## Coloring Library

I've started a vertex coloringModule library that implements heuristics to solve the vertex coloringModule problem (VCP).

It was developed using Java 8 and uses the [Gradle][gradle] build system to build the jar and run tests. It includes a gradle wrapper to build the library. To build from the command line, clone this repository and run:

```bash
./gradlew build
java -jar build/lib/com.devshawn.coloringModule.jar
```

[gradle]: https://gradle.org/
