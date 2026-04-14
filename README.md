# Demo

## Star Pyramid

此專案包含一個可重用、可測試的「星星金字塔」能力：

- 核心產生器：`com.attendance.demo.starpyramid.StarPyramidGenerator`
- 薄輸出層：`com.attendance.demo.starpyramid.StarPyramidPrinter`

### 在程式中使用

- 產生逐行字串：呼叫 `new StarPyramidGenerator().generateLines(height)`
- 輸出到終端：呼叫 `new StarPyramidPrinter(new StarPyramidGenerator()).print(height, System.out)`

### REST API

啟動後可透過 HTTP 取得星星金字塔：

```
GET /api/pyramid?height={n}
```

**成功回應（200 OK）**

```json
{
  "lines": ["  *", " ***", "*****"]
}
```

**錯誤回應（400 Bad Request）**

當 `height` 不合法（≤0、非整數、非數字）時：

```json
{
  "error": "高度必須為正整數"
}
```

範例：

```bash
curl "http://localhost:8080/api/pyramid?height=5"
curl "http://localhost:8080/api/pyramid?height=abc"
```

### 直接執行（CommandLineRunner）

執行 Spring Boot 時若提供第一個參數為高度（正整數），會印出星星金字塔：

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=5
```

不提供參數則不輸出；若參數不合法，會輸出用法並以非 0 結束碼結束。
