# star-pyramid

以 **Spring Boot 3**、**Java 21** 實作的示範專案，提供可測試的「置中星星圖形」產生能力：正立金字塔、倒立金字塔，以及兩者組合的**星星沙漏**（中線不重複）。

## 技術棧

- Java 21  
- Spring Boot 3.5.x（`spring-boot-starter-web`）  
- JUnit 5、Spring `MockMvc`、AssertJ、Mockito  

## 功能總覽

### 核心：`StarPyramidGenerator`

| 方法 | 說明 |
|------|------|
| `generate(int height)` | **正立置中**星星金字塔：第 1 行最窄、第 `height` 行最寬。 |
| `generateInverted(int height)` | **倒立置中**星星金字塔：第 1 行最寬、第 `height` 行最窄。 |
| `generateHourglass(int height)` | **星星沙漏**：前 `height` 行為倒立金字塔全部行，再接正立金字塔的第 2～`height` 行（略過與倒立最後一行重複的最窄行）。高度為 1 時僅 `["*"]`；合法高度下共 `2 * height - 1` 行。 |

高度必須為**正整數**；`height <= 0` 時拋出 `InvalidHeightException`（`400 Bad Request` 語意與其他端點一致）。

### 輸出：`StarPyramidPrinter`

| 方法 | 說明 |
|------|------|
| `print(List<String> lines, PrintWriter writer)` | 將已產生的各行寫入 writer。 |
| `printInverted(int height, PrintWriter writer)` | 依高度產生倒立金字塔並輸出。 |

（目前 REST 以 JSON 回傳行序列；列印類別可供程式內重用或測試。）

### REST API

基底路徑：`/api/star-pyramid`

所有成功回應皆包在共用型別 `ApiResponse<T>` 中：

```json
{
  "success": true,
  "message": "Success",
  "data": { ... }
}
```

資料 DTO 皆含 `lines`（`List<String>`），由產生器逐行字串組成。

#### 1. 正立置中金字塔

```http
GET /api/star-pyramid/{height}
```

**範例（高度 3）** — `data.lines`：

```json
["  *", " ***", "*****"]
```

#### 2. 倒立置中金字塔

```http
GET /api/star-pyramid/inverted/{height}
```

**範例（高度 3）** — `data.lines`：

```json
["*****", " ***", "  *"]
```

#### 3. 星星沙漏

```http
GET /api/star-pyramid/hourglass/{height}
```

**範例（高度 2）** — `data.lines`：

```json
["***", " *", "***"]
```

**範例（高度 3）** — `data.lines`：

```json
["*****", " ***", "  *", " ***", "*****"]
```

#### 錯誤回應

- 高度非正整數（含 0、負數）：`InvalidHeightException` → HTTP **400**，`success: false`，`message` 說明非法高度。  
- 路徑參數無法解析為整數（例如 `abc`、`2.5`）：HTTP **400**，`message` 提示該參數須為正整數。  
- 未預期錯誤：HTTP **500**，`message` 為泛用伺服器錯誤（細節見伺服器日誌）。

### cURL 範例

假設預設埠為 `8080`：

```bash
curl -s "http://localhost:8080/api/star-pyramid/3"
curl -s "http://localhost:8080/api/star-pyramid/inverted/3"
curl -s "http://localhost:8080/api/star-pyramid/hourglass/2"
curl -s "http://localhost:8080/api/star-pyramid/hourglass/0"
```

## 建置與測試

```bash
./mvnw.cmd clean test
```

## 啟動應用程式

```bash
./mvnw.cmd spring-boot:run
```

啟動後即可依上表路徑呼叫 API。

## OpenSpec

變更提案與規格置於 `openspec/changes/`（例如 `add-star-hourglass`），供規格驅動開發與審查參考。
