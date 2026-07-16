import { Component } from '@angular/core';

@Component({
  selector: 'app-faq',
  standalone: true,
  template: `
    <section id="faq" class="py-20 bg-gray-50">
      <div class="container mx-auto px-6 lg:px-20 max-w-3xl">
        <h2 class="text-3xl font-bold text-center text-gray-800 mb-4">Preguntas frecuentes</h2>
        <p class="text-center text-gray-500 mb-12">Todo lo que necesitas saber antes de inscribirte.</p>

        <div class="space-y-3">
          @for (faq of faqs; track faq.q) {
            <div class="collapse collapse-arrow bg-white shadow-sm">
              <input type="radio" name="faq-accordion" />
              <div class="collapse-title font-semibold text-gray-800">{{ faq.q }}</div>
              <div class="collapse-content text-gray-600 text-sm">
                <p>{{ faq.a }}</p>
              </div>
            </div>
          }
        </div>
      </div>
    </section>
  `
})
export class FaqComponent {
  faqs = [
    { q: '¿El servicio tiene algún costo?', a: 'si. El acompañamiento psicológico del Centro Acompañarte es a costo social.' },
    { q: '¿Quiénes pueden acceder al servicio?', a: 'Cualquier persona de la comunidad y que no presente situación de riesgo inminente.' },
    { q: '¿Cuántas sesiones puedo tener?', a: 'El proceso contempla hasta 10 sesiones de acompañamiento con un/a acompañante directo/a asignado/a.' },
    { q: '¿Las sesiones son presenciales o virtuales?', a: 'Ofrecemos ambas modalidades. Puedes indicar tu preferencia en el formulario de inscripción.' },
    { q: '¿Qué pasa si necesito atención más especializada?', a: 'Si durante la evaluación se determina que necesitas atención clínica especializada, te orientaremos hacia profesionales o instituciones que puedan ayudarte.' },
    { q: '¿Mis datos son confidenciales?', a: 'Sí. Toda la información que compartas es estrictamente confidencial y solo es accesible por el equipo del Centro Acompañarte.' },
    { q: '¿Cuánto tarda el proceso después de inscribirme?', a: 'Una vez completado el formulario, nuestro equipo evalúa tu caso y te contacta por WhatsApp para coordinar. Esto puede tomar entre 1 y 2 semanas.' },
  ];
}
