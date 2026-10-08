package app.pandajr.clinical

/**
 * Datos clínicos y misiones de la semana gestacional para PandaJr.
 */
data class WeekSize(
    val fruta: String,
    val geek: String
)

data class WeekInfo(
    val week: Int,
    val size: WeekSize,
    val lengthCm: Double?,
    val weightG: Int?,
    val milestone: String,
    val forMom: String,
    val forDad: String,
    val note: String? = null
)

object PregnancyWeeks {
    const val WEEK_MIN = 1
    const val WEEK_MAX = 42

    fun clampWeek(week: Int): Int {
        return week.coerceIn(WEEK_MIN, WEEK_MAX)
    }

    fun trimesterOfWeek(week: Int): Int {
        val w = clampWeek(week)
        return when {
            w <= 13 -> 1
            w <= 27 -> 2
            else -> 3
        }
    }

    // Datos de muestra representativos por trimestre; extensible hasta 42
    fun getWeekInfo(weekNumber: Int): WeekInfo {
        val w = clampWeek(weekNumber)
        return when (w) {
            1 -> WeekInfo(
                week = 1,
                size = WeekSize("Aún no hay embrión", "Pantalla de carga"),
                lengthCm = null,
                weightG = null,
                milestone = "Preparación: el ciclo empieza con la última regla",
                forMom = "Comienza con el ácido fólico diario (400 mcg) y anota la fecha de tu última regla para calcular la fecha probable de parto.",
                forDad = "Compra el ácido fólico, configura recordatorios compartidos y mantengan un ambiente tranquilo en casa."
            )
            4 -> WeekInfo(
                week = 4,
                size = WeekSize("Semilla de amapola", "Punta de un bolígrafo"),
                lengthCm = 0.1,
                weightG = null,
                milestone = "Implantación: el embrión se instala en el útero",
                forMom = "Una prueba de embarazo casera ya puede ser positiva. Descansa y mantente bien hidratada.",
                forDad = "Apoya a tu pareja evitando alimentos de riesgo y coordinando la primera cita con el obstetra."
            )
            12 -> WeekInfo(
                week = 12,
                size = WeekSize("Ciruela", "Batería AA"),
                lengthCm = 5.4,
                weightG = 14,
                milestone = "Fin del primer trimestre: sus órganos vitales ya están formados",
                forMom = "Muchas mamás sienten que las náuseas comienzan a disminuir. Buen momento para programar la ecografía de translucencia nucal.",
                forDad = "Acompaña a la ecografía del primer trimestre. Anota las preguntas para el obstetra en la app."
            )
            20 -> WeekInfo(
                week = 20,
                size = WeekSize("Plátano / Banana", "Un control de consola"),
                lengthCm = 25.6,
                weightG = 300,
                milestone = "¡Mitad del camino! Ecografía anatómica y primeros movimientos claros",
                forMom = "Empiezas a sentir los movimientos con mayor claridad. Tu abdomen sigue creciendo suavemente.",
                forDad = "Coloca tu mano en su vientre para sentir los primeros movimientos. Ayuda con masajes en la espalda baja."
            )
            28 -> WeekInfo(
                week = 28,
                size = WeekSize("Berenjena grande", "Teclado mecánico compacto"),
                lengthCm = 37.6,
                weightG = 1000,
                milestone = "Inicio del tercer trimestre: comienza el conteo de patadas",
                forMom = "Desde esta semana, realiza el conteo Cardiff de patadas en los momentos activos del bebé.",
                forDad = "Supervisa el contador de patadas con ella en la app. Empiecen a armar la maleta del hospital."
            )
            37 -> WeekInfo(
                week = 37,
                size = WeekSize("Acelga o sandía pequeña", "Laptop compacta"),
                lengthCm = 48.6,
                weightG = 2860,
                milestone = "Embarazo a término temprano: listo para nacer en cualquier momento",
                forMom = "Descansa con las piernas elevadas. Atenta a las contracciones regulares (regla 5-1-1) o rotura de aguas.",
                forDad = "Coche con gasolina, maleta del hospital en la puerta y teléfonos del obstetra y urgencias a mano en la app."
            )
            40 -> WeekInfo(
                week = 40,
                size = WeekSize("Calabaza / Sandía", "Torre de PC"),
                lengthCm = 51.2,
                weightG = 3460,
                milestone = "Fecha probable de parto: el gran encuentro",
                forMom = "Tu cuerpo está preparado. Confía en tu instinto y mantén la calma entre contracciones.",
                forDad = "Tu rol de copiloto es crucial: cronometra las contracciones, ofrece agua y sé su voz de apoyo."
            )
            else -> WeekInfo(
                week = w,
                size = WeekSize("Bebé en crecimiento semana $w", "Nivel $w"),
                lengthCm = (w * 1.2),
                weightG = (w * 80),
                milestone = "Semana $w: desarrollo activo y continuo de tu bebé",
                forMom = "Monitorea tus síntomas y mantén una alimentación balanceada.",
                forDad = "Revisa las tareas de esta semana y apoya en la logística del hogar."
            )
        }
    }
}
