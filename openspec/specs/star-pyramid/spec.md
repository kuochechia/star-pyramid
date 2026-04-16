## Purpose

定義依使用者指定高度產生置中星星金字塔之行序列、輸入驗證與列印行為，作為文字圖形產生能力之基線規格。

## Requirements

### Requirement: Generate centered star pyramid lines
系統 SHALL 允許使用者提供高度 \(n\)（正整數），並產生一個包含 \(n\) 行的字串序列，以呈現置中的星星金字塔。

每一行的格式 SHALL 符合以下規則（1-based，第 \(i\) 行）：

- 左側空白數 MUST 等於 \(n - i\)
- 星號數 MUST 等於 \(2i - 1\)
- 行內容 MUST 等於 `" ".repeat(n - i) + "*".repeat(2i - 1)`

#### Scenario: Height 1 produces single star
- **WHEN** 輸入高度為 1
- **THEN** 產生的行序列 MUST 等於 `["*"]`

#### Scenario: Height 2 produces two centered lines
- **WHEN** 輸入高度為 2
- **THEN** 產生的行序列 MUST 等於 `[" *", "***"]`

#### Scenario: Height 3 produces three centered lines
- **WHEN** 輸入高度為 3
- **THEN** 產生的行序列 MUST 等於 `["  *", " ***", "*****"]`

#### Scenario: Height 5 produces five centered lines
- **WHEN** 輸入高度為 5
- **THEN** 產生的行序列 MUST 等於 `["    *", "   ***", "  *****", " *******", "*********"]`

### Requirement: Validate height input
系統 MUST 驗證高度 \(n\) 為正整數；若輸入不合法，系統 MUST 以一致方式回報錯誤（例如拋出例外或回傳錯誤結果，依專案慣例擇一實作），且錯誤資訊 SHOULD 能指出輸入必須為正整數。

#### Scenario: Zero is rejected
- **WHEN** 輸入高度為 0
- **THEN** 系統 MUST 回報輸入不合法的錯誤

#### Scenario: Negative number is rejected
- **WHEN** 輸入高度為 -3
- **THEN** 系統 MUST 回報輸入不合法的錯誤

#### Scenario: Non-integer number is rejected
- **WHEN** 輸入高度為 2.5
- **THEN** 系統 MUST 回報輸入不合法的錯誤

#### Scenario: Non-numeric value is rejected
- **WHEN** 輸入高度為非數字（例如 `"abc"`）
- **THEN** 系統 MUST 回報輸入不合法的錯誤

### Requirement: Print pyramid using generated lines
系統 SHALL 提供一個輸出途徑可將產生的行依序輸出（例如寫到標準輸出或透過注入的 writer），且輸出層 MUST 不改變核心排版規則（不新增/刪減空白與星號，僅負責逐行輸出與換行策略）。

#### Scenario: Printing outputs all lines in order
- **WHEN** 使用列印功能輸出高度為 3 的金字塔
- **THEN** 輸出內容 MUST 依序包含三行 `["  *", " ***", "*****"]`（行與行之間以換行分隔）
