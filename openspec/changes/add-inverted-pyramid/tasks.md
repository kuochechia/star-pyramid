## 1. 核心產生邏輯

- [x] 1.1 在 `StarPyramidGenerator`（或獨立元件）實作倒立金字塔行序列：第 \(i\) 行為 `" ".repeat(i - 1) + "*".repeat(2n - 2i + 1)\`，並對 \(n \le 0\) 拋出與現有一致的 `InvalidHeightException`
- [x] 1.2 新增單元測試，覆蓋規格中 \(n=1,2,3,5\) 之預期行序列，以及高度不合法（0、負數）之情境

## 2. 服務與 API

- [x] 2.1 新增或擴充 DTO（例如倒立結果之 record），於 Service 介面與 `StarPyramidServiceImpl` 中新增產生倒立金字塔之方法，僅透過 Generator 取得行列表
- [x] 2.2 在 `StarPyramidController` 新增端點（例如 `GET` 子路徑或查詢參數區分倒立），以 `ResponseEntity<ApiResponse<...>>` 回傳；路徑參數不合法時行為與現有 `/api/star-pyramid/{height}` 一致
- [x] 2.3 新增 Web 層測試：成功回應之 JSON 行內容與倒立範例一致，不合法高度回傳預期錯誤

## 3. 列印與驗證一致性

- [x] 3.1 擴充 `StarPyramidPrinter`（或對等類別）支援依序輸出倒立行（不修改每行字串），並補測試或調整現有測試

## 4. 規格落地

- [x] 4.1 實作完成後執行 `openspec verify`（或專案約定之驗證流程）確認行為符合 `changes/add-inverted-pyramid/specs/inverted-star-pyramid/spec.md`
- [x] 4.2 歸檔變更時將倒立能力規格合併至 `openspec/specs/inverted-star-pyramid/spec.md`（依 OpenSpec 歸檔流程）
