package space.fanbox.android.fanbox.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Letter(
    @field:PrimaryKey
    val id: String,
    val subject: String,
    val body: String,
    val attachment: String,
    val closing: String,
    val recipient: String,
    val slug: String,
    val sender_name: String,
    val sender_id: String,
    val stamp_id: String,
    val category: String,
    val love: String,
    val hate: String,
    val views: String,
    val date_created: String,
    val tags: List<Tag>
)