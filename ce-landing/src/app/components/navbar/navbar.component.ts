import { Component, signal } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
  template: `
    <div class="navbar bg-white/95 backdrop-blur-sm shadow-sm fixed top-0 z-50">
      <div class="navbar-start">
        <div class="dropdown">
          <div tabindex="0" role="button" class="btn btn-ghost lg:hidden" (click)="toggleMenu()">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h8m-8 6h16" />
            </svg>
          </div>
          @if (menuOpen()) {
            <ul tabindex="0" class="menu menu-sm dropdown-content bg-white rounded-box z-10 mt-3 w-52 p-2 shadow">
              <li><a href="#inicio" (click)="toggleMenu()">Inicio</a></li>
              <li><a href="#que-es" (click)="toggleMenu()">¿Qué es?</a></li>
              <li><a href="#servicios" (click)="toggleMenu()">Servicios</a></li>
              <li><a href="#equipo" (click)="toggleMenu()">Equipo</a></li>
              <li><a href="#faq" (click)="toggleMenu()">Preguntas frecuentes</a></li>
              <li><a href="#contacto" (click)="toggleMenu()">Contacto</a></li>
              <li><a routerLink="/registro" class="text-violet-600 font-semibold" (click)="toggleMenu()">Inscríbete</a></li>
            </ul>
          }
        </div>
        <a href="#inicio" class="flex items-center gap-2 pl-2">
          <img src="assets/logo-ce.jpg" alt="Centro Acompañarte" class="h-10" />
        </a>
      </div>
      <div class="navbar-center hidden lg:flex">
        <ul class="menu menu-horizontal px-1 text-sm font-medium text-gray-700">
          <li><a href="#inicio">Inicio</a></li>
          <li><a href="#que-es">¿Qué es?</a></li>
          <li><a href="#servicios">Servicios</a></li>
          <li><a href="#equipo">Equipo</a></li>
          <li><a href="#faq">Preguntas frecuentes</a></li>
          <li><a href="#contacto">Contacto</a></li>
        </ul>
      </div>
      <div class="navbar-end pr-2">
        <a routerLink="/registro" class="btn btn-sm bg-violet-600 hover:bg-violet-700 text-white border-none">
          Inscríbete
        </a>
      </div>
    </div>
  `
})
export class NavbarComponent {
  menuOpen = signal(false);
  toggleMenu() { this.menuOpen.update(v => !v); }
}
