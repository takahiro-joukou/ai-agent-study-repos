# 全体アーキテクチャ概要

## 1. アーキテクチャ方針

本システムは、従業員管理機能を提供するバックエンド API として構築する。  
UI（HTML / JavaScript 等）は提供せず、REST API として機能を公開する。

- クライアント（Web / モバイル / 他システム）は本システムの REST API を利用する
- 本システムは状態を持たない API として動作する（ステートレス）

---

## 2. 技術スタック

### アプリケーションフレームワーク
- Spring Boot
- Java 17

### ビルドツール
- Gradle（Groovy DSL）

### データストア
- H2 Database
- アプリケーション再起動後もデータを保持できるよう、永続化設定を行う

### 提供インターフェース
- REST API（JSON 形式）
- UI（HTML / Thymeleaf 等）は実装対象外とする

---

## 3. 全体構成（論理構成）

```text
[ Client ]
    |
    |  HTTP / JSON (REST)
    v
[ Spring Boot Application ]
    |
    +-- Controller Layer
    |
    +-- Service Layer
    |
    +-- Repository Layer
    |
    v
[ In-Memory RDB (Persistent) ]

## 4. データモデル
docs/architecture/data-model.md を参照すること。

## 5. API 規約
docs/architecture/api/*.md を参照すること。