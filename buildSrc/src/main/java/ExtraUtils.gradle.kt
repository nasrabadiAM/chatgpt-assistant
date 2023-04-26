import org.gradle.api.plugins.ExtraPropertiesExtension

fun ExtraPropertiesExtension.getString(key: String): String {
    return get(key) as String
}

fun ExtraPropertiesExtension.getInt(key: String): Int {
    return get(key) as Int
}