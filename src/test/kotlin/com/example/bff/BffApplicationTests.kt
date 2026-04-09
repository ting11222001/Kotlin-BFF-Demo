package com.example.bff

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class BffApplicationTests {

	@Autowired
	lateinit var mockMvc: MockMvc

	// Test 1: the main BFF behaviour — one call returns two data sources combined
	@Test
	fun `home screen returns profile and activity for a known user with id = 1`() {
		mockMvc.get("/api/mobile/home/1")
			.andExpect {
				status { isOk() }
				jsonPath("$.profile.id") { value(1) }
				jsonPath("$.profile.name") { value("Li-Ting") }
				jsonPath("$.activity.userId") { value(1) }
				jsonPath("$.activity.lastAction") { value("Viewed flight status") }
			}
	}

	// Test 2: unknown user falls back to "Unknown" instead of crashing
	@Test
	fun `home screen returns fallback values for an unknown user`() {
		mockMvc.get("/api/mobile/home/999")
			.andExpect {
				status { isOk() }
				jsonPath("$.profile.name") { value("Unknown") }
				jsonPath("$.activity.lastAction") { value("Unknown") }
			}
	}

	@Test
	fun `dashboard returns profile, activity, and notification for a known user with id = 1`() {
		mockMvc.get("/api/mobile/dashboard/1")
			.andExpect {
				status { isOk() }
				jsonPath("$.profile.id") { value(1) }
				jsonPath("$.activity.lastAction") { value("Viewed flight status") }
				jsonPath("$.notification.unreadCount") { value(3) }
			}
	}
}
