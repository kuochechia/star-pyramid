## 1. 核心產生邏輯

- [x] 1.1 在 `StarPyramidGenerator` 新增 `generateHourglass(int height)`：呼叫 `generateInverted(height)` 與 `generate(height)`，串接為倒立全段加上正立第 2～`height` 行（`height == 1` 時僅倒立之一行）。
- [x] 1.2 新增單元測試，覆蓋高度 1、2、3 之期望行序列（與規格情境一致），以及高度 ≤0 拋出與既有相同之 `InvalidHeightException`。

## 2. DTO 與服務層

- [x] 2.1 新增 `StarHourglassResponse` record（`List<String> lines`），compact constructor 驗證 `lines` 非 null，風格對齊 `StarPyramidResponse`。
- [x] 2.2 在 `StarPyramidService` 宣告 `StarHourglassResponse generateHourglass(int height)`；於 `StarPyramidServiceImpl` 實作並委派 `generator.generateHourglass(height)`。

## 3. REST API

- [x] 3.1 在 `StarPyramidController` 新增 `GET /api/star-pyramid/hourglass/{height}`，回傳 `ResponseEntity<ApiResponse<StarHourglassResponse>>`，try/catch 與日誌風格對齊既有 `generate`／`generateInverted`。
- [x] 3.2 確認路徑與 `@GetMapping` 順序不使 `{height}` 誤吞 `hourglass`（必要時調整方法順序或路徑設計）。

## 4. 驗證與回歸

- [x] 4.1 新增控制器或服務層測試（依專案慣例），斷言成功回應含規格所列之 `lines`（至少高度 2）。
- [x] 4.2 執行既有測試套件，確認正立／倒立行為無迴歸。

## 5. （選用）列印

- [x] 5.1 若需與 `StarPyramidPrinter` 一致：新增 `printHourglass(int height, PrintWriter writer)` 並以既有 `print` 輸出各行；否則略過。（已略過：僅 API，與設計一致。）
