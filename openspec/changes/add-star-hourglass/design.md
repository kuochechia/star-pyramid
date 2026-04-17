## Context

專案已有 `StarPyramidGenerator`（正立 `generate`、倒立 `generateInverted`）、`StarPyramidService`、`StarPyramidController`（`/api/star-pyramid/{height}`、`/inverted/{height}`）與 `ApiResponse` 包裝。本變更新增「沙漏」組合：上半為倒立金字塔、下半為正立金字塔，並與既有驗證與 DTO 風格一致。

## Goals / Non-Goals

**Goals:**

- 以可測試的規則產生沙漏行序列：高度 \(n\) 時，行數為 \(2n - 1\)（中間最窄行只出現一次，不重複）。
- 高度驗證與 `InvalidHeightException`（或專案現有慣例）與既有金字塔一致。
- 新增 REST 端點回傳 `ApiResponse` 包裝之 DTO（含 `List<String> lines`），路徑清楚表達「沙漏」語意。

**Non-Goals:**

- 不改變既有正立／倒立單獨端點之行為或規格。
- 不要求 CLI 或 Thymeleaf 頁面（若後續產品需要可另案擴充）。

## Decisions

1. **沙漏行組合（不重複中線）**  
   令 `inv = generateInverted(n)`、`up = generate(n)`。沙漏行序列 SHALL 等於 `inv` 的全部 \(n\) 行，接上 `up` 的第 2 行到第 \(n\) 行（略過 `up` 第 1 行，因其與 `inv` 最後一行字串相同）。  
   *理由：* 視覺上為單一沙漏頸部；\(n=1\) 時僅一行 `["*"]\)。

2. **實作位置**  
   在 `StarPyramidGenerator` 新增 `generateHourglass(int height)`（或等價名稱），內部呼叫既有 `generate`／`generateInverted` 再串接，避免重複排版公式。  
   *曾考慮* 手寫單一迴圈：較難讀且與既有規格重複；組合既有方法較易對照測試。

3. **API 路徑**  
   建議 `GET /api/star-pyramid/hourglass/{height}`，與現有資源前綴一致，且與 `{height}`、`inverted/{height}` 不衝突（`hourglass` 為靜態路徑段）。

4. **DTO**  
   新增 `StarHourglassResponse(List<String> lines)`（或專案命名慣例之 record），compact constructor 驗證 `lines` 非 null，與 `StarPyramidResponse` 一致。

5. **列印（選用）**  
   若需與 `StarPyramidPrinter` 對齊，可新增 `printHourglass(int height, PrintWriter writer)` 呼叫產生器後沿用既有 `print`；若目前僅 API 需求，可於任務中列為可選。

## Risks / Trade-offs

- **[Risk]** 路徑註冊順序導致 `{height}` 誤匹配 `hourglass` — **Mitigation**：在 `Controller` 將較具體的 `/hourglass/{height}` 對應方法與現有對 `{height}` 的對應關係依 Spring 規則擺放（必要時調整路徑順序或使用單一路徑前綴）；實作後以請求驗證。
- **[Trade-off]** 組合實作會建立中間列表 — 對預期 \(n\) 規模可忽略；若未來需極大 \(n\) 可再改為單次迭代。

## Migration Plan

- 無資料遷移；僅部署新版本即新增端點與程式行為。
- Rollback：還原部署即可移除端點；不影響既有 API。

## Open Questions

- （無）若產品需要「中線重複兩次」的變體，需另開變更與規格。
