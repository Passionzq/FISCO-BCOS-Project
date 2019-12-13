# README

## 1. Environment

- Ubuntu18.04LST
- Gradle6.0
- FISCO_BCOS2.0
- Java --version 11
- Mysql



## 2. How to run

按照[官网教程]( https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/docs/tutorial/sdk_application.html )进行配置后：

```bash

# 需要先启动nodes
$ cd ~/asset-app
$ ./gradlew build
$ cd dist
$ bash asset_run.sh deploy
```



