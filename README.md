<p align="center">
  <img src="assets/FLINK-LOGO.png"/>
</p>

## Prerequisite

- IDEA插件：
    - IDEA Mind Map(用来支持: *.mmd)
    - Drawio(用来支持: *.drawio)
    - Scala
    - SBT

## How to run flink job

### Run in local

```shell
sbt run -c
```

### Run in cluster

```shell
flink run -c main *.jar
```

### Run in IDE

确保依赖 `scope` 不是provide，直接执行main

## Dependency Specification

### Table API

`flink-table-api-scala`:Table/SQL API for writing table programs within the table ecosystem using the Scala programming
language.
`flink-table-api-scala-bridge`:Table/SQL API for writing table programs that interact with other Flink APIs using the
Scala programming language.
`flink-table-planner-blink`:This module bridges Table/SQL API and runtime. It contains all resources that are required
during pre-flight and runtime phase. The content of this module is work-in-progress. It will replace flink-table-planner
once it is stable. See FLINK-11439 and FLIP-32 for more details.


