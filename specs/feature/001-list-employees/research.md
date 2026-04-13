# Research

## Decision
この機能は既存のアーキテクチャ文書に基づき、Java 17 と Spring Boot、Gradle、H2 データベースを使った REST API で実装します。

## Rationale
- `docs/architecture/overview.md` では Spring Boot と Java 17 を想定した REST API バックエンドが明示されている。
- 本機能は従業員一覧取得という読み取り専用 API であり、ステートレスな Spring Boot の設計と整合する。
- H2 Database の永続ストレージは、要件にある「従業員一覧の取得に失敗した場合の再試行案内」やテスト容易性に適した軽量データストアである。

## Alternatives considered
- Node.js / Express
  - 代替として検討したが、既存のアーキテクチャ文書が Java/Spring Boot に最適化されているため、整合性を優先して選択を見送った。
- Python / FastAPI
  - Python も REST API に適しているが、プロジェクトのドキュメントと現在の技術コンテキストに一致しない。
- Quarkus / Micronaut
  - JVM ベースでありながら、標準化された Spring Boot ベースの設計との互換性を維持するために今回は採用しない。

## Decision details
- 主要技術: Java 17、Spring Boot、Gradle
- データストレージ: H2 Database（再起動後も永続化できる設定）
- API 仕様: `GET /api/employees` で従業員一覧を JSON 形式で返却
- エラー処理: 403 権限拒否、500 内部エラーの明確なメッセージ、空リスト時の親しみやすい空状態メッセージ
