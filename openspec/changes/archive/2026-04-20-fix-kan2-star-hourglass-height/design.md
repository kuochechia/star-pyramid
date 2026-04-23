## Context

Jira **KAN-2**（標題 `[T-002]`）與 **KAN-1** 描述一致：沙漏的「高度」應與**輸入之路徑參數**相同，而不是將「正立金字塔高度 + 倒立金字塔高度」相加（語意上先前把參數當成**單臂高度** \(n\)，使輸出總行數為 \(2n-1\)）。

現況：`StarPyramidGenerator.generateHourglass(int height)` 以 `height` 同時餵給 `generateInverted` 與 `generate`，再串接正立之第 2 行起，故輸出長度為 \(2 \cdot height - 1\)。

## Goals / Non-Goals

**Goals:**

- 將路徑參數語意改為**沙漏輸出之總行數** \(n\)，並滿足 **`lines.size() == n`**。
- 以**單一、可測**之組合規則產生行序列：先建「對稱沙漏」所需之**臂高** \(h\)，再於必要時**由上而下截斷**至 \(n\) 行。
- 維持與既有 `InvalidHeightException` 等驗證慣例一致；不變更正立／倒立**單獨**端點之行為。

**Non-Goals:**

- 不改變 URL 路徑結構（仍為 `/api/star-pyramid/hourglass/{height}`）。
- 不引入新外部依賴；不要求 Thymeleaf／CLI。

## Decisions

1. **參數語意：總行數 \(n\)**  
   與票證一致：`height` 表示整份沙漏圖形之行數，而非單臂列數。

2. **臂高 \(h\) 與完整沙漏**  
   令 \(h = \lfloor n/2 \rfloor + 1\)（Java 中即 `n / 2 + 1`，\(n \ge 1\) 之整數除法）。  
   先產生**對稱**沙漏（與既有實作相同）：`inv = generateInverted(h)`，`up = generate(h)`，**完整**序列 `full = inv` 串接 `up.subList(1, h)`，長度為 \(2h - 1\)。  
   *理由：* 對奇數 \(n\) 常有 \(n = 2h - 1\)，此時不需截斷；對偶數 \(n\) 則 \(2h - 1 > n\)，需截斷，且由上而下保留較寬之上半與頸部，與「高度不足時保留上方」之直覺一致。

3. **回傳**  
   `return full.subList(0, n)`（或等價不可變清單）。  
   *曾考慮* 為偶數 \(n\) 另寫非對稱公式：重複與規格對照成本高；**先建完整再截斷**可與規格中之範例字串直接對照。

4. **與舊行為對照（**BREAKING**）**  
   - 舊：`hourglass/2` → 3 行（單臂高 2）。  
   - 新：`hourglass/2` → 2 行 `["***", " *"]`；若需舊版 3 行圖形，應呼叫 `hourglass/3`。

5. **測試**  
   更新 `StarPyramidGeneratorTest` 與 `StarPyramidControllerTest` 之預期行列表與 mock；可選加一則註解或 README 片段說明語意變更。

## Risks / Trade-offs

- **[Risk]** 呼叫端依賴舊「單臂高度」語意 → **緩解**：於 proposal／release 註記 **BREAKING**；客戶端改以總行數呼叫，或將期望行數換算為新參數（例如舊 `n` 臂 → 新總行數 `2n-1`）。
- **[Trade-off]** 偶數 \(n\) 為截斷後圖形，最底端可能不完整 → **緩解**：規格已用情境鎖定 \(n=2\) 之字串；若產品要「偶數亦為完整對稱」需另案調整幾何定義。

## Migration Plan

1. 合併並實作本變更之程式與測試。  
2. 部署後：整合方將沙漏端點之 `height` 改為**預期行數**；若有自動化比對舊回應，改以新表格更新期望值。  
3. 若需還原：還原 `generateHourglass` 與測試至前一版（恢復單臂語意）。

## Open Questions

- 若產品後續要求「偶數總行數亦須底部對稱完整」，需重新定義幾何與截斷策略（目前採上截斷以滿足票證與 \(lines.size()=n\)）。
