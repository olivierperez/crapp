package fr.o80.featurereminder.usecase

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

/**
 * @author Olivier Perez
 */
@ExtendWith(value = [MockitoExtension::class, RxExtension::class])
open class BaseUnitTest
