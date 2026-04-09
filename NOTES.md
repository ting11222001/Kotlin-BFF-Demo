# Notes

Go to https://start.spring.io/ to create a Spring Boot project.

---

## Adding a Unit Test with MockMvc

### Step 1: Open the test file

Open `src/test/kotlin/com/example/bff/BffApplicationTests.kt`.

It will look like this by default:

```kotlin
@SpringBootTest
open class BffApplicationTests {

    @Test
    fun contextLoads() {
    }
}
```

### Step 2: Add the test cases

Replace the whole file with this:

```
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class BffApplicationTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `home screen returns profile and activity for a known user`() {
        mockMvc.get("/api/mobile/home/1")
            .andExpect {
                status { isOk() }
                jsonPath("$.profile.id") { value(1) }
                jsonPath("$.profile.name") { value("Li-Ting") }
                jsonPath("$.activity.userId") { value(1) }
                jsonPath("$.activity.lastAction") { value("Viewed flight status") }
            }
    }
    ...
}
```

Why these annotations are needed:
- `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)` — boots the app with a fake web layer so MockMvc has something to call
- `@AutoConfigureMockMvc` — tells Spring to create the MockMvc bean and make it available to inject
- Without `webEnvironment = MOCK`, Spring does not create a web layer, so MockMvc has no bean to inject and the test fails with `UnsatisfiedDependencyException`

### Step 3: Run the tests

```bash
./mvnw test
```

There should be `BUILD SUCCESS` and tests passing.

