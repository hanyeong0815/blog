# blog
***
[English Description](#English)<br/>
[한글 설명](#한글)<br/>
[日本語説明](#日本語)
***

# English
***
## 1. OVERVIEW
This project was initiated to confirm the competence of the producer.
The subject matter is very common, and the aim is to advance the technologies used in it and to evaluate whether the technologies used so far have been appropriate.
The primary goal is to identify and correct any mistakes.

## 2. Tech Stack
> ### Framework
> Spring Boot 3
> ### Language
> Java 21
> ### Database
> PostgreSQL\
> MongoDB\
> Redis
> ### IDE
> intelliJ
> ### Build System
> gradle - groovy
> ### DevOps
> docker

## 3. Install & Run
- This project was developed with the assumption that [Docker Desktop](https://www.docker.com/products/docker-desktop/) and [Git](https://git-scm.com/downloads) are installed.

### PORT
> BE: 8080, 8090, 8100<br/>
> RDB: 5432, 5442<br/>
> Redis: 6379<br/>
> MongoDB: 27017, 27027<br/>
> Mongo express: 27018, 27028

### Install
```shell
git clone [code link]
```

### Run
- Open the terminal and use the `cd` command to navigate to the project folder.<br/>
Then, enter the following code into the terminal.
```shell
docker-compose up -d
```
- If you want to stop and remove the process, please enter the following code.
```shell
docker-compose down -v
```
***

# 한글
***
## 1. 목적
본 프로젝트는 제작자의 역량을 확인하기 위해 시작한 프로젝트입니다.<br/>
주제는 가장 흔하면서 그 안에 사용되는 기술들을 고도화하고 지금까지 사용한 기술들이 적절했는지 고찰하고<br/>
잘못된 점이 있다면 고치는 것이 가장 큰 목표입니다.

## 2. 기술 스택
> ### Framework
> Spring Boot 3
> ### Language
> Java 21
> ### Database
> PostgreSQL\
> MongoDB\
> Redis
> ### IDE
> intelliJ
> ### Build System
> gradle - groovy
> ### DevOps
> docker

## 3. 설치 및 실행
- 본 프로젝트는 [docker desktop](https://www.docker.com/products/docker-desktop/)과 [git](https://git-scm.com/downloads)이 설치되어 있음을 전제하에 작성되었습니다.

### PORT
> BE: 8080, 8090, 8100<br/>
> RDB: 5432, 5442<br/>
> Redis: 6379<br/>
> MongoDB: 27017, 27027<br/>
> Mongo express: 27018, 27028

### 설치
```shell
git clone [코드 링크]
```

### 실행
- 터미널을 열고 `cd` 커멘드를 사용하여 프로젝트 폴더로 이동합니다.<br/>
이후 아래 코드를 터미널에 입력하여 주세요.
```shell
docker-compose up -d
```
- 만약 프로세스를 정지 및 삭제를 원한다면 아래 코드를 입력하여 주세요.
```shell
docker-compose down -v
```
***

# 日本語
***
## 1. 目的
本プロジェクトは、製作者の能力を確認するために始めたプロジェクトです。<br/>
テーマは最も一般的なものであり、その中で使用される技術を高度化し、これまで使用してきた技術が適切であったかを考察し<br/>
誤っている点があれば修正することが最大の目標です。

## 2. 技術スタック
> ### Framework
> Spring Boot 3
> ### Language
> Java 21
> ### Database
> PostgreSQL\
> MongoDB\
> Redis
> ### IDE
> intelliJ
> ### Build System
> gradle - groovy
> ### DevOps
> docker

## 3. ダウンロード＆実行
- 本プロジェクトは [docker desktop](https://www.docker.com/products/docker-desktop/)と [git](https://git-scm.com/downloads)が設置された事を前提に作成されました。

### PORT
> BE: 8080, 8090, 8100<br/>
> RDB: 5432, 5442<br/>
> Redis: 6379<br/>
> MongoDB: 27017, 27027<br/>
> Mongo express: 27018, 27028

### ダウンロード
```shell
git clone [コードのリンク]
```

### 実行
- ターミナルを開き、`cd` コマンドを使用してプロジェクトフォルダに移動します。<br/>
  その後、以下のコードをターミナルに入力してください。
```shell
docker-compose up -d
```
- もしプロセスを停止および削除したい場合は、以下のコードを入力してください。
```shell
docker-compose down -v
```
***