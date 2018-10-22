package net.corda.notary.jpa

data class JPANotaryConfiguration(
    val batchSize: Int = 128,
    val batchTimeoutMs: Long = 1L,
    val maxInputStates: Int = 2000,
    val maxDBTransactionRetryCount: Int = 10,
    val backOffBaseMs: Long = 20L
)