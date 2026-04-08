package com.example.bff

/**
 * @author Li-Ting Liao
 * @version 1.0
 * @since 04/2026
 */

data class UserProfile(val id: Int, val name: String, val email: String)
data class RecentActivity(val userId: Int, val lastAction: String, val timestamp: String)
data class Notification(val message: String, val unreadCount: Int)

data class MobileHomeScreenResponse(val profile: UserProfile, val activity: RecentActivity)
data class MobileProfileScreenResponse(val profile: UserProfile)
data class MobileActivityScreenResponse(val activity: RecentActivity)
data class DashboardResponse(
    val profile: UserProfile,
    val activity: RecentActivity,
    val notification: Notification
)


val mockUsers = mapOf(
    1 to UserProfile(1, "Li-Ting", "li@example.com"),
    2 to UserProfile(2, "James", "james@example.com"),
    3 to UserProfile(3, "Sara", "sara@example.com")
)

val mockActivities = mapOf(
    1 to RecentActivity(1, "Viewed flight status", "2026-04-05T10:00:00"),
    2 to RecentActivity(2, "Checked in online", "2026-04-07T09:30:00"),
    3 to RecentActivity(3, "Booked a flight", "2026-04-08T08:00:00")
)


val mockNotifications = mapOf(
    1 to Notification("Your flight QF401 departs tomorrow", 3),
    2 to Notification("Check-in is now open", 1),
    3 to Notification("Your booking is confirmed", 0)
)