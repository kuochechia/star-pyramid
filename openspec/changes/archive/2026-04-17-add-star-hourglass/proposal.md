## Why

專案已支援正立與倒立置中星星金字塔；使用者需要一種**沙漏形**輸出，由上（倒立金字塔）與下（正立金字塔）組成，以單一能力描述並可透過 API 取得，便於展示與測試。

## What Changes

- 新增依高度 \(n\) 產生**星星沙漏**之行序列：上半為倒立金字塔（由寬到窄）、下半為正立金字塔（由窄到寬），兩段**共用同一個最窄寬度**（中間一行），避免重複最窄行。
- 新增與現有金字塔一致的高度驗證（正整數）與錯誤語意。
- 新增 REST 端點（或既有資源下之子路徑）回傳沙漏行資料（DTO），與既有 `ApiResponse` 風格一致。
- 核心生成器／服務層新增可測試的產生邏輯；可複用既有 `generate`／`generateInverted` 組合或等價實作，行為以規格鎖定。

## Capabilities

### New Capabilities

- `star-hourglass`: 定義星星沙漏（上倒立、下正立）之行序列、中線不重複、輸入驗證與列印／API 回傳行為。

### Modified Capabilities

- （無）既有 `star-pyramid` 與 `inverted-star-pyramid` 的單獨行為不變；本變更僅新增組合能力。

## Impact

- **程式**：`StarPyramidGenerator` 或相鄰元件、`StarPyramidService`／`Impl`、新 DTO、`StarPyramidController` 新路徑；可選測試與 `StarPyramidPrinter` 若需與列印路徑一致。
- **API**：新增 `/api/star-pyramid` 下之沙漏端點（實際路徑以設計與實作為準）。
- **相依**：無新增外部依賴；沿用 Spring Web、既有例外與回應包裝。
