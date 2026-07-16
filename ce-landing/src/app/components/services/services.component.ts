import { Component } from '@angular/core';

@Component({
  selector: 'app-services',
  standalone: true,
  template: `
    <section id="servicios" class="py-20 bg-gray-50">
      <div class="container mx-auto px-6 lg:px-20">
        <h2 class="text-3xl font-bold text-center text-gray-800 mb-4">¿Cómo funciona?</h2>
        <p class="text-center text-gray-500 mb-12 max-w-2xl mx-auto">
          El proceso es sencillo y está diseñado para que te sientas acompañado/a desde el primer momento.
        </p>

        <div class="grid md:grid-cols-2 lg:grid-cols-4 gap-6">
          @for (step of steps; track step.num) {
            <div class="relative">
              <div class="card bg-white shadow-sm hover:shadow-md transition-shadow h-full">
                <div class="card-body">
                  <div class="w-10 h-10 rounded-full bg-violet-600 text-white flex items-center justify-center font-bold text-lg mb-3">
                    {{ step.num }}
                  </div>
                  <h3 class="card-title text-base text-gray-800">{{ step.title }}</h3>
                  <p class="text-gray-600 text-sm">{{ step.desc }}</p>
                </div>
              </div>
            </div>
          }
        </div>

        <div class="mt-16">
          <h3 class="text-xl font-semibold text-center text-gray-800 mb-6">Modalidades de atención</h3>
          <div class="flex flex-col sm:flex-row gap-6 justify-center">

            <div class="card bg-white shadow-sm w-full sm:w-72">
              <div class="card-body items-center text-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-10 w-10 text-teal-600 mb-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 10l4.553-2.276A1 1 0 0121 8.618v6.764a1 1 0 01-1.447.894L15 14M5 18h8a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v8a2 2 0 002 2z" />
                </svg>
                <h4 class="font-semibold text-gray-800">Virtual</h4>
                <p class="text-sm text-gray-600">Por videollamada, desde la comodidad de tu hogar con total privacidad.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  `
})
export class ServicesComponent {
  steps = [
    { num: 1, title: 'Completa el formulario', desc: 'Llena el formulario de inscripción con tus datos y motivo de consulta.' },
    { num: 2, title: 'Evaluación del caso', desc: 'Nuestro equipo revisa tu solicitud para asignarte el mejor acompañamiento.' },
    { num: 3, title: 'Confirmación', desc: 'Te contactamos por WhatsApp para confirmar tu interés y coordinar horarios.' },
    { num: 4, title: 'Sesiones de acompañamiento', desc: 'Inicias tus sesiones con un acompañante directo asignado (hasta 10 sesiones).' },
  ];
}
