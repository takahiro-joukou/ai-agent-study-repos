# Data Model

## Employee

- **employeeID**: string
  - 従業員の一意な識別子。
  - 例: `E12345`
  - バリデーション: 空文字禁止、辞書順ソートに使用される。
- **fullName**: string
  - 表示用の従業員氏名。
  - 例: `山田 太郎`
  - バリデーション: 空文字禁止。
- **department**: string
  - 所属部署名。
  - 例: `営業部`
  - バリデーション: 空文字禁止。
- **title**: string
  - 役職名。
  - 例: `課長`
  - バリデーション: 空文字禁止。
- **employmentStatus**: string
  - 在籍ステータス。
  - 例: `在籍`, `休職`, `退職`
  - バリデーション: 事前定義されたステータス値に一致すること。

## Employee List

- **employees**: Employee[]
  - 取得された従業員レコードの配列。
  - ソート: `employeeID` による辞書順（昇順）。
  - 空状態: 配列が空の場合でも 200 OK を返し、必要に応じて `message` を含める。
- **message**: string (optional)
  - 取得結果が空の場合に親しみやすい説明文を提供するために使用する。
  - 例: `現在、登録された従業員は存在しません。`

## Authorized Actor

- **employeeID**: string
  - アクターの一意な識別子。
  - 例: `E54321`
- **role**: string
  - アクセス権限を示す。許可される値は:
    - `administrator`
    - `hr`
  - これらの権限を持つアクターのみが従業員一覧を取得できる。

## Validation rules

- `employeeID`, `fullName`, `department`, `title`, `employmentStatus` はすべて必須。
- `employmentStatus` は許可された値セットに制限する。
- `employees` 配列は `employeeID` 昇順で返却する。
- 権限のないリクエストは `403 Forbidden` で応答する。

## Notes

- 本機能は読み取り専用であり、従業員レコードの作成・更新・削除は本仕様の対象外。
- 将来的な拡張として、ページングやフィルタリングを追加する場合は `GET /api/employees` へクエリパラメーターを導入する。
