package app.pandajr.clinical

import java.time.Instant

/**
 * Registro individual de una contracción.
 */
data class ContractionRecord(
    val id: String,
    val startTimestampMs: Long,
    val durationSeconds: Int,
    val intensity: String = "moderada" // suave, moderada, fuerte
)

/**
 * Resultado del análisis clínico de contracciones.
 */
data class ContractionAnalysis(
    val is511ActiveLabor: Boolean,
    val isPretermRisk: Boolean,
    val contractionsInLastHour: Int,
    val averageFrequencySeconds: Double?,
    val averageDurationSeconds: Double?,
    val recommendation: String
)

object ContractionEvaluator {

    /**
     * Evalúa las contracciones registradas dentro de una ventana de tiempo (últimos 60 minutos).
     *
     * Reglas clínicas:
     * - Aviso de parto pretérmino: si currentWeek < 37 y hay >= 4 contracciones en 1 hora.
     * - Regla 5-1-1: contracciones cada 5 minutos (o menos), de al menos 45-60 segundos de duración, mantenidas durante 1 hora.
     */
    fun evaluate(
        records: List<ContractionRecord>,
        currentWeek: Int?,
        nowMs: Long = System.currentTimeMillis()
    ): ContractionAnalysis {
        val oneHourAgoMs = nowMs - (60 * 60 * 1000)
        val recent = records
            .filter { it.startTimestampMs >= oneHourAgoMs }
            .sortedBy { it.startTimestampMs }

        val count = recent.size

        if (count == 0) {
            return ContractionAnalysis(
                is511ActiveLabor = false,
                isPretermRisk = false,
                contractionsInLastHour = 0,
                averageFrequencySeconds = null,
                averageDurationSeconds = null,
                recommendation = "Inicia el cronómetro cuando sientas endurecerse el vientre."
            )
        }

        val avgDuration = recent.map { it.durationSeconds }.average()

        // Calcular intervalos entre contracciones consecutivas (frecuencia)
        val intervals = mutableListOf<Long>()
        for (i in 1 until recent.size) {
            val diffSeconds = (recent[i].startTimestampMs - recent[i - 1].startTimestampMs) / 1000
            intervals.add(diffSeconds)
        }
        val avgFrequency = if (intervals.isNotEmpty()) intervals.average() else null

        // Evaluación de riesgo pretérmino (< 37 semanas)
        val isPreterm = (currentWeek != null && currentWeek < 37) && count >= 4

        // Evaluación de Regla 5-1-1 (Frecuencia <= 5 min [300s], Duración >= 45s, al menos 6-8 contracciones en la hora)
        val is511 = count >= 6 &&
                (avgFrequency != null && avgFrequency <= 360) && // ~5-6 minutos
                avgDuration >= 45.0

        val recommendation = when {
            isPreterm -> "Alerta de parto pretérmino: Tienes menos de 37 semanas y 4 o más contracciones en 1 hora. Llama a tu obstetra o acude a urgencias."
            is511 -> "Posible trabajo de parto activo (Regla 5-1-1 cumplida): Contracciones regulares cada 5 minutos o menos. Es momento de contactar a tu equipo médico o preparar la salida al hospital."
            count >= 4 -> "Contracciones frecuentes detectadas. Mantén el reposo, hidrátate y sigue cronometrando para evaluar si aumenta el patrón."
            else -> "Monitoreo en curso. Sigue registrando cada contracción."
        }

        return ContractionAnalysis(
            is511ActiveLabor = is511,
            isPretermRisk = isPreterm,
            contractionsInLastHour = count,
            averageFrequencySeconds = avgFrequency,
            averageDurationSeconds = avgDuration,
            recommendation = recommendation
        )
    }
}
