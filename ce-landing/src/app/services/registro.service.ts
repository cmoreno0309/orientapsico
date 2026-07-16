import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface ConvocatoriaResponse {
  idConvocatoria: number;
  nombre: string;
  descripcion?: string;
  fechaInicio?: string;
  fechaCierre?: string;
  estado?: string;
}

export interface ConsultanteRegistro {
  nombreCompleto: string;
  documentoIdentidad: string;
  edad: number;
  genero: string;
  estadoCivil: string;
  paisResidencia: string;
  provincia: string;
  distrito: string;
  telefono: string;
  telefonoEmergencia: string;
  nombreContactoEmergencia: string;
  parentescoEmergencia: string;
  correo: string;
  esAlumnoUarm: boolean;
  esTrabajadorUarm: boolean;
  tieneSituacionRiesgo: boolean;
  enfermedadTratamiento: string;
  atendidoAntesCe: boolean;
  atencionPsicologicaPrevia: boolean;
  detalleAtencionPsicologica: string;
  atencionPsiquiatricaPrevia: boolean;
  detalleAtencionPsiquiatrica: string;
  motivoConsulta: string;
  tiempoConPreocupacion: string;
  impactoEnVida: string;
  diagnosticosPrevios: string;
  puedeCostearServicio: string;
  expectativaSesiones: string;
  expectativaPostSesiones: string;
  modalidadPreferida: string;
  espacioPrivado: boolean;
  preferenciaGeneroAd: string;
  tieneDisponibilidadFija: boolean;
  disponibilidadHoraria: string;
  comoSeEntero: string;
}

export interface ConsultanteResponse extends ConsultanteRegistro {
  idConsultante: number;
  fechaRegistro?: string;
}

export interface CasoRegistro {
  idConsultante: number;
  idConvocatoria: number;
  codigo: string;
}

export interface CasoResponse {
  idCaso: number;
  codigo: string;
  estado: string;
  idConsultante: number;
  idConvocatoria: number;
}

@Injectable({ providedIn: 'root' })
export class RegistroService {
  private http = inject(HttpClient);
  private apiUrl = environment.apiUrl;

  /**
   * Devuelve la convocatoria abierta o null si el backend responde 204 No Content.
   */
  getConvocatoriaAbierta(): Observable<ConvocatoriaResponse | null> {
    return this.http.get<ConvocatoriaResponse | null>(
      `${this.apiUrl}/convocatorias/abierta`
    );
  }

  verificarAtendido(documento: string): Observable<boolean> {
    return this.http.get<boolean>(
      `${this.apiUrl}/consultantes/verificar/${documento}`
    );
  }

  /**
   * Registra un consultante. El backend devuelve el DTO completo incluyendo
   * el idConsultante generado, que luego se usa para crear el caso.
   */
  registrarConsultante(data: ConsultanteRegistro): Observable<ConsultanteResponse> {
    return this.http.post<ConsultanteResponse>(
      `${this.apiUrl}/consultantes`,
      data
    );
  }

  crearCaso(data: CasoRegistro): Observable<CasoResponse> {
    return this.http.post<CasoResponse>(`${this.apiUrl}/casos`, data);
  }
}
