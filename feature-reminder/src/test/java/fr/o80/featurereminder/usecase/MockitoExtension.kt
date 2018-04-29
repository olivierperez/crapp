package fr.o80.featurereminder.usecase

import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * @author Olivier Perez
 */
class MockitoExtension : BeforeEachCallback, AfterEachCallback {

    override fun beforeEach(context: ExtensionContext) {
        val testInstance = context.testInstance.get()

        MockitoAnnotations.initMocks(testInstance)
    }

    override fun afterEach(context: ExtensionContext) {
        Mockito.validateMockitoUsage()
    }
}