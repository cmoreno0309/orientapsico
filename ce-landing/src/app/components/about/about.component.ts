import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  standalone: true,
  template: `
    <section id="que-es" class="py-20 bg-white">
      <div class="container mx-auto px-6 lg:px-20">
        <h2 class="text-3xl font-bold text-center text-gray-800 mb-4">¿Qué es el Centro Acompañarte?</h2>
        <p class="text-center text-gray-500 mb-12 max-w-2xl mx-auto">
          Un servicio avalado por la Universidad Antonio Ruiz de Montoya comprometido con el bienestar emocional de la comunidad.
        </p>

        <div class="grid md:grid-cols-3 gap-8">
          <div class="card bg-violet-50 shadow-sm">
            <div class="card-body items-center text-center">
              <div class="w-14 h-14 rounded-full bg-violet-100 flex items-center justify-center mb-3">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7 text-violet-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
                </svg>
              </div>
              <h3 class="card-title text-gray-800">Acompañamiento gratuito</h3>
              <p class="text-gray-600 text-sm">Ofrecemos hasta 10 sesiones de acompañamiento psicológico sin costo para personas de la comunidad.</p>
            </div>
          </div>

          <div class="card bg-teal-50 shadow-sm">
            <div class="card-body items-center text-center">
              <div class="w-14 h-14 rounded-full bg-teal-100 flex items-center justify-center mb-3">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7 text-teal-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
                </svg>
              </div>
              <h3 class="card-title text-gray-800">Equipo supervisado</h3>
              <p class="text-gray-600 text-sm">Nuestros acompañantes son estudiantes de psicología formados y supervisados por docentes especializados.</p>
            </div>
          </div>

          <div class="card bg-green-50 shadow-sm">
            <div class="card-body items-center text-center">
              <div class="w-14 h-14 rounded-full bg-green-100 flex items-center justify-center mb-3">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7 text-green-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z" />
                </svg>
              </div>
              <h3 class="card-title text-gray-800">Espacio seguro</h3>
              <p class="text-gray-600 text-sm">Garantizamos confidencialidad y un ambiente de respeto donde puedes expresarte libremente.</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  `
})
export class AboutComponent {}
