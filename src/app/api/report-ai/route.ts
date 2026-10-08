import { NextRequest, NextResponse } from "next/server";

export async function POST(req: NextRequest) {
  try {
    const body = await req.json();
    const { message, reply, reason } = body ?? {};

    if (!reply && !message) {
      return NextResponse.json({ ok: false, error: "Reporte sin contenido" }, { status: 400 });
    }

    // Registro seguro de la incidencia para auditoría interna y moderación de seguridad de IA
    console.warn("[AI_SAFETY_REPORT]", {
      timestamp: new Date().toISOString(),
      reason: String(reason || "inappropriate_or_inaccurate").slice(0, 100),
      messageSnippet: String(message || "").slice(0, 200),
      replySnippet: String(reply || "").slice(0, 200),
      ip: req.headers.get("x-forwarded-for") || "unknown",
    });

    return NextResponse.json({
      ok: true,
      message: "Reporte recibido. Gracias por ayudarnos a mantener la seguridad de PandaJr.",
    });
  } catch (error) {
    console.error("Error al procesar reporte de IA:", error);
    return NextResponse.json({ ok: false, error: "Error interno" }, { status: 500 });
  }
}
