import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: true,
  template: `
    <footer class="footer footer-center bg-gray-800 text-gray-300 p-10">
      <div>
        <p class="font-semibold text-white text-lg">Centro Acompañarte</p>
      </div>
      <div>
        <p class="text-xs text-gray-500">© {{ year }} Centro Acompañarte. Todos los derechos reservados.</p>
      </div>
    </footer>
  `
})
export class FooterComponent {
  year = new Date().getFullYear();
}
