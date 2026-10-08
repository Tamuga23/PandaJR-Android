package app.pandajr.clinical

/**
 * Sesión de conteo de patadas por método Cardiff (conteo hasta 10).
 */
data class KickSession(
    val id: String,
    val startTimeMs: Long,
    val endTimeMs: Long? = null,
    val count: Int = 0,
    val target: Int = 10,
    val notes: String = ""
) {
    val isComplete: Boolean get() = count >= target

    val durationMinutes: Long
        get() {
            val end = endTimeMs ?: System.currentTimeMillis()
            return ((end - startTimeMs) / 1000) / 60
        }

    val statusMessage: String
        get() {
            if (isComplete) {
                return "¡Completado! Registraste 10 movimientos en ${durationMinutes} minutos."
            }
            if (durationMinutes > 120) {
                return "Atención: Han pasado más de 2 horas sin alcanzar los 10 movimientos. Si notas que tu bebé se mueve menos de lo habitual, acude a revisión médica."
            }
            return "Contando: $count de $target movimientos (${durationMinutes} min transcurridos)."
        }
}
