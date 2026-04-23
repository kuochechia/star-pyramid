## Purpose

定義依**總行數**參數產生**星星沙漏**（上為倒立置中金字塔、下為正立置中金字塔）之行序列、中線不重複、輸入驗證與 API 回進行為，並與既有 `star-pyramid`／`inverted-star-pyramid` 單獨能力並存。

## Requirements

### Requirement: Generate centered star hourglass lines
系統 SHALL 允許使用者提供高度 \(n\)（正整數），該 \(n\) SHALL 表示**沙漏圖形之總行數**（輸出字串序列之長度），**不**再表示「上半倒立金字塔單獨高度」與「下半正立金字塔單獨高度」各為 \(n\) 時之組合。

令 \(h = \lfloor n/2 \rfloor + 1\)（整數除法）。先依 `inverted-star-pyramid` 規則產生倒立 \(h\) 行、依 `star-pyramid` 規則產生正立 \(h\) 行，並將「倒立之全部 \(h\) 行」串接「正立之第 2 行至第 \(h\) 行」（略過與倒立最後一行重複之最窄行），得到長度為 \(2h - 1\) 之**完整**沙漏序列。系統 SHALL 僅回傳該序列之**前 \(n\) 行**（當 \(n < 2h - 1\) 時為由上而下截斷；當 \(n = 2h - 1\) 時為完整序列）。

#### Scenario: Height 1 produces single shared waist line
- **WHEN** 輸入高度為 1
- **THEN** 產生的行序列 MUST 等於 `["*"]`

#### Scenario: Height 2 produces top two lines of the h=2 symmetric hourglass
- **WHEN** 輸入高度為 2
- **THEN** 產生的行序列 MUST 等於 `["***", " *"]`

#### Scenario: Height 3 produces full symmetric hourglass for arm height 2
- **WHEN** 輸入高度為 3
- **THEN** 產生的行序列 MUST 等於 `["***", " *", "***"]`

#### Scenario: Height 5 produces full symmetric hourglass for arm height 3
- **WHEN** 輸入高度為 5
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
系統 SHALL 提供 `GET` 端點，路徑為 `/api/star-pyramid/hourglass/{height}`（或與設計文件一致之等價路徑），路徑參數 `height` SHALL 表示**沙漏總行數**（與回傳 `lines` 長度一致）。回應內容 MUST 包裝於既有 `ApiResponse` 型別中，且承載資料 MUST 包含沙漏之各行字串列表（與產生器輸出順序一致）。

#### Scenario: Successful response returns lines for total height 2
- **WHEN** 客戶端以合法總高度 2 呼叫沙漏端點
- **THEN** 回應 MUST 為成功狀態且內含行列表 `["***", " *"]`
