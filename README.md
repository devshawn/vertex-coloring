# Vertex Coloring

[![Build Status](https://travis-ci.org/devshawn/vertex-coloring.svg?branch=master)](https://travis-ci.org/devshawn/vertex-coloring) [![Coverage Status](https://coveralls.io/repos/github/devshawn/vertex-coloring/badge.svg?branch=master)](https://coveralls.io/github/devshawn/vertex-coloring?branch=master)

Materials, notes, and code for a directed study on vertex coloring and related graph theory.

| List of work |
| --- |
| [Report write up on homework](work/work.pdf) |

## Coloring Library

I've started a vertex coloring library that implements heuristics to solve the vertex coloring problem (VCP).

It was developed using Java 8 and uses the [Gradle][gradle] build system to build the jar and run tests. It includes a gradle wrapper to build the library. To build from the command line, clone this repository and run:

```bash
./gradlew build
java -jar build/lib/com.devshawn.coloring.jar
```

[gradle]: https://gradle.org/
