# Quickstart

## 前提条件

- JDK 17 がインストールされていること
- Gradle Wrapper がリポジトリに含まれていること
- `specs/feature/001-list-employees/` フォルダは本機能の設計ドキュメント用

## 開発サイクル

1. リポジトリのルートで `./gradlew bootRun` を実行してアプリケーションを起動する。
2. テストを実行する場合は `./gradlew test` を使用する。
3. エンドポイントにアクセスするには `http://localhost:8080/api/employees` を利用する。

## 実行例

```bash
./gradlew bootRun
```

## API の確認

`GET /api/employees`

```bash
curl -H "Authorization: Bearer <token>" \
  http://localhost:8080/api/employees
```

## 想定される応答

- 従業員が存在する場合: 200 OK と従業員配列
- 従業員が存在しない場合: 200 OK と空配列、必要に応じた空状態メッセージ
- 権限がない場合: 403 Forbidden
- 内部エラーの場合: 500 Internal Server Error

## 実装場所

- Java アプリケーション実装: `src/main/java/...`
- REST コントローラ: `src/main/java/.../controller/`
- サービス層: `src/main/java/.../service/`
- リポジトリ層: `src/main/java/.../repository/`
- 単体テスト: `src/test/java/...`

## 備考

現在のリポジトリには実装ソースが含まれていないため、上記コマンドは実装後に有効となる。
