package app.pandajr.clinical

/**
 * Modelo de señal de alarma clínica para PandaJr (ACOG + CDC "Hear Her").
 */
data class AlarmSign(
    val id: String,
    val title: String,
    val detail: String,
    val isUrgent: Boolean = true,
    val minWeek: Int? = null,
    val maxWeek: Int? = null
)

object ClinicalProtocols {

    val URGENT_SIGNS = listOf(
        AlarmSign(
            id = "sangrado-liquido",
            title = "Sangrado o salida de líquido por la vagina",
            detail = "Cualquier cantidad de sangrado, o líquido que sale por la vagina (posible rotura de bolsa/fuente).",
            isUrgent = true
        ),
        AlarmSign(
            id = "preeclampsia",
            title = "Dolor de cabeza fuerte, visión borrosa o hinchazón repentina",
            detail = "Dolor de cabeza que no cede, visión borrosa o con destellos, o hinchazón rápida de cara y manos. Señales de alarma de preeclampsia.",
            isUrgent = true
        ),
        AlarmSign(
            id = "dolor-abdominal",
            title = "Dolor fuerte en el vientre que no se quita",
            detail = "Especialmente si es intenso, continuo o se localiza bajo las costillas del lado derecho.",
            isUrgent = true
        ),
        AlarmSign(
            id = "movimientos",
            title = "El bebé se mueve menos o dejó de moverse",
            detail = "Desde la semana 28, menos de 10 movimientos en 2 horas requiere revisión inmediata. No esperes si notas un cambio marcado.",
            isUrgent = true,
            minWeek = 20
        ),
        AlarmSign(
            id = "fiebre",
            title = "Fiebre de 38 °C o más",
            detail = "Temperatura de 38 °C (100.4 °F) o superior, aunque no haya otros síntomas evidentes.",
            isUrgent = true
        ),
        AlarmSign(
            id = "pretermino",
            title = "Contracciones regulares antes de la semana 37",
            detail = "4 o más contracciones en 1 hora antes de la semana 37, o presión pélvica constante. Puede indicar parto pretérmino.",
            isUrgent = true,
            maxWeek = 36
        ),
        AlarmSign(
            id = "respiracion-pecho",
            title = "Dificultad para respirar o dolor en el pecho",
            detail = "Sensación de falta de aire, dolor torácico o taquicardia muy marcada.",
            isUrgent = true
        ),
        AlarmSign(
            id = "desmayo-convulsion",
            title = "Desmayo, mareo muy intenso o convulsiones",
            detail = "Pérdida de consciencia o mareo incapacitante. No manejes; acude acompañado.",
            isUrgent = true
        ),
        AlarmSign(
            id = "salud-mental",
            title = "Pensamientos de hacerte daño o hacerle daño al bebé",
            detail = "No es tu culpa y tiene tratamiento inmediato. Pide ayuda ahora: llama a emergencias o a un ser querido.",
            isUrgent = true
        ),
        AlarmSign(
            id = "trombo-pierna",
            title = "Hinchazón, enrojecimiento o dolor en una sola pierna",
            detail = "Especialmente en la pantorrilla y caliente al tacto. Puede alertar sobre un coágulo o trombosis.",
            isUrgent = true
        ),
        AlarmSign(
            id = "vomitos",
            title = "Vómitos que no permiten retener líquidos",
            detail = "Incapacidad de retener líquidos durante horas, con riesgo severo de deshidratación.",
            isUrgent = true
        )
    )

    val CALL_TODAY_SIGNS = listOf(
        AlarmSign(
            id = "flujo",
            title = "Cambios sospechosos en el flujo vaginal",
            detail = "Flujo acuoso, verdoso, amarillento con picazón o mal olor.",
            isUrgent = false
        ),
        AlarmSign(
            id = "ardor-orinar",
            title = "Ardor, dolor al orinar o necesidad constante",
            detail = "Posible infección de vías urinarias. Requiere evaluación médica para prevenir complicaciones.",
            isUrgent = false
        ),
        AlarmSign(
            id = "picazon-palmas-plantas",
            title = "Comezón intensa en palmas de manos y plantas de pies",
            detail = "Sin sarpullido visible, peor en la noche. Puede ser señal de colestasis gestacional.",
            isUrgent = false
        )
    )

    /**
     * Resuelve el número de emergencias principal según el país (ISO 3166-1 alpha-2).
     */
    fun getEmergencyNumberForCountry(countryCode: String?): String {
        return when (countryCode?.uppercase()) {
            "US", "CA", "MX" -> "911"
            "CO" -> "123"
            "AR" -> "107"
            "CL" -> "131"
            "PE" -> "106"
            "ES" -> "112"
            "EC" -> "911"
            "GT", "SV", "HN", "NI", "CR", "PA" -> "911"
            "VE" -> "911"
            "DO", "PR" -> "911"
            "UY" -> "911"
            "PY" -> "911"
            "BO" -> "911"
            else -> "911"
        }
    }
}
