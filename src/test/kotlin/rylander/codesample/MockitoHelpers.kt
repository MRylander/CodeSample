package rylander.codesample

import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito

/**
 * This file contains various workarounds for Mockito Kotlin issues.
 */

// see: https://github.com/mockito/mockito/issues/1255
fun <T : Any> safeEq(value: T): T = eq(value) ?: value

// see: https://medium.com/@elye.project/befriending-kotlin-and-mockito-1c2e7b0ef791
fun <T> safeAny(): T {
    Mockito.any<T>()
    @Suppress("UNCHECKED_CAST")
    return null as T
}
