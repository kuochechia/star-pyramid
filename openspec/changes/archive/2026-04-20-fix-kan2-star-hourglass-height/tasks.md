## 1. 核心產生邏輯

- [x] 1.1 修改 `StarPyramidGenerator.generateHourglass(int height)`：將參數視為**總行數** \(n\)；令 `int h = n / 2 + 1`（整數除法），以既有方式組出對稱完整序列（`generateInverted(h)` 串接 `generate(h).subList(1, h)`），再回傳該序列之**前** \(n\) 行（`height <= 0` 仍拋 `InvalidHeightException`；若 `subList` 為可變視圖，改為 `List.copyOf(full.subList(0, n))` 或 `toList()` 等以符合不可變回傳慣例）。

## 2. 單元測試

- [x] 2.1 更新 `StarPyramidGeneratorTest` 沙漏相關案例：總高度 1 仍為 `["*"]`；總高度 2 為 `["***", " *"]`；總高度 3 為 `["***", " *", "***"]`；總高度 5 為 `["*****", " ***", "  *", " ***", "*****"]`；高度 0／負數仍拋 `InvalidHeightException`。
- [x] 2.2 確認正立／倒立／其他既有測試未改動且仍通過。

## 3. REST 與控制器測試

- [x] 3.1 更新 `StarPyramidControllerTest` 中沙漏成功案例：mock 與斷言改為新語意下之 `lines`（至少涵蓋總高度 2 與規格一致之列表）。
- [x] 3.2 確認非法高度與非數字路徑等測試仍符合預期（無需改動或僅微調）。

## 4. 文件與對外說明

- [x] 4.1 （選用）於 `README.md` 或 API 說明處加註：沙漏路徑參數為**輸出總行數**，與舊版「單臂高度」語意不同（**BREAKING**）；若專案政策不更新 README 則略過並於 PR 描述說明。（略過 README，由 PR／交付說明即可。）

## 5. 驗證

- [x] 5.1 執行 `mvn -q test`（或專案慣用之測試指令），全數通過。
- [x] 5.2 實作完成後執行 `openspec verify`（或專案約定之驗證流程），確認行為符合 `changes/fix-kan2-star-hourglass-height/specs/star-hourglass/spec.md`。（已執行 `openspec validate fix-kan2-star-hourglass-height` 與 `.\mvnw.cmd test`；CLI 無 `verify` 子命令。）
- [x] 5.3 （選用）歸檔變更時將 delta 合併至 `openspec/specs/star-hourglass/spec.md`（依 OpenSpec 歸檔流程）。
