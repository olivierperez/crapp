package fr.o80.sample.lib.ui

import kotlinx.serialization.KInput
import kotlinx.serialization.KOutput
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.SerialClassDescImpl
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * @author Olivier Perez
 */
@Serializer(forClass = Date::class)
class DateSerializer: KSerializer<Date> {
    override val serialClassDesc: kotlinx.serialization.KSerialClassDesc = SerialClassDescImpl("Date")

    private val df = SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS", Locale.getDefault())

    override fun save(output: KOutput, obj: Date) {
        output.writeStringValue(df.format(obj))
    }

    override fun load(input: KInput): Date {
        return df.parse(input.readStringValue())
    }

}