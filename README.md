# OrientaPsi

Plataforma de orientación psicológica virtual. Este repositorio agrupa los distintos módulos del proyecto: landing, backend (Spring Boot) y un backend reactivo, enfocados en el flujo de registro y gestión de consultantes.

## Estructura del repositorio

```
orientapsico/
├── ce-landing/            # Landing page del proyecto
├── orientapsi-backend/    # API REST (Spring Boot) - lógica principal de negocio
└── orientapsi-reactive/   # Módulo backend reactivo (Spring WebFlux)
```

### `ce-landing`
Landing page de presentación de OrientaPsi. Desarrollada en Angular/TypeScript + HTML/CSS.

### `orientapsi-backend`
API REST en Spring Boot. Gestiona consultantes, casos, convocatorias y psicólogos, incluyendo autenticación y lógica de matching entre consultantes y profesionales.

### `orientapsi-reactive`
Módulo backend construido con programación reactiva (Spring WebFlux), enfocado en el registro y validación de datos del consultante.

## Stack tecnológico

- **Backend:** Java, Spring Boot, Spring WebFlux
- **Frontend:** Angular, TypeScript
- **Otros:** HTML, CSS

## Cómo ejecutar cada módulo

Cada carpeta es un proyecto independiente. Entra a la carpeta correspondiente y sigue las instrucciones específicas de cada uno (`pom.xml` para los módulos Java, `package.json` para Angular).

```bash
# Backend (Spring Boot)
cd orientapsi-backend
mvn spring-boot:run

# Backend reactivo
cd orientapsi-reactive
mvn spring-boot:run

# Landing (Angular)
cd ce-landing
npm install
npm start
```

## Estado del proyecto

Proyecto en desarrollo activo como parte del portafolio y como base para una futura plataforma de acompañamiento psicológico accesible.

## Autora

Cynthia Moreno — [github.com/cmoreno0309](https://github.com/cmoreno0309)
