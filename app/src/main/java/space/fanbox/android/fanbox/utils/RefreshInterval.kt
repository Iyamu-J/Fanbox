package space.fanbox.android.fanbox.utils

import android.os.SystemClock
import androidx.collection.ArrayMap

import java.util.concurrent.TimeUnit

class RefreshInterval<in KEY>(timeout: Int, timeUnit: TimeUnit) {
    private val timeStamps = ArrayMap<KEY, Long>()
    private val timeout = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(key: KEY): Boolean {
        val lastFetched = timeStamps[key]
        val now = now()
        if (lastFetched == null) {
            timeStamps[key] = now
            return true
        }
        if (now - lastFetched > timeout) {
            timeStamps[key] = now
            return true
        }
        return false
    }

    private fun now() = SystemClock.uptimeMillis()

    @Synchronized
    fun reset(key: KEY) {
        timeStamps.remove(key)
    }
}