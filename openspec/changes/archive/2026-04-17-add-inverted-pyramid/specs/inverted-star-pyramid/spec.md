## ADDED Requirements

### Requirement: Generate centered inverted star pyramid lines
系統 SHALL 允許使用者提供高度 \(n\)（正整數），並產生一個包含 \(n\) 行的字串序列，以呈現置中的**倒立**星星金字塔（第 1 行最寬、第 \(n\) 行最窄）。

每一行的格式 SHALL 符合以下規則（1-based，第 \(i\) 行）：

- 左側空白數 MUST 等於 \(i - 1\)
- 星號數 MUST 等於 \(2n - 2i + 1\)
- 行內容 MUST 等於 `" ".repeat(i - 1) + "*".repeat(2n - 2i + 1)`

#### Scenario: Height 1 produces single star
- **WHEN** 輸入高度為 1
- **THEN** 產生的行序列 MUST 等於 `["*"]`

#### Scenario: Height 2 produces two centered inverted lines
- **WHEN** 輸入高度為 2
- **THEN** 產生的行序列 MUST 等於 `["***", " *"]`

#### Scenario: Height 3 produces three centered inverted lines
- **WHEN** 輸入高度為 3
- **THEN** 產生的行序列 MUST 等於 `["*****", " ***", "  *"]`

#### Scenario: Height 5 produces five centered inverted lines
- **WHEN** 輸入高度為 5
- **THEN** 產生的行序列 MUST 等於 `["*********", " *******", "  *****", "   ***", "    *"]`

### Requirement: Validate height input for inverted pyramid
系統 MUST 驗證高度 \(n\) 為正整數；若輸入不合法，系統 MUST 以與現有星星金字塔一致的方式回報錯誤（例如拋出例外或回傳錯誤結果，依專案慣例擇一實作），且錯誤資訊 SHOULD 能指出輸入必須為正整數。

#### Scenario: Zero is rejected for inverted generation
- **WHEN** 以高度 0 請求產生倒立金字塔
- **THEN** 系統 MUST 回報輸入不合法的錯誤

#### Scenario: Negative number is rejected for inverted generation
- **WHEN** 以高度 -3 請求產生倒立金字塔
- **THEN** 系統 MUST 回報輸入不合法的錯誤

#### Scenario: Non-integer number is rejected for inverted generation
- **WHEN** 以高度 2.5 請求產生倒立金字塔
- **THEN** 系統 MUST 回報輸入不合法的錯誤

#### Scenario: Non-numeric value is rejected for inverted generation
- **WHEN** 輸入高度為非數字（例如 `"abc"`）
- **THEN** 系統 MUST 回報輸入不合法的錯誤

### Requirement: Print inverted pyramid using generated lines
系統 SHALL 提供一個輸出途徑可將倒立金字塔產生的行依序輸出（例如寫到標準輸出或透過注入的 writer），且輸出層 MUST 不改變核心排版規則（不新增/刪減空白與星號，僅負責逐行輸出與換行策略）。

#### Scenario: Printing outputs all inverted lines in order
- **WHEN** 使用列印功能輸出高度為 3 的倒立金字塔
- **THEN** 輸出內容 MUST 依序包含三行 `["*****", " ***", "  *"]`（行與行之間以換行分隔）
