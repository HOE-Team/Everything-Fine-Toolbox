package utils

data class SystemOverview(
    val osVersion: String,
    val architecture: String,
    val windowsUpdateStatus: String,
    val platform: String,
    val computerName: String,
    val wallpaperPath: String?
)
