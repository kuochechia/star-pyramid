## Why

Jira **KAN-2**（`[T-002]`）指出：沙漏星星金字塔的**高度**不應以「正立金字塔高度 + 倒立金字塔高度」相加理解；**路徑參數所代表的高度**應與**輸入**一致。目前實作將參數 `n` 當成**單臂**（上半倒立／下半正立）的高度，導致輸出行數為 \(2n-1\)，與票證期望的「整體高度 = 輸入」不一致。需在規格與實作上對齊語意，並更新測試與文件。

## What Changes

- **BREAKING**：沙漏 API 之路徑參數 `height` 語意改為**沙漏圖形之總行數**（輸出 `lines` 之長度），不再表示「單臂」高度。
- 調整 `StarPyramidGenerator.generateHourglass`（及／或輔助方法）之組合規則，使 **`lines.size()`** 等於輸入 `height`（含奇數與偶數之定義，見設計與規格）。
- 更新單元測試、Controller 測試與（若適用）列印／展示路徑之預期輸出。
- 更新 `star-hourglass` 能力規格（delta），歸檔後合併至 `openspec/specs/star-hourglass/spec.md`。

## Capabilities

### New Capabilities

- （無）

### Modified Capabilities

- `star-hourglass`：沙漏輸入參數之語意（總行數）、輸出行數與情境範例（含高度 2、3、5 等）隨之修正。

## Impact

- **程式**：`StarPyramidGenerator`、`StarPyramidServiceImpl`、相關測試；若有依賴「輸入行數 = 2n-1」之呼叫端或文件需一併更新。
- **API**：路徑形狀不變（例如 `GET /api/star-pyramid/hourglass/{height}`），**語意**變更為 **BREAKING**。
- **相依**：無新增外部依賴。
