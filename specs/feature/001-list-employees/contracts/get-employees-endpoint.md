# REST API Contract: GET /api/employees

## Endpoint

- Method: `GET`
- Path: `/api/employees`
- Content-Type: `application/json`

## Authorization

- Header: `Authorization: Bearer <token>`
- Allowed roles: `administrator`, `hr`
- 権限のないユーザーは `403 Forbidden` を返す。

## Request

- No request body.
- Header:
  - `Accept: application/json`
  - `Authorization: Bearer <token>`

## Successful Response

### 200 OK

```json
{
  "employees": [
    {
      "employeeID": "E12345",
      "fullName": "山田 太郎",
      "department": "営業部",
      "title": "課長",
      "employmentStatus": "在籍"
    }
  ]
}
```

- `employees` は `employeeID` の辞書順でソートされた配列。
- 従業員が 0 件の場合は `employees: []` を返し、必要に応じて `message` を含める。

## Empty State

### 200 OK

```json
{
  "employees": [],
  "message": "現在、登録された従業員は存在しません。"
}
```

## Error Responses

### 403 Forbidden

```json
{
  "error": "Forbidden",
  "message": "従業員一覧を取得する権限がありません。"
}
```

### 500 Internal Server Error

```json
{
  "error": "InternalServerError",
  "message": "従業員一覧の取得に失敗しました。再試行してください。"
}
```

## Domain rules

- `employeeID` は一意であり、ソートキーとして使用される。
- `employmentStatus` は `在籍`, `休職`, `退職` のいずれか。
- 権限のない要求は `403` で返す。
- `employees` 配列は常に返却し、存在しない場合は空の配列とメッセージを含める。
