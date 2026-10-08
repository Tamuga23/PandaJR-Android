import type { Metadata } from "next";
import Link from "next/link";

export const metadata: Metadata = {
  title: "Política de Privacidad | PandaJr",
  description: "Política de privacidad y tratamiento de datos de salud y embarazo de PandaJr.",
};

export default function PrivacidadPage() {
  return (
    <main className="min-h-screen bg-[#faf9f5] dark:bg-[#181520] text-[#1c1917] dark:text-[#f5f5f4] py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-3xl mx-auto space-y-8">
        <header className="border-b border-black/10 dark:border-white/10 pb-6">
          <Link
            href="/"
            className="inline-flex items-center text-sm font-semibold text-emerald-700 dark:text-emerald-400 hover:underline mb-4"
          >
            ← Volver a PandaJr
          </Link>
          <h1 className="text-3xl font-extrabold tracking-tight font-display sm:text-4xl text-[#1c1917] dark:text-[#f5f5f4]">
            Política de Privacidad de PandaJr
          </h1>
          <p className="mt-2 text-sm text-[#78716c] dark:text-[#a8a29e]">
            Última actualización: 8 de octubre de 2026 • Versión 1.0
          </p>
        </header>

        <section className="space-y-4 text-base leading-relaxed text-[#44403c] dark:text-[#d6d3d1]">
          <div className="p-4 rounded-xl bg-amber-50 dark:bg-amber-950/30 border border-amber-200 dark:border-amber-800/40 text-amber-900 dark:text-amber-200 text-sm">
            <strong>Aviso de salud y seguridad:</strong> PandaJr es una herramienta educativa y de organización para la pareja gestante. 
            <strong> No es un dispositivo médico y no diagnostica, trata, cura ni previene ninguna condición médica.</strong> Ante cualquier síntoma de alarma o duda sobre la salud de la madre o del bebé, se debe consultar inmediatamente al obstetra, ginecólogo o acudir al servicio de urgencias.
          </div>

          <h2 className="text-xl font-bold text-[#1c1917] dark:text-[#f5f5f4] pt-4">
            1. Responsable del Tratamiento
          </h2>
          <p>
            PandaJr («nosotros», «nuestro» o «la aplicación») es responsable del tratamiento de los datos personales y de bienestar recopilados a través de nuestra aplicación web y de nuestra aplicación móvil disponible en Google Play Store.
          </p>

          <h2 className="text-xl font-bold text-[#1c1917] dark:text-[#f5f5f4] pt-4">
            2. Información que Recopilamos
          </h2>
          <p>Para brindarte el servicio de acompañamiento gestacional en pareja, recopilamos las siguientes categorías de información:</p>
          <ul className="list-disc pl-6 space-y-2">
            <li>
              <strong>Datos de Cuenta e Identificación:</strong> Identificador único anónimo (UID de Firebase), nombre o apodo que elijas mostrar a tu pareja, y dirección de correo electrónico si decides autenticarte con Google o enlace por correo.
            </li>
            <li>
              <strong>Datos del Embarazo y Bienestar:</strong> Fecha probable de parto (FPP), semana gestacional actual, registros de síntomas, conteo de patadas fetales (método Cardiff), cronometraje de contracciones, citas médicas agendadas, lista de nombres favoritos y artículos de la maleta del hospital.
            </li>
            <li>
              <strong>Datos de Vinculación en Pareja:</strong> Código de invitación generado para enlazar dos teléfonos y sincronizar la información del embarazo en tiempo real.
            </li>
            <li>
              <strong>Consultas a PandaIA:</strong> Texto de las consultas que realizas al asistente conversacional. Estas consultas se procesan de forma transitoria para generar la respuesta educativa y sugerencias de acompañamiento.
            </li>
          </ul>

          <h2 className="text-xl font-bold text-[#1c1917] dark:text-[#f5f5f4] pt-4">
            3. Finalidad del Tratamiento de los Datos
          </h2>
          <p>Tus datos son utilizados exclusivamente para:</p>
          <ul className="list-disc pl-6 space-y-2">
            <li>Calcular y mostrar el avance del embarazo semana a semana con hitos y tareas para la mamá y el copiloto.</li>
            <li>Permitir que la pareja comparta un estado en tiempo real sincronizado entre sus dispositivos mediante Google Firebase Firestore.</li>
            <li>Detectar de forma determinista y preventiva señales de alerta médica reconocidas por guías clínicas (ACOG/CDC) para sugerir contacto inmediato con el equipo de salud o números de emergencia locales.</li>
            <li>Facilitar la organización de la maleta de parto, plan de parto y recordatorios de consultas obstétricas.</li>
          </ul>

          <h2 className="text-xl font-bold text-[#1c1917] dark:text-[#f5f5f4] pt-4">
            4. Tratamiento de Inteligencia Artificial (PandaIA)
          </h2>
          <p>
            PandaIA opera utilizando la API de Google Gemini en servidores seguros. Tus consultas son transmitidas con el contexto estrictamente necesario (semana gestacional y rol). <strong>No vendemos tus datos a terceros ni utilizamos tu información médica o personal para entrenar modelos públicos de inteligencia artificial.</strong>
          </p>
          <p>
            En cumplimiento con las políticas de Google Play, la aplicación ofrece mecanismos dentro del chat para reportar cualquier respuesta generada por IA que consideres inexacta, inapropiada o confusa.
          </p>

          <h2 className="text-xl font-bold text-[#1c1917] dark:text-[#f5f5f4] pt-4">
            5. Almacenamiento y Seguridad
          </h2>
          <p>
            Los datos se almacenan en la infraestructura en la nube de Google Firebase (Firestore y Firebase Authentication). Se aplican protocolos de cifrado en tránsito (HTTPS/TLS) y en reposo, así como reglas estrictas de seguridad (Firestore Security Rules) que limitan el acceso a los datos únicamente a los miembros vinculados a ese embarazo específico.
          </p>

          <h2 className="text-xl font-bold text-[#1c1917] dark:text-[#f5f5f4] pt-4">
            6. Compartición con Terceros
          </h2>
          <p>
            <strong>No vendemos, alquilamos ni comercializamos tus datos personales ni de salud con anunciantes, aseguradoras ni terceros con fines publicitarios.</strong> La información solo se procesa a través de nuestros proveedores de infraestructura técnica indispensables:
          </p>
          <ul className="list-disc pl-6 space-y-2">
            <li>Google Cloud / Firebase (Base de datos y autenticación).</li>
            <li>Vercel Inc. (Alojamiento seguro del backend y API).</li>
          </ul>

          <h2 className="text-xl font-bold text-[#1c1917] dark:text-[#f5f5f4] pt-4">
            7. Eliminación y Retención de Datos
          </h2>
          <p>
            Conservamos tus datos únicamente mientras mantengas activa tu cuenta o el seguimiento de tu embarazo. Puedes solicitar o ejecutar la eliminación total e irrevocable de tu cuenta y todos los registros asociados en cualquier momento directamente desde la aplicación o a través de nuestra página web:
          </p>
          <p>
            <Link
              href="/eliminar-cuenta"
              className="font-semibold text-rose-600 dark:text-rose-400 hover:underline"
            >
              → Solicitar o Gestionar la Eliminación de Cuenta y Datos
            </Link>
          </p>

          <h2 className="text-xl font-bold text-[#1c1917] dark:text-[#f5f5f4] pt-4">
            8. Público Objetivo y Menores de Edad
          </h2>
          <p>
            PandaJr está dirigida a personas adultas mayores de 18 años. No recopilamos intencionalmente información personal de menores de edad sin el consentimiento explícito de sus tutores.
          </p>

          <h2 className="text-xl font-bold text-[#1c1917] dark:text-[#f5f5f4] pt-4">
            9. Contacto
          </h2>
          <p>
            Si tienes dudas, solicitudes sobre privacidad o deseas ejercer tus derechos de acceso, rectificación o supresión de datos, puedes comunicarte con nuestro equipo a través de:
          </p>
          <p className="font-mono text-sm bg-black/5 dark:bg-white/5 p-3 rounded-lg">
            soporte.pandajr@gmail.com
          </p>
        </section>

        <footer className="pt-8 border-t border-black/10 dark:border-white/10 text-xs text-[#78716c] dark:text-[#a8a29e] text-center">
          © {new Date().getFullYear()} PandaJr. Todos los derechos reservados.
        </footer>
      </div>
    </main>
  );
}
