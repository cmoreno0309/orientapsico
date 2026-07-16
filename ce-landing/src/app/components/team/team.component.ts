import { Component } from '@angular/core';

@Component({
  selector: 'app-team',
  standalone: true,
  template: `
    <section id="equipo" class="py-20 bg-white">
      <div class="container mx-auto px-6 lg:px-20">
        <h2 class="text-3xl font-bold text-center text-gray-800 mb-4">Nuestro equipo</h2>
        <p class="text-center text-gray-500 mb-12 max-w-2xl mx-auto">
          Un equipo comprometido con tu bienestar, formado por profesionales y estudiantes de psicología de la UARM.
        </p>

        <div class="grid md:grid-cols-3 gap-8">
          <div class="card bg-violet-50 shadow-sm">
            <div class="card-body items-center text-center">
              <div class="avatar placeholder mb-3">
                <div class="bg-violet-600 text-white rounded-full w-16">
                  <span class="text-2xl">👩‍🏫</span>
                </div>
              </div>
              <h3 class="card-title text-gray-800">Coordinadora</h3>
              <p class="text-gray-600 text-sm">Psicóloga responsable de la dirección y supervisión general del Centro Acompañarte.</p>
            </div>
          </div>

          <div class="card bg-teal-50 shadow-sm">
            <div class="card-body items-center text-center">
              <div class="avatar placeholder mb-3">
                <div class="bg-teal-600 text-white rounded-full w-16">
                  <span class="text-2xl">👨‍🎓</span>
                </div>
              </div>
              <h3 class="card-title text-gray-800">Practicantes</h3>
              <p class="text-gray-600 text-sm">Estudiantes de últimos ciclos que gestionan la logística, evaluación de casos y seguimiento.</p>
            </div>
          </div>

          <div class="card bg-green-50 shadow-sm">
            <div class="card-body items-center text-center">
              <div class="avatar placeholder mb-3">
                <div class="bg-green-600 text-white rounded-full w-16">
                  <span class="text-2xl">🤝</span>
                </div>
              </div>
              <h3 class="card-title text-gray-800">Acompañantes Directos</h3>
              <p class="text-gray-600 text-sm">Estudiantes de psicología capacitados que brindan el acompañamiento directo, supervisados por docentes.</p>
            </div>
          </div>
        </div>

        <div class="mt-10 text-center">
          <div class="badge badge-lg bg-violet-100 text-violet-700 border-violet-200 p-4">
            Todos nuestros acompañantes son supervisados semanalmente por docentes especializados
          </div>
        </div>
      </div>
    </section>
  `
})
export class TeamComponent {}
