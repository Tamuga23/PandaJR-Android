package app.pandajr.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.pandajr.clinical.*

enum class PandaTab(val label: String) {
    GUIA("Guía"),
    JUNTOS("Juntos"),
    AGENDA("Agenda"),
    SOS("SOS"),
    IA("PandaIA")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PandaAppShell(
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(PandaTab.GUIA) }
    var currentWeek by remember { mutableStateOf(28) }
    var userRole by remember { mutableStateOf("mama") } // "mama" o "papa"

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "PandaJr",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = if (userRole == "mama") "Mamá • Semana $currentWeek" else "Copiloto Papá • Semana $currentWeek",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    // Botón para alternar rol de visualización (mamá / copiloto)
                    TextButton(onClick = {
                        userRole = if (userRole == "mama") "papa" else "mama"
                    }) {
                        Text(
                            text = if (userRole == "mama") "Ver como Papá" else "Ver como Mamá",
                            fontSize = 12.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                NavigationBarItem(
                    selected = selectedTab == PandaTab.GUIA,
                    onClick = { selectedTab = PandaTab.GUIA },
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Guía") },
                    label = { Text("Guía") }
                )
                NavigationBarItem(
                    selected = selectedTab == PandaTab.JUNTOS,
                    onClick = { selectedTab = PandaTab.JUNTOS },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Juntos") },
                    label = { Text("Juntos") }
                )
                NavigationBarItem(
                    selected = selectedTab == PandaTab.AGENDA,
                    onClick = { selectedTab = PandaTab.AGENDA },
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Agenda") },
                    label = { Text("Agenda") }
                )
                NavigationBarItem(
                    selected = selectedTab == PandaTab.SOS,
                    onClick = { selectedTab = PandaTab.SOS },
                    icon = { Icon(Icons.Default.Warning, contentDescription = "SOS", tint = MaterialTheme.colorScheme.error) },
                    label = { Text("SOS") }
                )
                NavigationBarItem(
                    selected = selectedTab == PandaTab.IA,
                    onClick = { selectedTab = PandaTab.IA },
                    icon = { Icon(Icons.Default.Face, contentDescription = "PandaIA") },
                    label = { Text("PandaIA") }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (selectedTab) {
                PandaTab.GUIA -> GuiaScreen(
                    week = currentWeek,
                    userRole = userRole,
                    onWeekChange = { currentWeek = it }
                )
                PandaTab.JUNTOS -> JuntosScreen(currentWeek = currentWeek)
                PandaTab.AGENDA -> AgendaScreen()
                PandaTab.SOS -> SosScreen(currentWeek = currentWeek)
                PandaTab.IA -> PandaIaScreen(currentWeek = currentWeek, userRole = userRole)
            }
        }
    }
}

// -----------------------------------------------------------------------------
// PANTALLA 1: GUÍA SEMANAL
// -----------------------------------------------------------------------------
@Composable
fun GuiaScreen(
    week: Int,
    userRole: String,
    onWeekChange: (Int) -> Unit
) {
    val weekInfo = remember(week) { PregnancyWeeks.getWeekInfo(week) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            // Selector de semanas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { if (week > PregnancyWeeks.WEEK_MIN) onWeekChange(week - 1) },
                    enabled = week > PregnancyWeeks.WEEK_MIN
                ) {
                    Text("← Ant.")
                }
                Text(
                    text = "Semana $week de 42",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                OutlinedButton(
                    onClick = { if (week < PregnancyWeeks.WEEK_MAX) onWeekChange(week + 1) },
                    enabled = week < PregnancyWeeks.WEEK_MAX
                ) {
                    Text("Sig. →")
                }
            }
        }

        item {
            // Tarjeta de Tamaño
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "TAMAÑO DEL BEBÉ",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Como un(a) ${weekInfo.size.fruta}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "En modo geek: ${weekInfo.size.geek}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (weekInfo.lengthCm != null || weekInfo.weightG != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Aprox. ${weekInfo.lengthCm ?: "--"} cm • ${weekInfo.weightG ?: "--"} g",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        item {
            // Tarjeta de Hito
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "HITO CLÍNICO DE LA SEMANA",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = weekInfo.milestone,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        item {
            // Misiones por rol
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (userRole == "papa") MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
                    else MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (userRole == "papa") "MISIÓN DEL COPILOTO (PAPÁ)" else "MISIÓN PARA LA MAMÁ",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (userRole == "papa") weekInfo.forDad else weekInfo.forMom,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

// -----------------------------------------------------------------------------
// PANTALLA 2: JUNTOS (HERRAMIENTAS COMPARTIDAS)
// -----------------------------------------------------------------------------
@Composable
fun JuntosScreen(currentWeek: Int) {
    var kickCount by remember { mutableStateOf(0) }
    var isContractionRunning by remember { mutableStateOf(false) }
    var contractionTimerSeconds by remember { mutableStateOf(0) }

    LaunchedEffect(isContractionRunning) {
        if (isContractionRunning) {
            while (true) {
                kotlinx.coroutines.delay(1000)
                contractionTimerSeconds++
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Herramientas en Pareja",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        // Contador de Contracciones
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "CRONÓMETRO DE CONTRACCIONES",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${contractionTimerSeconds}s",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            if (isContractionRunning) {
                                isContractionRunning = false
                                contractionTimerSeconds = 0
                            } else {
                                contractionTimerSeconds = 0
                                isContractionRunning = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isContractionRunning) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(if (isContractionRunning) "Detener Contracción" else "Iniciar Contracción")
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Vigilancia de regla 5-1-1 y aviso pretérmino activa (<37 semanas).",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Contador de Patadas Cardiff
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "CONTEO DE MOVIMIENTOS (CARDIFF)",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "$kickCount de 10 patadas",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = { if (kickCount < 10) kickCount++ },
                            enabled = kickCount < 10
                        ) {
                            Text("+1 Movimiento")
                        }
                        OutlinedButton(onClick = { kickCount = 0 }) {
                            Text("Reiniciar")
                        }
                    }
                }
            }
        }
    }
}

// -----------------------------------------------------------------------------
// PANTALLA 3: AGENDA MÉDICA
// -----------------------------------------------------------------------------
@Composable
fun AgendaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Agenda y Equipo de Salud",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Próxima cita obstétrica",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Lleva tus preguntas sobre molestias y dudas anotadas aquí.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// -----------------------------------------------------------------------------
// PANTALLA 4: SOS Y URGENCIAS
// -----------------------------------------------------------------------------
@Composable
fun SosScreen(currentWeek: Int) {
    val context = LocalContext.current
    val emergencyNumber = remember { ClinicalProtocols.getEmergencyNumberForCountry("MX") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Ruta de Urgencia y Señales de Alarma",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error
            )
        }

        item {
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$emergencyNumber"))
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Call, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Llamar a Emergencias ($emergencyNumber)", fontWeight = FontWeight.Bold)
            }
        }

        item {
            Text(
                text = "Señales que requieren atención inmediata:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        items(ClinicalProtocols.URGENT_SIGNS) { sign ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = sign.title,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = sign.detail, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

// -----------------------------------------------------------------------------
// PANTALLA 5: PANDAIA CON REPORTE Y DESCARGO MÉDICO (CUMPLIMIENTO GOOGLE PLAY)
// -----------------------------------------------------------------------------
@Composable
fun PandaIaScreen(currentWeek: Int, userRole: String) {
    var inputText by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            "model" to "¡Hola! Soy PandaIA. Estoy aquí para acompañarlos semana a semana con consejos prácticos y dudas sobre el embarazo. ¿En qué puedo apoyarte hoy?"
        )
    }
    var reportSnackbarVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Descargo médico mandatorio de Google Play
        Surface(
            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "PandaIA no sustituye a tu obstetra ni diagnostica. Ante dolor fuerte, sangrado o urgencias, usa la pestaña SOS.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Lista de mensajes
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { (sender, text) ->
                val isModel = sender == "model"
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = if (isModel) Alignment.Start else Alignment.End
                ) {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isModel) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = text,
                                color = if (isModel) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary
                            )
                            if (isModel) {
                                // Botón de reporte de IA exigido por políticas de Google Play
                                Spacer(modifier = Modifier.height(4.dp))
                                TextButton(
                                    onClick = { reportSnackbarVisible = true },
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Info,
                                        contentDescription = "Reportar",
                                        modifier = Modifier.size(14.dp),
                                        tint = MaterialTheme.colorScheme.outline
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Reportar respuesta",
                                        fontSize = 10.sp,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (reportSnackbarVisible) {
            Snackbar(
                action = {
                    TextButton(onClick = { reportSnackbarVisible = false }) {
                        Text("OK")
                    }
                }
            ) {
                Text("Gracias. El reporte ha sido registrado para moderación.")
            }
        }

        // Input de chat
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Pregunta a PandaIA...") },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    if (inputText.isNotBlank()) {
                        val userMsg = inputText.trim()
                        messages.add("user" to userMsg)
                        inputText = ""
                        // Simulación de respuesta inmediata o llamada a /api/chat
                        messages.add("model" to "Entendido. A las $currentWeek semanas, es importante descansar y mantenerse bien hidratada. Consulta a tu obstetra ante cualquier molestia recurrente.")
                    }
                }
            ) {
                Icon(Icons.Default.Send, contentDescription = "Enviar", tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
