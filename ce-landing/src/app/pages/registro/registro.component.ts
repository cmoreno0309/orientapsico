import { Component, inject, signal, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterLink, Router } from '@angular/router';
import { RegistroService } from '../../services/registro.service';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './registro.component.html'
})
export class RegistroComponent implements OnInit {
  private fb = inject(FormBuilder);
  private registroService = inject(RegistroService);
  private router = inject(Router);

  step = signal(1);
  loading = signal(false);
  errorMsg = signal('');
  registroExitoso = signal(false);
  noConvocatoria = signal(false);
  codigoCaso = signal('');
  convocatoriaNombre = signal('');
  convocatoriaId = signal<number | null>(null);

  // Señal computada que detecta si el usuario debe ser derivado
  // y qué motivo aplica (orden de prioridad: riesgo > alumno > trabajador)
  motivoDerivacion = signal<'riesgo' | 'alumno' | 'trabajador' | null>(null);

  form: FormGroup = this.fb.group({
    // Step 1
    nombreCompleto: ['', Validators.required],
    documentoIdentidad: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
    edad: [null, [Validators.required, Validators.min(12)]],
    genero: ['', Validators.required],
    estadoCivil: ['', Validators.required],
    paisResidencia: ['Perú', Validators.required],
    provincia: ['', Validators.required],
    distrito: ['', Validators.required],
    telefono: ['', [Validators.required, Validators.pattern(/^\d{9}$/)]],
    correo: ['', [Validators.required, Validators.email]],
    telefonoEmergencia: [''],
    nombreContactoEmergencia: [''],
    parentescoEmergencia: [''],
    // Step 2
    esAlumnoUarm: [false],
    esTrabajadorUarm: [false],
    tieneSituacionRiesgo: [false],
    enfermedadTratamiento: [''],
    atendidoAntesCe: [false],
    atencionPsicologicaPrevia: [false],
    detalleAtencionPsicologica: [''],
    atencionPsiquiatricaPrevia: [false],
    detalleAtencionPsiquiatrica: [''],
    diagnosticosPrevios: [''],
    // Step 3
    motivoConsulta: ['', Validators.required],
    tiempoConPreocupacion: [''],
    impactoEnVida: [''],
    puedeCostearServicio: [''],
    expectativaSesiones: [''],
    expectativaPostSesiones: [''],
    // Step 4
    modalidadPreferida: ['', Validators.required],
    espacioPrivado: [false],
    preferenciaGeneroAd: ['SIN_PREFERENCIA'],
    tieneDisponibilidadFija: [false],
    disponibilidadHoraria: ['', Validators.required],
    comoSeEntero: [''],
  });

 ngOnInit() {
    this.registroService.getConvocatoriaAbierta().subscribe({
      next: (conv) => {
        if (conv && conv.idConvocatoria) {
          this.convocatoriaNombre.set(conv.nombre);
          this.convocatoriaId.set(conv.idConvocatoria);
        } else {
          // 204 No Content → body null → no hay convocatoria abierta
          this.noConvocatoria.set(true);
        }

        // Detectar derivación al marcar cualquiera de los 3 filtros
          ['tieneSituacionRiesgo', 'esAlumnoUarm', 'esTrabajadorUarm'].forEach(name => {
          this.form.controls[name].valueChanges.subscribe(() => this.evaluarDerivacion());
          });


      },
      error: () => this.noConvocatoria.set(true)
    });
  }

 private evaluarDerivacion() {
  // Leer directamente de cada control (no de form.value que puede ser snapshot viejo)
  const riesgo     = this.form.controls['tieneSituacionRiesgo'].value;
  const alumno     = this.form.controls['esAlumnoUarm'].value;
  const trabajador = this.form.controls['esTrabajadorUarm'].value;

  if (riesgo)          this.motivoDerivacion.set('riesgo');
  else if (alumno)     this.motivoDerivacion.set('alumno');
  else if (trabajador) this.motivoDerivacion.set('trabajador');
  else                 this.motivoDerivacion.set(null);
}

  private limpiarCamposClinicos() {
    this.form.patchValue({
      enfermedadTratamiento: '',
      atendidoAntesCe: false,
      atencionPsicologicaPrevia: false,
      detalleAtencionPsicologica: '',
      atencionPsiquiatricaPrevia: false,
      detalleAtencionPsiquiatrica: '',
      diagnosticosPrevios: ''
    });
  }


    /**
 * Devuelve los nombres de los controles requeridos en el paso actual.
 * Centralizar esto evita duplicar la lista en canAdvance y nextStep.
 */
  private getRequiredControlsForStep(): string[] {
    const s = this.step();
    if (s === 1) return ['nombreCompleto', 'documentoIdentidad', 'edad','genero','estadoCivil','paisResidencia','provincia','distrito','telefono', 'correo'];
    if (s === 3) return ['motivoConsulta'];
    return [];
  }

  canAdvance(): boolean {
    return this.getRequiredControlsForStep()
      .every(name => this.form.controls[name].valid);
  }

  nextStep() {
    this.errorMsg.set('');

    // Si hay campos inválidos en este paso, marcarlos y mostrar mensaje
    if (!this.canAdvance()) {
      this.getRequiredControlsForStep().forEach(name => {
        this.form.controls[name].markAsTouched();
      });
      this.errorMsg.set('Por favor completa los campos obligatorios marcados en rojo.');
      return;
    }

    // Regla especial del paso 2
    if (this.step() === 2 && this.form.value.atendidoAntesCe) {
      this.errorMsg.set('El Centro de Escucha solo puede atender a cada persona una vez. Si necesitas apoyo, te orientaremos hacia otros servicios.');
      return;
    }

    this.step.update(s => Math.min(s + 1, 4));
  }

  prevStep() {
    this.errorMsg.set('');
    this.step.update(s => Math.max(s - 1, 1));
  }

  submitForm() {
    if (this.form.invalid) {
      this.errorMsg.set('Por favor completa todos los campos obligatorios.');
      return;
    }

    this.loading.set(true);
    this.errorMsg.set('');

    const data = this.form.value;

    // Primero verificar si ya fue atendido por documento
    this.registroService.verificarAtendido(data.documentoIdentidad).subscribe({
      next: (yaAtendido) => {
        if (yaAtendido) {
          this.loading.set(false);
          this.errorMsg.set('Este documento ya tiene un registro previo en el Centro de Escucha.');
          return;
        }
        this.crearRegistro(data);
      },
      error: () => this.crearRegistro(data) // Si falla verificación, intentar igual
    });
  }

  private crearRegistro(data: any) {
    this.registroService.registrarConsultante(data).subscribe({
      next: (consultante) => {
        const idConsultante = consultante.idConsultante;
        const idConvocatoria = this.convocatoriaId();

        if (!idConsultante || !idConvocatoria) {
          this.loading.set(false);
          this.errorMsg.set('Hubo un error al procesar tu registro. Intenta nuevamente.');
          return;
        }

        const year = new Date().getFullYear();
        const codigo = `CE-${year}-${String(idConsultante).padStart(3, '0')}`;

        this.registroService.crearCaso({
          idConsultante,
          idConvocatoria,
          codigo
        }).subscribe({
          next: () => {
            this.codigoCaso.set(codigo);
            this.registroExitoso.set(true);
            this.loading.set(false);
          },
          error: () => {
            this.loading.set(false);
            this.errorMsg.set('Se registraron tus datos pero hubo un error al crear el caso. Contacta al equipo del CE.');
          }
        });
      },
      error: () => {
        this.loading.set(false);
        this.errorMsg.set('Hubo un error al procesar tu registro. Intenta nuevamente.');
      }
    });
  }

  errorMessage(controlName: string): string | null {
    const control = this.form.controls[controlName];
    if (!control || !control.touched || !control.invalid) return null;
    //if (control.errors?.['required']) return 'Este campo es obligatorio.';
    if (control.errors?.['email']) return 'Ingresa un correo válido.';
    if (control.errors?.['min']) return `La edad no pueder ser menor a  ${control.errors['min'].min}.`;

    // Pattern es específico de cada campo
    if (control.errors?.['pattern']) {
      switch (controlName) {
        case 'documentoIdentidad': return 'El DNI debe tener 8 dígitos numéricos.';
        case 'telefono':           return 'El teléfono debe tener 9 dígitos y empezar con 9.';
        //case 'edad':               return 'La edad no pueder ser menor a 12 años.'; // Aunque esto también se controla con min, es bueno un mensaje específico
        // ...agrega más según los validators que vayas agregando
        default:                   return 'El formato no es válido.';
      }
    }
    return null;
  }
}
