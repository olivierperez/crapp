package fr.o80.featurereminder.usecase

import fr.o80.featurereminder.dagger.ReminderComponent
import fr.o80.sample.lib.core.LibConfiguration
import fr.o80.sample.lib.di.LibModule
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * @author Olivier Perez
 */
class DaggerMockExtension : BeforeEachCallback, AfterEachCallback {

    private val daggerMockRule = DaggerMock.rule<ReminderComponent>(LibModule(Mockito.mock(LibConfiguration::class.java))) {
//        addComponentDependency<LibComponent>(LibModule())
    }

    override fun beforeEach(context: ExtensionContext) {
        MockitoAnnotations.initMocks(context.testInstance)
        val setupComponent = daggerMockRule.javaClass.getDeclaredMethod("setupComponent", Object::class.java)
        setupComponent.setAccessible(true)
        setupComponent.invoke(daggerMockRule, context.testInstance)
    }

    override fun afterEach(context: ExtensionContext) {
        Mockito.validateMockitoUsage()
    }
}