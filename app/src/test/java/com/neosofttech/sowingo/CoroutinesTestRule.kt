package com.neosofttech.sowingo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class CoroutinesTestRule (
    private val testCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher(),
    private val testCoroutineScope: TestCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)
) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    fun runBlockingTest(bloc: suspend TestCoroutineScope.() -> Unit) {
        testCoroutineScope.runBlockingTest(bloc)
    }
}