## 1. 專案結構規劃

- [x] 1.1 建立 `src/main/java/com/attendance/demo/starpyramid/` package 目錄
- [x] 1.2 確認對應測試目錄 `src/test/java/com/attendance/demo/starpyramid/` 存在（若無則建立）

## 2. 核心邏輯（StarPyramidGenerator）

- [x] 2.1 建立 `StarPyramidGenerator` 類別（package: `com.attendance.demo.starpyramid`）
- [x] 2.2 實作 `public List<String> generateLines(int n)` 方法：  
  第 `i` 行（1-based）格式 = `" ".repeat(n - i) + "*".repeat(2 * i - 1)`，行尾不補右側空白
- [x] 2.3 實作高度輸入驗證：`n <= 0` 時拋出 `IllegalArgumentException`，錯誤訊息需明確指出「高度必須為正整數」

## 3. 輸出層（StarPyramidPrinter）

- [x] 3.1 建立 `StarPyramidPrinter` 類別，注入 `StarPyramidGenerator`（可透過建構子注入）
- [x] 3.2 實作 `public void print(int n, PrintStream out)` 方法：呼叫 generator 取得行序列後逐行輸出，輸出層不得修改行內容（不新增或刪減空白/星號）
- [x] 3.3 確認 `print` 不承擔任何排版/格式決策（pure pass-through）

## 4. 後端 REST API 開發

- [x] 4.1 建立 `StarPyramidController`（`@RestController`，package: `com.attendance.demo.starpyramid`）
- [x] 4.2 定義端點：`GET /api/pyramid`，以 Query Parameter 接收高度（`?height={n}`）
  - 回傳 HTTP 200：JSON 格式 `{ "lines": ["...", "..."] }`
- [x] 4.3 非數字或小數（例如 `"abc"`、`"2.5"`）等字串解析失敗時，回傳 HTTP 400 `{ "error": "height 必須為正整數" }`
- [x] 4.4 建立 `StarPyramidExceptionHandler`（`@RestControllerAdvice`，package: `com.attendance.demo.starpyramid`）：
  - `IllegalArgumentException` → 400 Bad Request，包含錯誤說明
  - `MethodArgumentTypeMismatchException` → 400 Bad Request（處理 non-integer 型別轉換失敗）

## 5. 資料庫異動

> 本功能為純運算邏輯，不涉及任何資料存取。

- [x] 5.1 確認無需新增/修改資料表、Migration Script 或 JPA Entity
- [x] 5.2 確認 `pom.xml` / `build.gradle` 無需引入任何新資料庫相關依賴

## 6. 單元測試 — StarPyramidGenerator

測試類別：`StarPyramidGeneratorTest`（JUnit5）

- [x] 6.1 `n=1` → 期望 `["*"]`（單行單星）
- [x] 6.2 `n=2` → 期望 `[" *", "***"]`
- [x] 6.3 `n=3` → 期望 `["  *", " ***", "*****"]`
- [x] 6.4 `n=5` → 期望 `["    *", "   ***", "  *****", " *******", "*********"]`（完整 5 行逐行比對）
- [x] 6.5 `n=0` → 必須拋出 `IllegalArgumentException`，並驗證 exception message 含關鍵字（例如 "正整數"）
- [x] 6.6 `n=-3` → 必須拋出 `IllegalArgumentException`

## 7. 單元測試 — StarPyramidPrinter

測試類別：`StarPyramidPrinterTest`（JUnit5）

- [x] 7.1 高度 3 時，輸出到 `ByteArrayOutputStream` 的內容必須依序包含三行 `"  *"`, `" ***"`, `"*****"`（以換行分隔）
- [x] 7.2 確認輸出行數等於輸入高度（無多餘空行）

## 8. 整合測試 — StarPyramidController

測試類別：`StarPyramidControllerTest`（使用 `@WebMvcTest` + MockMvc）

- [x] 8.1 `GET /api/pyramid?height=3` → HTTP 200，response body `lines` 陣列長度為 3，且各行符合規格
- [x] 8.2 `GET /api/pyramid?height=0` → HTTP 400，response body 含錯誤說明
- [x] 8.3 `GET /api/pyramid?height=-1` → HTTP 400，response body 含錯誤說明
- [x] 8.4 `GET /api/pyramid?height=abc` → HTTP 400（型別轉換失敗）
- [x] 8.5 `GET /api/pyramid?height=2.5` → HTTP 400（非整數）

## 9. 文件與收尾

- [x] 9.1 更新 `README.md`：說明如何呼叫 API（endpoint 範例、成功與錯誤回應格式）
- [x] 9.2 確認所有測試通過（`./mvnw test` 或 `./gradlew test`）
- [x] 9.3 確認無多餘 import / 未使用的類別
