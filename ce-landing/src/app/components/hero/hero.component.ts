import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-hero',
  standalone: true,
  imports: [RouterLink],
  template: `
    <section id="inicio" class="min-h-screen flex items-center bg-gradient-to-br from-violet-50 via-white to-teal-50 pt-16">
      <div class="container mx-auto px-6 lg:px-20 flex flex-col lg:flex-row items-center gap-12">
        <div class="lg:w-1/2 text-center lg:text-left">
          <h1 class="text-4xl lg:text-5xl font-bold text-gray-800 leading-tight">
            Un espacio seguro para
            <span class="text-violet-600"> ser escuchado</span>
          </h1>
          <p class="mt-6 text-lg text-gray-600 leading-relaxed">
            El Centro Acompañarte ofrece acompañamiento psicológico
            a personas de la comunidad.
          </p>
          <div class="mt-8 flex flex-col sm:flex-row gap-4 justify-center lg:justify-start">
            <a routerLink="/registro" class="btn bg-violet-600 hover:bg-violet-700 text-white border-none btn-lg">
              Solicitar acompañamiento
            </a>
            <a href="#que-es" class="btn btn-outline border-violet-600 text-violet-600 hover:bg-violet-50 btn-lg">
              Conocer más
            </a>
          </div>
        </div>
        <div class="lg:w-1/2 flex justify-center">
          <img src="assets/logo-ce.jpg" alt="Centro Acompañarte" class="w-80 lg:w-96 drop-shadow-lg" />
        </div>
      </div>
    </section>
  `
})
export class HeroComponent {}
