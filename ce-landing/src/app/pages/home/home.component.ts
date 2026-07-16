import { Component } from '@angular/core';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { HeroComponent } from '../../components/hero/hero.component';
import { AboutComponent } from '../../components/about/about.component';
import { ServicesComponent } from '../../components/services/services.component';
import { TeamComponent } from '../../components/team/team.component';
import { FaqComponent } from '../../components/faq/faq.component';
import { ContactComponent } from '../../components/contact/contact.component';
import { FooterComponent } from '../../components/footer/footer.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NavbarComponent, HeroComponent, AboutComponent,
    ServicesComponent, TeamComponent, FaqComponent,
    ContactComponent, FooterComponent
  ],
  template: `
    <app-navbar />
    <app-hero />
    <app-about />
    <app-services />
    <app-team />
    <app-faq />
    <app-contact />
    <app-footer />
  `
})
export class HomeComponent {}
