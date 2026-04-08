package com.example.bff

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class BffApplication

fun main(args: Array<String>) {
	runApplication<BffApplication>(*args)
}


@RestController
class MobileScreenController {

	@GetMapping("/api/mobile/users")
	fun getAllUsers(): List<UserProfile> {
		return mockUsers.values.toList()
	}

	@GetMapping("/api/mobile/home/{userId}")
	fun getHomeScreen(@PathVariable userId: Int): MobileHomeScreenResponse {
		val profile = mockUsers[userId] ?: UserProfile(userId, "Unknown", "Unknown")
		val activity = mockActivities[userId] ?: RecentActivity(userId, "Unknown", "Unknown")
		return MobileHomeScreenResponse(profile, activity)
	}

	@GetMapping("/api/mobile/profile/{userId}")
	fun getProfileScreen(@PathVariable userId: Int): MobileProfileScreenResponse {
		val profile = mockUsers[userId] ?: UserProfile(userId, "Unknown", "Unknown")
		return MobileProfileScreenResponse(profile)
	}

	@GetMapping("/api/mobile/activity/{userId}")
	fun getActivityScreen(@PathVariable userId: Int): MobileActivityScreenResponse {
		val activity = mockActivities[userId] ?: RecentActivity(userId, "Unknown", "Unknown")
		return MobileActivityScreenResponse(activity)
	}

	@GetMapping("/api/mobile/dashboard/{userId}")
	fun getDashboard(@PathVariable userId: Int): DashboardResponse {
		val profile = mockUsers[userId] ?: UserProfile(userId, "Unknown", "unknown@example.com")
		val activity = mockActivities[userId] ?: RecentActivity(userId, "No recent activity", "")
		val notification = mockNotifications[userId] ?: Notification("No notifications", 0)
		return DashboardResponse(profile, activity, notification)
	}
}