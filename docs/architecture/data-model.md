# Data Model

## Employee

- **employeeID**: string
  - 従業員の一意な識別子。
  - 例: `E12345`
- **fullName**: string
  - 従業員の表示名。
  - 例: `山田 太郎`
- **department**: string
  - 所属部署名。
  - 例: `営業部`
- **title**: string
  - 役職名。
  - 例: `課長`
- **employmentStatus**: string
  - 在籍ステータス。
  - `在籍`, `休職`, `退職`

## Employee List

- **employees**: Employee[]
  - 指定された条件に一致する従業員の配列。
  - 取得時は従業員番号の辞書順でソートする。

## Authorized Actor
- **employeeID**: string
  - 従業員の一意な識別子。
  - 例: `E12345`
- **role**: string
  - `administrator` または `hr` のいずれか。
  - これらの権限を持つアクターのみが一覧の取得を許可される。
