## 1. 共用基礎建設

- [x] 1.1 建立 `ApiResponse<T>` 通用回應包裝類別（含 `success`、`message`、`data` 欄位）
- [x] 1.2 建立 `GlobalExceptionHandler`（`@RestControllerAdvice`，處理 `InvalidHeightException` 及通用例外）

## 2. 自訂例外

- [x] 2.1 建立 `InvalidHeightException`（繼承 `RuntimeException`，攜帶不合法高度的描述訊息）

## 3. DTO

- [x] 3.1 建立 `StarPyramidResponse` record（含 `List<String> lines` 欄位，compact canonical constructor 驗證 lines 不為 null）

## 4. 核心產生器

- [x] 4.1 建立 `StarPyramidGenerator`（`@Component`，純函式：給定正整數高度 n，回傳 `List<String>` 逐行星星金字塔）
- [x] 4.2 於 `StarPyramidGenerator` 中實作高度驗證（n ≤ 0 時拋出 `InvalidHeightException`）

## 5. 輸出層

- [x] 5.1 建立 `StarPyramidPrinter`（`@Component`，薄輸出層：接受 `List<String>` 與 `PrintWriter`，逐行輸出，不修改內容）

## 6. 服務層

- [x] 6.1 建立 `StarPyramidService` 介面（定義 `generate(int height): StarPyramidResponse` 方法）
- [x] 6.2 實作 `StarPyramidServiceImpl`（`@Service`，建構子注入 `StarPyramidGenerator`，呼叫產生器並回傳 `StarPyramidResponse` DTO）

## 7. REST Controller

- [x] 7.1 建立 `StarPyramidController`（`@RestController`、`@RequestMapping("/api/star-pyramid")`，建構子注入 `StarPyramidService`）
- [x] 7.2 實作 `GET /{height}` 端點（`@GetMapping("/{height}")`，以 `@PathVariable` 接收 height，以 try-catch 包覆，回傳 `ResponseEntity<ApiResponse<StarPyramidResponse>>`）

## 8. 單元測試

- [x] 8.1 撰寫 `StarPyramidGeneratorTest`（驗證 height=1,2,3,5 逐行字串比對，以及 height=0、負數時拋出 `InvalidHeightException`）
- [x] 8.2 撰寫 `StarPyramidPrinterTest`（驗證逐行輸出順序正確且以換行分隔，不新增/刪減空白或星號）
- [x] 8.3 撰寫 `StarPyramidControllerTest`（`@WebMvcTest`：合法 height 回傳 HTTP 200 含正確行數，不合法 height 回傳 HTTP 4xx）
