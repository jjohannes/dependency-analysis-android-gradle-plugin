package com.autonomousapps.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = false)
data class GradleVariantIdentification(
  val capabilities: Set<String>,
  val attributes: Map<String, String>,
  val classifier: String? = null
): Serializable, Comparable<GradleVariantIdentification> {

  override fun equals(other: Any?): Boolean {
    if (other !is GradleVariantIdentification) {
      return false
    }
    return toSingleString() == other.toSingleString()
  }

  override fun hashCode(): Int {
    return toSingleString().hashCode()
  }

  override fun compareTo(other: GradleVariantIdentification): Int {
    return toSingleString().compareTo(other.toSingleString())
  }

  // The classifier (!) is ignored on purpose as it is only additional information on the declaration we want to preserve if possible
  private fun toSingleString() =
    capabilities.sorted().joinToString() + attributes.map { (k, v) -> "$k=$v" }.sorted().joinToString()
}
