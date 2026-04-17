## Purpose

定義依高度產生**星星沙漏**（上為倒立置中金字塔、下為正立置中金字塔）之行序列、中線不重複、輸入驗證與 API 回進行為，並與既有 `star-pyramid`／`inverted-star-pyramid` 單獨能力並存。

## ADDED Requirements

### Requirement: Generate centered star hourglass lines
系統 SHALL 允許使用者提供高度 \(n\)（正整數），並產生一個字串序列，以呈現置中的星星沙漏：前 \(n\) 行為倒立金字塔（第 1 行最寬、第 \(n\) 行最窄），後續行為正立金字塔自第 2 行起至第 \(n\) 行（第 \(n+1\) 行起由窄到寬），且**不重複**倒立金字塔最後一行與正立金字塔第一行（兩者字串相同）。

令倒立 \(n\) 行依 `inverted-star-pyramid` 規則產生，正立 \(n\) 行依 `star-pyramid` 規則產生。沙漏行序列 SHALL 等於「倒立之全部 \(n\) 行」串接「正立之第 2 行至第 \(n\) 行」（共 \(n + (n-1) = 2n - 1\) 行）。

#### Scenario: Height 1 produces single shared waist line
- **WHEN** 輸入高度為 1
- **THEN** 產生的行序列 MUST 等於 `["*"]`

#### Scenario: Height 2 produces three lines hourglass
- **WHEN** 輸入高度為 2
- **THEN** 產生的行序列 MUST 等於 `["***", " *", "***"]`

#### Scenario: Height 3 produces five lines hourglass
- **WHEN** 輸入高度為 3
- **THEN** 產生的行序列 MUST 等於 `["*****", " ***", "  *", " ***", "*****"]`

### Requirement: Validate height input for hourglass
系統 MUST 驗證高度 \(n\) 為正整數；若輸入不合法，系統 MUST 以與現有星星金字塔一致的方式回報錯誤，且錯誤語意 SHOULD 與單獨金字塔／倒立端點相同（例如同一例外型別與訊息慣例）。

#### Scenario: Zero is rejected for hourglass
- **WHEN** 以高度 0 請求產生沙漏
- **THEN** 系統 MUST 回報輸入不合法的錯誤

#### Scenario: Negative number is rejected for hourglass
- **WHEN** 以高度 -1 請求產生沙漏
- **THEN** 系統 MUST 回報輸入不合法的錯誤

### Requirement: Expose hourglass lines via REST API
系統 SHALL 提供 `GET` 端點，路徑為 `/api/star-pyramid/hourglass/{height}`（或與設計文件一致之等價路徑），回應內容 MUST 包裝於既有 `ApiResponse` 型別中，且承載資料 MUST 包含沙漏之各行字串列表（與產生器輸出順序一致）。

#### Scenario: Successful response returns lines for height 2
- **WHEN** 客戶端以合法高度 2 呼叫沙漏端點
- **THEN** 回應 MUST 為成功狀態且內含行列表 `["***", " *", "***"]`
